package cs131.pa4.CarsTunnels;

/**
 * The class implementing the Factory interface for creating instances of classes
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 11/30/2020
 * COSI 131A PA4
 */

import java.util.Collection;
import cs131.pa4.Abstract.Direction;
import cs131.pa4.Abstract.Factory;
import cs131.pa4.Abstract.Scheduler;
import cs131.pa4.Abstract.Tunnel;
import cs131.pa4.Abstract.Vehicle;

public class ConcreteFactory implements Factory {

	/**
     *create a new basic tunnel with the provided name and return it.
     */
    @Override
    public Tunnel createNewBasicTunnel(String name){
    	return new BasicTunnel(name);
    }

    /**
     *create a new car object with the provided name and direction, and return it.
     */
    @Override
    public Vehicle createNewCar(String name, Direction direction){
    		return new Car(name, direction);
    }

    /**
     *create a new sled object with the provided name and direction, and return it.
     */
    @Override
    public Vehicle createNewSled(String name, Direction direction){
    		return new Sled(name, direction);
    }

	/**
	 *create a new prority scheduler with the provided name and tunnel collection.
	 */
	@Override
	public Scheduler createNewPriorityScheduler(String name, Collection<Tunnel> tunnels) {
		
		return new PriorityScheduler(name, tunnels);
	}
}
