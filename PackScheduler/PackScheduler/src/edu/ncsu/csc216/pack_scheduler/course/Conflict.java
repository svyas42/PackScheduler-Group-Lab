package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Creating an interface called conflict
 * @author Sachi Vyas
 *
 */
public interface Conflict {
	
	/**
	 * Creating an abstract conflict checker which checks if there is a potential conflict int eh 
	 * schedule i.e. checks if the instance of the activity parameter is already in the schedule or 
	 * if its overlaps or conflicts with something already in the schedule
	 * 
	 * @param possibleConflictingActivity an instance of the activity class which needs to be checked 
	 * if its with the Activity calling method
	 * @throws ConflictException if the two activities conflict or overlap due to day and times
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
