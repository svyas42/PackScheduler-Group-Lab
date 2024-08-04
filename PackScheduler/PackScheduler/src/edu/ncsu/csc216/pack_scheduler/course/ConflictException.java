package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Defines the Conflict exception and contains two constructors parameterized and void
 * @author Sachi Vyas
 * @author Jason Maher
 *
 */
public class ConflictException extends Exception {
	
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ConflictException object constructed with message passed as a string parameter in the parent class 
	 * using super()
	 * @param message the error message
	 */
	public ConflictException(String message) {
		
		super(message);
	}
	
	/**
	 * The default message to put in if the conflict exception is thrown
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
