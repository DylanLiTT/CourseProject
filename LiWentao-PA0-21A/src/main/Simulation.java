/**
 * This class contains a main method and simulates the building. It contains a building instance and several person Objects.
 * Known Bugs: NONE
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 1/22/2019
 * COSI 21A PA0
 */
package main;
public class Simulation {

	/**
	 * @param args
	 * This is the main method.
	 */
	public static void main(String[] args) {
		
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
		
		System.out.println(house.toString());

	}

}
