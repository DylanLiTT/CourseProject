package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.*;
import java.util.Arrays;
class StudentQueueTest {

	@Test
	void testInsertAndRank() {
		PriorityQueue q = new PriorityQueue();
		Email a = new Email("a", "1");
		a.setSpamScore(6);
		Email b = new Email("b", "3");
		b.setSpamScore(7);
		Email c = new Email("c", "7");
		c.setSpamScore(10);
		Email d = new Email("d", "6");
		d.setSpamScore(3);
		Email e = new Email("e", "11");
		e.setSpamScore(9);
		q.insert(a);
		q.insert(b);
		q.insert(c);
		q.insert(d);
		q.insert(e);
		assertEquals("[c -- 10, e -- 9, b -- 7, d -- 3, a -- 6]", Arrays.toString(q.heapToString()));
		assertEquals("[c -- 10, e -- 9, b -- 7, a -- 6, d -- 3]", Arrays.toString(q.rankEmails()));
		
	}
	
	@Test
	void testUpdateScoreAndMax() {
		PriorityQueue q = new PriorityQueue();
		Email a = new Email("a", "1");
		a.setSpamScore(6);
		Email b = new Email("b", "3");
		b.setSpamScore(7);
		Email c = new Email("c", "7");
		c.setSpamScore(10);
		Email d = new Email("d", "6");
		d.setSpamScore(3);
		Email e = new Email("e", "11");
		e.setSpamScore(9);
		q.insert(a);
		q.insert(b);
		q.insert(c);
		q.insert(d);
		q.insert(e);
		assertEquals(10, q.getMaximumSpamScore());
		q.updateSpamScore("a", 11);
		assertEquals("[a -- 11, c -- 10, b -- 7, d -- 3, e -- 9]", Arrays.toString(q.heapToString()));
		assertEquals("a", q.extractMaximum().getID());
	}
	
	

}
