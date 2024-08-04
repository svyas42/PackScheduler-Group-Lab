package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Tests the LinkedQueue class
 * @author Max Farthing
 *
 */
class LinkedQueueTest {

	/**
	 * Tests constructor
	 */
	@Test
	public void arrayQueueConstructor() {
		LinkedQueue<String> list = new LinkedQueue<String>(10);
		assertEquals(0, list.size());
		assertEquals(10, list.getCapacity());
		assertTrue(list.isEmpty());
	}
	
	/**
	 * tests enqueue and dequeue method
	 */
	@Test
	public void testEnqueueAndDequeue() {
		LinkedQueue<String> list = new LinkedQueue<String>(10);
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
		//assertEquals(0, list.size());

		try {
			list.dequeue();
			//assertEquals(3, list.size());
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
		


	}


}
