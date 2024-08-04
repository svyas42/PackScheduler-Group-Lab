package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Making a directory that can add and remove faculty records and keep the record up to date
 * @author Sachi Vyas
 */
public class FacultyDirectory {
	/** Linked list of the faculty directory */
	private LinkedList<Faculty> facultyDirectory;
	
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Constructor for the FacultyDirectory class
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}
	
	/**
	 * Created an empty linked list for the faculty directory
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * Loads a file into the system
	 * @param filename the filename from which we get the faculty records
	 * @throws IllegalArgumentException if unable to read file
	 */
	public void loadFacultyFromFile(String filename) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(filename);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + filename);
		}
	}
	
	/**
	 * Hashes a String according to the SHA-256 algorithm, and outputs the digest in base64 encoding.
	 * This allows the encoded digest to be safely copied, as it only uses [a-zA-Z0-9+/=].
	 * 
	 * @param toHash the String to hash 
	 * @return the encoded digest of the hash algorithm in base64
	 * @throws IllegalArgumentException if password cannot be hashed
	 */
	private static String hashString(String toHash) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(toHash.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Returns true if the faculty is added to the directory and false otherwise
	 * @param firstName the first name of the faculty member
	 * @param lastName the last name of the faculty member
	 * @param id the id of the faculty member
	 * @param email the email of the faculty member
	 * @param password the password of the faculty member
	 * @param repeatPassword the password that user types in again to confirm
	 * @param maxCourses the maximum number of courses a faculty member can teach
	 * @return boolean true if the record is added to the directory and false otherwise
	 * @throws IllegalArgumentException if the passwords are invalid
	 * @throws IllegalArgumentException if the passwords do not match
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, 
			int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}
		
		hashPW = hashString(password);
		repeatHashPW = hashString(repeatPassword);
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		Faculty f = null;
		if (maxCourses > 1 || maxCourses <= 3) {
			f = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		}
		
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User fac = facultyDirectory.get(i);
			if (fac.getId().equals(f.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(f);		
	}
	
	/**
	 * Removes faculty member from the list
	 * @param id the id of the faculty member to remove
	 * @return boolean true if the record is removed from the directory and false otherwise
	 */
	public boolean removeFaculty(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User faculty = facultyDirectory.get(i);
			if (faculty.getId().equals(id)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;		
	}
	
	/**
	 * Returns a 2d string array of the faculty directory with the first and last name and the id
	 * @return directory the 2d array with the first and last name and id of the faculty member
	 */
	public String[][] getFacultyDirectory() {
		String[][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User faculty = facultyDirectory.get(i);
			directory[i][0] = faculty.getFirstName();
			directory[i][1] = faculty.getLastName();
			directory[i][2] = faculty.getId();
		}
		return directory;
	}
	
	/**
	 * Saves the file directory to a given file
	 * @param filename the name of the file to write to
	 * @throws IllegalArgumentException if unable to write to the file
	 */
	public void saveFacultyDirectory(String filename) {
		try {
			FacultyRecordIO.writeFacultyRecords(filename, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + filename);
		}
	}
	
	/**
	 * Returns the faculty records based on id
	 * @param id the id of the faculty members
	 * @return faculty if the id is the same or else return null
	 */
	public Faculty getFacultyById(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty faculty = facultyDirectory.get(i);
			if (faculty.getId().equals(id)) {
				return faculty;
			}
		}
		return null;
	}
}
