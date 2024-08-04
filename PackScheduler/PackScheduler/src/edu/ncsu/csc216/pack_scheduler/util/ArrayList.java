package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;


/**
 * Implementation of the array list functionality for unordered lists
 * @author Sachi Vyas
 * @param <E> elements that can be added to an array list
 */
public class ArrayList<E> extends AbstractList<E> {
	
	/** A constant value for initializing the list size */
	private static final int INIT_SIZE = 10;
	/** An array of type E */
	private E[] list;
	/** The size of the list */
	private int size;
	
	/**
	 * Making an empty array list
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}
	
	/**
	 * Returns the size of the array list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Helper method to add an element of the array
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		int newSize = size * 2;
		E[] growingList = (E[]) new Object[newSize];
		for (int i = 0; i < list.length; i++) {
			growingList[i] = list[i];
		}
		list = growingList;
	}
	
	/**
	 * Adds an element to the array list
	 * @param idx the index at which to add the element
	 * @param element the index at which the element is to be added
	 * @throws IndexOutOfBoundsException is the index is less than 0 or greater than or equal to
	 * size of the array list
	 * @throws NullPointerException if the element is null
	 * @throws IllegalArgumentException if there are duplicates i.e. the element that we are adding
	 * already exists in the array list
	 */
	@Override
	public void add(int idx, E element) {
		if (size == list.length) {
			growArray();
		}
		
		if (element == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		//front or middle shift and add
		if (idx < size) {
			for (int i = size; i > idx; i--) {
				list[i] = list[i - 1];
			}
			list[idx] = element;
			size++;
		}
		
		//if at the end add
		if (idx == size && size < list.length) {
			list[idx] = element;
			size++;
		}
	}
	
	/**
	 * Returns the index at which the Element to be removed is there
	 * @param idx the index at which the element is to be removed
	 * @return the element from the list to remove
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the
	 * size of the array list
	 */
	@SuppressWarnings("unchecked")
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Object e = list[idx];
		if (idx == size - 1 && size < list.length) {
			list[size - 1] = null;
			size--;
		}
		if (size - 1 > idx) {
			for (int i = idx; i < size - 1; i++) {
				list[i] = list[i + 1];
			}
			list[size - 1] = null;
			size--;
		}
		return (E) e;
	}
	
	/**
	 * Gets the element at the index and the return type is E
	 * @param idx the idx at which we want to get the element from
	 * @throws IndexOutOfBoundsException is the index is less than 0 or greater than or equal to size 
	 * of the array list
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return list[idx];
	}
	
	/**
	 * Return the original element at the location of type E
	 * @param idx the index to set the element at
	 * @param element that needs to be added / set
	 * @throws IndexOutOfBoundsException is the index is less than 0 or greater than or equal to the
	 * size of the array list
	 * @throws NullPointerException if the element is null
	 * @throws IllegalArgumentException if there are duplicates i.e. the element that we are adding
	 * already exists in the array list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E set(int idx, E element) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		Object e = list[idx];
		if (idx < size) {
			list[idx] = element;
		}
		return (E) e;
	}
}
