package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Invalid Transition Exception class
 * @author Jason Maher
 * tests the custom exception: InvalidTransitionException 
 *
 */
class InvalidTransitionExceptionTest {

	/**
	 * tests default method
	 */
	@Test
	public void testInvalidTransitionException() {
		InvalidTransitionException e = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", e.getMessage());
	}
	/**
	 * tests default method
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		InvalidTransitionException e = new InvalidTransitionException("Custom Message.");
		assertEquals("Custom Message.", e.getMessage());
	}

}
