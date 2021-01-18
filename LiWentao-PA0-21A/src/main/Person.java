/**
 * This is the class of the Person object, and the person object has the first name, the last name and
 * their location regarding the building.
 * Known Bugs: NONE
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 1/22/2019
 * COSI 21A PA0
 */
package main;
public class Person {
	
	private  String firstName;
	private  String lastName;
	private  int location;
	public boolean onFloor = false;        //whether the person has arrived on their target floor.
	public boolean waitService = false;	   //whether the person is waiting to be serviced.
	
	
	/**
	 * @param firstName - the first name of the person
	 * @param lastName - the last name of the person
	 * This is the person object's constructor.
	 */
	public Person(String firstName, String lastName) {

		this.firstName = firstName;
		this.lastName = lastName;
		
	}

	/**
	 * @param building - the building the person is going to enter.
	 * @param floor - the floor the person wants to go.
	 * @return - whether the request of the person can be fulfilled.
	 * This method calls the building object's enterElevatorRequest method and try to see
	 * whether the building will accept the person's request.
	 */
	public boolean enterBuilding(Building building, int floor) {


		if(floor == 0) {          // Solve the case if the person's target floor is the lobby.
			
			building.enterFloor(this, 0);
			location = 0;
			onFloor = true;
			return true;
			
		} else {
			
			return building.enterElevatorRequest(this, floor);
			
		}
		
	}
	
	/**
	 * @return - the current location of the person.
	 * This should return this Person’s location as a String.
	 */
	public String getLocation() {
		
		if(!onFloor && !waitService) {     // the case the person object has just be created and does not send a job to the elevator.
			
			return "In Lobby";
			
		} else if(onFloor == true && location == 0) {   // the case the person's destination is the lobby.
			
			return "In Lobby";
			
		} else if (onFloor == true && location != 0){    // the case the person entered a specific floor.
			
			return "In Floor " + location;
			
		}else {     // the case that the person is still in the lobby and waiting to be serviced.
			
			return "Waiting to be serviced";
			
		}
		
		
	}
	
	/**
	 * @return - the name of the person
	 */
	public String getName() {
		
		return firstName + " " + lastName;
		
	}
	


	/**
	 * @param floor - the floor the person just entered
	 * This method is called when a person is sent to his or her target floor.
	 */
	public void onFloor(int floor) {
		
		this.waitingService();
		onFloor = true;
		location = floor;
		
	}

	/**
	 * The method is called to change the service status of the person.
	 */
	public void waitingService() {
		
		if (waitService) {
			
			waitService = false;
			
		} else {
			
			waitService = true;
			
		}
		
	}
	
	public String toString() {
		
		return this.getName() + " " + this.getLocation();
		
	}
}
