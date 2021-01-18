package main;

/**
* This class provides the functionality of a splay tree node discussed in class (adding, searching, deleting, etc.).
* Known Bugs: None
*
* @author Wentao Li
* wentaoli@brandeis.edu
* 4/11/2020
* COSI 21A PA2
*/
public class WorkEntrySearchNode implements Comparable<WorkEntrySearchNode> {
	
	public WorkEntrySearchNode left; // KEEP THIS PUBLIC 

	public WorkEntrySearchNode right; // KEEP THIS PUBLIC 

	public WorkEntrySearchNode parent; // KEEP THIS PUBLIC
	
	private WorkEntry[] entries;  //entries stored in this node
	
	private int entryCount;      //number of entries in the node
	
	private String activity;     //activity of this node
	public WorkEntrySearchNode(String activity) {

		this.left = null;
		this.right = null;
		this.parent = null;
		this.entries = new WorkEntry[0];
		this.entryCount = 0;
		this.activity = activity;
		
	}
	
	/**
	 * This method compares two nodes based on their activity keys.
	 * O(1)
	 */
	public int compareTo(WorkEntrySearchNode other) {
		
		return this.getActivity().compareTo(other.getActivity());
		
	}
	
	/**
	 * @param node - the key node needs to be found
	 * @return - the node of key or where the node supposed to be 
	 * This should search for a node in the splay tree rooted 
	 * at this node and return the node that matches or the last node encountered in the search.
	 * In the end, splay the node.
	 * WorstCase: O(logn)
	 */
	public WorkEntrySearchNode search(WorkEntrySearchNode node) {
		
		WorkEntrySearchNode temp = this;
		WorkEntrySearchNode pointer = this;
		
		while(temp != null && temp.getActivity().compareTo(node.getActivity()) != 0) {
			
			pointer = temp;
			if(node.getActivity().compareTo(temp.getActivity()) < 0) {
				temp = temp.left;
			} else {
				temp = temp.right;
			}
		}
		
		if(temp == null) {
			temp = pointer;
		}
		temp.splay();
		return temp;
			
	}
	
	/**
	 * @param node - the key node needs to be found
	 * @return - the node of key or where the node supposed to be 
	 * This should search for a node in the splay tree rooted 
	 * at this node and return the node that matches or the last node encountered in the search.
	 * Worst case O(h)
	 */
	public  WorkEntrySearchNode binarySearch(WorkEntrySearchNode node) {
		
		if(this.activity == node.getActivity()) {
			
			return this;
			
		} else {
			
			if(this.compareTo(node) < 0) {
				if(this.right == null) {
					return this;
				}else {
					return this.right.binarySearch(node);
				}
			} else {
				if(this.left == null) {
					return this;
				}else {
					return this.left.binarySearch(node);
				}
			}	
			
		}
		
		
	}
	
	
	/**
	 * @param node - the node needs to be inserted
	 * @return - the node that inserted
	 * This method should insert a node into the splay tree rooted at this node 
	 * and return what gets inserted or a node already in the tree that matches this key.
	 * In the end, splay the node.
	 * O(logn)
	 */
	public WorkEntrySearchNode insert(WorkEntrySearchNode node) {
		
		WorkEntrySearchNode temp  = this.binarySearch(node);
		if(temp.compareTo(node)  == 0) {
			
			temp.splay();
			return temp;
			
		} else {
			
			if(temp.compareTo(node) < 0) {
				temp.right =  node;
				node.parent = temp;
				node.splay();
				return node;
			} else {
				temp.left = node;
				node.parent = temp;
				node.splay();
				return node;
			}
			
		}
		
	}
	
	/**
	 * This method should return an inorder traversal of the tree rooted 
	 * at this node with the nodes’ keys on separate lines.
	 * O(n)
	 */
	public String toString() {
		
		StringBuilder string = new StringBuilder();
		string = this.inOrderToString(string);
		return string.toString();
		
	}
	
	/**
	 * @param str - StringBuilder Object
	 * @return - the updated StringBuilder
	 * The recursive helper method that traverse the tree in order.
	 * O(n)
	 */
	public StringBuilder inOrderToString(StringBuilder str) {
		
		if(this.left != null) this.left.inOrderToString(str);
		str.append(this.activity + "\n");
		if(this.right != null) this.right.inOrderToString(str);
		
		return str;
		
	}
	
	/**
	 * @return - a String describes the shape of the tree
	 * This method should return a String representation using parentheses to display the structure of the tree as described in the examples 
	 * O(n)
	 */
	public String getStructure() {
		StringBuilder str = new StringBuilder();
		str = this.structurer(str);
		return str.toString();
	}
	
	/**
	 * @param str - a StringBuilder object
	 * @return a updated StringBuilder object
	 * This method is a helper method of the getStructure.
	 * O(n)
	 */
	public StringBuilder structurer(StringBuilder str) {
		
		if(this.left != null) {
			str.append("(");
			this.left.structurer(str);
			str.append(")");
		}
		str.append(this.getActivity());
		if(this.right != null) {
			str.append("(");
			this.right.structurer(str);
			str.append(")");
		}
		
		return str;
		
	}
	
