package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementing the linked list class
 * 
 * @author Jason Maher
 * @param <E> element that can be added to the linked list
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	/** Variable to store the size of the linked list */
	private int size;
	/** The list node that points to the front of the linked list */
	private ListNode<E> front;
	/** The list node that points to the back of the linked list */
	private ListNode<E> back;

	/**
	 * Constructor for the linked list
	 */
	public LinkedList() {
		front = new ListNode<E>(null);
		back = new ListNode<E>(null);
		front.next = back;
		back.previous = front;
		size = 0;
	}

	/**
	 * Returns the number of elements in the list.
	 * 
	 * @return number of elements in the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds an element to the specified index, if the element is not within the list
	 * 
	 * @param index   Index the element will be added.
	 * @param element Element to be added to the list.
	 * @throws IllegalArgumentException if the element is already within the list
	 */
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (this.contains(element)) {
			throw new IllegalArgumentException("list already contains element");
		}
		super.add(index, element);
	}

	/**
	 * Sets an element to a new value at a given index.
	 * 
	 * @param index   Index of the element to be set to a new value.
	 * @param element Element to replace element within given index.
	 * @throws IllegalArgumentException if the element exists within the list
	 */
	@Override
	public E set(int index, E element) {

		if (this.contains(element)) {
			throw new IllegalArgumentException("cannot change to element, element already exists within the list");
		}
		return super.set(index, element);
	}

	/**
	 * Constructs a new LinkedListIterator passes an index to position the iterator.
	 * 
	 * @param index Index of element LinkedListIterator.next() would return
	 * @return Constructed LinkedListIterator
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		LinkedList<E>.LinkedListIterator iterator = new LinkedListIterator(index);
		return iterator;
	}

	/**
	 * Private class that implements the list iterator interface
	 * 
	 * @author Jason Maher
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** Reference to the previous node in the linked list */
		private ListNode<E> previous;
		/** Reference to the next node in the linked list */
		private ListNode<E> next;
		/** Reference to the previous index from the current position of the iterator */
		private int previousIndex;
		/** Reference to the next index from the current position of the iterator */
		private int nextIndex;
		/**
		 * Reference to the ListNode that was returned on the last call to either
		 * previous() or next(), null if a call to previous() or next() was not the last
		 * call on the ListIterator
		 */
		private ListNode<E> lastRetrieved;

		/**
		 * Constructor for the linked list iterator
		 * 
		 * @param index the index of an element in the linked list
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException("index out of bounds for positiong a new iterator");
			}
			ListNode<E> current = front;
			for (int i = 0; i < index; i++) {

				current = current.next;
			}
			next = current.next;
			previous = current;
			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null;
		}

		/**
		 * Check if there is a next ListNode
		 * 
		 * @return false is next is null, else true
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		/**
		 * Returns the next element in the list and advances the cursor position.
		 * 
		 * @throws NoSuchElementException if the next listNode is null.
		 */
		@Override
		public E next() {
			if (next.data == null) {
				throw new NoSuchElementException("next is null");
			}
			// current next
			E nextElement = next.data;
			lastRetrieved = next;
			// reassign fields
			next = next.next;
			previous = next;
			nextIndex++;
			previousIndex++;
			return nextElement;
		}

		/**
		 * Checks if there is a previous ListNode
		 * 
		 * @return false if previous is null, else true
		 */
		@Override
		public boolean hasPrevious() {
			return previous.data != null;
		}

		/**
		 * Returns the previous element in the list and moves the cursor position
		 * backwards.
		 * 
		 * @throws NoSuchElementException if the previous listNode is null.
		 */
		@Override
		public E previous() {
			if (previous.data == null) {
				throw new NoSuchElementException("previous is null");
			}
			// current previous
			E previousElement = previous.data;
			lastRetrieved = previous;
			// reassign fields
			next = previous;
			previous = previous.previous;
			nextIndex--;
			previousIndex--;
			return previousElement;
		}

		/** Returns the value of the next index */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/** Returns the value of the next index */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/** Removes a node from the list, and decrements the size */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException("lastRetrieved cannot be null");
			}
			// update pointers
			lastRetrieved.previous.next = lastRetrieved.next;
			lastRetrieved.next.previous = lastRetrieved.previous;
			size--;
		}
		/**
		 * Sets an element's data with the given parameter.
		 * @param e New element to be set to.
		 * @throws NullPointerException if e is null
		 * @throws IllegalStateException if last element retrieved, from next() or previous(), is null
		 */
		@Override
		public void set(E e) {
			if (e == null) {
				throw new NullPointerException("cannot set objects to null");
			}
			if (lastRetrieved == null) {
				throw new IllegalStateException("lastRetrieved cannot be null");
			}
			lastRetrieved.data = e;
		}

		/**
		 * Inserts the specified element into the list
		 * 
		 * @param e Element to be inserted
		 * @throws NoSuchElementException if the element to be inserted is null
		 */
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NoSuchElementException("cannot add null elements");
			}
			// create and add new node
			ListNode<E> node = new ListNode<E>(e);
			// node's next and previous
			node.previous = previous;
			node.next = next;
			// add pointers to new node
			previous.next = node;
			next.previous = node;
			lastRetrieved = null;
			size++;
		}
	}

	/**
	 * Individual nodes in the linked list
	 * 
	 * @author Jason Maher
	 * @author Sachi Vyas
	 * @param <E> the type that is in the list node
	 */
	public class ListNode<E> {
		/** The data that is present in the code */
		E data;
		/** The next node reference */
		private ListNode<E> next;
		/** The previous node reference */
		private ListNode<E> previous;

		/**
		 * Constructs a ListNode with a given data type
		 * 
		 * @param type Type of data to be stored.
		 */
		public ListNode(E type) {
			data = type;
		}

		/**
		 * Constructs a listnode with previous and next listnode parameters.
		 * 
		 * @param type     Type of data.
		 * @param nextNode Previous ListNode.
		 * @param prevNode Next Listnode.
		 */
		public ListNode(E type, ListNode<E> nextNode, ListNode<E> prevNode) {
			this(type);
			next = nextNode;
			previous = prevNode;
		}
	}
}
