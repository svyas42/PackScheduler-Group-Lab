package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.io.File;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads student records from .txt files and processes them line by line to read the contents of the file
 * @author Sachi Vyas
 *
 */
public class StudentRecordIO {
	/**
     * Reads course records from a file and generates a list of valid student records.  Any invalid
     * student are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read student records from
     * @return a list of valid student records
     * @throws FileNotFoundException if the file cannot be found or read
     * @throws IllegalArgumentException if the inputs are invalid
     * @throws NoSuchElementException if the input does not exist
     */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> studentDirectory = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				
				boolean dataDuplicates = false;
				for (int i = 0; i < studentDirectory.size(); i++) {
					User current = studentDirectory.get(i);
					
					if (student.getFirstName().equals(current.getFirstName()) && student.getLastName().equals(current.getLastName()) ) {
						dataDuplicates = true;
						break;
					}
				}
				if (!dataDuplicates) {
					studentDirectory.add(student);
				}
//				if (dataDuplicates) {
//					studentDirectory.remove(student);
//				}
			} catch (IllegalArgumentException e) {
				//invalid because we are not creating a course in this method hence there can be no illegal arguments
			} catch (NoSuchElementException e) {
				//invalid
			}
	}
		fileReader.close();
		return studentDirectory;
	}
	
	/**
	 * Private helper method to process each student record from each line
	 * @param line the line to read from the file
	 * @return s the new student record
	 * @throws IllegalArgumentException if invalid student record
	 */	
	private static Student processStudent(String line) {
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");
		String firstName;
		String lastName;
		String id;
		String email;
		String hashPW;
		int maxCredits;
		Student s = null;
		try {
			firstName = scan.next();
			lastName = scan.next();
			id = scan.next();
			email = scan.next();
			hashPW = scan.next();
			maxCredits = scan.nextInt();
			s = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		} catch (IllegalArgumentException e) {
			scan.close();
			throw new IllegalArgumentException();
		}
		scan.close();
		return s;
	}
	
	/**
     * Writes the given list of student records to 
     * @param fileName file to write student records to
     * @param studentDirectory list of student records to write
     * @throws IOException if cannot write to file
     */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}
		fileWriter.close();
	}

}