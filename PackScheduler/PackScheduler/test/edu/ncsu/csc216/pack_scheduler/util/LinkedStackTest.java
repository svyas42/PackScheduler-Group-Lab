package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Tests the LinkedStack class
 * @author Sachi Vyas
 * @param <E> elements in the linked stack
 */
public class LinkedStackTest<E> {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#LinkedStack(int)}.
	 */
	@Test
	void testLinkedStack() {
		LinkedStack<E> array = new LinkedStack<E>(20);
		assertTrue(array instanceof LinkedStack);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#push(java.lang.Object)}.
	 */
	@Test
	void testPush() {
		LinkedStack<String> array = new LinkedStack<String>(10);
		String str1 = "apple";
		String str2 = "banana";
		String str3 = "orange";
		String str4 = "pear";
		
		try {
			array.push(str1);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			array.push(str2);
			array.push(str3);
			array.push(str4);
			assertEquals(4, array.size());
		} catch (IllegalArgumentException e) {
			fail();
		}	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#pop()}.
	 */
	@Test
	void testPop() {
		LinkedStack<String> array = new LinkedStack<String>(10);
		String str1 = "apple";
		String str2 = "banana";
		String str3 = "orange";
		String str4 = "pear";
		String str5 = "pumkin";
		array.push(str1);
		array.push(str2);
		array.push(str3);
		array.push(str4);
		array.push(str5);
		assertEquals(5, array.size());
		
		//popping just one element
		try {
			array.pop();
			assertEquals(4, array.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		//popping multiple elements
		try {
			array.pop();
			array.pop();
			array.pop();
			assertEquals(1, array.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		//popping last element
		try {
			array.pop();
			assertEquals(0, array.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		LinkedStack<String> array = new LinkedStack<String>(10);
		String str1 = "apple";
		String str2 = "banana";
		assertTrue(array.isEmpty());
		array.push(str1);
		array.push(str2);
		assertFalse(array.isEmpty());
		array.pop();
		array.pop();
		assertTrue(array.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#size()}.
	 */
	@Test
	void testSize() {
		LinkedStack<String> array = new LinkedStack<String>(10);
		String str1 = "apple";
		String str2 = "banana";
		String str3 = "orange";		
		assertEquals(0, array.size());
		array.push(str1);
		assertEquals(1, array.size());
		array.push(str2);
		assertEquals(2, array.size());
		array.push(str3);
		assertEquals(3, array.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#setCapacity(int)}.
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#getCapacity()}.
	 */
	@Test
	void testSetAndGetCapacity() {
		LinkedStack<String> array = new LinkedStack<String>(10);
		LinkedStack<String> array1 = new LinkedStack<String>(10);
		LinkedStack<String> array2 = new LinkedStack<String>(10);
		String str1 = "apple";
		String str2 = "banana";
		String str3 = "orange";
		String str4 = "pear";
		String str5 = "pumkin";
		
		try {
			array2.setCapacity(7);
			assertEquals(7, array2.getCapacity());
		} catch (IllegalArgumentException e) {
			fail();
		}
		array.push(str1);
		array.push(str2);
		array.push(str3);
		array.push(str4);
		array.push(str5);
		
		array1.push(str1);
		array1.push(str2);
		array1.push(str3);
		array1.push(str4);
		array1.push(str5);
		
		try {
			array.setCapacity(7);
			assertEquals(7, array.getCapacity());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			array1.setCapacity(9);
			assertEquals(9, array1.getCapacity());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			array.setCapacity(5);
			assertEquals(5, array.getCapacity());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	

//	/**
//	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#getCapacity()}.
//	 */
//	@Test
//	void testGetCapacity() {
//		fail("Not yet implemented"); // TODO
//	}

}
