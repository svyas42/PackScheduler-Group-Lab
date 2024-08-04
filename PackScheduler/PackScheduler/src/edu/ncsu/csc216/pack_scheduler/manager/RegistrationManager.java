package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Creates a Registration Manager that can manage the course catalog, student
 * directory and more.
 * 
 * @author musamalik
 * @author Sachi Vyas
 * @author Jason Maher
 *
 */
public class RegistrationManager {

	/** Instance of registration manager */
	private static RegistrationManager instance;
	/** course catalog */
	private CourseCatalog courseCatalog;
	/** student directory */
	private StudentDirectory studentDirectory;
	/** registrar user */
	private User registrar;
	/** current user */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** properties file */
	private static final String PROP_FILE = "registrar.properties";
	/** flag for logins */
	private Boolean loggedIn = false;
	/** Directory of the faculty used by the registration manager */
	private FacultyDirectory facultyDirectory;

	/**
	 * Constructor for registration manager
	 */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
	}

	/**
	 * Creates a new registrar
	 * 
	 * @throws IllegalArgumentException if the registrar cannot be created
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Takes password and hashes it
	 * 
	 * @param pw password
	 * @return hashed password
	 * @throws IllegalArgumentException if the password cannot be hashed
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Returns instance of registration manager
	 * 
	 * @return instance of registration manager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Returns course catalog
	 * 
	 * @return course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Returns student directory
	 * 
	 * @return student directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Logs in user. Should not allow multiple users to login at the same time.
	 * 
	 * @param id       id of user
	 * @param password password
	 * @return if successfully logged in
	 * @throws IllegalArgumentException if the user does not exist
	 */
	public boolean login(String id, String password) {
		if (!loggedIn) {
//			System.out.println("logging in: " + id);
			String localHashPW = hashPW(password);
			if (registrar.getId().equals(id) && registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				loggedIn = true;
				return true;		
				// add return false statement
			} else if (registrar.getId().equals(id) && !registrar.getPassword().equals(localHashPW)) {
				return false;
			}
//			System.out.println(Arrays.deepToString(studentDirectory.getStudentDirectory()));
			Student s = studentDirectory.getStudentById(id);
			Faculty f = facultyDirectory.getFacultyById(id);

			if (s == null && f == null) {
				throw new IllegalArgumentException("User doesn't exist.");
			}
//			System.out.println(s.getId());
			if (s != null) {
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					loggedIn = true;
					return true;
				}
			}
			else {
				if (f.getPassword().equals(localHashPW)) {
					currentUser = f;
					loggedIn = true;
					return true;
				}
				else if (!f.getPassword().equals(localHashPW)) {
					throw new IllegalArgumentException("Invalid id or password");
				}
			}
			
			
//			Faculty f = facultyDirectory.getFacultyById(id);
//			if (f == null) {
//				throw new IllegalArgumentException("User doesn't exist.");
//			}
//			if (f.getPassword().equals(localHashPW)) {
//				currentUser = f;
//				loggedIn = true;
//				return true;
//			}
//			else if (!f.getPassword().equals(localHashPW)) {
//				throw new IllegalArgumentException("Invalid id or password");
//			}
		}
		return false;
	}

	/**
	 * Logs out user
	 */
	public void logout() {
		currentUser = null;
		loggedIn = false;
	}

	/**
	 * Returns current user
	 * 
	 * @return current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Clears data for course catalog and student directory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}

	/**
	 * Registrar class that creates a new registrar user
	 * 
	 * @author musamalik
	 */
	private static class Registrar extends User {
		/**
		 * Creates a registrar user.
		 *
		 * @param firstName user first name
		 * @param lastName  user last name
		 * @param id        unique user id
		 * @param email     user email
		 * @param hashPW    hashed password value of user's password
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c Course to enroll in
	 * @return true if enrolled, false otherwise
	 * @throws IllegalArgumentException if the current user is null
	 * @throws IllegalArgumentException if the current user is not an instance of student
	 * 
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (currentUser == null) {
			throw new IllegalArgumentException("Illegal Action");
		}
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}
		} catch (IllegalArgumentException e) {
			return false;
		}
//		if (currentUser == null) {
//			throw new IllegalArgumentException("Illegal Action");
//		}
//		if (!(currentUser instanceof Student)) {
//			throw new IllegalArgumentException("Illegal Action");
//		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c Course to drop
	 * @return true if dropped, false otherwise
	 * @throws IllegalArgumentException if the current user is null
	 * @throws IllegalArgumentException if the current user is not an instance of
	 *                                  student
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (currentUser == null) {
			throw new IllegalArgumentException("Illegal Action");
		}
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * 
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 * 
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 * 
	 * @throws IllegalArgumentException if the current user is null
	 * @throws IllegalArgumentException if the current user is not an instance of student
	 */
	public void resetSchedule() {

		if (currentUser == null) {
			throw new IllegalArgumentException("Illegal Action");
		}
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}

		if (currentUser == null) {
			throw new IllegalArgumentException("Illegal Action");
		}
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}
	}
	
	/**
	 * Returns the faculty directory being used currently
	 * @return the faculty directory being used
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
	
	/**
	 * Adds a faculty to the course
	 * @param c the course to add the faculty to
	 * @param f the faculty to add to the course
	 * @return boolean true if the faculty get added, false otherwise
	 * @throws IllegalArgumentException if the current user is not equal to null or is not an instance of registrar
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {
//		if (currentUser instanceof Registrar && currentUser != null) {
//			return f.getSchedule().addCourseToSchedule(c);
//		}
		if (currentUser instanceof Registrar) {
			return f.getSchedule().addCourseToSchedule(c);
		}
			
		
		else if (currentUser != null && !(currentUser instanceof Registrar)) {
			throw new IllegalArgumentException();
		}
		else {
			return false;
		}
	}
	
	/**
	 * Adds a faculty to the course
	 * @param c the course to remove the faculty to
	 * @param f the faculty to remove to the course
	 * @return boolean true if the faculty get removed, false otherwise
	 * @throws IllegalArgumentException if the current user is not equal to null or is not an instance of registrar
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {
//		if (currentUser instanceof Registrar && currentUser != null) {
//			return f.getSchedule().removeCourseFromSchedule(c);
//		}
		
		if (currentUser instanceof Registrar) {
			return f.getSchedule().removeCourseFromSchedule(c);
		}
		else if (currentUser != null && !(currentUser instanceof Registrar)) {
			throw new IllegalArgumentException();
		}
		else {
			return false;
		}
	}
	
	/**
	 * Resets the faculty members schedule
	 * @param f the faculty whose schedule should be cleared from the system
	 * @throws IllegalArgumentException if the current user is not equal to null or is not an instance of registrar
	 */
	public void resetFacultySchedule(Faculty f) {
//		if (currentUser instanceof Registrar && currentUser != null) {
//			f.getSchedule().resetSchedule();
//		}
		if (currentUser instanceof Registrar) {
			f.getSchedule().resetSchedule();
		}
		else if (currentUser != null && !(currentUser instanceof Registrar)) {
			throw new IllegalArgumentException();
		}
	}
}