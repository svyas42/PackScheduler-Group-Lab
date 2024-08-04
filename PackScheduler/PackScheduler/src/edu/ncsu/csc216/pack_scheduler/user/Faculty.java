package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Gets all the information about the faculty members
 * 
 * @author Sachi Vyas
 * @author Jason Maher
 *
 */
public class Faculty extends User {
	/** Max courses that the faculty can teach */
	private int maxCourses;
	/** A Faculty's schedule */
	private FacultySchedule schedule;
	/** The minimum courses a faculty member can teach */
	public static final int MIN_COURSES = 1;
	/** The maximum courses a faculty member can teach */
	public static final int MAX_COURSES = 3;

	/**
	 * Constructor for the Faculty class with max courses
	 * 
	 * @param firstName  the first name of the faculty member
	 * @param lastName   the last name of the faculty member
	 * @param id         the id of the faculty member
	 * @param email      the email of the faculty member
	 * @param password   the password of the faculty member
	 * @param maxCourses the maximum number of courses a faculty member can teach
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		// create a new recursive linkedlist of faculty's schedule
		this.schedule = new FacultySchedule(id);
	}

	/**
	 * Sets the max courses variable
	 * 
	 * @param maxCourses the maximum number of courses a faculty member can teach
	 * @throws IllegalArgumentException if the max courses is invalid
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}

	/**
	 * Returns the value for the max courses variable
	 * 
	 * @return maxCourses the maximum number of courses a faculty member can teach
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Returns a FacultySchedule for a given faculty member
	 * 
	 * @return FacultySchedule for a given faculty member
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}

	/**
	 * Checks if the faculty member is teaching too many courses
	 * 
	 * @return true if the number of faculty's scheduled courses is greater than The
	 *         maximum courses a faculty member can teach, else returns false
	 */
	public boolean isOverloaded() {
		return schedule.getNumScheduledCourses() > maxCourses;
	}

	/**
	 * Generates the hashcode for faculty objects
	 * 
	 * @return result the hashcode for the faculty
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Compares two faculty objects
	 * 
	 * @return boolean true is the objects are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
//		if (maxCourses != other.maxCourses) {
//			return false;
//		}
//		return true;
		return maxCourses == other.maxCourses;
	}

	/**
	 * Returns the to string format for the faculty
	 * 
	 * @return the string representation of faculty information
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ maxCourses;
	}

}
