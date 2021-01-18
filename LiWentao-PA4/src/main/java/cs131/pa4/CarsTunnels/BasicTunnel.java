package cs131.pa4.CarsTunnels;

import cs131.pa4.Abstract.Direction;
import cs131.pa4.Abstract.Tunnel;
import cs131.pa4.Abstract.Vehicle;

/**
 * 
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 11/30/2020
 * COSI 131A PA4
 */
public class BasicTunnel extends Tunnel{

	//private filed of the BasicTunnel
		//Including the number of cars and sled inside the tunnel and the direction of the tunnel.
		private int carNum = 0;
		private int sledNum = 0;
		private Direction direction;
		
		/**
		 * Creates a new instance of a basic tunnel with the given name
		 * @param name the name of the basic tunnel
		 */
		public BasicTunnel(String name) {
			super(name);
		}

		/**
		 *Return the boolean value whether the vehicle have the condition to enter the tunnel
		 */
		@Override
		protected synchronized boolean tryToEnterInner(Vehicle vehicle) {
			
			if(vehicle instanceof Car) {						//the case the vehicle try to enter is a car
				
				if(carNum == 3 || sledNum == 1) {
					return false;
				} else if(carNum == 0) {     					//When there is no cars or sled inside the tunnel
					this.direction = vehicle.getDirection();
					carNum++;
					return true;
				} else if(vehicle.getDirection().equals(this.direction)){										//Normal case: when there is already 1 or 2 cars in the tunnel
					carNum++;
					return true;
				}	
				
			} else if(vehicle instanceof Sled) {				//the case the vehicle try to enter is a sled
				
				//if a sled wants to enter the tunnel, the tunnel must be empty.
				if(carNum == 0 && sledNum == 0) {
					sledNum++;
					return true;
				}
				
				return false;
			}
			return false;
		}

		/**
		 *Remove one vehicle from the tunnel.
		 */
		@Override
		protected synchronized void exitTunnelInner(Vehicle vehicle) {
			
			if(vehicle instanceof Car) {
				carNum--;
			} else if(vehicle instanceof Sled) {
				sledNum--;
			}
			
		}
		
}
