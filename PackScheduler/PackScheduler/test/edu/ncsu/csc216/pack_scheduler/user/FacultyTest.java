/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Faculty class
 * @author Sachi Vyas
 *
 */
public class FacultyTest {
	
	/** Test student first name valid */
	public static final String FIRST = "first";
	/** Test student last name valid */
	public static final String LAST = "last";
	/**Test student id valid */
	public static final String ID = "id";
	/** Test student email valid */
	public static final String EMAIL = "email@ncsu.edu";
	/** Test student email valid */
	public static final String PASSWORD = "hashedpassword";
	/** Test student max credits valid */
	public static final int MAX_COURSES = 3;

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#hashCode()}.
	 */
	@Test
	void testHashCode() {
		User s1 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s2 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s3 = new Faculty("Brie", LAST, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s4 = new Faculty(FIRST, "Larson", ID, EMAIL, PASSWORD, MAX_COURSES);
		User s5 = new Faculty(FIRST, LAST, "BLarson", EMAIL, PASSWORD, MAX_COURSES);
		User s6 = new Faculty(FIRST, LAST, ID, "brie@ncsu.edu", PASSWORD, MAX_COURSES);
		User s7 = new Faculty(FIRST, LAST, ID, EMAIL, "larson$#1!", MAX_COURSES);
		
		assertEquals(s1.hashCode(), s2.hashCode());
		
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		User s1 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s2 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s3 = new Faculty("Brie", LAST, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s4 = new Faculty(FIRST, "Larson", ID, EMAIL, PASSWORD, MAX_COURSES);
		User s5 = new Faculty(FIRST, LAST, "BLarson", EMAIL, PASSWORD, MAX_COURSES);
		User s6 = new Faculty(FIRST, LAST, ID, "brie@ncsu.edu", PASSWORD, MAX_COURSES);
		User s7 = new Faculty(FIRST, LAST, ID, EMAIL, "larson$#1!", MAX_COURSES);
		
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#Faculty(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	void testFaculty() {
		Faculty s = new Faculty("Zoe", "Wades", "zwades", "zoe@ncsu.edu", "Wades$!42", 2);
		assertEquals("Zoe", s.getFirstName());
		assertEquals("Wades", s.getLastName());
		assertEquals("zwades", s.getId());
		assertEquals("zoe@ncsu.edu", s.getEmail());
		assertEquals("Wades$!42", s.getPassword());
		assertEquals(2, s.getMaxCourses());
		assertEquals(0, s.getSchedule().getNumScheduledCourses());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#setMaxCourses(int)}.
	 */
	@Test
	void testSetMaxCourses() {
		Faculty s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAX_COURSES);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setMaxCourses(0));
		assertEquals("Invalid max courses", e1.getMessage());
		assertEquals(MAX_COURSES, s.getMaxCourses());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s.setMaxCourses(19));
		assertEquals("Invalid max courses", e2.getMessage());
		assertEquals(MAX_COURSES, s.getMaxCourses());
	}

//	/**
//	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#getMaxCourses()}.
//	 */
//	@Test
//	void testGetMaxCourses() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#toString()}.
	 */
	@Test
	void testToString() {
		Faculty s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAX_COURSES);
		String valid = FIRST + "," + LAST + "," + ID + "," + EMAIL + "," + PASSWORD + "," + MAX_COURSES;
		assertEquals(valid, s.toString());
	}

}
