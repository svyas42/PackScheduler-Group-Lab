package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Base64;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Testing the StudentRecordIO file for valid and invalid inputs
 * @author Sachi Vyas
 *
 */
public class StudentRecordIOTest {
	/** Valid student record 1*/
	private String validStudent1 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** Valid student record 2 */
	private String validStudent2 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** Valid student record 3 */
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** Valid student record 4 */
	private String validStudent4 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/** Valid student record 5 */
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** Valid student record 6 */
	private String validStudent6 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/** Valid student record 7 */
	private String validStudent7 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** Valid student record 8 */
	private String validStudent8 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** Valid student record 9 */
	private String validStudent9 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** Valid student record 10 */
	private String validStudent10 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	
	/** Creating an array of valid student records */
	private String [] validStudents = {validStudent3, validStudent7, validStudent8, validStudent5, validStudent9, validStudent1,
	        validStudent10, validStudent4, validStudent2, validStudent6};
	
	/** Creating a private string for the hashed password */
	private String hashPW;
	
	/** Defining the hash algorithm that we want to use */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Set up method to help write the password into hashcode
	 * @throws NoSuchAlgorithmException if the password to create the hashcode cannot be found
	 */
	@BeforeEach
	public void setUp() throws NoSuchAlgorithmException {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * Testing valid and invalid student records
	 * @throws IOException if the file to read does not exist
	 * @throws FileNotFoundException if the file to read cannot be found
	 */
	@Test
	public void testReadStudentRecords() throws IOException {
		try {
			SortedList<Student> s = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
			assertEquals(10, s.size());
			for (int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], s.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail();
		}
		
		try {
			SortedList<Student> s = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");
			assertEquals(0, s.size());
		} catch (FileNotFoundException e) {
			fail("No such file or directory");
		}
	}
	/**
	 * Testing to see if an exception is thrown when we write to a file whose path is not available and we 
	 * do not have the permission to access it
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
			SortedList<Student> students = new SortedList<Student>();
			students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
			
			Exception exception = assertThrows(IOException.class, 
					() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
			assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
	}
	
	/**
	 * Testing to see if an exception is thrown when we write to a file whose path is available and permission
	 * for access
	 */
	@Test
	public void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		students.add(new Student("Demetrius", "Austin", "daustin", "Curabitur.egestas.nunc@placeratorcilacus.co.uk", hashPW, 18));
		students.add(new Student("Cassandra", "Schwartz", "cschwartz", "semper@imperdietornare.co.uk", hashPW, 4));
//		Exception exception = assertThrows(IOException.class, 
//				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
//		assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
		
		try {
			StudentRecordIO.writeStudentRecords("test-files/student_record1.txt", students);
		} catch (IOException e) {
			fail();
		}
		checkFiles("test-files/expected_student_records.txt", "test-files/student_record1.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
