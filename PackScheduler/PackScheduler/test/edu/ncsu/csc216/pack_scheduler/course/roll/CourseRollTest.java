package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
import org.junit.jupiter.api.Test;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the course roll class.
 * 
 * @author Jason Maher
 * @author Sachi Vyas
 *
 */
public class CourseRollTest {
	
	/** Creating an instance of the student directory class */
	private StudentDirectory sd;
	
	/** Test student first name valid */
	public static final String FIRST = "Jason";
	/** Test student last name valid */
	public static final String LAST = "Maher";
	/** Test student id valid */
	public static final String ID = "jlmaher";
	/** Test student email valid */
	public static final String EMAIL = "jlmaher@ncsu.edu";
	/** Test student email valid */
	public static final String PASSWORD = "hashedpassword";
	/** Test student max credits valid */
	public static final int CREDITS = 18;

	//Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	//CourseRoll roll = new CourseRoll(10); //Update as below
	//CourseRoll roll = c.getCourseRoll();
	/** Creating a new student directory */
	public void before() {
		
		sd = new StudentDirectory();
		sd.loadStudentsFromFile("test-files/student_records.txt");
		sd.addStudent(FIRST, LAST, ID, EMAIL, PASSWORD, PASSWORD, CREDITS);
		sd.addStudent("Grace", "Hopper", "ghopper", EMAIL, PASSWORD, PASSWORD, CREDITS);
	}
	/**
	 * Tests the setEnrollmentCap() method
	 */
	@Test
	void testSetEnrollmemtCapError() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		assertThrows(IllegalArgumentException.class, 
				() -> new CourseRoll(c, 500));
		
