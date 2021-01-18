/**
 * This is the class of the Job object, 
 * and job object represents a request to the elevator and holds the person and the target floor of the job.
 * Known Bugs: NONE
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 1/22/2019
 * COSI 21A PA0
 */
package main;
public class Job {
	
	private Person person;
	private int floor;
	
	/**
	 * @param person - the person who request job
	 * @param floor	- the floor the person wants to go
	 * This is the constructor of Job object.
	 */
	public Job(Person person, int floor) {
		
		this.person = person;
		this.floor = floor;
		
	}
	
	/**
	 * @return - the person who request the job
	 */
	public Person getPerson() {
		
		return person;
		
	}
	
	/**
	 * @return - the floor number the person wants to go
	 */
	public int getFloor() {
		
		return floor;
		
	}
	
	/**
	 * Report the basic info of the job.
	 */
	public String toString() {
		
		return "The job of the elevator is to send " + person.getName() + " to floor " + floor;
		
	}
	
}