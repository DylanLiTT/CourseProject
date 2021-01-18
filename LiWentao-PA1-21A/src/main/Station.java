/**
 * This class represents a Station on the red line.
 * A Station should track which Trains and Riders are waiting to go north or south. 
 * Known Bugs: None
 *
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 2/27/2020
 * COSI 21A PA1
 */
package main;

public class Station {

	public Queue<Rider> northBoundRiders;
	public Queue<Rider> southBoundRiders;
	public Queue<Train> northBoundTrains;
	public Queue<Train> southBoundTrains;
	private String name;
	
	/**
	 * @param name - the String that represents the name of the station.
	 * This is a constructor of the Station, and initializes four empty queues.
	 */
	public Station(String name) {
		this.name = name;
		this.northBoundRiders = new Queue<Rider>(20);
		this.southBoundRiders = new Queue<Rider>(20);
		this.northBoundTrains = new Queue<Train>(20);
		this.southBoundTrains = new Queue<Train>(20);
	}
	
	/**
	 * @param r - the rider object that needs to get in the station.
	 * @return - return whether the rider can be entered into the station.
	 * O(1)
	 */
	public boolean addRider(Rider r) {

		if(r.getStarting().equals(name)) {
			if(r.goingNorth()) {
				northBoundRiders.enqueue(r);
			} else {
				southBoundRiders.enqueue(r);
			}
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * @param t - the train object that needs to be added to the station.
	 * @return - the String that contains the info of people who get off here.
	 * O(n) - disembarkPassengers() is O(n)
	 */
	public String addTrain(Train t) {
		
		String str = this.name + " Disembarking Passengers: \n" +
					 t.disembarkPassengers();
		
		if(t.goingNorth()) {
			this.northBoundTrains.enqueue(t);
		}else {
			this.southBoundTrains.enqueue(t);
		}
		
		return str;
		
	}
	
	/**
	 * @return - a Train object that move southward with as many passengers as possible.
	 * prepare a southbound Train of passengers by performing the following steps:
	 * 1) Dequeuing a train from the southbound train queue.
	 * 2) Adding as many southbound Riders to the Train as possible.
	 * 3) Return the train that has been filled or null if no Train could be filled.
	 * O(n)
	 */
	public Train southBoardTrain() {
		
		if(southBoundTrains.size() != 0) {
			
			Train t = southBoundTrains.front();
			southBoundTrains.dequeue();
			
			while(t.hasSpaceForPassengers() && southBoundRiders.size() != 0) {	
				t.addPassenger(southBoundRiders.front());
				southBoundRiders.dequeue();
			}
			
			return t;
			
		}
		return null;
		
	}
	
	/**
	 * @return - a Train object that move northward with as many passengers as possible.
	 * prepare a northbound Train of passengers by performing the following steps:
	 * 1) Dequeuing a train from the northbound train queue.
	 * 2) Adding as many northbound Riders to the Train as possible.
	 * 3) Return the train that has been filled or null if no Train could be filled.
	 * O(n)
	 */
	public Train northBoardTrain() {

		if(northBoundTrains.size() != 0) {
			
			Train t = northBoundTrains.front();
			northBoundTrains.dequeue();
			
			while(t.hasSpaceForPassengers() && northBoundRiders.size() != 0) {	
				t.addPassenger(northBoundRiders.front());
				northBoundRiders.dequeue();
			}
			
			return t;
			
		}
		return null;
		
	}
	
	/**
	 * This method changes the direction of the first waiting northbound Train 
	 * and moves it to the southbound queue.
	 * O(1)
	 */
	public void moveTrainNorthToSouth() {
		
		if(northBoundTrains.size() != 0) {
			Train t = northBoundTrains.front();
			t.swapDirection();
			northBoundTrains.dequeue();
			southBoundTrains.enqueue(t);
		}
		
	}
	
	/**
	 * This method changes the direction of the first waiting southbound Train 
	 * and moves it to the northbound queue.
	 * O(1)
	 */
	public void moveTrainSouthToNorth() {
		
		if(southBoundTrains.size() != 0) {
			
			Train t = southBoundTrains.front();
			t.swapDirection();
			southBoundTrains.dequeue();
			northBoundTrains.enqueue(t);
			
		}
		
	}
	
	/**
	 * This method returns his should return the name and status of the station.
	 * O(1)
	 */
	@Override
	public String toString() {
		
		String str = "Station: " + 
					  this.stationName() + 
					  "\n" + northBoundTrains.size() + " north-bound trains waiting" +
					  "\n" + southBoundTrains.size() + " south-bound trains waiting" +
					  "\n" + northBoundRiders.size() + " north-bound passengers waiting" +
					  "\n" + southBoundRiders.size() + " south-bound passengers waiting";
		return str;
		
	}
	
	/**
	 * @return - the station's name
	 * O(1)
	 */
	public String stationName() {
		return name;
	}
	
	/**
	 *Checks if a Station is equal to some object based on name.
	 *O(1)
	 */
	@Override
	public boolean equals(Object o) {
		
		if(o instanceof Station) {
			Station s = (Station) o;
			if(this.stationName().equals(s.stationName())) {
				return true;
			}
		}
		return false;
		
		
	}
}
