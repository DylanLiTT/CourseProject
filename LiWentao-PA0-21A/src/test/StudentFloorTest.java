package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.*;

class StudentFloorTest {

	@Test
	void testEmpty() {
		
		Floor floor1 = new Floor();
		assertEquals("", floor1.toString());
		
	}
	
	@Test
	void testFloor() {
		
		Floor floor1 = new Floor();
		Person p1 = new Person("Tom", "Li");
		Person p2 = new Person("Ryan", "Huang");
		Person p3 = new Person("Eddie", "He");
		floor1.enterFloor(p1);
		floor1.enterFloor(p2);
		floor1.enterFloor(p3);
		
		assertEquals("1.Tom Li 2.Ryan Huang 3.Eddie He ", floor1.toString());
		
	}

}
