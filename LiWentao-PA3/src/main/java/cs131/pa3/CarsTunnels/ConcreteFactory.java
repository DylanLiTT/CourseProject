package cs131.pa3.CarsTunnels;

import cs131.pa3.Abstract.Direction;
import cs131.pa3.Abstract.Factory;
import cs131.pa3.Abstract.Tunnel;
import cs131.pa3.Abstract.Vehicle;

/**
 * The class implementing the Factory interface for creating instances of classes
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 11/19/2020
 * COSI 131A PA3
 */
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
}
