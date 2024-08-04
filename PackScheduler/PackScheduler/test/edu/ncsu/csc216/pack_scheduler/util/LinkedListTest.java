package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Tests the custom linkedlist implementation
 * 
 * @author Jason Maher
 *
 */
class LinkedListTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedList#LinkedList()}.
	 */
	@Test
	void testLinkedList() {
		try {
			LinkedList<Object> list = new LinkedList<Object>();
			assertEquals(0, list.size());
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedList#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		LinkedList<String> list = new LinkedList<String>();
		String str1 = "apples";
		String str2 = "peach";
		String str3 = "banana";
		String str4 = "iceberg lettuce";
		String str5 = "mango";

		// adding to the start of list
		try {
			list.add(0, str1);
			assertEquals(1, list.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		}

		// adding a duplicate
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> list.add(1, str1));
		assertEquals("list already contains element", e1.getMessage());

		// adding to the end of list
		try {
			list.add(1, str2);
			assertEquals(2, list.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		}

		// adding to beginning of the list again
		try {
			list.add(0, str3);
			assertEquals(3, list.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		}

		// adding in middle of list
		try {
			list.add(2, str4);
			assertEquals(4, list.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		}

		// adding past capacity of list
		try {
			list.add(0, str5);
			assertEquals(5, list.size());
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		}

		// trying to add at a negative index such that it throws
		// IllegalArgumentException
		try {
			list.add(-1, str5);
			fail();
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, list.size());
		}

		// trying to add at a negative index such that it throws
		// IndexOutOfBoundsException
		try {
			String h = "humming bird";
			list.add(-1, h);
			fail();
		} catch (NullPointerException e) {
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(5, list.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedList#remove(int)}.
	 */
	@Test
	void testRemoveInt() {
		LinkedList<String> list = new LinkedList<String>();
		String str1 = "apple";
		String str2 = "peach";
		String str3 = "banana";
		String str4 = "iceberg lettuce";
		String str5 = "mango";
		String str6 = "orange";
		String str7 = "berry";
		list.add(str1);
		list.add(str2);
		list.add(str3);
		list.add(str4);
		list.add(str5);
		list.add(str6);
		list.add(str7);
		assertEquals(7, list.size());

		// removing elements from the end of the list
		try {
			assertEquals("berry", list.remove(6));
			assertEquals(6, list.size());
		} catch (NullPointerException e) {
			fail();
		}
		// removing from start of the list
		try {
			assertEquals("apple", list.remove(0));
			assertEquals(5, list.size());
		} catch (NullPointerException e) {
			fail();
		}
		// removing from the middle of list
		try {
			assertEquals("iceberg lettuce", list.remove(2));
			assertEquals(4, list.size());
		} catch (NullPointerException e) {
			fail();
		}
		// trying to get index out of bounds exception
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedList#get(int)}.
	 */
	@Test
	void testGetInt() {
		LinkedList<String> list = new LinkedList<String>();
		String str1 = "apple";
		String str2 = "peach";
		String str3 = "banana";
		list.add(0, str1);
		list.add(0, str2);
		list.add(0, str3);
		assertEquals(3, list.size());

		assertEquals("banana", list.get(0));
		assertEquals("peach", list.get(1));
		assertEquals("apple", list.get(2));

		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedList#set(int, java.lang.Object)}.
	 */
	@Test
	void testSetIntE() {
		LinkedList<String> list = new LinkedList<String>();
		String apple = "apple";
		String peach = "peach";
		String banana = "banana";
		String lettuce = "iceberg lettuce";
		String mango = "mango";
		String orange = "orange";
		String berry = "berry";
		list.add(0, apple);
		list.add(1, peach);
		list.add(2, banana);
		list.add(3, lettuce);
		list.add(4, mango);
		list.add(5, orange);
		list.add(6, berry);
		assertEquals(7, list.size());

		assertEquals("apple", list.get(0));
		list.set(0, "kiwi");
		assertEquals("kiwi", list.get(0));
		assertEquals("peach", list.get(1));

		list.set(6, "dragonfruit");
		assertEquals("dragonfruit", list.get(6));

		// set an element that already exists
		String n = null;
		String b = "banana";
		Exception e1 = assertThrows(NullPointerException.class, () -> list.set(1, n));
		assertEquals("cannot set objects to null", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> list.set(1, b));
		assertEquals("cannot change to element, element already exists within the list", e2.getMessage());

		try {
			list.set(-1, "daisies");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(7, list.size());
		}

	}

	@Test
	void testIterator() {
		LinkedList<String> list = new LinkedList<String>();
		String apple = "apple";
		String peach = "peach";
		String banana = "banana";
		String lettuce = "iceberg lettuce";
		String mango = "mango";
		String orange = "orange";
		String berry = "berry";
		list.add(0, apple);
		list.add(1, peach);
		list.add(2, banana);
		list.add(3, lettuce);
		list.add(4, mango);
		list.add(5, orange);
		list.add(6, berry);
		assertEquals(7, list.size());
		ListIterator<String> iterator1 = list.listIterator(2);
		assertEquals("peach", iterator1.previous());
		assertEquals("apple", iterator1.previous());
		Exception e1 = assertThrows(NoSuchElementException.class, () -> iterator1.previous());
		assertEquals("previous is null", e1.getMessage());

		ListIterator<String> iterator2 = list.listIterator(7);
		Exception e2 = assertThrows(NoSuchElementException.class, () -> iterator2.next());
		assertEquals("next is null", e2.getMessage());
	}

	/**
	 * tests second constructor
	 */
	@Test
	void testListNode() {
		LinkedList<String> list = new LinkedList<String>();
		LinkedList<String>.ListNode<String> node = list.new ListNode<>("Banana", null, null);
		assertEquals(node.data, "Banana");
	}
}
