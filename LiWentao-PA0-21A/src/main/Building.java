/**
 * This is the class of the building object and it contains a array of all floors and a elevator.
 * Known Bugs: NONE
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 1/22/2019
 * COSI 21A PA0
 */
package main;
public class Building {
	
	private int numFloors;
	private Floor[] floors;
	private Elevator lift = new Elevator(this);
	
	
	/**
	 * @param numFloors - the floor numbers of the building
	 * This is the constructor of the building.
	 */
	public Building(int numFloors) {
		
		this.numFloors = numFloors;
		floors = new Floor[numFloors + 1];
		
	}
	
	/**
	 * @param person - the person who wants to request a elevator job.
	 * @param floor - the floor the person wants to go.
	 * @return - whether the job can be created.
	 * This method will handle the request made by a Person to enter the building and be taken to some floor
	 */
	public boolean enterElevatorRequest(Person person, int floor) {
		
		
		if(floor > numFloors || floor < 1) {
			
			return false;
			
		} else {
			
			lift.createJob(person, floor);
			person.waitingService();
			return true;
			
		}
		
		
	}
	
	/**
	 * This method is used to make the elevator start to work and process all the jobs.
	 */
	public void startElevator() {
		
		lift.processAllJobs();
		
	}
	
	/**
	 * @param person - the person who are sent to the target floor.
	 * @param floor - the target floor of the person.
	 * This method should save a reference of a Person in the Floor with the provided floor number.
	 */
	public void enterFloor(Person person, int floor) {
	
		if(floors[floor] == null) {
			
			floors[floor] = new Floor();
			floors[floor].enterFloor(person);
			
		} else {
			
			floors[floor].enterFloor(person);
			
		}
		
	}
	
	/**
	 *This method reports the building personnel.
	 */
	public String toString() {
		String str = "";
		if(floors[0] != null) {
			str += "Building Personnel: \n" + "Lobby: " + floors[0].toString() + "\n";
		} else {
			str += "Building Personnel: \n" + "Lobby: " + "No Person" + "\n";
		}
		
		for(int i = 1; i <= numFloors; i++) {
			if(floors[i] != null) {
				str += "Floor " + i + ": " + floors[i].toString() + "\n";
			} else {
				str += "Floor " + i + ": " +  "No Person" + "\n";
			}
		}
		
		return str;
		
	}

}
