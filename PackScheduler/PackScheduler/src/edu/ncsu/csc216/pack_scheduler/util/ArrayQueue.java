package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * An implementation of a Queue data structure that uses an underlying ArrayList
 * 
 * @author Max Farthing
 * @param <E> generic element type
 */
public class ArrayQueue<E> implements Queue<E> {
	/** queue using an array list **/
	private ArrayList<E> list;
	/** the capacity of the queue **/
	private int capacity;
	/** the size of the queue **/
	private int size;

	/**
	 * Constructor for Queue using underlying array list
	 * 
	 * @param capacity the maximum elements queue can hold
	 */
	public ArrayQueue(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
		size = 0;
	}

	/**
	 * Adds an element to the back of the queue
	 * 
	 * @param element generic element type
	 * @throws IllegalArgumentException if there is no room
	 */
	@Override
	public void enqueue(E element) {
		if (list.size() >= getCapacity()) {
			throw new IllegalArgumentException();
		}
		else {
			//list.add(size, element);
			list.add(size(), element);
			size++;
		}
//		list.add(size, element);
//		size++;
	}

	/**
	 * removes and returns the element at the front of the queue
	 * 
	 * @return E generic element type
	 * @throws NoSuchElementException is queue is empty
	 */
	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			E element = list.remove(0);
			size--;
			return element;
		}
//		E element = list.remove(0);
//		size--;
//		return element;
	}

	/**
	 * returns true if queue is empty
	 * 
	 * @return boolean
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * checks the number of elements in the queue
	 * 
	 * @return int of number of elements
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the queues capacity
	 * 
	 * @param capacity new queue capacity
	 * @throws IllegalArgumentException if parameter is negative or less than size
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		else {
			this.capacity = capacity;
		}
		
	}

	/**
	 * getter for capacity
	 * 
	 * @return int of capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Checks if an element is within an arrayQueue
	 * 
	 * @param element Element to be compared against students in the array queue.
	 * @return true if arrayQueue contains student
	 */
	public boolean contains(E element) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(element)) {
					return true;
				}
			}
		}
		return false;
	}

}
