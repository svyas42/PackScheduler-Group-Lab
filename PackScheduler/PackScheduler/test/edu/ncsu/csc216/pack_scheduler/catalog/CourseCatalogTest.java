package edu.ncsu.csc216.pack_scheduler.catalog;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Testing the CourseCatalog class
 * @author Sachi Vyas
 */
public class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course enrollment cap */
	private static final int ENROLLMENT_CAP = 14;
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		//Test that the CourseCatalog is initialized to an empty list
		CourseCatalog cc = new CourseCatalog();
		assertFalse(cc.removeCourseFromCatalog("sesmith5", "002"));
		assertEquals(0, cc.getCourseCatalog().length);
	}
	
	/**
	 * Tests CourseCatalog.NewCourseCatalog().
	 */
	@Test
	public void testNewCourseCatalog() {
		
		CourseCatalog cc = new CourseCatalog();
		
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
		
		cc.newCourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
	}
	
	/**
	 * Tests CourseCatalog.loadCoursesFromFile().
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog cc = new CourseCatalog();
				
		//Test valid file
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
		
		//Test invalid file
		CourseCatalog cc1 = new CourseCatalog();
		try {
			cc1.loadCoursesFromFile("File");
		} catch (IllegalArgumentException e) {
			assertEquals(0, cc1.getCourseCatalog().length);
		}
	}
	
	/**
	 * Tests CourseCatalog.addCourseToCatalog().
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		//Test valid Course
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		String [][] catalog = cc.getCourseCatalog();
		assertEquals(1, catalog.length);
		assertEquals(NAME, catalog[0][0]);
		assertEquals(TITLE, catalog[0][2]);
		assertEquals(SECTION, catalog[0][1]);
		
		//Test invalid Course
		CourseCatalog cc1 = new CourseCatalog();
		String[][] catalog1 = cc1.getCourseCatalog();
		
		try {
			cc1.addCourseToCatalog("", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
			assertEquals(0, catalog1.length);	
		
			cc1.addCourseToCatalog(NAME, "", SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
			assertEquals(0, catalog1.length);
			
			cc1.addCourseToCatalog(NAME, TITLE, SECTION, 0, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
			assertEquals(0, catalog1.length);
			
			cc1.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 1300, 1300);
			assertEquals(0, catalog1.length);
			
			cc1.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, "", ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
			assertEquals(0, catalog1.length);
			
			cc1.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "", START_TIME, END_TIME);
			assertEquals(0, catalog1.length);
			
		} catch (IllegalArgumentException e) {
			//not needed, we just need to catch the exception
			//System.out.println("Error when adding course to catalog");
		}
	}
	
	/**
	 * Tests CourseCatalog.removeCourseFromCatalog().
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		assertFalse(cc.removeCourseFromCatalog("CSC116", "002"));
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
		assertTrue(cc.removeCourseFromCatalog("CSC116", "002"));
		assertEquals(12, cc.getCourseCatalog().length);
	}
	
	/**
	 * Test CourseCatalog.getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);	
	}
	
	/**
	 * Tests CourseCatalog.getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		//Getting a course that does not exist
		assertNull(cc.getCourseFromCatalog("CSC 218", "001"));
	}
	
	/**
	 * Tests CourseCatalog.saveCourseCatalog()
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		assertEquals(0, cc.getCourseCatalog().length);
		cc.saveCourseCatalog("test-files/course_save.txt");
	}
	
}
