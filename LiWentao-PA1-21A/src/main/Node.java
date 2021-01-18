/**
 * This class provides the implementation of a doubly linked list node. 
 * These nodes should have a pointer to the next node, a pointer to the previous node, 
 * and data.
 * Known Bugs: None
 *
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 2/27/2020
 * COSI 21A PA1
 */
package main;

public class Node<T> {
	Node<T> next;
	Node<T> prev;
	T data;
	
	/**
	 * @param element - the data stored in the node
	 * This is the constructor of the Node class.
	 */
	public Node(T element) {
		
		this.next = null;
		this.prev = null;
		this.data = element;
		
	}
	
	/**
	 * @param element - data needed to be stored
	 * This method changes the date field of the node. 
	 * O(1)
	 */
	public void setElement(T element) {
		
		this.data = element;
		
	}
	
	/**
	 * @param next - prepared next node for a given node.
	 * This method change the pointer of the next node of a given node.
	 * O(1)
	 */
	public void setNext(Node<T> next) {
		
		this.next = next;
		
	}
	
	/**
	 * @param prev - prepared previous node for a given node.
	 * This method change the pointer of the previous node of a given node.
	 * O(1)
	 */
	public void setPrev(Node<T> prev) {
		
		this.prev = prev;
		
	}
	
	/**
	 * @return - the next node of a given node
	 * Running time: O(1)
	 */
	public Node<T> getNext() {
		
		return next;
		
	}
	
	/**
	 * @return - the previous node of a given node
	 * Running time: O(1)
	 */
	public Node<T> getPrev() {
		
		return prev;
		
	}
	
	/**
	 * @return - the data stored in the node
	 * Running time: O(1)
	 */
	public T getElement() {
		
		return data;
		
	}
	
	/**
	 *returns the String representation of this node’s element.
	 */

	public String toString() {
		
		return this.getElement().toString();
		
	}
}
