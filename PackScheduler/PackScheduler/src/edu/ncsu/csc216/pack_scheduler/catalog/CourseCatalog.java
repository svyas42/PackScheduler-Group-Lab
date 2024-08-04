package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Building a course catalog
 * @author Sachi Vyas
 *
 */
public class CourseCatalog {
	
	/** List of courses in the catalog */
	private SortedList<Course> catalog;
	
	/**
	 * Constructs an empty catalog
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}
	
	/**
	 * Constructing a new course catalog
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Gets the list of courses from the file and uploads them to the GUI
	 * @param fileName the name of the file to load
	 * @throws IllegalArgumentException if file cannot be read
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adding a course to the catalog
	 * @param name the name of the course
	 * @param title the title of the course
	 * @param section the section number of the course
	 * @param credits the number of credits of the course
	 * @param instructorId the id of the instructor
	 * @param enrollmentCap the roll’s enrollment capacity
	 * @param meetingDays the meeting days of the course
	 * @param startTime the start time of the course
	 * @param endTime the end time of the course
	 * @return boolean i.e., false if the course already exists in the catalog or else the course is added to the catalog
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
									  String meetingDays, int startTime, int endTime) {
		Course addCourse = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if(c.getName().equals(addCourse.getName()) && c.getSection().equals(addCourse.getSection())) {
				return false;
			}
		}
		return catalog.add(addCourse);
	}
	
	/**
	 * Removing a course from the catalog
	 * @param name the name of the course
	 * @param section the section of the course
	 * @return boolean i.e., true if the course can be removed and false otherwise
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the course from the catalog with the given name and section
	 * @param name the name of the course
	 * @param section the section of the course
	 * @return course if it is present in the catalog or null if not present
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Returns a 2d array with name, section, title, and meeting information
	 * @return catalogCourse with the name, section, title, and meeting information
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogCourse = new String[catalog.size()][5];
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			catalogCourse[i] = c.getShortDisplayArray();
		}
		return catalogCourse;
	}
	/**
	 * Saves all the courses in the catalog to a file
	 * @param fileName the name of the file to save the courses to
	 * @throws IllegalArgumentException if unable to write to file
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
}
