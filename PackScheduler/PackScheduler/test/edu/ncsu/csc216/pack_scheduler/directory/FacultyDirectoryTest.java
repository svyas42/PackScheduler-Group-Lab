package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Tests  the Faculty Directory class
 * @author Sachi Vyas
 *
 */
public class FacultyDirectoryTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_COURSES = 3;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#FacultyDirectory()}.
	 */
	@Test
	void testFacultyDirectory() {
		//Test that the StudentDirectory is initialized to an empty list
		FacultyDirectory sd = new FacultyDirectory();
		assertFalse(sd.removeFaculty("sesmith5"));
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#newFacultyDirectory()}.
	 */
	@Test
	void testNewFacultyDirectory() {
		FacultyDirectory sd = new FacultyDirectory();
		
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		
		sd.newFacultyDirectory();
		assertEquals(0, sd.getFacultyDirectory().length);
		//fail();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#loadFacultyFromFile(java.lang.String)}.
	 */
	@Test
	void testLoadFacultyFromFile() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Test valid file
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		
		//Test invalid file
		FacultyDirectory sd1 = new FacultyDirectory();
		try {
			sd1.loadFacultyFromFile("File");
		} catch (IllegalArgumentException e) {
			assertEquals(0, sd1.getFacultyDirectory().length);
		}
		//fail();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#addFaculty(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	void testAddFaculty() {
		FacultyDirectory sd1 = new FacultyDirectory();
		sd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		sd1.addFaculty("zoe", "Wynns", "zWynns", EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String[][] facultyDirectory = sd1.getFacultyDirectory();
		assertEquals(2, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#removeFaculty(java.lang.String)}.
	 */
	@Test
	void testRemoveFaculty() {
		FacultyDirectory sd1 = new FacultyDirectory();
		//String[][] facultyDirectory = sd1.getFacultyDirectory();
		sd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		sd1.addFaculty("William", "Zoe", "zWilliams", "zwilliams@ncsu.edu", "pwzoe", "pwzoe", 2);
		assertEquals(2, sd1.getFacultyDirectory().length);
		sd1.removeFaculty("zWilliams");
		assertEquals(1, sd1.getFacultyDirectory().length);
	}

//	/**
//	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#getFacultyDirectory()}.
//	 */
//	@Test
//	void testGetFacultyDirectory() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#saveFacultyDirectory(java.lang.String)}.
	 */
	@Test
	void testSaveFacultyDirectory() {
		FacultyDirectory sd1 = new FacultyDirectory();
		sd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
//		String[][] facultyDirectory = sd1.getFacultyDirectory();
		assertEquals(1, sd1.getFacultyDirectory().length);
		sd1.saveFacultyDirectory("test-files/my_faculty_records");
		//checkFiles()
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#getFacultyById(java.lang.String)}.
	 */
	@Test
	void testGetFacultyById() {
		FacultyDirectory sd1 = new FacultyDirectory();
		sd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		sd1.addFaculty("William", "Zoe", "zWilliams", "zwilliams@ncsu.edu", "pwzoe", "pwzoe", 2);
		assertEquals(2, sd1.getFacultyDirectory().length);
		
		Faculty f = sd1.getFacultyById(ID);
		
		assertEquals(FIRST_NAME, f.getFirstName());
	}

}
