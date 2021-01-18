/**
 * This class provides the functionality of a specialized heap-based maximum priority queue 
 * for storing Email objects. 
 * Known Bugs: None
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * <May 8th, 2020>
 * COSI 21A PA3
*/
package main;

import java.util.NoSuchElementException;

public class PriorityQueue {

	public Email[] heap; // KEEP THIS PUBLIC
	private int heapSize;
	
	/**
	 * This is the constructor of a priority queue.
	 */
	public PriorityQueue() {
		this.heap = new Email[10];
		this.heapSize = 0;
	}
	
	
	/**
	 * @param i - index of the first email
	 * @param j - index of the second email
	 * This method swaps two emails located respectively at i and j.
	 * O(1)
	 */
	private void swap(int i, int j) {
		Email temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
		
	}

	
	/**
	 * @param index - the email at index needs to be heapified
	 * This method applies the heapify down algorithm from lecture on the Email stored at index in this PriorityQueue
	 * O(logn)
	 */
	private void heapifyDown(int index) {
		
		int left = 2*index + 1;
		int right = 2*index + 2;
		int largest = index;
		
		if(left <= this.heapSize-1 && heap[left].getSpamScore() > heap[index].getSpamScore() ) {
			largest = left;
		}
		if(right <= this.heapSize-1 && heap[right].getSpamScore() > heap[largest].getSpamScore() ) {
			largest = right;
		}
		if (largest != index) {
			this.swap(largest, index);
			heapifyDown(largest);
		}
		
	}
	
	
	/**
	 * @param index - the Email at index needs to be heapified up
	 * This method applies the heapify up algorithm from lecture on the Email stored at index in this PriorityQueue.
	 * O(logn)
	 */
	private void heapifyUp(int index) {
		
		while(index > 0 && heap[index].getSpamScore() > heap[(index-1)/2].getSpamScore()){
			
			this.swap(index, (index-1)/2);
			index = (index-1)/2;
			
		}
		
	}
	
	/**
	 * @param e - the new Email needs to added
	 * This method should add an Email to the PriorityQueue.
	 * O(n)
	 */
	public void insert(Email e) {
		
		if(heapSize == heap.length) {
			Email[] temp = new Email[this.heapSize*2];
			for(int i = 0; i < heapSize; i++) {
				temp[i] = heap[i];
			}
		}
		heap[heapSize] = e;
		heapifyUp(heapSize);
		heapSize++;
		
	}
	
	/**
	 * @param eID - the indentifier of the email that needs to update its spam score
	 * @param score - the new score of the email
	 * This method should update the spam score of the Email with the given ID.
	 * O(logn) 
	 */
	public void updateSpamScore(String eID, int score) {
		
		int index = searchHeap(eID);
		if(index == -1) {
			
			throw new NoSuchElementException("Email with ID " + eID + " is not in the priority queue.");
			
		} else {
			
			if(score > heap[index].getSpamScore()) {
				heap[index].setSpamScore(score);
				heapifyUp(index);
			}
			
		}
	}
	
	/**
	 * @param eID - the indentifier of the email;
	 * @return - the index of this email in the heap
	 * O(n)
	 */
	private int searchHeap(String eID) {
		
		for(int i = 0; i < heapSize; i++) {
			if(heap[i].getID().equals(eID)) {
				return i;
			}
		}
		return -1;
		
	}


	/**
	 * @return - the highest spam score
	 * O(1)
	 */
	public int getMaximumSpamScore() {
		
		if(heapSize == 0) {
			throw new NoSuchElementException();
		} else {
			return heap[0].getSpamScore();
		}
		
	}
	
	/**
	 * @return - the Email with the highest spamScore
	 * This method should remove the Email from this queue with the highest spam score. 
	 * O(logn)
	 */
	public Email extractMaximum() {
		
		if(heapSize == 0) {
			throw new NoSuchElementException();
		}
		
		Email max = heap[0];
		heap[0] = heap[heapSize-1];
		heapSize--;
		heapifyDown(0);
		return max;
		
	}
	
	/**
	 * @return - all the Emails' IDs
	 * This method returns the identifiers of the Emails stored in this PriorityQueue in any order.
	 * O(n)
	 */
	public String[] getIDs() {
		
		String[] IDS = new String[heapSize];
		
		for(int i = 0; i < heapSize; i++) {
			IDS[i] = heap[i].getID();
		}
		
		return IDS;
		
	}
	
	/**
	 * @return - String array that ranks the Emails from highest spam score to lowest
	 * This method returns the unique identifiers and spam scores of the Emails stored in this PriorityQueue 
	 * sorted from highest to lowest spam score. 
	 * O(nlogn)
	 */
	public String[] rankEmails() {
		
		//make a copy of current heap
		Email[] temp = new Email[heapSize];
		for(int i = 0; i < heapSize; i++) {
			temp[i] = heap[i];
		}
		
		//heap sort
		for(int i = heapSize - 1; i > 0; i--) {
			
			this.swap(0, i);
			heapSize--;
			heapifyDown(0);
			
		}
		
		String[] rank = new String[temp.length];
		for(int i = 0; i < temp.length; i++) {
			rank[temp.length-1-i] = heap[i].toString();
		}
		heap = temp;
		heapSize = temp.length;
		return rank;
		
	}
	
	/**
	 * @param id - the Email identifier
	 * @return - the String array of the content of the provided Email ID
	 * This method returns the words of an Email stored in this PriorityQueue with the provided ID.
	 * O(n)
	 */
	public String[] getWords(String id) {
		
		int index = this.searchHeap(id);
		if(index == -1) {
			return null;
		} else {
			return heap[index].getWords();
		}
		
	}
	
	/**
	 * @return - the heap size
	 * O(1)
	 */
	public int size() {
		return this.heapSize;
	}
	
	/**
	 * @return - the String array of all email toString
	 * O(n)
	 */
	public String[] heapToString() {
		
		String[] temp = new String[heapSize];
		for(int i = 0; i < heapSize; i++) {
			temp[i] = heap[i].toString();
		}
		return temp;
		
	}
}
