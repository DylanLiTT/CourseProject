package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.*;

class StudentTreeTest {

	@Test
	void testInsert() {
		WorkEntrySearchNode root = new WorkEntrySearchNode("a");
		WorkEntrySearchNode node1 = new WorkEntrySearchNode("b");
		WorkEntrySearchNode node2 = new WorkEntrySearchNode("c");
		WorkEntrySearchNode node3 = new WorkEntrySearchNode("d");
		WorkEntrySearchNode node4 = new WorkEntrySearchNode("e");
		root = root.insert(node1);
		root = root.insert(node2);
		root = root.insert(node3);
		root = root.insert(node4);

		assertEquals("e\nd\nc\nb\na\n", root.getByRecent());
		
	}
	
	@Test
	void testSplayAndToString() {
		WorkEntrySearchNode root = new WorkEntrySearchNode("a");
		WorkEntrySearchNode node1 = new WorkEntrySearchNode("e");
		WorkEntrySearchNode node2 = new WorkEntrySearchNode("g");
		WorkEntrySearchNode node3 = new WorkEntrySearchNode("b");
		WorkEntrySearchNode node4 = new WorkEntrySearchNode("d");
		root = root.insert(node1);
		root = root.insert(node2);
		root = root.insert(node3);
		root = root.insert(node4);

		assertEquals("d\nb\ne\na\ng\n", root.getByRecent());
		
	}
	
	@Test
	void testStructure() {
		WorkEntrySearchNode root = new WorkEntrySearchNode("a");
		WorkEntrySearchNode node1 = new WorkEntrySearchNode("b");
		WorkEntrySearchNode node2 = new WorkEntrySearchNode("c");
		root = root.insert(node1);
		root = root.insert(node2);
		assertEquals("((a)b)c", root.getStructure());
		
	}

}
