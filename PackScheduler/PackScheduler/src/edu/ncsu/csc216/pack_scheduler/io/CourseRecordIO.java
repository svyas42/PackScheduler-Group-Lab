package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

//import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.File;

import edu.ncsu.csc216.pack_scheduler.course.Course;
//import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc217.collections.list.SortedList;
//import edu.ncsu.csc216.pack_scheduler.user.Faculty;
/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Sarah Heckman
 * @author Sachi Vyas
 */
public class CourseRecordIO {

	/**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch(IllegalArgumentException e) {
				//The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		fileReader.close();
		return courses;
	}
	
	/**
	 * Private helper method to process each course
	 * @param line the line to read from the file
	 * @return Course()
	 * @throws IllegalArgumentException if .hasNext() or .hasNextInt() is equal to true
	 * @throws NoSuchElementException if the inputed element does not exist
	 */	
	private static Course readCourse(String line) {
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");
		String name;
		String title;
		String section;
		int credits;
		String instructorId;
		int enrollmentCap;
		String meetingDays;
		int startTime = 0;
		int endTime = 0;
		
		try {
			name = scan.next();
			title = scan.next();
			section = scan.next();
			credits = scan.nextInt();
			instructorId = scan.next();
			enrollmentCap = scan.nextInt();
			meetingDays = scan.next();
			
			if (!"A".equals(meetingDays)) {
				startTime = scan.nextInt();
				endTime = scan.nextInt();
			
				try {
					Course c = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime, 
							endTime);
					Faculty f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId);
					scan.close();
					
//					if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId) != null) {
//						RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId).getSchedule().addCourseToSchedule(c);
//					}
					if (f != null) {
						f.getSchedule().addCourseToSchedule(c);
					}
					
					return c;
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException();
				}
		   }
			else {
				Course a = new Course(name, title, section, credits, null, enrollmentCap, meetingDays);
				Faculty f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId);
//				if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId) != null) {
//					RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId).getSchedule().addCourseToSchedule(a);
//				}
				if (f != null) {
					f.getSchedule().addCourseToSchedule(a);
				}
				scan.close();
				return a;
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		
	
		
		
	}
		
	/**
     * Writes the given list of Courses to 
     * @param fileName file to write schedule of Courses to
     * @param courses list of Courses to write
     * @throws IOException if cannot write to file
     */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
		    fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();		
	}


}