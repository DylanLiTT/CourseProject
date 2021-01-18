/**
 * This class contains your program’s main method and should run a simulation of a Railway.
 * Known Bugs: None
 *
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 2/27/2020
 * COSI 21A PA1
 */
package main;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MBTA {

	public static final int SOUTHBOUND = 1;
	public static final int NORTHBOUND = 0;
	
	static final int TIMES = 6;
	static Railway r;
	
	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("INITIATED RED LINE\n");
		r = new Railway();
		initStations("redLine.txt");
		initTrains("trains.txt");
		initRiders("riders.txt");
		System.out.println(r);
		
		System.out.println("BEGINNING RED LINE SIMULATION\n");
		runSimulation();
		
	}
	
	/**
	 * This method runs the simulation using TIMES and Railway’s simulate(
	 * O(n^2) since TIMES is a constant.
	 */
	public static void runSimulation() {
		for(int i = 0; i < TIMES; i++) {
			System.out.println(" ------ " + (i+1) + " ------ \n");
			System.out.println(r.simulate());
		}
	}
	
	/**
	 * @param trainsFile - the name of the trains file
	 * @throws FileNotFoundException
	 * This method constructs Trains from an input file and adds them to the Railway.
	 * O(n)
	 */
	public static void initTrains (String trainsFile) throws FileNotFoundException {
		
		Scanner input = new Scanner(new File(trainsFile));
		while(input.hasNextLine()) {
			Train t = new Train(input.nextLine(), input.nextInt());
			r.addTrain(t);
			input.nextLine();
		}
		
	}
	
	/**
	 * @param ridersFile - the file contains rider info
	 * @throws FileNotFoundException
	 * This method constructs Riders from an input file and adds them to the Railway.
	 * O(n)
	 */
	public static void initRiders(String ridersFile) throws FileNotFoundException {
		Scanner input = new Scanner(new File(ridersFile));
		while(input.hasNextLine()) {
			Rider p = new Rider(input.nextLine(), input.nextLine(), input.nextLine());
			r.addRider(p);
		}
	}
	
	/**
	 * @param stationsFile - the file contains the station info
	 * @throws FileNotFoundException
	 * This method constructs Stations from an input file and adds them to the Railway.
	 * O(n)
	 */
	public static void initStations(String stationsFile) throws FileNotFoundException {
		Scanner input = new Scanner(new File(stationsFile));
		while(input.hasNextLine()) {
			Station s = new Station(input.nextLine());
			r.addStation(s);
		}
	}
}
