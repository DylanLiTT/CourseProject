package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.*;

class StudentDLLTest {

	@Test
	void testEmpty() {
		DoubleLinkedList d = new DoubleLinkedList();
		assertEquals("", d.toString());
	}
	
	@Test
	void testInt() {
		DoubleLinkedList d = new DoubleLinkedList();
		d.insert(1);
		d.insert(2);
		d.insert(3);
		d.insert(4);
		d.delete(3);
		
		assertEquals("1\n2\n4\n", d.toString());
	}
	
	

}
