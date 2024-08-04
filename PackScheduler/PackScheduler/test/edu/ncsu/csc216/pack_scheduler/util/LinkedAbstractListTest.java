/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the LinkedAbstractList method
 * @author Sachi Vyas
 */
public class LinkedAbstractListTest {
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#LinkedAbstractList(int)}.
	 */
	@Test
	void testLinkedAbstractList() {
		try {
			LinkedAbstractList<Object> list = new LinkedAbstractList<Object>(10);
			assertEquals(0, list.size());
		} catch (Exception e) {
			fail();
		}
		
		try {
			LinkedAbstractList<Object> list = new LinkedAbstractList<Object>(0);
			assertEquals(0, list.size());
		} catch (IllegalArgumentException e) {
			fail();
		}

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#get(int)}.
	 */
	@Test
	void testGetInt() {
		LinkedAbstractList<Object> array = new LinkedAbstractList<Object>(7);
		String str1 = "apple";
		String str2 = "peach";
		String str3 = "banana";
		array.add(str1);
		array.add(str2);
		array.add(str3);
		assertEquals(3, array.size());
		
		assertEquals("apple", array.get(0));
		assertEquals("peach", array.get(1));
		assertEquals("banana", array.get(2));
		
		try {
			array.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, array.size());
		}
		
		try {
			array.get(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, array.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#set(int, java.lang.Object)}.
	 */
	@Test
	void testSetIntE() {
		LinkedAbstractList<Object> array = new LinkedAbstractList<Object>(7);
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
		try {
			assertEquals("apple", array.get(0));
			array.set(0, str2);
			assertEquals("banana", array.get(0));
			assertEquals("orange", array.get(1));			
		} catch (IllegalArgumentException e) {
			//fail();
		}
		
		try {
			assertEquals("orange", array.get(5));
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
			assertEquals("apple", array.get(0));
		}
		
		try {
			array.set(5, str7);
			assertEquals("berry", array.get(5));
			assertEquals(7, array.size());
		} catch (IllegalArgumentException e) {
			//fail();
		}
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		LinkedAbstractList<Object> array = new LinkedAbstractList<Object>(8);
		String str1 = "apples";
//		String str2 = "peach";
//		String str3 = "banana";
//		String str4 = "iceberg lettuce";
		String str5 = "mango";
//		String str6 = "berries";
		String str7 = "jackfruit";
//		String str8 = "carrots";
		assertEquals(0, array.size());
		//adding to the start of list
		try {
			array.add(0, str1);
			assertEquals(1, array.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			//fail();
		}
		
		//trying to a add null object
		try {
			String str9 = null;
			array.add(0, str9);
			fail();
		} catch (NullPointerException e) {
			assertEquals(1, array.size());
		}
		
		//adding at a negative index
		try {
			array.add(-1, str5);
			fail();
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			//fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, array.size());
		}
		
		//adding at the capacity of the list
		try {
			String s = "grapes";
			array.add(8, s);
			assertEquals(8, array.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			//fail();
		}
		
		//try to add null element
		try {
			String str9 = null;
			array.add(0, str9);
			fail();
		} catch (NullPointerException e) {
			assertEquals(1, array.size());
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
			//fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, array.size());
		}
		
		//adding past capacity of list
		try {
			array.add(0, str5);
			assertEquals(2, array.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		try {
			array.add(3, str7);
			assertEquals(3, array.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			//fail();
		}

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#remove(int)}.
	 */
	@Test
	void testRemoveInt() {
		LinkedAbstractList<Object> array = new LinkedAbstractList<Object>(7);
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
		
		//removing elements from the beginning of the list
		try {
			assertEquals("apple", array.remove(0));
			assertEquals(5, array.size());
		} catch (NullPointerException e) {
			fail();
		}
		
		//remove item beyond index (negative number)
		try {
			array.remove(-3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(5, array.size());
		}
		
		try {
			assertEquals("iceberg lettuce", array.remove(2));
			assertEquals(4, array.size());
		} catch (NullPointerException e) {
			fail();
		}
	}

	/**
	 * Test method for setCapacity() and getCapacity().
	 */
	@Test
	void testSetAndGetCapacity() {
		LinkedAbstractList<Object> array = new LinkedAbstractList<Object>(12);
		String str1 = "apple";
		String str2 = "peach";
		String str3 = "banana";
		String str4 = "iceberg lettuce";
		String str5 = "mango";
		String str6 = "orange";
		
		array.add(str1);
		array.add(str2);
		array.add(str3);
		array.add(str4);
		array.add(str5);
		array.add(str6);
		assertEquals(6, array.size());
		assertEquals(12, array.getCapacity());
		
		array.setCapacity(14);
		assertEquals(14, array.getCapacity());
		try {
			array.setCapacity(5);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(14, array.getCapacity());
		}
		
		try {
			array.setCapacity(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(14, array.getCapacity());
		}
		
		try {
			array.setCapacity(-2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(14, array.getCapacity());
		}
	}


}
