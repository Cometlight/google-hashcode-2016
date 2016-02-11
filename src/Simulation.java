import java.util.Iterator;
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

        //choose the shortest delivery
        Delivery shortestDelivery;
        int shortestDeliveryDistance = Integer.MAX_VALUE;
        Warehouse shortestDeliveryWarehouse; //The products need to be reservered here
        Iterator deliveryIterator = _deliveries.iterator();
        for (int i = 0; i < deliveryCount; i++){
            //get the next delivery to check
            Delivery checkedDelivery = (Delivery) deliveryIterator.next();
            int totalDistance = 0;

            //get the closest warehouse
            Warehouse closestWarehouse = _warehouseManager.getClosestWarehouse(curLocation, checkedDelivery._product, checkedDelivery._amount);

            //get the distance to the closest warehouse
            totalDistance += Utils.getDistance(curLocation, closestWarehouse._location);

            //get the distance from the warehouse to the destination
            totalDistance += Utils.getDistance(closestWarehouse._location, checkedDelivery._order._destination);

            //replace the current shortest delivery if the distance is shorter
            if (totalDistance < shortestDeliveryDistance){
                shortestDeliveryDistance = totalDistance;
                shortestDelivery = checkedDelivery;
                shortestDeliveryWarehouse = closestWarehouse;
            }
        }

        //Reserve the products in the warehouse


        return null; //todo
    }

    public static Simulation getInstance(){
        if (_instance == null){
            _instance = new Simulation();
        }
        return _instance;
    }
}
