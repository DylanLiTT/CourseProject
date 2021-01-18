package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.*;

class StudentJobTest {

	@Test
	void testJob1() {
		
		Person p1 = new Person("Tom", "Li");
		Job job1 = new Job(p1, 3);
		assertEquals("The job of the elevator is to send Tom Li to floor 3", job1.toString());
		
	}
	
	@Test
	void testJob2() {
		
		Person p2 = new Person("Carrie", "Li");
		Job job2 = new Job(p2, 3);
		assertEquals("The job of the elevator is to send Carrie Li to floor 3", job2.toString());
		
	}

}
