package main;
/**
* This class will hold the implementation of a generic FIFO queue that will 
* be used in the implementation of getByRecent() in WorkEntrySearchNode.java. 
* Known Bugs: None
*
* @author Wentao Li
* wentaoli@brandeis.edu
* 4/11/2020
* COSI 21A PA2
*/
public class Queue<T> {
	
	/**
	 * Node Class used to create a linked list 
	 */
	public class Node{
		
		public Node next;
		public T data;
		
		public Node(T element) {
			this.next = null;
			this.data = element;
		}
	}
	
	private Node front;
	private Node  tail;
	private int size;
	
	/**
	 * The constructor of an empty queue implemented by linked list.
	 */
	public Queue() {
		
		this.front = null;
		this.tail = null;
		this.size = 0;
	
	}
	
	/**
	 * @param data - the data needs to be stored in the queue
	 * This method adds a new node that contains the data to the end the queue.
	 * O(1)
	 */
	public void enqueue(T data) {
		
		Node temp = this.tail;
		this.tail = new Node(data);
		this.tail.next = null;
		
		if(this.size == 0) {         //the queue is empty
			this.front = this.tail;
		} else {						  //normal case
			temp.next = this.tail;
		}
		size++;
		
		
	}

	/**
	 * @return - the data stored in the first node of the queue
	 * This method dequeues the data element at the front of the queue and returns it. 
	 * If there is no element in the queue, return null;
	 * O(1)
	 */
	public T dequeue() {
		
		if(size == 0) {
			return null;
		} else {
			T data = this.front.data;
			this.front = this.front.next;
			size--;
			if(this.size == 0) this.tail = null;
			return data;
		}
		
	}
	
	/**
	 * @return - the first data of the queue
	 * This method gets the data at the front of the queue.
	 * If there are no elements in the queue, return null;
	 * O(1)
	 */
	public T front() {
		
		if(size == 0) {
			return null;
		} else {
			return this.front.data;
		}
		
	}
	
	/**
	 * @return - the number of elements in the queue
	 * O(1)
	 */
	public int size() {
		return this.size;
	}
	
}
