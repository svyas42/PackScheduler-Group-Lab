package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Test class for ArrayQueue
 * @author Max Farthing
 *
 */
public class ArrayQueueTest {

	/**
	 * Tests constructor
	 */
	@Test
	public void arrayQueueConstructor() {
		ArrayQueue<String> list = new ArrayQueue<String>(10);
		assertEquals(0, list.size());
		assertEquals(10, list.getCapacity());
		assertTrue(list.isEmpty());
	}
	
	/**
	 * tests enqueue and dequeue method
	 */
	@Test
	public void testEnqueueAndDequeue() {
		ArrayQueue<String> list = new ArrayQueue<String>(10);
		assertEquals(0, list.size());

		try {
			list.enqueue("Apple");
			list.enqueue("Banana");
			list.enqueue("Carrot");
			list.enqueue("Pineapple");
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(4, list.size());

		try {
			list.dequeue();
			assertEquals(3, list.size());
		} catch(IllegalArgumentException e) {
			fail();
		}
		

		try {
			list.dequeue();
			list.dequeue();
			assertEquals(1, list.size());
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		
		try {
			list.dequeue();
			assertEquals(0, list.size());
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertThrows(NoSuchElementException.class, () -> list.dequeue());
		assertThrows(IllegalArgumentException.class, () -> list.setCapacity(-1));

		try {
			list.setCapacity(1);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
//		list.enqueue("apple");
		list.enqueue("test");
		assertThrows(IllegalArgumentException.class, () -> list.enqueue("test"));

	}
	
	/**
	 * Tests the waitlist issue in jenkins
	 */
	@Test
	public void testCapacity() {
		ArrayQueue<String> list = new ArrayQueue<String>(5);
		assertEquals(5, list.getCapacity());
		list.enqueue("a");
		list.enqueue("b");
		list.enqueue("c");
		list.enqueue("d");
		list.enqueue("e");
		
//		list.enqueue("f");
		try {
			list.enqueue("f");
		} catch(IllegalArgumentException e) {
			//fail();
		}
	}

}
