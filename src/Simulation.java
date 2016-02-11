import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by Zopo on 11.02.2016.
 */
public class Simulation {
    List<Drone> _drones;
    List<Order> _orders;
    PriorityQueue<Delivery> _deliveries;
    List<Warehouse> _warehouses;
    Set<ProductType> _products;
    WarehouseManager _warehouseManager;
    int _nrOfDroneCommands = 0;

    int _curTime = 1;
    int _maxTime;

    int _width;
    int _height;

    int _commandCount;

    public void performTimestep() {
    	for(Drone drone : _drones) {
    		drone.performTimestep();
    	}
    }

    public void run(){
    	if (_curTime <= _maxTime) {
	    	performTimestep();
    	} else {
    		// time is up, the simulation has finished
    		System.out.println("Nr. of commands: " + _nrOfDroneCommands);
    	}
    }
    
	public Delivery getNextDelivery(Coordinate curLocation){
        return null; //todo
    }
}
