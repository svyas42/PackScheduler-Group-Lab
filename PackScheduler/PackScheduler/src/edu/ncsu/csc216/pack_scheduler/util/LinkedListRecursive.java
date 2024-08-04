package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Maintains and creates a linked list that is recursive
 * @author Sachi Vyas
 *
 * @param <E> the element to add to the list
 */
public class LinkedListRecursive<E> {
	/** The size of the recursive list */
	private int size;
	/** An instance of the list node inner class that points to the front of the list */
	private ListNode front;
	
	/**
	 * Constructor of the linked list recursive class
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * Returns zero if the list is empty
	 * @return zero if the list is empty i.e. if the boolean value is true
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns the size of the linked list
	 * @return size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Checks if an element is added to the list
	 * @param element the element to be added in the list
	 * @return true if the element is added, false otherwise
	 * @throws IllegalArgumentException if the list already contains the element to be added
	 */
	public boolean add(E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (isEmpty()) {
			front = new ListNode(element, null);
			size++;
			return true;
		}
		add(size, element);
		return true;
	}
	
	/**
	 * Adds an element to the list
	 * @param idx the index at which the element is to be added
	 * @param element the element to be added
	 * @throws NullPointerException if the element to be added is null
	 * @throws IllegalArgumentException if the list already contains the element to be added
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the size
	 */
	public void add(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (!isEmpty() && idx == 0) {
			front = new ListNode(element, front);
			size++;
		}
//		else if(idx == 0 && isEmpty()) {
//			front = new ListNode(element, null);
//			size++;
//		}
		else if(idx == 0 || isEmpty()) {
			front = new ListNode(element, null);
			size++;
		}
		else {
			front.add(idx - 1, element);
			size++;
		}
	}
	
	/**
	 * Returns the element at a index from the list
	 * @param idx the index at which the element is to be retrieved at
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the size
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E e = front.get(idx);
		return e;
	}
	
	/**
	 * Checks if an element can be removed from the list
	 * @param element the element to be removed
	 * @return true if the element can be removed and false otherwise
	 */
	public boolean remove(E element) {
		if (element == null) {
			return false;
		}
		if (isEmpty()) {
			return false;
		}
		if (front.data.equals(element)) {
			front = front.next;
			size--;
			return true;
		}
		return front.remove(element);
	}
	
	/**
	 * Returns the element that is removed from the list
	 * @param idx the index at which the element is removed
	 * @return element which is removed
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the size
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E e;
		if (idx == 0) {
			e = front.data;
			front = front.next;
			size--;
			return e;
		}
		e = front.remove(idx);
		return e;
	}
	
	/**
	 * Sets the given element at a particular index
	 * @param idx the index at which to set the element at
	 * @param element the element to set
	 * @return the element that is replaced in the list
	 * @throws NullPointerException if the element to be set is null
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the size
	 * @throws IllegalArgumentException if the list already contains the element to be added
	 */
	public E set(int idx, E element) {
//		if (element == null) {
//			throw new NullPointerException();
//		}
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (isEmpty()) {
			front = new ListNode(element, null);
			size++;
			return null;
		}
		if (element == null) {
			throw new NullPointerException();
		}
		return front.set(idx, element);
	}
	
	/**
	 * Checks if the given element is contained in the list
	 * @param element the element to check
	 * @return true if the element is in the list, false otherwise
	 */
	public boolean contains(E element) {
		if (isEmpty() ) {
			return false;
		}
		return front.contains(element);
	}	
	
	/**
	 * Manages the nodes and add, remove operations of the list
	 * @author Sachi Vyas
	 */
	private class ListNode {
		/** The node that points to the next element in the list */
		public ListNode next;
		/** The data present in the list node */
		public E data;
		
		/**
		 * Constructor for the list node class
		 * @param data the the element in the list node
		 * @param next that node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.next = next;
			this.data = data;
		}
		
//		/**
//		 * Needed or not?
//		 * @param element the element to add in the list
//		 * @return true if the element can be added, false otherwise
//		 */
//		public boolean add(E element) {
//			if (next == null) {
//				next = new ListNode(element, next);
//				size++;
//				return true;
//			}
//			return next.add(element);
//		}
		
		
		
		/**
		 * Adds an element to the list
		 * @param idx the index at which the element is to be added
		 * @param element the element to add
		 */
		public void add (int idx, E element) {
			if (idx == 0) {
				this.next = new ListNode(element, next);
			} else {
				int index = idx;
				index--;
	
				next.add(index, element);
			}
		}
		
		/**
		 * Getting the element at the index
		 * @param idx the index at which to get the element at
		 * @return the next element on the linked list
		 */
		public E get(int idx) {
			if (idx == 0) {
				return data;
			}
			else {
				if (next == null) {
					return null;
				}
				
				return next.get(idx - 1);
			}
//			int index = idx;
//			//index--;
//			return next.get(index - 1);
		}
		
		/**
		 * Removes an element from the list
		 * @param idx the index from where to remove from
		 * @return the element removed from the list
		 */
		public E remove(int idx) {
			if (next == null) {
				return null;
			}
			else if (idx == 1 && next != null) {
				E e = next.data;
				next = next.next;
				size--;
				return e;
			}
			else {
				return next.remove(idx - 1);
			}
		}
		
		/**
		 * Returns true if an element is removed
		 * @param element the element to be removed from the list
		 * @return true if the element is removed, false otherwise
		 */
		public boolean remove(E element) {
			if (next == null) {
				return false;
			}
			if (next.data.equals(element)) {
				next = next.next;
				size--;
				return true;
			}
			return next.remove(element);
		}
		
		/**
		 * Sets an element in the list
		 * @param idx the index where to the set the element
		 * @param element the element to set in the list
		 * @return the value that was replaced in the list
		 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the size
		 */
		public E set(int idx, E element) {
			
			if (idx < 0 || idx >= size()) {
				throw new IndexOutOfBoundsException();
			}
			if (idx == 0) {
				E e = data;
				data = element;
				return e;
			}
			int index = idx;
			index--;
			return next.set(index, element);
		}
		
		/**
		 * Returns true if the element is contained in the list
		 * @param element the element to check if contained in the list
		 * @return true if the element is in the list, false otherwise
		 */
		public boolean contains(E element) {
			if (data.equals(element)) {
				return true;
			}
			if (next == null) {
				return false;
			}
			return next.contains(element);
		}
		
//		/**
//		 * Constructor for the list node class
//		 * @param element the the element in the list node
//		 * @param node that node in the list
//		 */
//		public ListNode(E element, ListNode node) {
//			next = node;
//			data = element;
//		}
	}

}
