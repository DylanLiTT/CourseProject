/**
 * This class provides the implementation of a generic circular first-in-first-out queue. 
 * Known Bugs: None
 *
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 2/27/2020
 * COSI 21A PA1
 */
package main;

import java.util.NoSuchElementException;

public class Queue<T> {

	public T[] q;
	public int head;
	public int tail;
	public int numEntries;
	
	/**
	 * @param capacity - the limited size of the queue
	 * This is the construtor of a queue.
	 */
	@SuppressWarnings("unchecked")
	public Queue(int capacity) {
		this.q = (T[]) new Object[capacity];
		head = 0;
		tail = 0;
		numEntries = 0;
	}
	
	/**
	 * @param element - type T element that needs to be added to the tail of the queue.
	 * This method adds the element to the tail of the queue.
	 * O(1)
	 */
	public void enqueue(T element) {
		
		if(numEntries == q.length) {
			System.out.println("Error: Queue is full.");
		}else {
			q[tail] = element;
			if(tail == q.length-1) {
				tail = 0;
			}else {
				tail++;
			}
			numEntries++;
		}
		
	}
	
	/**
	 * Remove the fisrt element of the queue.
	 * O(1)
	 */
	public void dequeue() {
		
		if(numEntries == 0) {
			throw new NoSuchElementException("The Queue is empty.");
		}else {
			q[head] = null;
			if(head == q.length-1) {
				head = 0;
			}else {
				head++;
			}
			numEntries--;
		}
		
	}
	
	/**
	 * @return - the first element of the queue.
	 * O(1)
	 */
	public T front() {
		
		if(numEntries == 0) {
			throw new NoSuchElementException("The Queue is empty.");
		}else {
			return q[head];
		}
		
	}
	
	/**
	 * @return - return the number of elements in the queue.
	 * O(1)
	 */
	public int size() {
		return numEntries;
	}
	
	/**
	 *Return a String representation of the queue’s elements.
	 */
	@Override
	public String toString() {
		
		String str = "";
		for(int i = 0; i < this.size(); i++) {
			
			str += this.front().toString() + "\n";
			this.enqueue(this.front());
			this.dequeue();
			
		}
		return str;
	}
}
