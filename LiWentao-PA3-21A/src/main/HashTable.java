/**
 * This class provides the functionality of a generic hash table.
 * Known Bugs: None
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * <May 8th, 2020>
 * COSI 21A PA3
*/

package main;

public class HashTable<V> {
	
	
	// KEEP THIS PUBLIC 
	public Entry<V>[] entries;
	
	// KEEP THIS PUBLIC AND NOT FINAL 
	// However, feel free to change the load factor 
	public static double LOAD_FACTOR = 0.5;
	
	private int size;
	
	private int[] deleted;
	private int delnum;
	
	
	@SuppressWarnings("unchecked")
	public HashTable() {
		
		// Use this to create generic arrays of type "Entry<V>" 
		// feel free to change the size of this array from 11 
		entries = (Entry<V>[]) new Entry[10];
		this.size = 0;
		this.deleted = new int[0];
		this.delnum = 0;
		
	}
	
	/**
	 * @param key - the key needs to be searched
	 * @return - the data value of the key or return the null if not found
	 * O(n)
	 */
	public V get(String key) {
		
		int target = searchHash(key);
		if(target != -1) {
			return entries[target].getValue();
		}
		return null;
		
	}
	
	/**
	 * @param key - the key neeeds to be searched
	 * @return - the index of the key
	 * This method finds the ideal key index in the array.
	 * O(n)
	 */
	public int searchHash(String key) {
		
		int ascii = (int) key.charAt(key.length()-1);
		int spot = ascii % entries.length;
		int newSpot = spot;
		int i = 1;
		while(checkDelete(newSpot) || entries[newSpot] != null) {
			
			if(!checkDelete(newSpot)) {
				if(entries[newSpot].getKey().equals(key)) {
					return newSpot;
				}
			}
			newSpot = (spot + i*(7-(ascii%7))) % entries.length;
			i++;
			
		}
		
		return -1;
	}

	/**
	 * @param target - the index needs to to be checked whether it is deleted before
	 * @return - boolean
	 * O(n)
	 */
	private boolean checkDelete(int target) {
		
		for(int i = 0; i < delnum; i++) {
			if(target == deleted[i]) return true;
		}
		return false;
	}

	/**
	 * @param key - the entry key
	 * @return - the supposed spot of this entry key
	 * This method finds the supposed spot of a entry key;
	 * O(n)
	 */
	public int insertHash(Entry[] entries, String key) {
		
		int ascii = (int) key.charAt(key.length()-1);
		int spot = ascii % entries.length;
		int i = 0;
		while(true) {
			
			if(entries[(spot + i*(7-(ascii%7))) % entries.length] == null) {
				return (spot + i*(7-(ascii%7))) % entries.length;
			}
			i++;
			
		}
		
	}

	/**
	 * @param key - the new entry key
	 * @param value - the new entry value
	 * This method puts the new entry into the hashtable.
	 */
	public void put(String key, V value) {
		
		if(this.get(key) != null) {
			
			entries[this.searchHash(key)].setValue(value);
			
		} else {
			
			int target = this.insertHash(this.entries, key);
			entries[target] = new Entry(key, value);
			size++;
			double lf = ((double)size) / entries.length;
			if(lf > LOAD_FACTOR) {
				this.rehash();
			}
			
		}
		
	}
	
	/**
	 * Rehash the table when the loadfactor is passed.
	 * 
	 */
	private void rehash() {
		
		Entry[] temp = new Entry[entries.length*2];
		for(int i = 0; i < entries.length ; i++) {
			if(entries[i] != null) {

				int target = insertHash(temp, entries[i].getKey());				
				temp[target] = new Entry(entries[i].getKey(), entries[i].getValue());
			}
		}
		this.entries = temp;
		this.deleted = new int[0];
		this.delnum = 0;
		
	}

	/**
	 * @param key - entry that needs to be deleted with key
	 * @return - the deleted entry data
	 * O(n)
	 */
	public V delete(String key) {
		
		V value = this.get(key);
		if(value != null) {
			int target = this.searchHash(key);
			entries[target] = null;
			addDeleteMark(target);
			this.size--;
		}
		
		return value;
	}
	
	/**
	 * @param target - the spot which is null but has been deleted 
	 * This methods add a delete mark to a specific spot.
	 * O(n)
	 */
	public void addDeleteMark(int target) {
		
		if(delnum == deleted.length) {
			int[] temp = new int[deleted.length + 1];
			for(int i = 0; i < deleted.length; i++) {
				temp[i] = deleted[i];
			}
			deleted = temp;
		}
		deleted[delnum] = target;
		delnum++;
		
	}

	/**
	 * @return - a String array that contains all the keys in the hashtable.
	 * O(n)
	 */
	public String[] getKeys() {
		String[] keys = new String[size];
		int count = 0;
		for(int i = 0; i < entries.length; i++) {
			if(entries[i] != null) {
				keys[count] = entries[i].getKey();
				count++;
			}
		}
		return keys;
				
	}
	
	/**
	 * @return - the entry number
	 * O(1)
	 */
	public int size() {
		return size;
	}
}
