/**
 * This class represents the red line railway and is made up of a list of Stations. 
 * Known Bugs: None
 *
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 2/27/2020
 * COSI 21A PA1
 */
package main;

public class Railway {

	public DoubleLinkedList<Station> railway;
	public String[] stationNames;
	private int stationCounter;
	
	/**
	 * This is the constructor of the railway object
	 */
	public Railway() {
		
		this.railway = new DoubleLinkedList();
		stationNames = new String[18];
		stationCounter = 0;
		
	}
	
	/**
	 * @param s - the station
	 * Add the station to the list and name to the array.
	 * O(n)
	 */
	public void addStation(Station s) {
		
		railway.insert(s);
		stationNames[stationCounter] = s.stationName();
		stationCounter++;
		
	}
	
	/**
	 * @param r - new rider object
	 * This method (i) sets a Rider’s direction based on the order of the Stations in the Railway and 
	 * (ii) adds the Rider to the appropriate Station in the Railway
	 * O(n)
	 */
	public void addRider(Rider r) {
		
		setRiderDirection(r);
		Station s = new Station(r.getStarting());
		Station start = railway.get(s);
		if(start != null) {
			
			start.addRider(r);
		
		}
		
	}
	
	/**
	 * @param t - the new train
	 * This method adds a Train to the appropriate Station in this Railway.
	 * O(n)
	 */
	public void addTrain(Train t) {
		
		Station s = new Station(t.getStation());
		if(railway != null) {
			Station start = railway.get(s);
			start.addTrain(t);
		}
		
	}
	
	/**
	 * @param r - the rider object
	 * This method set the correct moving direction of the rider based on their starting and destination.
	 * O(n)
	 */
	public void setRiderDirection(Rider r) {
		int start = 0;
		for(int i = 0; i < stationCounter; i++) {
			if(r.getStarting().equals(stationNames[i])) {
				start = i;
			}
		}
		int dest = 0;
		for(int i = 0; i < stationCounter; i++) {
			if(r.getDestination().equals(stationNames[i])) {
				dest = i;
			}
		}
		if(start <= dest) {
			if(r.goingNorth()) {
				r.swapDirection();
			}
		}else {
			if(!r.goingNorth()) {
				r.swapDirection();
			}
		}
		
	}
	
	/**
	 * @return - the running log of the train
	 * This method will execute one simulation of the Railway. 
	 * You should log the events that occur in the process of your simulation such as the status of 
	 * each Station, where Trains and Riders are, and when Riders exit a Train.
	 * O(n^2) - addTrain is O(n)
	 */
	public String simulate() {
		String str = "";
		Node<Station> pointer = railway.getFirst();
		while(pointer != null) {
			Station s = pointer.getElement();
			str += s.toString() + "\n\n";
			if(s.stationName().equals("Braintree")) {
				Train north = s.northBoardTrain();
				if(north != null) {
					north.updateStation(pointer.getPrev().getElement().stationName());
					str += pointer.getPrev().getElement().addTrain(north);
					str += north.toString() + "\n\n";
				}
				s.moveTrainSouthToNorth();
			}else if(s.stationName().equals("Alewife")) {
				Train south = s.southBoardTrain();
				if(south != null) {
					south.updateStation(pointer.getNext().getElement().stationName());
					str += pointer.getNext().getElement().addTrain(south);
					str += south.toString() + "\n\n";
				}
				s.moveTrainNorthToSouth();
			}else {
				Train north = s.northBoardTrain();
				if(north != null) {
					north.updateStation(pointer.getPrev().getElement().stationName());
					str += pointer.getPrev().getElement().addTrain(north);
					str += north.toString() + "\n\n";
				}
				Train south = s.southBoardTrain();
				if(south != null) {
					south.updateStation(pointer.getNext().getElement().stationName());
					str += pointer.getNext().getElement().addTrain(south);
					str += south.toString() + "\n\n";
				}
			}
				
			pointer = pointer.getNext();
		}
		return str;
		
	}
	
	/**
	 * This returns the Stations list’s String representation.
	 */
	@Override
	public String toString() {
		String str = "";
		Node<Station> pointer = railway.getFirst();
		while(pointer != null) {
			str += pointer.getElement().toString() + "\n\n";
			pointer = pointer.getNext();
		}
		return str;
	}
}
