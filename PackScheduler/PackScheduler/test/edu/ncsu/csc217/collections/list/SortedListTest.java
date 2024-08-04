package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testing the SortedList Library
 * @author Sachi Vyas
 */
public class SortedListTest {
	
	/**
	 * Tests if the sorted list can be initialized and can be expanded beyond its initial length of 10
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		list.add("apple");
		assertEquals(1, list.size());
		list.add("banana");
		assertEquals(2, list.size());
		list.add("cherry");
		assertEquals(3, list.size());
		list.add("pears");
		assertEquals(4, list.size());
		list.add("orange");
		assertEquals(5, list.size());
		list.add("grape");
		assertEquals(6, list.size());
		list.add("plum");
		assertEquals(7, list.size());
		list.add("mango");
		assertEquals(8, list.size());
		list.add("watermelon");
		assertEquals(9, list.size());
		list.add("strawberry");
		assertEquals(10, list.size());
		list.add("raspberry");
		assertEquals(11, list.size());
	}
	
	/**
	 * Tests if we can add elements to the sorted list at various locations in the list as well as 
	 * null and duplicate elements
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//Test to add to front of list
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		
		//Test to add to the end of list
		list.add("plum");
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("plum", list.get(2));
		
		//Test to add in the middle of list
		list.add("mango");
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("mango", list.get(2));
		assertEquals("plum", list.get(3));
		
		//Trying to add null element to the list
		String elementNull = null;
		try {
			list.add(elementNull);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("mango", list.get(2));
			assertEquals("plum", list.get(3));
		}
		
		//Trying to add duplicate element to the list
		try {
			list.add("mango");
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("mango", list.get(2));
			assertEquals("plum", list.get(3));
		}
	}
	
	/**
	 * Tests trying to get items from the sorted list focusing on index values that could potentially throw an
	 * out of bounds exception
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		try {
			list.get(0);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		list.add("apple");
		list.add("pears");
		list.add("jackfruit");
		list.add("blueberries");
		
		try {
			list.get(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		try {
			assertEquals("blueberries", list.get(4));
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
	}
	
	/**
	 * Tests removing items from various indexes in the sorted list
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		//TODO Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//TODO Add some elements to the list - at least 4
		list.add("apple");
		list.add("pears");
		list.add("jackfruit");
		list.add("blueberries");
		list.add("lemon");
		list.add("strawberry");
		list.add("mango");
		
		//TODO Test removing an element at an index < 0
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(7, list.size());
		}
		
		//TODO Test removing an element at size
		try {
			assertEquals("mango", list.remove(7));
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(7, list.size());
		}
		
		//TODO Test removing a middle element
		try {
			list.remove(3);
			assertEquals(6, list.size());
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		//TODO Test removing the last element
		try {
			list.remove(5);
			assertEquals(5, list.size());
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		//TODO Test removing the first element
		try {
			list.remove(0);
			assertEquals(4, list.size());
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		//TODO Test removing the last element
		try {
			list.remove(3);
			assertEquals(3, list.size());
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
	}
	
	/**
	 * Testing the indexOf() function of the sorted list by trying to check valid and invalid indices
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		assertEquals(-1, list.indexOf("mango"));
		
		//TODO Add some elements
		list.add("apple");
		list.add("pears");
		list.add("jackfruit");
		list.add("blueberries");
		list.add("lemon");
		list.add("berries");
		assertEquals(6, list.size());
		
		//TODO Test various calls to indexOf for elements in the list and not in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(5, list.indexOf("pears"));
		assertEquals(4, list.indexOf("lemon"));
		assertEquals(-1, list.indexOf("banana"));
		assertEquals(-1, list.indexOf("strawberry"));
		
		//TODO Test checking the index of null
		try {
			list.indexOf(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(6, list.size());
		}
	}
	
	/**
	 * Testing to check if the sorted list is emptied correctly
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//TODO Add some elements
		list.add("apple");
		list.add("pears");
		list.add("jackfruit");
		list.add("blueberries");
		list.add("lemon");
		assertEquals(5, list.size());
		
		//TODO Clear the list
		list.clear();
		
		//TODO Test that the list is empty
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests to check if the list is empty and gets populated when we try to add some elements
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test that the list starts empty
		assertEquals(0, list.size());
		
		//TODO Add at least one element
		list.add("apple");
		list.add("pears");
		list.add("jackfruit");
		
		//TODO Check that the list is no longer empty
		assertEquals(3, list.size());
	}
	
	/**
	 * Tests the contains() method to check if the sorted list contains the specified elements
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test the empty list case
		assertFalse(list.contains("banana"));
		
		//TODO Add some elements
		list.add("apple");
		list.add("pears");
		list.add("jackfruit");
		list.add("honeydew");
		list.add("grapes");
		assertEquals(5, list.size());
		
		//TODO Test some true and false cases
		assertTrue(list.contains("apple"));
		assertTrue(list.contains("pears"));
		assertTrue(list.contains("jackfruit"));
	}
	
	/**
	 * Tests the elements of the sorted list for equality in both directions
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list1.add("apple");
		list1.add("pears");
		list1.add("jackfruit");
		list1.add("honeydew");
		list1.add("grapes");
		assertEquals(5, list1.size());
		
		list2.add("apple");
		list2.add("pears");
		list2.add("jackfruit");
		list2.add("honeydew");
		list2.add("grapes");
		assertEquals(5, list2.size());
		
		list3.add("carrots");
		list3.add("potato");
		list3.add("tomato");
		list3.add("coriander");
		assertEquals(4, list3.size());
		
		//TODO Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list2));
		assertFalse(list1.equals(list3));
		assertFalse(list2.equals(list3));
	}
	
	/**
	 * Tests if proper hashcodes are created for the sorted list and if 2 elements in the list have the same hashcode (check for
	 * equality)
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list1.add("apple");
		list1.add("pears");
		list1.add("jackfruit");
		list1.add("honeydew");
		list1.add("grapes");
		assertEquals(5, list1.size());
		
		list2.add("apple");
		list2.add("pears");
		list2.add("jackfruit");
		list2.add("honeydew");
		list2.add("grapes");
		assertEquals(5, list2.size());
		
		list3.add("carrots");
		list3.add("potato");
		list3.add("tomato");
		list3.add("coriander");
		assertEquals(4, list3.size());
		
		//TODO Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list1.hashCode());
		assertEquals(list2.hashCode(), list1.hashCode());
		assertEquals(list1.hashCode(), list2.hashCode());
		assertEquals(list2.hashCode(), list2.hashCode());
		
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
	}
}
