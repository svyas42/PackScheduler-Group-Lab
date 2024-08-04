package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the ArrayList class
 * @author Sachi Vyas
 *
 */
public class ArrayListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#ArrayList()}.
	 */
	@Test
	void testArrayList() {
		try {
			ArrayList<Object> array = new ArrayList<Object>();
			assertEquals(0, array.size());
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		ArrayList<Object> array = new ArrayList<Object>();
		String str1 = "apples";
		String str2 = "peach";
		String str3 = "banana";
		String str4 = "iceberg lettuce";
		String str5 = "mango";
		String str6 = null;
		
		//adding to the start of list
		try {
			array.add(0, str1);
			assertEquals(1, array.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		}

		//adding to the end of list
		try {
			array.add(1, str2);
			assertEquals(2, array.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		//adding to beginning of the list again
		try {
			array.add(0, str3);
			assertEquals(3, array.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		//adding in middle of list
		try {
			array.add(2, str4);
			assertEquals(4, array.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		//adding past capacity of list
		try {
			array.add(0, str5);
			assertEquals(5, array.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		//try to add null element
		try {
			array.add(0, str6);
			fail();
		} catch (NullPointerException e) {
			assertEquals(5, array.size());
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		//trying to add at a negative index such that it throws IllegalArgumentException
		try {
			array.add(-1, str5);
			fail();
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, array.size());
		}
		
		//trying to add at a negative index such that it throws IndexOutOfBoundsException
		try {
			String h = "humming bird";
			array.add(-1, h);
			fail();
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(5, array.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#remove(int)}.
	 */
	@Test
	void testRemoveInt() {
		ArrayList<Object> array = new ArrayList<Object>();
		String str1 = "apple";
		String str2 = "peach";
		String str3 = "banana";
		String str4 = "iceberg lettuce";
		String str5 = "mango";
		String str6 = "orange";
		String str7 = "berry";
		array.add(str1);
		array.add(str2);
		array.add(str3);
		array.add(str4);
		array.add(str5);
		array.add(str6);
		array.add(str7);
		assertEquals(7, array.size());
		
		//removing elements from the end of the list
		try {
			assertEquals("berry", array.remove(6));
			assertEquals(6, array.size());
		} catch (NullPointerException e) {
			fail();
		}
		//removing from start of the list
		try {
			assertEquals("apple", array.remove(0));
			assertEquals(5, array.size());
		} catch (NullPointerException e) {
			fail();
		}
		//removing from the middle of list
		try {
			assertEquals("iceberg lettuce", array.remove(2));
			assertEquals(4, array.size());
		} catch (NullPointerException e) {
			fail();
		}
		//trying to get index out of bounds exception
		try {
			array.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, array.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#get(int)}.
	 */
	@Test
	void testGetInt() {
		ArrayList<Object> array = new ArrayList<Object>();
		String str1 = "apple";
		String str2 = "peach"; 
		String str3 = "banana";
		array.add(0, str1);
		array.add(0, str2);
		array.add(0, str3);
		assertEquals(3, array.size());
		
		assertEquals("banana", array.get(0));
		assertEquals("peach", array.get(1));
		assertEquals("apple", array.get(2));
		
		try {
			array.get(-1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(3, array.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#set(int, java.lang.Object)}.
	 */
	@Test
	void testSetIntE() {
		ArrayList<Object> array = new ArrayList<Object>();
		String str1 = "apple";
		String str2 = "peach"; 
		String str3 = "banana";
		String str4 = "iceberg lettuce";
		String str5 = "mango";
		String str6 = "orange";
		String str7 = "berry";
		array.add(0, str1);
		array.add(0, str2);
		array.add(0, str3);
		array.add(0, str4);
		array.add(0, str5);
		array.add(0, str6);
		array.add(0, str7);
		assertEquals(7, array.size());
		
		try {
			assertEquals("berry", array.get(0));
			array.set(0, str2);
			assertEquals("banana", array.get(0));
			assertEquals("orange", array.get(1));			
		} catch (IllegalArgumentException e) {
			//fail();
		}
		
		try {
			assertEquals("peach", array.get(5));
			array.set(5, str4);
			assertEquals(6, array.size());
			assertEquals("iceberg lettuce", array.get(5));
			assertEquals("peach", array.get(4));
		} catch (IllegalArgumentException e) {
			//fail();
		}
		
		try {
			String n = null;
			array.set(0, n);
			fail();
		} catch (NullPointerException e) {
			assertEquals(7, array.size());
			assertEquals("berry", array.get(0));
		}
		
		try {
			array.set(-1, str5);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(7, array.size());
		}
		
		try {
			array.set(5, str6);
			assertEquals("orange", array.get(5));
			assertEquals(6, array.size());
		} catch(IllegalArgumentException e) {
			//fail();
		}
		
		
	}

}
