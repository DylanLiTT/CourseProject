/**
 * This class will be used in the implementation of the HashTable class as the data type of the internal table array.
 * Known Bugs: None
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * <May 8th, 2020>
 * COSI 21A PA3
*/

package main;

public class Entry<V> {
	
	private String key;
	private V value;

	/**
	 * @param key - the key String value
	 * @param value - the value stored in the entry
	 * This is the constructor of the entry class.
	 */
	public Entry(String key, V value) {
		
		this.key = key;
		this.value = value;
		
	}
	
	/**
	 * @return - the key String
	 * O(1)
	 */
	public String getKey() {
		return this.key;
	}
	
	/**
	 * @return - the entry value
	 * O(1)
	 */
	public V getValue() {
		return this.value;
	}
	
	/**
	 * @param value - the updated data value
	 * O(1)
	 */
	public void setValue(V value) {
		this.value = value;
	}
	
	public String toString() {
		return key + " " + this.value ;
	}
}
