package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Test class for Faculty IO
 * @author Max Farthing
 *
 */
public class FacultyRecordIOTest {

	/** Valid faculty record 1*/
	private String validFaculty1 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,2";
	/** Valid faculty record 2 */
	private String validFaculty2 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** Valid faculty record 3 */
	private String validFaculty3 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** Valid faculty record 4 */
	private String validFaculty4 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** Valid faculty record 5 */
	private String validFaculty5 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** Valid faculty record 6 */
	private String validFaculty6 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** Valid faculty record 7 */
	private String validFaculty7 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** Valid faculty record 8 */
	private String validFaculty8 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,2";
	
	/** Creating an array of valid student records */
	private String [] validFaculty = {validFaculty1, validFaculty2, validFaculty3, validFaculty4, validFaculty5, validFaculty6,
			validFaculty7, validFaculty8};
	
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
	        
	        for (int i = 0; i < validFaculty.length; i++) {
	        	validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * Testing valid and invalid faculty records
	 * @throws IOException if the file to read does not exist
	 * @throws FileNotFoundException if the file to read cannot be found
	 */
	@Test
	public void testReadStudentRecords() throws IOException {
		try {
			LinkedList<Faculty> f = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			assertEquals(8, f.size());
//			for (int i = 0; i < validFaculty.length; i++) {
//				assertEquals(validFaculty[i], f.get(i).toString());
//			}
		} catch (FileNotFoundException e) {
			fail();
		}
		
		try {
			LinkedList<Faculty> f = FacultyRecordIO.readFacultyRecords("test-files/invalid_faculty_records.txt");
			assertEquals(0, f.size());
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
			LinkedList<Faculty> faculty = new LinkedList<Faculty>();
			faculty.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquemagna.net", hashPW, 2));
			
			Exception exception = assertThrows(IOException.class, 
					() -> FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_student_records.txt", faculty));
			assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
	}
	
	/**
	 * Testing to see if an exception is thrown when we write to a file whose path is available and permission
	 * for access
	 */
	@Test
	public void testWriteStudentRecords() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		faculty.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquemagna.net", hashPW, 2));
		faculty.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3));
		faculty.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));
//		Exception exception = assertThrows(IOException.class, 
//				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
//		assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
		
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/faculty_record1.txt", faculty);
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



