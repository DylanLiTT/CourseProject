package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.*;

class StudentPersonTest {

	@Test
	void testDefault() {
		
		Person p1 = new Person("Tom", "Li");
		assertEquals("Tom Li In Lobby", p1.toString());
	}
	
	@Test
	void testEnterFloor() {
		
		Building house = new Building(3);
		Person p1 = new Person("Tom", "Li");
		p1.enterBuilding(house, 3);
		house.startElevator();
		assertEquals("Tom Li In Floor 3", p1.toString());
		
	}

}
