/**
 * This is the class of the Floor object and contains an array of person objects that are currently on this floor.
 * Known Bugs: NONE
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 1/22/2019
 * COSI 21A PA0
 */
package main;
public class Floor {
	
	private Person[] people = new Person[1];
	private int counter = 0;
	
	/**
	 * @param person - Person who enters the floor.
	 * Add a reference of the person within the Floor object.
	 */
	public void enterFloor(Person person) {
		
		if(people.length == counter) {
			
			Person[] extendedPeople = new Person[counter + 1];
			for(int i = 0; i < people.length; i++) {
				
				extendedPeople[i] = people[i];
				
			}
			people = extendedPeople;
			
		}
		people[counter] = person;
		counter++;
		
	}
	
	/**
	 * Return the String that contains all people who are on the floor.
	 */
	public String toString() {
		
		String str = "";
		for(int i = 0; i < counter; i++) {
			
			str += i+1 + "." + people[i].getName() + " ";
			
		}
		return str;
		
	}
	
}