		CourseRoll cr = new CourseRoll(c, 50);
		cr.setEnrollmentCap(51);
		assertEquals(51, cr.getEnrollmentCap());
		Student jason = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
		Student s1 = new Student("a", LAST, ID, EMAIL, PASSWORD);
		Student s2 = new Student("b", LAST, ID, EMAIL, PASSWORD);
		Student s3 = new Student("c", LAST, ID, EMAIL, PASSWORD);
		Student s4 = new Student("d", LAST, ID, EMAIL, PASSWORD);
		Student s5 = new Student("e", LAST, ID, EMAIL, PASSWORD);
		Student s6 = new Student("f", LAST, ID, EMAIL, PASSWORD);
		Student s7 = new Student("g", LAST, ID, EMAIL, PASSWORD);
		Student s8 = new Student("h", LAST, ID, EMAIL, PASSWORD);
		Student s9 = new Student("i", LAST, ID, EMAIL, PASSWORD);
		Student s10 = new Student("j", LAST, ID, EMAIL, PASSWORD);
		cr.enroll(s1);
		cr.enroll(s2);
		cr.enroll(s3);
		cr.enroll(s4);
		cr.enroll(s5);
		cr.enroll(s6);
		cr.enroll(s7);
		cr.enroll(s8);
		cr.enroll(s9);
		cr.enroll(s10);
		cr.enroll(jason);
		assertThrows(IllegalArgumentException.class, () -> cr.setEnrollmentCap(10));
	}
	
	/**
	 * Tests the constructor of CourseRoll.java
	 */
	@Test
	void testConstructor() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll = new CourseRoll(c, 75);
		assertEquals(courseRoll.getEnrollmentCap(), 75);
		assertEquals(courseRoll.getOpenSeats(), 75);
		
	}
	
	/**
	 * Tests the enroll student method
	 */
	@Test
	void testEnrollAndWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll = new CourseRoll(c, 10);
		Student jason = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
		Student s1 = new Student("a", LAST, ID, EMAIL, PASSWORD);
		Student s2 = new Student("b", LAST, ID, EMAIL, PASSWORD);
		Student s3 = new Student("c", LAST, ID, EMAIL, PASSWORD);
		Student s4 = new Student("d", LAST, ID, EMAIL, PASSWORD);
		Student s5 = new Student("e", LAST, ID, EMAIL, PASSWORD);
		Student s6 = new Student("f", LAST, ID, EMAIL, PASSWORD);
		Student s7 = new Student("g", LAST, ID, EMAIL, PASSWORD);
		Student s8 = new Student("h", LAST, ID, EMAIL, PASSWORD);
		Student s9 = new Student("i", LAST, ID, EMAIL, PASSWORD);
		Student s10 = new Student("j", LAST, ID, EMAIL, PASSWORD);
		courseRoll.enroll(jason);
		assertEquals(courseRoll.getEnrollmentCap(), 10);
		assertEquals(courseRoll.getOpenSeats(), 9);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> courseRoll.enroll(null));
		assertEquals(null, e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> courseRoll.enroll(jason));
		assertEquals("Cannot enroll student, jlmaher is already enrolled.", e2.getMessage());
		courseRoll.enroll(s1);
		courseRoll.enroll(s2);
		courseRoll.enroll(s3);
		courseRoll.enroll(s4);
		courseRoll.enroll(s5);
		courseRoll.enroll(s6);
		courseRoll.enroll(s7);
		courseRoll.enroll(s8);
		courseRoll.enroll(s9);
		assertTrue(courseRoll.canEnroll(s10));
		courseRoll.enroll(s10);
		assertEquals(courseRoll.getNumberOnWaitlist(), 1);
		
		courseRoll.drop(s9);
		assertEquals(courseRoll.getNumberOnWaitlist(), 0);
	}
	
	/**
	 * Tests the drop() method
	 */
	@Test
	void testDrop() {
	
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll = new CourseRoll(c, 10);
		Student jason = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
		Student s1 = new Student("Wendy", "William", "wWilliams", EMAIL, PASSWORD);
		Student s2 = new Student("Jonathan", "Smith", "jsmith", EMAIL, PASSWORD);
		Student s3 = new Student("Jon", "Wynns", "jWynns", EMAIL, PASSWORD);
		Student s4 = new Student("Joana", "Fulcher", "jfulcher", EMAIL, PASSWORD);
		Student s5 = new Student("Sachi", "Vyas", "svyw", EMAIL, PASSWORD);
		Student s6 = new Student("Blair", "Dorothy", "bdoroty", EMAIL, PASSWORD);
		Student s7 = new Student("Taylor", "Lewis", "tlweis", EMAIL, PASSWORD);
		Student s8 = new Student("Louis", "Lee", "llee", EMAIL, PASSWORD);
		Student s9 = new Student("Robby", "Perez", "rperez", "rperez@ncsu.edu", "pw1");
		Student s10 = new Student("Rob", "Pere", "rpere", "rperez@ncsu.edu", "pw1");
		Student s11 = new Student("Robbbb", "Perre", "rperee", "rpereze@ncsu.edu", "pw1");
		
		courseRoll.enroll(jason);
		assertEquals(courseRoll.getOpenSeats(), 9);
		courseRoll.drop(jason);
		assertEquals(courseRoll.getOpenSeats(), 10);
		assertThrows(IllegalArgumentException.class, () -> courseRoll.drop(null));
		
		courseRoll.canEnroll(jason);
		assertEquals(10, courseRoll.getEnrollmentCap());
		try {
			courseRoll.canEnroll(null);
		} catch (IllegalArgumentException e) {
			//no need
		}
		
		courseRoll.enroll(jason);
		courseRoll.enroll(s1);
		assertEquals(courseRoll.getOpenSeats(), 8);
		courseRoll.enroll(s2);
		courseRoll.enroll(s3);
		courseRoll.enroll(s4);
		courseRoll.enroll(s5);
		courseRoll.enroll(s6);
		courseRoll.enroll(s7);
		courseRoll.enroll(s8);
		assertEquals(courseRoll.getOpenSeats(), 1);
		courseRoll.enroll(s9);
		
		try {
			courseRoll.enroll(s10);
			assertEquals(10, courseRoll.getEnrollmentCap());
			assertEquals(0, courseRoll.getOpenSeats());
			assertEquals(1, courseRoll.getNumberOnWaitlist());
		} catch (IllegalArgumentException e) {
			fail();
		}
		courseRoll.drop(jason);
		assertEquals(0, courseRoll.getOpenSeats());
		courseRoll.drop(s1);
		assertEquals(1, courseRoll.getOpenSeats());
		courseRoll.enroll(s11);
		try {
			assertEquals(0, courseRoll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			//just catch it
		}		
		
	}
	
	/**
	 * Testing a test from jenkins which checks if the course roll has open seats and then it can enroll
	 * the student from the waitlist
	 */
	@Test
	void testWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll = new CourseRoll(c, 10);

		Student s1 = new Student("Wendy", "William", "wWilliams", EMAIL, PASSWORD);
		Student s2 = new Student("Jonathan", "Smith", "jsmith", EMAIL, PASSWORD);
		Student s3 = new Student("Jon", "Wynns", "jWynns", EMAIL, PASSWORD);
		Student s4 = new Student("Joana", "Fulcher", "jfulcher", EMAIL, PASSWORD);
		Student s5 = new Student("Sachi", "Vyas", "svyw", EMAIL, PASSWORD);
		Student s6 = new Student("Blair", "Dorothy", "bdoroty", EMAIL, PASSWORD);
		Student s7 = new Student("Taylor", "Lewis", "tlweis", EMAIL, PASSWORD);
		Student s8 = new Student("Louis", "Lee", "llee", EMAIL, PASSWORD);
		Student s9 = new Student("Robby", "Perez", "rperez", "rperez@ncsu.edu", "pw1");
		Student s10 = new Student("Rob", "Pere", "rpere", "rperez@ncsu.edu", "pw1");
		Student s11 = new Student("Robbbb", "Perre", "rperee", "rpereze@ncsu.edu", "pw1");
		
		courseRoll.enroll(s1);
		courseRoll.enroll(s2);
		courseRoll.enroll(s3);
		courseRoll.enroll(s4);
		courseRoll.enroll(s5);
		courseRoll.enroll(s6);
		courseRoll.enroll(s7);
		courseRoll.enroll(s8);
		courseRoll.enroll(s9);
		courseRoll.enroll(s10);
		courseRoll.enroll(s11);
		assertEquals(0, courseRoll.getOpenSeats());
		assertEquals(1, courseRoll.getNumberOnWaitlist());
		courseRoll.drop(s1);
		assertEquals(0, courseRoll.getOpenSeats());
		assertEquals(0, courseRoll.getNumberOnWaitlist());
		Schedule schedule = s11.getSchedule();
		String[][] sa = schedule.getScheduledCourses();
		assertEquals(1, sa.length);
		
	}
	
	/**
	 * Testing the student waitlist issue on jenkins
	 */
	@Test
	void studentWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll = new CourseRoll(c, 10);
		
		Student s1 = new Student("Wendy", "William", "wWilliams", EMAIL, PASSWORD);
		Student s2 = new Student("Jonathan", "Smith", "jsmith", EMAIL, PASSWORD);
		Student s3 = new Student("Jon", "Wynns", "jWynns", EMAIL, PASSWORD);
		Student s4 = new Student("Joana", "Fulcher", "jfulcher", EMAIL, PASSWORD);
		Student s5 = new Student("Sachi", "Vyas", "svyw", EMAIL, PASSWORD);
		Student s6 = new Student("Blair", "Dorothy", "bdoroty", EMAIL, PASSWORD);
		Student s7 = new Student("Taylor", "Lewis", "tlweis", EMAIL, PASSWORD);
		Student s8 = new Student("Louis", "Lee", "llee", EMAIL, PASSWORD);
		Student s9 = new Student("Robby", "Perez", "rperez", "rperez@ncsu.edu", "pw1");
		Student s10 = new Student("Rob", "Pere", "rpere", "rperez@ncsu.edu", "pw1");
