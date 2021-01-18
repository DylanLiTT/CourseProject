/**
 * This class represents a Train on the red line. 
 * All Trains hold a specified number of passengers in an array, 
 * the current number of passengers, the current Station where the Train is, 
 * and the Train’s current direction. 
 * Known Bugs: None
 *
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 2/27/2020
 * COSI 21A PA1
 */
package main;

public class Train {

	public static final int TOTAL_PASSENGERS = 10;
	public Rider[] passengers;
	public int passengerIndex;
	private String currentStation;
	private int direction;
	
	/**
	 * @param currentStation - the station where the train is in
	 * @param direction - the moving direction of the train
	 * This is the constructor of the train.
	 */
	public Train(String currentStation, int direction) {
		
		this.passengers = new Rider[TOTAL_PASSENGERS];
		this.passengerIndex = 0;
		this.currentStation = currentStation;
		this.direction = direction;
		
	}
	
	/**
	 * @return - whether the train is going north.
	 * O(1)
	 */
	public boolean goingNorth() {
		
		if(direction == 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * Change the direction of the train.
	 * O(1)
	 */
	public void swapDirection() {
		
		if(direction == 1) {
			direction = 0;
		}else {
			direction = 1;
		}
	}
	
	/**
	 * @return - the passengers on the train with their basic info
	 * This method returns a String of the current passengers on the Train. 
	 * O(n)
	 */
	public String currentPassengers() {
		
		String str = "";
		for(int i = 0; i < passengerIndex; i++) {
			
			str += passengers[i].getRiderID() + ", " + passengers[i].getDestination() + "\n";
			
		}
		return str;

	}
	
	/**
	 * @param r - boarding rider
	 * @return - whether the train will accpet the rider.
	 * This method adds a passenger to the Train as long as 
	 * (i) the Rider is at the correct Station to enter the Train, 
	 * (ii) the Train is going in the appropriate direction, and 
	 * (iii) there is space on the Train. Return true if the addition was completed. 
	 * Else, return false.
	 * O(1)
	 */
	public boolean addPassenger(Rider r) {
		
		if(r.getStarting().equals(currentStation) && r.goingNorth() == this.goingNorth() && this.hasSpaceForPassengers()) {
			
			passengers[passengerIndex] = r;
			passengerIndex++;
			return true;
			
		} else {
			
			return false;
			
		}
	}
	
	/**
	 * @return - check whether the train is full.
	 * O(1)
	 */
	public boolean hasSpaceForPassengers() {
		
		return passengerIndex < TOTAL_PASSENGERS;
		
	}
	
	/**
	 * @return - the info of passengers who are getting off in a specific station
	 * This should remove all of the passengers who should be exiting the Train at the Train’s current station. 
	 * It should also return a String of the removed passengers. 
	 * O(n)
	 */
	public String disembarkPassengers() {
		
		String str = "";
		for(int i = 0; i < passengerIndex; i++) {
			if(passengers[i].getDestination().equals(currentStation)) {
				if(i != passengerIndex-1) {    // swap the middle getting-off person with the last passengers, so the null spots are always in the end
					Rider temp = passengers[passengerIndex-1];
					str += passengers[i].getRiderID() + "\n";
					passengers[i] = temp;
					passengers[passengerIndex-1] = null;
					passengerIndex--;
				}else {
					str += passengers[i].getRiderID() + "\n";
					passengers[i] = null;
					passengerIndex--;
				}
			}
		}
		return str;
		
	}
	
	/**
	 * @param s - the name of the next station
	 * O(1)
	 */
	public void updateStation(String s) {
		
		currentStation = s;
		
	}
	
	/**
	 * @return - where is the train
	 * O(n)
	 */
	public String getStation() {
		return currentStation;
	}
	
	/**
	 * returns a String representation of this Train.
	 */
	@Override
	public String toString() {
		
		String str = "Direction: ";
		if(this.goingNorth()) {
			str += "Northbound\n" + "Passengers: \n";
		} else {
			str += "Southbound\n" + "Passengers: \n";
		}

		if(passengerIndex != 0) {
			for (int i = 0; i < passengerIndex; i++){
				
				
				str += passengers[i].toString() + "\n";
			}
			
		}
		
		return str + "Current station: " + this.getStation();
	}
}
