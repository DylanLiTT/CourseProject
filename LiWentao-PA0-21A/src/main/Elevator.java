/**
 * This is the class of object Elevator. An Elevator will contain an array of Jobs 
 * where each Job holds a Person and the number of the floor which they desire to go to.
 * The elvator sends the person to their target floor.
 * Known Bugs: NONE
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 1/22/2019
 * COSI 21A PA0
 */
package main;
public class Elevator {
	/**
	 * This specifies the number of people that can be brought to their floors by an Elevator 
	 * instance at any given time. 
	 * <p>DO NOT REMOVE THIS FIELD</p>
	 * <p>You may edit it. But keep it public.</p>
	 */
	public static int maxOccupants = 3;
	private Job[] jobs = new Job[1];
	private int jobCounter = 0;
	private int currentLocation = 0;
	private Building building;
	
	/**
	 * @param building - the building object that the elevator belong to.
	 * This is a constructor of the elevator object.
	 */
	public Elevator(Building building) {
		
		this.building = building;
		
	}
	

	
	/**
	 * @param person - the person who wants to enter a floor.
	 * @param floor - the target floor
	 * This method adds a job to the jobs list of the elevator.
	 */
	public void createJob(Person person, int floor) {
		
		// extend the job list when the job list is full.
		if (jobs.length == jobCounter) {
			
			Job[] extendedJobs = new Job[jobCounter + 1];
			for(int i = 0; i < jobs.length; i++) {
				
				extendedJobs[i] = jobs[i];
				
			}
			
			jobs = extendedJobs;
			
		}
		
		jobs[jobCounter] = new Job(person, floor);
		jobCounter++;
		
	}
	
	/**
	 * This method is how the elevator process all the jobs.
	 * The elevator is sent to the lobby when finishing 3 jobs and its last job.
	 */
	public void processAllJobs() {
		
		int i = 0;
		
		while (i < jobCounter) {
			
			processJob(jobs[i]);
			if((i + 1) % maxOccupants == 0|| i == jobCounter - 1) {
				
				processJob(new Job(null, 0));
				
			}
			i++;
		}
	}
	
	/**
	 * @param job - the job the elevator is going to process.
	 * This is the method how the elevator sends a person to a specific floor.
	 */
	public void processJob(Job job) {
		
		if(job.getFloor() >= currentLocation) {
			
			for(int i = currentLocation; i <= job.getFloor(); i++) {
				
				System.out.println(this.toString());
				currentLocation++;
				
			}
			
		} else {
			
			for(int i = currentLocation; i >= job.getFloor(); i--) {
				
				System.out.println(this.toString());
				currentLocation--;
				
			}
			
		}
		
		currentLocation = job.getFloor();
		
		if(job.getPerson() != null) {
			
			this.exit(job.getPerson(), job.getFloor());
			
		}
		
		
	}
	
	/**
	 * @param person - the person just being sent
	 * @param floor - the floor the person entered
	 * This is method that calls the enterFloor of the building.
	 */
	public void exit(Person person, int floor) {

		building.enterFloor(person, floor);
		person.onFloor(floor);
		
	}
	
	/**
	 *Report the current location of the elevator.
	 */
	public String toString() {
		if(currentLocation == 0) {
			
			return "Elevator in Lobby";
			
		} else {
			
			return "Elevator on floor " + currentLocation;
			
		}
	}

	
}