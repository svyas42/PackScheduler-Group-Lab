package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Queue class that uses an underlying LinkedList
 * @author Max Farthing
 *
 * @param <E> generic element type
 */
public class LinkedQueue<E> implements Queue<E> {
	/** queue using an array list **/
	private LinkedAbstractList<E> linkedQueue;
	/** the capacity of the queue **/
	private int capacity;
	/** the size of the queue **/
	private int size;
	
	/**
	 * Constructor for linked queue
	 * @param capacity maximum allowed size
	 */
	public LinkedQueue(int capacity) {
		linkedQueue = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
		size = 0;
	}

	/**
	 * Adds an element to the back of the queue
	 * @param element generic element type
	 * @throws IllegalArgumentException if size is greater than capacity
	 */
	@Override
	public void enqueue(E element) {
//		if(linkedQueue.size() > getCapacity()) {
//			throw new IllegalArgumentException();
//		}
//		linkedQueue.add(size, element);
//		size++;
		
		if (size() < getCapacity()) {
			int end = size();
			linkedQueue.add(end, element);
			size++;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * removes and returns the element at the front of the queue
	 * @return E generic element type
	 * @throws NoSuchElementException if list is empty
	 */
	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			E element = linkedQueue.remove(0);
			size--;
			return element;
		}
		
	}

	/**
	 * returns true if queue is empty
	 * @return boolean
	 */
	@Override
	public boolean isEmpty() {
		return linkedQueue.size() == 0;
	}

	/**
	 * checks the number of elements in the queue
	 * @return int of number of elements
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the queues capacity
	 * @param capacity new queue capacity
	 * @throws IllegalArgumentException if the capacity is less than 0 or if the capacity if less than the size
	 */
	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
	
	/**
	 * getter for capacity
	 * @return int of capacity
	 */
	public int getCapacity() {
		return capacity;
	}

}
