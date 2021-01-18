package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.*;

class StudentBuildingTest {

	@Test
	void testEmpty() {
		Building house = new Building(3);
		assertEquals("Building Personnel: \n" + 
					 "Lobby: No Person\n" + 
					 "Floor 1: No Person\n" + 
					 "Floor 2: No Person\n" + 
					 "Floor 3: No Person\n", house.toString());
	}
	
	@Test
	void testPeople() {
		Building house = new Building(3);
		Person p1 = new Person("Tom", "Li");
		Person p2 = new Person("Ryan", "Huang");
		Person p3 = new Person("Eddie", "He");
		Person p4 = new Person("George", "Ye");
		Person p5 = new Person("Yifeng", "Song");
		Person p6 = new Person("Wentao", "Li");
		Person p7 = new Person("Jeff", "Jiang");
		Person p8 = new Person("Dylan", "Zhu");
		
		p1.enterBuilding(house, 2);
		p2.enterBuilding(house, 3);
		p3.enterBuilding(house, 1);
		p4.enterBuilding(house, 0);
		p5.enterBuilding(house, 1);
		p6.enterBuilding(house, 2);
		p7.enterBuilding(house, 3);
		p8.enterBuilding(house, 2);
		
		house.startElevator();
		assertEquals("Building Personnel: \n" +
					 "Lobby: 1.George Ye \n" +
					 "Floor 1: 1.Eddie He 2.Yifeng Song \n" +
					 "Floor 2: 1.Tom Li 2.Wentao Li 3.Dylan Zhu \n" +
					 "Floor 3: 1.Ryan Huang 2.Jeff Jiang \n", house.toString());
		
	}
}
