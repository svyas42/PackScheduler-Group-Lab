package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface that outline the stack objects behaviors
 * @author Sachi Vyas
 * @param <E> the elements in the stack on which the behaviors are executed
 */
public interface Stack<E> {
	/**
	 * Adds an element to the top of the stack, if there is capacity for the given element to be added
	 * @param element the element to push on the top of the stack
	 */
	void push(E element);
	
	/**
	 * Removes the element from the top of the stack
	 * @return element the element removed from the stack
	 */
	E pop();
	
	/**
	 * Checks if the stack is  empty
	 * @return boolean true if the stack is empty and false otherwise
	 */
	boolean isEmpty();
	
	/**
	 * Gets the size of the stack
	 * @return the number of elements in the stack
	 */
	int size();
	
	/**
	 * Sets the capacity of the stack if the new capacity is greater than the current number of elements in the stack
	 * @param capacity the amount of the elements that can be added more to the stack
	 */
	void setCapacity(int capacity);
}