//		Student s11 = new Student("Robbbb", "Perre", "rperee", "rpereze@ncsu.edu", "pw1");
		
		Student s12 = new Student("Wend", "Willia", "wWilliam", EMAIL, PASSWORD);
		Student s13 = new Student("Jonatha", "Smit", "jsmit", EMAIL, PASSWORD);
		Student s14 = new Student("Jo", "Wynn", "jWynn", EMAIL, PASSWORD);
		Student s15 = new Student("Joan", "Fulche", "jfulche", EMAIL, PASSWORD);
		Student s16 = new Student("Sach", "Vya", "svy", EMAIL, PASSWORD);
		Student s17 = new Student("Blai", "Doroth", "bdorot", EMAIL, PASSWORD);
		Student s18 = new Student("Taylo", "Lewi", "tlwei", EMAIL, PASSWORD);
		Student s19 = new Student("Loui", "Le", "lle", EMAIL, PASSWORD);
		Student s20 = new Student("Robb", "Pere", "rper", "rperez@ncsu.edu", "pw1");
		Student s21 = new Student("Rob", "Pere", "rpeuuu", "rperez@ncsu.edu", "pw1");
//		Student s22 = new Student("Robbb", "Perr", "rpere", "rpereze@ncsu.edu", "pw1");
		
		courseRoll.enroll(s1);
		courseRoll.enroll(s2);
		courseRoll.enroll(s3);
		courseRoll.enroll(s4);
		courseRoll.enroll(s5);
		courseRoll.enroll(s6);
		courseRoll.enroll(s7);
		courseRoll.enroll(s8);
		courseRoll.enroll(s9);
		courseRoll.enroll(s10);
		
		courseRoll.enroll(s12);
		courseRoll.enroll(s13);
		courseRoll.enroll(s14);
		courseRoll.enroll(s15);
		courseRoll.enroll(s16);
		courseRoll.enroll(s17);
		courseRoll.enroll(s18);
		courseRoll.enroll(s19);
		courseRoll.enroll(s20);
		courseRoll.enroll(s21);
		
		assertEquals(10, courseRoll.getNumberOnWaitlist());
		
		courseRoll.drop(s12);
		
		assertEquals(9, courseRoll.getNumberOnWaitlist());
	}

	

}
