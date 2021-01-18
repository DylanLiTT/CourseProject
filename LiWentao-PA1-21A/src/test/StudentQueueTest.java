package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.*;

class StudentQueueTest {

	@Test
	void testEmpty() {
		Queue<Integer> q = new Queue<Integer>(10);
		assertEquals("", q.toString());
	}
	
	@Test
	void testEnqueueAndDequeue() {
		Queue<Integer> q = new Queue<Integer>(10);
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(4);
		q.dequeue();
		assertEquals(2, q.front());
		assertEquals("2\n4\n", q.toString());
	}
	

}
