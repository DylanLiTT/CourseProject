package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.*;

class StudentElevatorTest {

	@Test
	void testStart() {
		
		Building house = new Building(3);
		Elevator lift = new Elevator(house);
		assertEquals("Elevator in Lobby", lift.toString());
		
	}
	
	@Test
	void testFinish() {
		
		Building house = new Building(3);
		Elevator lift = new Elevator(house);
		Person p1 = new Person("Tom", "Li");
		p1.enterBuilding(house, 3);
		house.startElevator();
		assertEquals("Elevator in Lobby", lift.toString()); 
		
	}

}
