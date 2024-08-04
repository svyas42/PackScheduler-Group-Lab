package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface that outlines Queue behaviors for this project
 * @author Max Farthing
 *@param <E> generic type elements
 */
public interface Queue<E> {

	/**
	 * Adds element to the back of the queue
	 * @param element generic element type
	 */
	void enqueue(E element);
	
	/**
	 * removes and returns element at the front of the queue
	 * @return a generic element type
	 */
	E dequeue();
	
	/**
	 * returns true if queue is empty
	 * @return boolean
	 */
	boolean isEmpty();
	
	/**
	 * checks the number of elements in the queue
	 * @return int of number of elements
	 */
	int size();
	
	/**
	 * Sets the queues capacity
	 * @param capacity new queue capacity
	 */
	void setCapacity(int capacity);
	
}
