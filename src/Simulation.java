import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by Zopo on 11.02.2016.
 */
public class Simulation {
    static Simulation _instance;

    List<Drone> _drones;
    int _droneCapacity;
    List<Order> _orders;
    PriorityQueue<Delivery> _deliveries;
    int DELIVERY_LOOKUP_COUNT = 32;
    List<Warehouse> _warehouses;
    List<ProductType> _products;
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
        int deliveryCount = Math.min(DELIVERY_LOOKUP_COUNT, _deliveries.size());

        for (int i = 0; i < deliveryCount; i++){

        }
        int distanceToClosestWarehouse;
        int distanceToDestination;
        return null; //todo
    }

    public static Simulation getInstance(){
        if (_instance == null){
            _instance = new Simulation();
        }
        return _instance;
    }
}
