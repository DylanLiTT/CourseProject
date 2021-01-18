package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import main.*;

class StudentTableTest {

	@Test
	void deleteTest() {
		
		HashTable table = new HashTable();
		table.put("a", 1);		//97
		table.put("ba", 2);		//97
		table.put("c", 2);		//99
		table.put("d", 2);		//100
		table.put("e", 2);		//101
		table.put("f", 2);		//102
		table.put("g", 2);		//103
		assertEquals(table.delete("a"), 1);
		assertEquals(Arrays.toString(table.getKeys()), "[d, e, f, g, ba, c]");
		
	}
	
	@Test
	void rehashTest() {
		HashTable table = new HashTable();
		table.put("a", 1);		//97
		table.put("ba", 2);		//97
		table.put("c", 2);		//99
		table.put("d", 2);		//100
		table.put("e", 2);		//101
		table.put("f", 2);		//102
		table.put("g", 2);		//103
		assertEquals(table.entries.length, 20);
		assertEquals(Arrays.toString(table.getKeys()), "[d, e, f, g, a, ba, c]");
	}

}
