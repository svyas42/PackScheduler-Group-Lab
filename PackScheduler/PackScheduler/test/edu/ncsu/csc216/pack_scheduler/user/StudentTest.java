package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Testing the student.java file with valid and invalid inputs
 * @author Sachi Vyas
 *
 */
public class StudentTest {
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
	public static final int CREDITS = 18;
	
	/**
	 * Tests that hashCode works correctly is generated appropriately
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, CREDITS);
		User s2 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, CREDITS);
		User s3 = new Student("Brie", LAST, ID, EMAIL, PASSWORD, CREDITS);
		User s4 = new Student(FIRST, "Larson", ID, EMAIL, PASSWORD, CREDITS);
		User s5 = new Student(FIRST, LAST, "BLarson", EMAIL, PASSWORD, CREDITS);
		User s6 = new Student(FIRST, LAST, ID, "brie@ncsu.edu", PASSWORD, CREDITS);
		User s7 = new Student(FIRST, LAST, ID, EMAIL, "larson$#1!", CREDITS);
		User s8 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, 12);
		
		assertEquals(s1.hashCode(), s2.hashCode());
		
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}
	
	/**
	 * Testing to check if the all the valid inputs including max credits
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		Student s = new Student("Zoe", "Wades", "zwades", "zoe@ncsu.edu", "Wades$!42", 13);
		assertEquals("Zoe", s.getFirstName());
		assertEquals("Wades", s.getLastName());
		assertEquals("zwades", s.getId());
		assertEquals("zoe@ncsu.edu", s.getEmail());
		assertEquals("Wades$!42", s.getPassword());
		assertEquals(13, s.getMaxCredits());
	}
	
	/**
	 * Testing to check if the all the valid inputs excluding max credits
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		User s = new Student("Zoe", "Wades", "zwades", "zoe@ncsu.edu", "Wades$!42");
		assertEquals("Zoe", s.getFirstName());
		assertEquals("Wades", s.getLastName());
		assertEquals("zwades", s.getId());
		assertEquals("zoe@ncsu.edu", s.getEmail());
		assertEquals("Wades$!42", s.getPassword());
	}
	
	/**
	 * Tests the setter of the first name to check if the condition are met to be a first name
	 */
	@Test
	public void testSetFirstName() {
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, CREDITS);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setFirstName(null));
		assertEquals("Invalid first name", e1.getMessage());
		assertEquals(FIRST, s.getFirstName());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s.setFirstName(""));
		assertEquals("Invalid first name", e2.getMessage());
		assertEquals(FIRST, s.getFirstName());			
	}
	
	/**
	 * Tests the setter of the last name to check if the condition are met to be a last name
	 */
	@Test
	public void testSetLastName() {
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, CREDITS);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setLastName(null));
		assertEquals("Invalid last name", e1.getMessage());
		assertEquals(LAST, s.getLastName());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s.setLastName(""));
		assertEquals("Invalid last name", e2.getMessage());
		assertEquals(LAST, s.getLastName());			
	}
	
	/**
	 * Tests the setter of the email to check if the conditions for a valid email are met
	 */
	@Test
	public void testSetEmail() {
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, CREDITS);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setEmail(null));
		assertEquals("Invalid email", e1.getMessage());
		assertEquals(EMAIL, s.getEmail());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s.setEmail(""));
		assertEquals("Invalid email", e2.getMessage());
		assertEquals(EMAIL, s.getEmail());
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> s.setEmail("email#ncsu.edu"));
		assertEquals("Invalid email", e3.getMessage());
		assertEquals(EMAIL, s.getEmail());
		Exception e4 = assertThrows(IllegalArgumentException.class, () -> s.setEmail("email@ncsuedu"));
		assertEquals("Invalid email", e4.getMessage());
		assertEquals(EMAIL, s.getEmail());
		Exception e5 = assertThrows(IllegalArgumentException.class, () -> s.setEmail("email.ncsu@edu"));
		assertEquals("Invalid email", e5.getMessage());
		assertEquals(EMAIL, s.getEmail());
	}
	
	/**
	 * Tests the setter of the password to check if the conditions for a valid password are met
	 */
	@Test
	public void testSetPassword() {
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, CREDITS);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setPassword(null));
		assertEquals("Invalid password", e1.getMessage());
		assertEquals(PASSWORD, s.getPassword());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s.setPassword(""));
		assertEquals("Invalid password", e2.getMessage());
		assertEquals(PASSWORD, s.getPassword());
	}
	
	/**
	 * Tests if the max credits are valid
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, CREDITS);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setMaxCredits(1));
		assertEquals("Invalid max credits", e1.getMessage());
		assertEquals(CREDITS, s.getMaxCredits());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s.setMaxCredits(19));
		assertEquals("Invalid max credits", e2.getMessage());
		assertEquals(CREDITS, s.getMaxCredits());
	}

	/**
	 * Tests that the equals method works for all Student fields.
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, CREDITS);
		User s2 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, CREDITS);
		User s3 = new Student("Brie", LAST, ID, EMAIL, PASSWORD, CREDITS);
		User s4 = new Student(FIRST, "Larson", ID, EMAIL, PASSWORD, CREDITS);
		User s5 = new Student(FIRST, LAST, "BLarson", EMAIL, PASSWORD, CREDITS);
		User s6 = new Student(FIRST, LAST, ID, "brie@ncsu.edu", PASSWORD, CREDITS);
		User s7 = new Student(FIRST, LAST, ID, EMAIL, "larson$#1!", CREDITS);
		User s8 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, 12);
		
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
	}
	
	/**
	 * Test toString() method to get the required output in the formatted string
	 */
	@Test
	public void testToString() {
		User s1 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
		String valid = FIRST + "," + LAST + "," + ID + "," + EMAIL + "," + PASSWORD + "," + CREDITS;
		assertEquals(valid, s1.toString());
	}
	
	/**
	 * Tests the compareTo() method to compare student records
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student("Georgia", "Smith", "gSmith", EMAIL, PASSWORD, CREDITS);
		Student s2 = new Student("Ben", "Smith", "gSmith", EMAIL, PASSWORD, CREDITS);
		Student s3 = new Student("Georgia", "Smith", "gSmith", EMAIL, PASSWORD, CREDITS);
		Student s4 = new Student("Georgia", "Walters", "eWalters", EMAIL, PASSWORD, CREDITS);
		Student s5 = new Student("Georgia", "Walters", "gWalters", EMAIL, PASSWORD, CREDITS);
		
		//Test the same student
		assertEquals(0, s1.compareTo(s1));
		//Test different students with identical information
		assertEquals(0, s1.compareTo(s3));
		//Test student with later last name returns a p+ integer
		assertTrue(s4.compareTo(s3) > 0);
		//Test student with prior last name returns a p- integer
		assertTrue(s3.compareTo(s4) < 0);
		//Test student with prior first name returns p- integer
		assertTrue(s2.compareTo(s3) < 0);
		//Test student with later first name returns a p+ integer
		assertTrue(s3.compareTo(s2) > 0);
		//Test student with same first name, last name, and prior id returns p- integer
		assertTrue(s4.compareTo(s5) < 0);
		//Test student with same first name, last name, and later id returns a p+ integer
		assertTrue(s5.compareTo(s4) > 0);
	}

}
