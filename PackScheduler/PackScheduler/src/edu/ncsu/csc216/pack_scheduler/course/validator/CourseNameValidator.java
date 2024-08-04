package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Validates the course name
 * @author Sachi Vyas
 * @author musamalik
 *
 */
public class CourseNameValidator {
	/** private variable to store the boolean value */
	private boolean validEndState;
	/** Number of letters in the course name */
	private int letterCount;
	/** Number of digits in the course name */
	private int digitCount;
	/** Holds the letter state */
	private State letterState;
	/** Holds the number state */
	private State numberState;
	/** Holds the suffix state */
	private State suffixState;
	/** Holds the initial state */
	private State initialState;
	/** Holds the current state */
	private State currentState;
	
	
	/**
	 * Constructor for CourseNameValidator class
	 */
	public CourseNameValidator() {
		initialState = new InitialState();
		numberState = new NumberState();
		letterState = new LetterState();
		suffixState = new SuffixState();
		currentState = initialState;
	}
	
	/**
	 * Determines if a given name is a valid course name
	 * @param courseName the name of the course
	 * @return boolean true if the course name is valid and false otherwise
	 * @throws InvalidTransitionException when a transition is invalid
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		letterCount = 0;
		validEndState = false;
		digitCount = 0;
		currentState = initialState;
		int i = 0;
		while(i < courseName.length()) {
			char letter = courseName.charAt(i++);
			if (Character.isLetter(letter)) {
				currentState.onLetter();
			}
			else if (Character.isDigit(letter)) {
				currentState.onDigit();
			}
			else {
				currentState.onOther();
			}
		}
		
		if (digitCount != 3) {
			throw new InvalidTransitionException("Course name must have 3 digits.");
		}
		
		
		return validEndState;
	}
	
	/**
	 * Abstract class and an inner class of the CourseNamaValidator, represents the string state of the FSM
	 * @author Sachi Vyas
	 *
	 */
	private abstract class State {
		/**
		 * Empty constructor
		 */
		public State() {
			//not needed
		}
		
		/**
		 * Abstract method for handling a letter input
		 * @throws InvalidTransitionException if the course name has invalid transitions between states
		 */
	   public abstract void onLetter() throws InvalidTransitionException;
	   
	   /**
	    * Abstract method for handling a digit input
	    * @throws InvalidTransitionException if course name has invalid transitions between states
	    */
	   public abstract void onDigit() throws InvalidTransitionException;
	   
	   /**
	    * Concrete method for handling any other input. 
	    * @throws InvalidTransitionException with the message course name can only 
	    * contain letters and digits.
	    */
	   public void onOther() throws InvalidTransitionException {
		   throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * State in which the leading letters of the course name have been identified
	 * @author Sachi Vyas
	 *
	 */
	private class LetterState extends State {
		
		/**
		 * Handles the processing of alphabetical characters
		 * @throws InvalidTransitionException if the course name is more than 4 letters
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			
			letterCount++;
			currentState = letterState;
			
			if (letterCount > 4) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
			
		}
		
		/**
		 * Handles the processing of numeric characters
		 */
		@Override
		public void onDigit() {
			digitCount++;
			currentState = numberState;
		}
		
	}
	
	/**
	 * State in which the suffix letter of the course name has been identified
	 * @author Sachi Vyas
	 *
	 */
	private class SuffixState extends State {
		/** Creating an integer to store the suffix value */
		int suffixCount;
		
		/**
		 * Handles the processing of alphabetical characters
		 * @throws InvalidTransitionException if the course name has more than 1 letter suffix
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			suffixCount++;
			currentState = suffixState;
			if (suffixCount >= 1) {
				validEndState = false;
				throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
			}			
		}
		
		/**
		 * Handles the processing of numeric characters
		 * @throws InvalidTransitionException if course name contains digits after the suffix
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			validEndState = false;
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");			
		}
	}
	

	/**
	 * Initial State of the FSM for course name
	 * @author Sachi Vyas
	 */
	private class InitialState extends State {
		
		/**
		 * Handles the processing of alphabetical characters
		 */
		@Override
		public void onLetter() {
			letterCount++;
			currentState = letterState;
		}
		
		/**
		 * Handles the processing of numeric characters
		 * @throws InvalidTransitionException if the course name does not start with a letter
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
		
	}
	

	/**
	 * State in which the digits of the course name has been identified
	 * @author Sachi Vyas
	 *
	 */
	private class NumberState extends State {
		
		/**
		 * Handles the processing of alphabetical characters
		 * @throws InvalidTransitionException if the course name has less than 3 digits
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount == 3) {
				currentState = suffixState;
			}
			else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}
		
		/**
		 * Handles the processing of numeric characters
		 * @throws InvalidTransitionException if the course name has more than 3 digits
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if (digitCount < 3) {
				digitCount++;
				currentState = numberState;
				if (digitCount == 3) {
					validEndState = true;
				}
			}
			else if (digitCount == 3) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
			
		}
		
	}
}
