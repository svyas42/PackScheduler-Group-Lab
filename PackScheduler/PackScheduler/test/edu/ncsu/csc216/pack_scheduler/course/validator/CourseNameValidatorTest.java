package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the CourseNameValidator() class
 * @author Musa Malik
 *
 */
public class CourseNameValidatorTest {
	
	/** CourseNameValidatorFSM object */
	private CourseNameValidator state;

	/**
	 * Sets up the RegistrationManager and clears the data.
	 * @throws Exception if error
	 */
	@BeforeEach
	public void setUp() throws Exception {
		state = new CourseNameValidator();
	}
	
	/**
	 * Test method for isValid() - State initial using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testInvalidFSMTransitionNoMessage() throws InvalidTransitionException {
		Exception e = new InvalidTransitionException();
		assertEquals(e.getMessage(), "Invalid FSM Transition.");
	}
	
	/**
	 * Test method for isValid() - State initial using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateIntialNextLetterValid() throws InvalidTransitionException {
		assertTrue(state.isValid("CSC216"));
	}
	
	/**
	 * Test method for isValid() - State initial using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateInitialNextDigialInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("1SC216"));
		assertEquals(e.getMessage(), "Course name must start with a letter.");
	}
	
	/**
	 * Test method for isValid() - State initial using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateInitialNextOtherInValid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("&SC216"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	
	}
	/**
	 * Test method for isValid() - State L using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateLNextLetterValid() throws InvalidTransitionException {
		assertTrue(state.isValid("CSCA216"));
	}
	
	/**
	 * Test method for isValid() - State L using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateLNextDigitValid() throws InvalidTransitionException {
		assertTrue(state.isValid("C216"));
	}
	
	/**
	 * Test method for isValid() - State L using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateLNextOtherInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("C&C216"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State LL using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateLLNextLetterValid() throws InvalidTransitionException {
		assertTrue(state.isValid("CSC216"));
	}
	
	/**
	 * Test method for isValid() - State LL using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateLLNextDigitValid() throws InvalidTransitionException {
		assertTrue(state.isValid("CS216"));
	}
	
	/**
	 * Test method for isValid() - State LL using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateLLNextOtherInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CS&216"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State LLL using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateLLLNextetterValid() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertTrue(c.isValid("CSC216"));
	}
	
	/**
	 * Test method for isValid() - State LLL using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateLLLNextDigitValid() throws InvalidTransitionException {
		assertTrue(state.isValid("CSC216"));
	}
	
	/**
	 * Test method for isValid() - State LLL using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateLLLNextOtherInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CSC&16"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State LLLL using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateLLLLNextLetterInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CSCAF216"));
		assertEquals(e.getMessage(), "Course name cannot start with more than 4 letters.");
	}
	
	/**
	 * Test method for isValid() - State LLLL using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateLLLLNextDigitValid() throws InvalidTransitionException {
		assertTrue(state.isValid("CSCA216"));
	}
	
	/**
	 * Test method for isValid() - State LLLL using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateLLLLNextOtherInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CSCA&16"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State D using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateDNextLetterInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CSCA2C6"));
		assertEquals(e.getMessage(), "Course name must have 3 digits.");
	}
	
	/**
	 * Test method for isValid() - State D using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateDNextDigitValid() throws InvalidTransitionException {
		assertTrue(state.isValid("CSC216"));
		assertTrue(state.isValid("CC216"));
		assertTrue(state.isValid("C216"));
	}
	
	/**
	 * Test method for isValid() - State D using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateDNextOtherInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CSC2&6"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State DD using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateDDNextLetterInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CSC2&6"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State DD using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateDDNextDigitValid() throws InvalidTransitionException {
		assertTrue(state.isValid("CSC216"));
		assertTrue(state.isValid("CC216"));
		assertTrue(state.isValid("C216"));
	}
	
	/**
	 * Test method for isValid() - State DD using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateDDNextOtherInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CSC21&"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State DDD using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateDDDNextLetterValid() throws InvalidTransitionException {
		assertTrue(state.isValid("CSC216A"));
		assertTrue(state.isValid("CC216A"));
		assertTrue(state.isValid("C216A"));
	}
	
	/**
	 * Test method for isValid() - State DDD using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateDDDNextDigitInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CSC2167"));
		assertEquals(e.getMessage(), "Course name can only have 3 digits.");
	}
	
	/**
	 * Test method for isValid() - State DDD using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateDDDNextOtherInValid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CSC216&"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}
	
	/**
	 * Test method for isValid() - State suffix using letter
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateSuffixNextLetterInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CSC216CC"));
		assertEquals(e.getMessage(), "Course name can only have a 1 letter suffix.");
	}
	
	/**
	 * Test method for isValid() - State suffix using digit
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateSuffixNextDigitInvalid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CSC216C7"));
		assertEquals(e.getMessage(), "Course name cannot contain digits after the suffix.");
	}
	
	/**
	 * Test method for isValid() - State suffix using other
	 * @throws InvalidTransitionException if transition is illegal
	 */
	@Test
	public void testStateSuffixNextOtherInValid() throws InvalidTransitionException {
		Exception e = assertThrows(InvalidTransitionException.class,
				() ->  state.isValid("CSC216&"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
	}

}
