package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Implementation of stack based on the functionalities of an array list
 * @author Sachi Vyas
 *
 * @param <E> the element on which the behaviors of the array stack are being performed on
 */
public class ArrayStack<E> implements Stack<E> {
	
	/** The stack */
	private ArrayList<E> array;
	/** The capacity of the stack */
	private int capacity;
	/** The size of the stack a given time */
	private int size;
	
	/**
	 * Constructor of the ArrayStack class
	 * @param capacity the number of elements that the stack can hold
	 */
	public ArrayStack(int capacity) {
		array = new ArrayList<E>();
		setCapacity(capacity);
		size = 0;
	}
	
	/**
	 * Adds an element to the top of the stack
	 * @param element the element to add to the top of the stack
	 * @throws IllegalArgumentException if the array size is greater than capacity
	 */
	@Override
	public void push(E element) {
		if (array.size() < getCapacity()) {
			array.add(0, element);
			size++;
		}
		else {
			throw new IllegalArgumentException();
		}	
	}
	
	/**
	 * Pops the element off the top of the stack
	 * @return the element that is removed from the stack
	 * @throws EmptyStackException if the array size is empty
	 */
	@Override
	public E pop() {
		if (!isEmpty()) {
			E elementRemoved = array.remove(0);
			size--;
			return elementRemoved;
		}
		else {
			throw new EmptyStackException();
		}
	}
	
	/**
	 * Checks if the stack is empty
	 * @return true if the stack is empty or false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return array.size() == 0;
	}
	
	/**
	 * Returns the size of the stack
	 * @return size the size of the stack
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Sets the capacity of the stack
	 * @param capacity the number of elements that the array list can contain
	 * @throws IllegalArgumentException if the capacity is a negative number or less than the given size
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;		
	}
	
	/**
	 * Gets the capacity of the stack
	 * @return capacity the amount of elements the stack can hold
	 */
	public int getCapacity() {
		return capacity;
	}

}
