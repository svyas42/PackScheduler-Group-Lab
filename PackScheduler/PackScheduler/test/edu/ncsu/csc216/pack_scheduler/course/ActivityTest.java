package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Testing Activity.java for potential errors
 * @author Sachi Vyas
 *
 */
public class ActivityTest {
	/** Example activity 1 */
	Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "W", 1400, 1430);
	/** Example activity 2 */
	Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	/** Example activity 3 */
	Activity a3 = new Course("CSC226", "Discrete Math", "002", 3, "schmidt", 10, "MW", 1445, 1530);
	/** Example activity 4 */
	Activity a4 = new Course("CSC217", "Software Development Fundamentals Lab", "003", 1, "schmidt", 10, "MW", 1445, 1530);
	
	/**
	 * Test method for checkConflict(edu.ncsu.csc216.pack_scheduler.course.Activity)}.
	 */
	@Test
	public void testCheckConflict() {
		
		try {
			a1.checkConflict(a2);
			fail();
		} catch (ConflictException e) {
			assertEquals( "W 2:00PM-2:30PM", a1.getMeetingString());
			assertEquals("MW 1:30PM-2:45PM", a2.getMeetingString());
		}
		
    	try {
			a3.checkConflict(a4);
			fail();
		} catch (ConflictException e) {
			assertEquals("MW 2:45PM-3:30PM", a3.getMeetingString());
			assertEquals("MW 2:45PM-3:30PM", a4.getMeetingString());
		}
    	
    	try {
			a2.checkConflict(a3);
			fail();
		} catch (ConflictException e) {
			assertEquals("MW 2:45PM-3:30PM", a3.getMeetingString());
			assertEquals("MW 1:30PM-2:45PM", a2.getMeetingString());
		}
	    
	}

}
