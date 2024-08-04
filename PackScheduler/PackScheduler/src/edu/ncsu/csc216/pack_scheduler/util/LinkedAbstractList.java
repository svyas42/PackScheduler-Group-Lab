package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Implementing a Linked abstract list that allows us to add, remove, set, get elements from a list and
 * throws exceptions if the index is out of bounds or if the element to add is null
 * @author Sachi Vyas
 * @param <E> element that can be added to the abstract list
 *
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** The list node that points to the front of the linked list */
	private ListNode<E> front;
	/** The list node that points to the back of the linked list */
	private ListNode<E> back;
	/** The size of the linked list */
	private int size;
	/** The capacity of the linked list */
	private int capacity;
	
	/**
	 * Constructor for the LinkedAbstractList class
	 * @param capacity the capacity of the linked abstract list
	 * @throws IllegalArgumentException if the capacity is less than 0
	 */
	public LinkedAbstractList(int capacity) {
		front = null;
		back = null;
		size = 0;
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		else {
			this.capacity = capacity;
		}
	}
	
	/**
	 * Returns the size of the list
	 * @return size the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Gets the index of the data in the linked list
	 * @return index of where the data is located
	 * @throws IllegalArgumentException if the index is out of bounds
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return atNode(index).data;
	}
	
	/**
	 * Sets element at the given index in the linked list
	 * @param index the index to set the element at
	 * @param element the element to set at the given index
	 * @return removeData the element to move
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 * @throws NullPointerException if the element to be set is null
	 * @throws IllegalArgumentException if there is a duplicate element in the list 
	 */
	public E set(int index, E element) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size(); i++) {
			if (element.equals(get(i))) {
				throw new IllegalArgumentException();
			}
		}
		E removeData;
		//setting the element
		if (index == 0) {
			removeData = front.data;
			front.data = element;
			return removeData;
		}
		else {
			ListNode<E> current = atNode(index);
			removeData = current.data;
			current.data = element;
			return removeData;
		}
		
	}
	
	/**
	 * Adding the element to the list at the given index
	 * @param index the index at which the element is suppose to be added
	 * @param element the element to be added to the linked list
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 * @throws NullPointerException if the element to be set is null
	 * @throws IllegalArgumentException if there is a duplicate element in the list 
	 */
	public void add(int index, E element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size(); i++) {
			if (element.equals(get(i))) {
				throw new IllegalArgumentException();
			}
		}
		if (front == null) {
			front = new ListNode<E>(element, null);
			back = front;
			size++;
			return;
		}
		if (index == 0) {
			front = new ListNode<E>(element, front);
			size++;
		}
		else {
			if (index == size() && size != 0) {
				back.next = new ListNode<E>(element, null);
				back = back.next;
				size++;
			}
			else {
				ListNode<E> currentNode = atNode(index - 1);
				currentNode.next = new ListNode<E>(element, currentNode.next);
				size++;
			}
		}
	}
	
	/**
	 * Removes an element from the given list
	 * @param index the index from which the element is to be removed
	 * @return dataToRemove the element to remove from the list
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the size
	 */
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (front == null) {
			return null;
		}
		E dataToRemove;
		if (index == 0) {
			dataToRemove = front.data;
			front = front.next;
			size--;
			return dataToRemove;
		}
		else {
			if (index == size() - 1 && size() != 1) {
				ListNode<E> previousNode = atNode(index - 1);
				dataToRemove = back.data;
				back = previousNode;
				size--;
				return dataToRemove;
			}
			else {
				ListNode<E> currentNode = atNode(index - 1);
				dataToRemove = currentNode.next.data;
				currentNode.next = currentNode.next.next;
				size--;
				return dataToRemove;
			}
		}		
	}
	
	/**
	 * Setting the capacity of the list
	 * @param capacity the integer to set the capacity to
	 * @throws IllegalArgumentException if the capacity is less then 0 or less than the size of hte linked list
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;		
	}
	
	/**
	 * Returns the capacity of the linked list
	 * @return capacity the capacity of the list
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Finds the list node at the given index in the linked list
	 * @param index the index at which the list node is located
	 * @return currentNode the list node at the given index
	 */
	private ListNode<E> atNode(int index) {
		ListNode<E> currentNode = front;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}
	
	/**
	 * Handles the individual nodes in the linked list
	 * @author Sachi Vyas
	 * @param <T> the type that is in the list node
	 */
	private class ListNode<T> {
		/** The data that is present in the code */
		T data;
		/** The next node reference in the list node */
		private ListNode<T> next;
		
		/**
		 * Constructor for the ListNode class
		 * @param type the type for the element T
		 */
		public ListNode(T type) {
			data = type;
		}
		
		/**
		 * The second constructor for the ListNode class with 2 parameters
		 * @param type the type of element T
		 * @param nextNode the next node in the list
		 */
		public ListNode(T type, ListNode<T> nextNode) {
			this(type);
			next = nextNode;
		}
	}
}
