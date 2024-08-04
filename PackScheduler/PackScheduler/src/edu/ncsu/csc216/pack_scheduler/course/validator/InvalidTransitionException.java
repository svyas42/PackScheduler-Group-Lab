
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Custom Exception to be thrown if an invalid state transition is attempted.
 * 
 * @author Jason Maher
 *
 */
public class InvalidTransitionException extends Exception {
	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * specified conflict message to be displayed Thrown when invalid transitions
	 * are attempted
	 * 
	 * @param message message to be thrown upon invalid transition attempt
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}

	/**
	 * default message thrown when an invalid transition attempt arises
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
}
