package edu.ncsu.csc216.pack_scheduler.user;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
//import edu.ncsu.csc216.pack_scheduler.course.Course;
/**
 * Get the data of the student objects including their first name, last name, id, email, password, and
 * max credits 
 * @author Sachi Vyas
 *
 */
public class Student extends User implements Comparable<Student> {
	
	/** Maximum number of credits any student can have */
	public static final int MAX_CREDITS = 18;
	/** max credits a specific student can enroll in */
	int maxCredits;
	/** Student's schedule */
	private Schedule schedule;
	
	/**
	 * Creates a students records with help of the firstName, lastName, id, email, password, credits
	 * 
	 * @param firstName the first name of the student
	 * @param lastName the last name of the student
	 * @param id the id of the student
	 * @param email the email of the student
	 * @param hashPW the password
	 * @param maxCredits the max credits the student can take
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		this.schedule = new Schedule();
	}
	
	/**
	 * Creates a students records with help of the firstName, lastName, id, email, password, credits
	 * @param firstName the first name of the student
	 * @param lastName the last name of the student
	 * @param id the id of the student
	 * @param email the email of the student
	 * @param hashPW the password
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
	}
	
	/**
	 * Returns the max credits that a specific student can take
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the max number of credits a specific student can take
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if the credits are invalid
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}
	
	/**
	 * Returns a comma separated value String of all Student fields.
	 * @return String representation of the Student information
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
	}
	
	/**
	 * Compares the students for ordering i.e. first name, last name and then id are compared for 
	 * alphabetical order.
	 * @param s the student to compare
	 * @return negative integer, zero, or positive integer if the given should be earlier in the list of the
	 * student calling method, zero if the students are equal, and positive integer if the student s should be 
	 * later in the list than in the student calling the method.
	 */
	@Override
	public int compareTo(Student s) {
		int idOrder = this.getId().toUpperCase().compareTo(s.getId().toUpperCase());
		int firstNameOrder =  this.getFirstName().toLowerCase().compareTo(s.getFirstName().toLowerCase());
		int lastNameOrder = this.getLastName().toLowerCase().compareTo(s.getLastName().toLowerCase());
		
		if (lastNameOrder != 0) {
			return lastNameOrder;
		}
		if (firstNameOrder != 0) {
			return firstNameOrder;
		}
		return idOrder;
		
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * @return hashCode for student information
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
//		if (maxCredits != other.maxCredits) {
//			return false;
//		}
//		return true;
			
	}
	
	/**
	 * Returns the schedule of the Student
	 * @return schedule the current schedule of the student
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Checks if a course can be added to a schedule of a student
	 * @param course the course to examine if it can fit in the schedule of the student
	 * @return boolean true if the course can be added to the student's schedule and false otherwise
	 */
	public boolean canAdd(Course course) {
		int creditsCurrent = schedule.getScheduleCredits() + course.getCredits();
		if (creditsCurrent > maxCredits) {
			return false;
		}
		return schedule.canAdd(course);
		
	}
	
	
}