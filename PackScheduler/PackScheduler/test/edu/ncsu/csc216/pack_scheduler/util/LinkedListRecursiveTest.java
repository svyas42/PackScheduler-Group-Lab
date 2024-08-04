package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the linked list recursive test
 * @author Sachi Vyas
 *
 */
public class LinkedListRecursiveTest {
	/** String object to add to the list */
	String a = "a";
	/** String object to add to the list */
	String b = "b";
	/** String object to add to the list */
	String c = "c";
	/** String object to add to the list */
	String d = "d";
	/** String object to add to the list */
	String f = "f";

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#LinkedListRecursive()}.
	 */
	@Test
	void testLinkedListRecursive() {
		try {
			LinkedListRecursive<Object> r = new LinkedListRecursive<Object>();
			assertEquals(0, r.size());
		} catch(Exception e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		LinkedListRecursive<Object> r = new LinkedListRecursive<Object>();
		assertTrue(r.isEmpty());
		r.add(a);
		assertFalse(r.isEmpty());
		r.remove(a);
		assertTrue(r.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#add(java.lang.Object)}.
	 */
	@Test
	void testAddE() {
		LinkedListRecursive<Object> r = new LinkedListRecursive<Object>();
		//trying to add to empty list
		try {
			r.add(0, a);
			assertEquals(1, r.size());
			assertEquals(a, r.get(0));
		} catch(Exception e) {
			fail();
		}
		
		//adding to the front of the list
		try {
			r.add(b);
			r.add(c);
			r.add(d);
			assertEquals(4, r.size());
		} catch(Exception e) {
			fail();
		}
		
		try {
			r.add(b);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(4, r.size());
		}
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		LinkedListRecursive<Object> r = new LinkedListRecursive<Object>();
		try {
			r.add(0, a);
			assertEquals(1, r.size());
			assertEquals(a, r.get(0));
		} catch(Exception e) {
			fail();
		}
		
		//valid add to front
		try{
			r.add(0, b);
			assertEquals(2, r.size());
			assertEquals(b, r.get(0));
			assertEquals(a, r.get(1));
		} catch(Exception e){
			fail();
		}
		
		//adding to the end
		try {
			r.add(2, c);
			assertEquals(3, r.size());
			assertEquals(c, r.get(2));
		} catch(Exception e) {
			//fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#get(int)}.
	 */
	@Test
	void testGet() {

		LinkedListRecursive<Object> r = new LinkedListRecursive<Object>();
		try {
			r.get(-1);
			fail();
		} catch(Exception e) {
			assertEquals(0, r.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveE() {
		LinkedListRecursive<Object> r = new LinkedListRecursive<Object>();
		r.add(a);
		r.add(b);
		r.add(c);
		r.add(d);
	
		assertTrue(r.remove(a));
		assertTrue(r.remove(c));
		assertFalse(r.remove(f));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(int)}.
	 */
	@Test
	void testRemoveInt() {
		LinkedListRecursive<Object> r = new LinkedListRecursive<Object>();
		try {
			r.remove(-1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, r.size());
		}
		
		r.add(a);
		r.add(b);
		r.add(c);
		r.add(d);
		r.add(f);
		
		//removing from the end of the list
		assertEquals(f, r.remove(4));
		//remove from the front
		assertEquals(a, r.remove(0));
		//from the middle
		assertEquals(c, r.remove(1));
		assertEquals(d, r.remove(1));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#set(int, java.lang.Object)}.
	 */
	@Test
	void testSet() {
		LinkedListRecursive<Object> r = new LinkedListRecursive<Object>();
		r.add(a);
		r.add(b);
		r.add(c);
		
		assertEquals(b, r.set(1, d));
		try {
			r.set(-1, f);
		} catch(IndexOutOfBoundsException e) {
			assertEquals(3, r.size());
		}
		
		try {
			r.set(5, f);
		} catch(IndexOutOfBoundsException e) {
			assertEquals(3, r.size());
		}
	}

//	/**
//	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#contains(java.lang.Object)}.
//	 */
//	@Test
//	void testContains() {
//		fail("Not yet implemented"); // TODO
//	}

}
