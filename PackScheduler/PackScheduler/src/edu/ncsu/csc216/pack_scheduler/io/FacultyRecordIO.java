package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Reads faculty records from txt files and processes them line by line
 * @author Max Farthing
 *
 */
public class FacultyRecordIO {
	
	/**
	 * Reads in a txt file of faculty members, processed line by line
	 * @param fileName directed filename
	 * @return linked list of faculty
	 * @throws FileNotFoundException if the file is not found
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyDirectory = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				Faculty faculty = processFaculty(fileReader.nextLine());
				
				boolean dataDuplicates = false;
				for (int i = 0; i < facultyDirectory.size(); i++) {
					User current = facultyDirectory.get(i);
					
					if (faculty.getFirstName().equals(current.getFirstName()) && faculty.getLastName().equals(current.getLastName()) ) {
						dataDuplicates = true;
						break;
					}
				}
				if (!dataDuplicates) {
					facultyDirectory.add(faculty);
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
		return facultyDirectory;
		
	}
	
	/**
	 * private method to help process line into faculty member
	 * @param line line of txt file
	 * @return Faculty member object
	 */
	public static Faculty processFaculty(String line) {
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");
		String firstName;
		String lastName;
		String id;
		String email;
		String hashPW;
		int maxCredits;
		Faculty f = null;
		try {
			firstName = scan.next();
			lastName = scan.next();
			id = scan.next();
			email = scan.next();
			hashPW = scan.next();
			maxCredits = scan.nextInt();
			 f = new Faculty(firstName, lastName, id, email, hashPW, maxCredits);
		} catch (IllegalArgumentException e) {
			scan.close();
			throw new IllegalArgumentException();
		}
		scan.close();
		return f;
	}
	
	/**
	 * Writes faculty records to txt files using file name and a linked list of faculty members
	 * @param fileName directed filename 
	 * @param facultyDirectory linkedlist of faculty directory
	 * @throws IOException if the file to write to is not found
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}
		fileWriter.close();
	}
}
