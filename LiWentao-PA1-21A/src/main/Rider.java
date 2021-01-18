/**
 * This class represents a Rider on the red line. 
 * A rider should have an ID, starting station, destination station, and a direction.
 * Known Bugs: None
 *
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 2/27/2020
 * COSI 21A PA1
 */
package main;

public class Rider {
	private String Id;
	private String startingStation;
	private String destinationStation;
	private int direction;
	
	/**
	 * @param riderID - String that contains the id
	 * @param startingStation - boarding station of the rider
	 * @param destinationStation - destination of the rider
	 * This is the constructor of the Rider Object.
	 */
	public Rider(String riderID, String startingStation, String destinationStation) {
		
		this.Id = riderID;
		this.startingStation = startingStation;
		this.destinationStation = destinationStation;
		this.direction = 1;
		
	}
	
	/**
	 * @return - the boarding station of the rider.
	 * O(1)
	 */
	public String getStarting() {
		return startingStation;
	}
	
	/**
	 * @return - the destination of the rider
	 * O(1)
	 */
	public String getDestination() {
		return destinationStation;
	}
	
	/**
	 * @return - the id of the rider
	 * O(1)
	 */
	public String getRiderID() {
		return Id;
	}
	
	/**
	 * @return - whether the rider is going northward.
	 * O(1)
	 */
	public boolean goingNorth() {	
		
		if(direction == 0){
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * Change the moving direction of the rider.
	 * O(1)
	 */
	public void swapDirection() {
		
		if(direction == 0){
			direction = 1;
		}else {
			direction = 0;
		}
		
	}
	
	/**
	 * This method returns a String representation of this Rider.
	 * O(1)
	 */
	@Override
	public String toString() {
		return this.getRiderID() + ", " + this.getDestination();
	}
	
	/**
	 *This method checks if this Rider is equal to another Object based on ID
	 *O(1)
	 */
	@Override
	public boolean equals(Object s) {
		
		if(s instanceof Rider) {
			Rider p = (Rider) s;
			if(this.getRiderID().equals(p.getRiderID())) {
				return true;
			}
		}
		return false;
	}
}
