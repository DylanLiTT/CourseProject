/**
 * This class provides the implementation of a generic doubly linked list.
 * Known Bugs: None
 *
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 2/27/2020
 * COSI 21A PA1
 */
package main;

public class DoubleLinkedList<T> {
	Node<T> head;
	int size;
	
	/**
	 * The constructor of the DoubleLinkedList.
	 */
	public DoubleLinkedList() {
		
		this.head = null;
		this.size = 0;
		
	}
	
	/**
	 * @return - the first node of the linked list.
	 * O(1)
	 */
	public Node<T> getFirst() {
		
		return head;
		
	}
	
	
	/**
	 * @param element - the data that needs to be stored in the new node.
	 * Add a new node with the element to the end of the list.
	 * O(n)
	 */
	public void insert(T element) {
		
		Node<T> add = new Node(element);
		Node<T> pointer = head;
		if(head == null) {
			head = add;
		}else {
			while (pointer.getNext() != null) {
				pointer = pointer.getNext();
			}
			pointer.setNext(add);
			add.setPrev(pointer);
			add.setNext(null);
		}
		size++;
		
	}
	
	/**
	 * @param key - data in the node that needs to be deleted
	 * @return - the data in the node that will be deleted.
	 * O(n)
	 */
	public T delete(T key) {
		
		Node<T> pointer = head;
		while (pointer != null) {
			if(pointer.getElement() == key) {
				if(pointer.getPrev() != null) {
					pointer.getPrev().setNext(pointer.getNext());
				}else {
					head = pointer.getNext();
				}
				if(pointer.getNext() != null) {
					pointer.getNext().setPrev(pointer.getPrev());
				}
				size--;
				return pointer.getElement();		
			}else {
				pointer = pointer.getNext();
			}
		}
		return null;
	}
	
	/**
	 * @param key - the data needs to te found
	 * @return - the data that will be found
	 * This methods returns the first element in the list that matches the provided key or null if one cannot be found
	 * O(n)
	 */
	public T get(T key) {
		
		Node<T> pointer = head;
		while (pointer != null) {
			if(pointer.getElement().equals(key)) {
				return pointer.getElement();
			}
			pointer = pointer.getNext();
		}
		return null;
		
	}
	
	/**
	 * @return - the number of nodes in the list.
	 * O(1)
	 */
	public int size() {
		return size;
	}
	
	/**
	 *returns a String representation of this list’s elements.
	 */
	@Override
	public String toString() {
		
		Node<T> pointer = head;
		String str = "";
		while (pointer != null) {
			str += pointer.getElement().toString() + "\n";
			pointer = pointer.getNext();
		}
		return str;
		
	}
}
