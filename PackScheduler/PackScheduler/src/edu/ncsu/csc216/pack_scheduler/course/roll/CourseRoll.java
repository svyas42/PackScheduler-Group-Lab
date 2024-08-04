package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.ArrayQueue;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * Maintains and modifies a roll of students associated with a particular
 * course. Allows modification of the enrollment cap, adding students to the
 * roll and removing from the roll. Retrieves the enrollment cap, the roll of
 * students, and the number of open seats. Validates that a student can enroll.
 * 
 * @author Jason Maher
 * @author Sachi Vyas
 *
 */
public class CourseRoll {
	/**
	 * LinkedAbstractList of students within the course.
	 */
	private LinkedAbstractList<Student> roll;
	/**
	 * Waitlist of students for a course
	 */
	private ArrayQueue<Student> waitlist;
	/**
	 * Course students are attempting to enroll in
	 */
	private Course course;
	/**
	 * Maximum enrollment capacity.
	 */
	private int enrollmentCap;
	/**
	 * Smallest permissible class size.
	 */
	private static final int MIN_ENROLLMENT = 10;
	/**
	 * Largest permissible class size.
	 */
	private static final int MAX_ENROLLMENT = 250;
	/**
	 * Size of the Waitlist for the course
	 */
	private static final int WAITLIST_SIZE = 10;

	/**
	 * CourseRoll Constructor CourseRoll Constructor Creates a linked abstract list
	 * of students
	 * 
	 * @param enrollmentCap Enrollment capacity of the course.
	 * @param c        Course student is attempting to enroll in
	 * @throws IllegalArgumentException if the course is null
	 */
	public CourseRoll(Course c, int enrollmentCap) {
		if (c == null) {
			throw new IllegalArgumentException("Course cannot be null");
		}
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		waitlist = new ArrayQueue<Student> (WAITLIST_SIZE);
		
//		setCourse(c);
		this.course = c;
	}

//	private void setCourse(Course c) {
//		if (c == null) {
//			throw new IllegalArgumentException("Course cannot be null");
//		}
//		this.course = c;
//	}

	/**
	 * Retrieves enrollment capacity.
	 *
	 * @return enrollment capacity
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * Sets enrollment capacity.
	 * 
	 * @param enrollmentCap the enrollment capacity.
	 * @throws IllegalArgumentException if the capacity is greater than
	 *                                  max_enrollment or less than min_enrollment
	 * @throws IllegalArgumentException if the enrollment cap is less than 0 and the
	 *                                  capacity is less than roll size
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap > MAX_ENROLLMENT || enrollmentCap < MIN_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
//		if (enrollmentCap > 0 && capacity < roll.size()) {
//			throw new IllegalArgumentException();
//		}
//		if (capacity >= roll.size()) {
//			enrollmentCap = capacity;
//		}
		if (roll == null) {
			this.enrollmentCap = enrollmentCap;
		} else {
			if (enrollmentCap > roll.getCapacity()) {
				roll.setCapacity(enrollmentCap);
			}
			if (enrollmentCap >= roll.size()) {
				this.enrollmentCap = enrollmentCap;
			} else if (enrollmentCap < roll.size()) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Enrolls a student in the course.
	 * 
	 * @param student Student to be enrolled.
	 * @throws IllegalArgumentException if there is an error adding the student to a
	 *                                  course.
	 * @throws IllegalArgumentException if student cannot be added to course
	 * @throws IllegalArgumentException if course cannot be added           
	 * @throws IllegalArgumentException when student is null                
	 */
	public void enroll(Student student) {
		
		if (student == null) {
			throw new IllegalArgumentException();
		}
		if (!canEnroll(student)) {
			throw new IllegalArgumentException("Course cannot be added to schedule.");
		}
		if (!(getOpenSeats() > 0)) {
			//checking if there is space on the waitlist and if the student can be added
			if (waitlist.size() < WAITLIST_SIZE) { 
				try {
					waitlist.enqueue(student);
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException();
				}
			}
			else {
				throw new IllegalArgumentException("The course cannot be added");
			}
		}
		else {
			try {
				roll.add(roll.size(), student);
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
		}
		
//		if (canEnroll(student)) {
//			try {
//				if (roll.size() == enrollmentCap) {
//					if (waitlist.size() == WAITLIST_SIZE) {
//						throw new IllegalArgumentException("No room in waitlist");
//					}
//					waitlist.enqueue(student);
//				} else {
//					roll.add(student);
//				}
//			} catch (Exception e) {
//				throw new IllegalArgumentException("Error adding to course roll.");
//			}
//		}
	}

	/**
	 * Drops a student from the course.
	 * 
	 * @param student Student to be dropped.
	 * @throws IllegalArgumentException if the student is null.
	 */
	public void drop(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("Student is null");
		}
//		if (!roll.contains(student)) {
//			return;
//			
//		}
		
//		roll.remove(roll.indexOf(student));
		if (roll.contains(student)) {
			roll.remove(roll.indexOf(student));
		}
		
//		for (int i = 0; i < waitlist.size(); i++) {
//				waitlist.get;
//			//deque itesm from the list
//			//student not equal to the student and 
//			
//		}
		if (waitlist.contains(student)) {
			waitlist.dequeue();
		}
		if (waitlist != null && waitlist.size() > 0) {
			
			Student s = waitlist.dequeue();
			
			enroll(s);
			
			s.getSchedule().addCourseToSchedule(course);
		}
		
		
//		if (getNumberOnWaitlist() > 0 && getOpenSeats() > 0) {
//			Student addStudent = waitlist.dequeue();
//			
//			if (addStudent.canAdd(course)) {
//				enroll(addStudent);
//				addStudent.getSchedule().addCourseToSchedule(course);
//			}
//		}
	}

	/**
	 * Retrieves number of open seats left in the course.
	 * 
	 * @return Number of open seats.
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}

	/**
	 * Determines if a student is able to enroll in a course.
	 * 
	 * @param student Student to be checked.
	 * @return true if can be added, else returns false.
	 * @throws IllegalArgumentException if the student is null.
	 * @throws IllegalArgumentException if the roll contains the given student.
	 */
	public boolean canEnroll(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("Student is null");
		} else if (roll.contains(student)) {
			throw new IllegalArgumentException("Cannot enroll student, " + student.getId() + " is already enrolled.");
		} 
//			else if (waitlist.contains(student)) {
//			return false;
//		}
		return true;

	}
	/**
	 * Returns the number of students on the waitlist.
	 * @return The number of students on the waitlist.
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
}
