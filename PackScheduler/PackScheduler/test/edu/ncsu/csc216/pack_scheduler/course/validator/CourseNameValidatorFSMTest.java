package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the CourseNameValidatorFSM() class
 * @author Musa Malik
 * @author Jason King
 *
 */
class CourseNameValidatorFSMTest {
	
	/** CourseNameValidatorFSM object */
	private CourseNameValidatorFSM fsm;

	/**
	 * Sets up the RegistrationManager and clears the data.
	 * @throws Exception if error
	 */
	@BeforeEach
	public void setUp() throws Exception {
		fsm = new CourseNameValidatorFSM();
	}
	
	/**
	 * Test method for isValid() - State initial using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateIntialNextLetterValid() throws InvalidTransitionException {
		assertTrue(fsm.isValid("CSC216"));
	}
	
	/**
	 * Test method for isValid() - State initial using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateInitialNextDigialInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("1SC216"));
		assertEquals(e.getMessage(), "Course name must start with a letter.");
	}
	
	/**
	 * Test method for isValid() - State initial using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateInitialNextOtherInValid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("&SC216"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	
	}
	/**
	 * Test method for isValid() - State L using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateLNextLetterValid() throws InvalidTransitionException {
		assertTrue(fsm.isValid("CSC216"));
	}
	
	/**
	 * Test method for isValid() - State L using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateLNextDigitValid() throws InvalidTransitionException {
		assertTrue(fsm.isValid("C216"));
	}
	
	/**
	 * Test method for isValid() - State L using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateLNextOtherInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("C&C216"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State LL using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateLLNextLetterValid() throws InvalidTransitionException {
		assertTrue(fsm.isValid("CSC216"));
	}
	
	/**
	 * Test method for isValid() - State LL using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateLLNextDigitValid() throws InvalidTransitionException {
		assertTrue(fsm.isValid("CS216"));
	}
	
	/**
	 * Test method for isValid() - State LL using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateLLNextOtherInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CS&216"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State LLL using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateLLLNextetterValid() throws InvalidTransitionException {
		assertTrue(fsm.isValid("CSCA216"));
	}
	
	/**
	 * Test method for isValid() - State LLL using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateLLLNextDigitValid() throws InvalidTransitionException {
		assertTrue(fsm.isValid("CSC216"));
	}
	
	/**
	 * Test method for isValid() - State LLL using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateLLLNextOtherInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CSC&16"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State LLLL using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateLLLLNextLetterInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CSCAF216"));
		assertEquals(e.getMessage(), "Course name cannot start with more than 4 letters.");
	}
	
	/**
	 * Test method for isValid() - State LLLL using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateLLLLNextDigitValid() throws InvalidTransitionException {
		assertTrue(fsm.isValid("CSCA216"));
	}
	
	/**
	 * Test method for isValid() - State LLLL using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateLLLLNextOtherInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CSCA&16"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State D using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateDNextLetterInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CSCA2C6"));
		assertEquals(e.getMessage(), "Course name must have 3 digits.");
	}
	
	/**
	 * Test method for isValid() - State D using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateDNextDigitValid() throws InvalidTransitionException {
		assertTrue(fsm.isValid("CSC216"));
		assertTrue(fsm.isValid("CC216"));
		assertTrue(fsm.isValid("C216"));
	}
	
	/**
	 * Test method for isValid() - State D using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateDNextOtherInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CSC2&6"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State DD using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateDDNextLetterInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CSC2&6"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State DD using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateDDNextDigitValid() throws InvalidTransitionException {
		assertTrue(fsm.isValid("CSC216"));
		assertTrue(fsm.isValid("CC216"));
		assertTrue(fsm.isValid("C216"));
	}
	
	/**
	 * Test method for isValid() - State DD using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateDDNextOtherInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CSC21&"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State DDD using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateDDDNextLetterValid() throws InvalidTransitionException {
		assertTrue(fsm.isValid("CSC216A"));
		assertTrue(fsm.isValid("CC216A"));
		assertTrue(fsm.isValid("C216A"));
	}
	
	/**
	 * Test method for isValid() - State DDD using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateDDDNextDigitInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CSC2167"));
		assertEquals(e.getMessage(), "Course name can only have 3 digits.");
	}
	
	/**
	 * Test method for isValid() - State DDD using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateDDDNextOtherInValid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CSC216&"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State suffix using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateSuffixNextLetterInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CSC216CC"));
		assertEquals(e.getMessage(), "Course name can only have a 1 letter suffix.");
	}
	
	/**
	 * Test method for isValid() - State suffix using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateSuffixNextDigitInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CSC216C7"));
		assertEquals(e.getMessage(), "Course name cannot contain digits after the suffix.");
	}
	
	/**
	 * Test method for isValid() - State suffix using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	void testStateSuffixNextOtherInValid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  fsm.isValid("CSC216&"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}

}
