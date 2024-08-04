/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the Schedule() class and its methods
 * @author musamalik
 * @author Sachi Vyas
 *
 */
public class ScheduleTest {

	/** Schedule object used for testing */
	private Schedule schedule;
	
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
	/** Creating course1 to test */
	Course c1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	/** Creating course2 to test */
	Course c2 = new Course("CSC116", "Intro to Java", "001", 3, "sesmith5", 10, "MT", 1500, 1600);
	/** Creating course3 to test */
	Course c3 = new Course("CSC226", "Discrete Math", "001", 3, "sesmith5", 10, "TF", 1610, 1700);
	
	/**
	 * Test method for Scheduler() constructor
	 */
	@Test
	final void testSchedule() {
		schedule = new Schedule();
		assertEquals(schedule.getScheduledCourses().length, 0);
		assertEquals(schedule.getTitle(), "My Schedule");
	}

	/**
	 * Test method for addCourseToSchedule()
	 */
	@Test
	final void testAddCourseToSchedule() {
		schedule = new Schedule();
		
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME); 
		assertTrue(schedule.addCourseToSchedule(course));
		assertEquals(schedule.getScheduledCourses().length, 1);
		
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> schedule.addCourseToSchedule(course));
		assertEquals(e.getMessage(), "You are already enrolled in " + course.getName());
		
	}

	/**
	 * Test method for removeCourseFromSchedule()
	 */
	@Test
	final void testRemoveCourseFromSchedule() {
		schedule = new Schedule();
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME); 
		Course course2 = new Course("CSC116", TITLE, "003", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", START_TIME, END_TIME);
		assertTrue(schedule.addCourseToSchedule(course));
		
		assertFalse(schedule.removeCourseFromSchedule(course2));
		assertTrue(schedule.removeCourseFromSchedule(course));
		assertEquals(schedule.getScheduledCourses().length, 0);
	}

	/**
	 * Test method for resetSchedule()
	 */
	@Test
	final void testResetSchedule() {
		schedule = new Schedule();
		
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME); 
		assertTrue(schedule.addCourseToSchedule(course));
		schedule.setTitle("other title");
		
		schedule.resetSchedule();
		
		assertEquals(schedule.getScheduledCourses().length, 0);
		assertEquals(schedule.getTitle(), "My Schedule");
	}

	/**
	 * Test method for getScheduledCourses()
	 */
	@Test
	final void testGetScheduledCourses() {
		schedule = new Schedule();
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME); 
		assertTrue(schedule.addCourseToSchedule(course));
		Course course2 = new Course("CSC116", TITLE, "003", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", START_TIME, END_TIME); 
		assertTrue(schedule.addCourseToSchedule(course2));
		
		String [][] scheduledCourses = schedule.getScheduledCourses();
		
		assertEquals(NAME, scheduledCourses[0][0]);
		assertEquals(SECTION, scheduledCourses[0][1]);
		assertEquals(TITLE, scheduledCourses[0][2]);
		assertEquals("MW 1:30PM-2:45PM", scheduledCourses[0][3]);
		assertEquals("CSC116", scheduledCourses[1][0]);
		assertEquals("003", scheduledCourses[1][1]);
		assertEquals(TITLE, scheduledCourses[1][2]);
		assertEquals("TH 1:30PM-2:45PM", scheduledCourses[1][3]);
	}

	/**
	 * Test method for setTitle()
	 */
	@Test
	final void testSetTitle() {
		schedule = new Schedule();
		assertEquals(schedule.getTitle(), "My Schedule");
		
		schedule.setTitle("testTitle");
		
		assertEquals(schedule.getTitle(), "testTitle");
		
		Exception e = assertThrows(IllegalArgumentException.class, 
				() -> schedule.setTitle(null));
		assertEquals(e.getMessage(), "Title cannot be null.");
	}
	
	/**
	 * Tests the getScheduledCredits() method
	 */
	@Test
	final void testGetScheduledCredits() {
		Schedule s = new Schedule();
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		assertEquals(6, s.getScheduleCredits());
	}
	
	/**
	 * Tests the canAdd() method
	 */
	@Test
	final void testCanAdd() {
		Schedule s = new Schedule();
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		assertTrue(s.canAdd(c3));
		assertFalse(s.canAdd(c1));
		assertFalse(s.canAdd(null));
	}

}
