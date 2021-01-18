package cs131.pa4.CarsTunnels;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cs131.pa4.Abstract.Scheduler;
import cs131.pa4.Abstract.Tunnel;
import cs131.pa4.Abstract.Vehicle;

/**
 * The priority scheduler assigns vehicles to tunnels based on their priority
 * It extends the Scheduler class.
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 11/30/2020
 * COSI 131A PA4
 */
public class PriorityScheduler extends Scheduler{
	
	private Lock lock = new ReentrantLock();
	private Condition exit = lock.newCondition();
	private LinkedList<Vehicle> vehicles = new LinkedList<Vehicle>();
	private Collection<Tunnel> tunnels;
	private HashMap<Vehicle, Tunnel> tunnelMap = new HashMap<>();
	
	/**
	 * Creates an instance of a priority scheduler with the given name and tunnels
	 * @param name the name of the priority scheduler
	 * @param tunnels the tunnels where the vehicles will be scheduled to
	 */
	public PriorityScheduler(String name, Collection<Tunnel> tunnels) {
		super(name, tunnels);
		this.tunnels = tunnels;
	}

	@Override
	public Tunnel admit(Vehicle vehicle) {
		
		//set lock for the vehicle
		lock.lock();
		
		try {
			boolean entered = false;
			vehicles.add(vehicle);
			while(!entered) {
				if(this.isMaxPriority(vehicle)) {
					
					Iterator<Tunnel> tunnelIterator = tunnels.iterator();
					while(tunnelIterator.hasNext()) {
						Tunnel t = tunnelIterator.next();
						if (t.tryToEnter(vehicle)) {					//the case the vehicle is succesfully admitted by a tunnel
							vehicles.remove(vehicle);
							entered = true;
							tunnelMap.put(vehicle,t);						//map the entered vehicle with the entered tunnel
							return t;
						}else {
							try {
								exit.await();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					
				} else {
					try {
						exit.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} finally {
			lock.unlock();
		}
		
		return null;
		
	}
	

	@Override
	public void exit(Vehicle vehicle) {
		
		lock.lock();
		
		//get the tunnel that contains the vehicle
		Tunnel tunnel = tunnelMap.get(vehicle);
		tunnel.exitTunnel(vehicle);
		
		//remove the vehicle out of that tunnel
		tunnelMap.remove(vehicle);
		
		//wake up all other waiting threads
		exit.signalAll();

		lock.unlock();
	}
	
	/**
	 * @param vehicle - the vehicle that needs to be checked
	 * @return - whether the vehicle has the higheset priority.
	 */
	private boolean isMaxPriority(Vehicle vehicle) {
		
		for(Vehicle v :vehicles) {
			if (vehicle.getPriority() < v.getPriority()) {
				return false;
			}
		}
		return true;
		
	}
}
