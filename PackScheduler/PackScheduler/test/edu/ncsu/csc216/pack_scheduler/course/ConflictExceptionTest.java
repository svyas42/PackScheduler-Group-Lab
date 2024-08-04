package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Testing the conflict exception with test cases
 * @author Sachi Vyas
 * @author Jason Maher
 *
 */
class ConflictExceptionTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException(java.lang.String)}.
	 */
	@Test
	public void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Schedule conflict.");
		assertEquals("Schedule conflict.", ce.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException()}.
	 */
	@Test
	public void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}

}