	/**
	 * @param e - thw WorkEntry object that needs to be added to the node
	 * This method adds a WorkEntry to this node. Entries should be stored in the same order in which they were added from the input CSV file.
	 * O(n)
	 */
	public void add(WorkEntry e) {
		
		//entend the WorkEntry array when it is full
		if(entryCount == entries.length) {
			
			WorkEntry[] temp = new WorkEntry[entryCount+1];
			for(int i = 0; i < entries.length; i++) {
				
				temp[i]  = entries[i];
				
			}
			entries = temp;
		}
		entries[entryCount] = e;
		entryCount++;
		
	}
	
	/**
	 * @param i - the index of the WorkEntry
	 * @return - the node that deleted one entry
	 * This method should remove the ith WorkEntry stored in this node.
	 * O(logn)
	 */
	public WorkEntrySearchNode del(int i) {
		
		// check the index validation
		if(i >= entryCount || i < 0) {
			throw new IndexOutOfBoundsException("[del] command  received an invalid index");
		}
		
		//remove the ith entry and shift all the data
		entries[i] = null;
		if(i != entryCount-1) {
			
			for(int j = i+1; j < entryCount; j++) {
				
				entries[j-1] = entries[j];
				
			}
			entries[entryCount-1] = null;
		}
		entryCount--;
		
		if(entryCount == 0) {
			
			this.splay();
			if(this.left == null && this.right == null) {
				
				return null;
				
			} else if(this.left == null) {
				
				return this.right;
				
			} else{
				
				WorkEntrySearchNode temp = this.left;
				while(temp.right != null) {
					
					temp = temp.right;
					
				}
				
				temp.splay();
				if(this.right !=null) {
					this.right.parent = temp;
				}
				temp.right = this.right;
				return temp;
			}
				
				
		} else {
			
			
			return this;
			
		}
		
		
	}
	
	
	
	
	/**
	 * @return - data string
	 * This method returns the String representations of the WorkEntry objects associated with this node 
	 * on separate lines followed by the total time spend on these entries rounded to one decimal place
	 * O(n)
	 */
	public String getEntryData() {
		
		String data = "";
		double time = 0.0;
		
		for(int i = 0; i < entryCount; i++) {
			data +=  entries[i].toString() + "\n";
			time += entries[i].getTimeSpent();
		}
		
		data += "Total: " + time + " h\n";
		
		return data;
	}
	
	/**
	 * @return - a string
	 * This method will return a level order traversal of the tree 
	 * where each node’s key is displayed on a separate line.
	 * O(n)
	 */
	public String getByRecent() {
		
		Queue<WorkEntrySearchNode> q = new Queue<WorkEntrySearchNode>();
		q.enqueue(this);
		String listr = "";
		while(q.size() != 0) {
			
			WorkEntrySearchNode temp = q.dequeue();
			listr += temp.getActivity() + "\n";
			if(temp.left != null) {
				q.enqueue(temp.left);
			}
			if(temp.right != null) {
				q.enqueue(temp.right);
			}
			
		}
		return listr;
	}
	
	/**
	 * @return - the string that represents the activity of this node
	 * O(1)
	 */
	public String getActivity() {
		
		return activity;
		
	}
	
	
	
	/**
	 * This method performs a single AVL right rotation to this node.
	 * O(1)
	 */
	public void rightRotation() {
		
		WorkEntrySearchNode node = this.left;
		this.left = node.right;
		if(node.right!= null) {
			node.right.parent = this;
		}
		node.parent = this.parent;
		if(this.parent != null) {
			
			if(this == this.parent.right) {
				this.parent.right = node;
			} else {
				this.parent.left = node;
			}
			
		}
		
		node.right = this;
		this.parent = node;
	}
	
	/**
	 * This method performs a single AVL left rotation to this node.
	 * O(1)
	 */
	public void leftRotation() {
		
		WorkEntrySearchNode node = this.right;
		this.right = node.left;
		if(node.left!= null) {
			node.left.parent = this;
		}
		node.parent = this.parent;
		if(this.parent != null) {
			
			if(this == this.parent.left) {
				this.parent.left = node;
			} else {
				this.parent.right = node;
			}
			
		}
		
		node.left = this;
		this.parent = node;
	}
	
	
	/**
	 * Splay this node to the root of the tree.
	 * O(1)
	 */
	public void splay() {
		while (this.parent != null) {
			
			if(this.parent.parent == null) {
				if(this == this.parent.left) {
					this.parent.rightRotation();
				} else {
					this.parent.leftRotation();
					
				} 
					
			}else if(this == this.parent.left  && this.parent == this.parent.parent.left) {
				this.parent.parent.rightRotation();
				this.parent.rightRotation();
				
			}else if(this ==  this.parent.right &&  this.parent == this.parent.parent.right){
				
				this.parent.parent.leftRotation();
				this.parent.leftRotation();
				
			}else if(this == this.parent.right && this.parent == this.parent.parent.left) {
				
				this.parent.leftRotation();
				this.parent.rightRotation();
				
			} else {
				this.parent.rightRotation();
				this.parent.leftRotation();
				
			}
		}
	}
	

}
