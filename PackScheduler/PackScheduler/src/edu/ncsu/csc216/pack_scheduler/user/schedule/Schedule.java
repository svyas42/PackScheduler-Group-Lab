/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Schedule class creates a schedule and allows for user to modify it.
 * @author Musa Malik
 * @author Sachi Vyas
 */
public class Schedule {
	/** Array List of courses in schedule */
	private ArrayList<Course> schedule;
	/** Title of schedule */
	private  String title;
	
	/**
	 * Constructor for Schedule
	 */
	public Schedule() {
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
	}
	
	/**
	 * Add course to schedule
	 * @param course course to add
	 * @return if successfully added
	 * @throws IllegalArgumentException if the student is already enrolled in the course once the conflict exception is
	 * caught
	 * @throws IllegalArgumentException if the student is already enrolled
	 */
	public boolean addCourseToSchedule(Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			Course c = schedule.get(i);
			
			try {
				c.checkConflict(course);
			}
			catch (ConflictException e) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}
			
			if(c.equals(course) || c.isDuplicate(course)) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}
		}
		return schedule.add(course);
	}
	
	/**
	 * Removes course from schedule
	 * @param course course to remove
	 * @return if successfully removed
	 */
	public boolean removeCourseFromSchedule(Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			Course c = schedule.get(i);
			if(c.equals(course)) {
				return schedule.remove(course);
			}
		}
		return false;
	}
	
	/**
	 * Resets schedule to default
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
	}
	
	/**
	 * Returns 2D array of scheduled courses
	 * @return 2D array of schedule
	 */
	public String[][] getScheduledCourses() {
		String[][] mySchedule = new String[schedule.size()][];
		for (int i = 0; i < schedule.size(); i++) {
			mySchedule[i] = schedule.get(i).getShortDisplayArray();
		}
		return mySchedule;
	}
	
	/**
	 * Sets title of schedule
	 * @param title title of schedule
	 * @throws IllegalArgumentException if the title is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/**
	 * Returns title of schedule
	 * @return title of schedule
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Returns the number of credits on the schedule
	 * @return scheduleCredits the number of credits on the schedule currently
	 */
	public int getScheduleCredits() {
		int scheduleCredits = 0;
		
		for (int i = 0; i < schedule.size(); i++) {
			scheduleCredits += schedule.get(i).getCredits();
		}
		return scheduleCredits;
	}
	
	/**
	 * Checks if a course can be added to the schedule
	 * @param course the course that could be added to the schedule
	 * @return boolean true if the course can be added, false otherwise
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		}
//		for (int i = 0; i < schedule.size(); i++) {
//			if (course.isDuplicate(schedule.get(i))) {
//				return false;
//			}
//		}
//		try {
//			for (int i = 0; i < schedule.size(); i++) {
//				course.checkConflict(schedule.get(i));
//			}
//		} catch (ConflictException e) {
//			return false;
//		}
		
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(course)) {
				return false;
			}
		}
		
		try {
			for (int i = 0; i < schedule.size(); i++) {
				schedule.get(i).checkConflict(course);
			}
		} catch (ConflictException e) {
			return false;
		}
		return true;
	}
	
}
