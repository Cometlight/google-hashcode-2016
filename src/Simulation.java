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
    PriorityQueue<Delivery> _deliveries = new PriorityQueue<>();
    int DELIVERY_LOOKUP_COUNT = 4096;
    List<Warehouse> _warehouses;
    List<ProductType> _products;
    WarehouseManager _warehouseManager;
    int _nrOfDroneCommands = 0;

    int _curTime = 0;
    int _maxTime;

    int _width;
    int _height;

    int _commandCount;

    public void performTimestep() {
        _curTime++;
    	for(Drone drone : _drones) {
    		drone.performTimestep();
    	}
    }

    public void run(){
    	while (_curTime <= _maxTime) {
	    	performTimestep();
    	}
		// time is up, the simulation has finished
		System.out.println("Nr. of commands: " + _nrOfDroneCommands);
    }

    public Delivery getNextDeliveryFromWarehouse(Drone drone, Warehouse warehouse){
        int deliveryCount = Math.min(DELIVERY_LOOKUP_COUNT, _deliveries.size());

        //choose the shortest delivery
        Delivery shortestDelivery = null;
        int shortestDeliveryDistance = Integer.MAX_VALUE;

        Iterator deliveryIterator = _deliveries.iterator();
        for (int i = 0; i < deliveryCount; i++){
            //get the next delivery to check
            Delivery checkedDelivery = (Delivery) deliveryIterator.next();
            int totalDistance = 0;

            //get the distance from the warehouse to the destination
            totalDistance += Utils.getDistance(drone._deliveries.get(drone._deliveries.size()-1)._order._destination, checkedDelivery._order._destination);

            //replace the current shortest delivery if the distance is shorter and the current warehouse has the product in stock
            int productStock = warehouse._stock.get(checkedDelivery._product);
            if (totalDistance < shortestDeliveryDistance && productStock >= checkedDelivery._amount && checkedDelivery.getWeight() + drone.getCurrentWeight() <= drone._maxWeight){
                shortestDeliveryDistance = totalDistance;
                shortestDelivery = checkedDelivery;
            }
            
        }

        //Abort when there are no deliveries found
        if (shortestDelivery == null){
            return null;
        }

        confirmDelivery(warehouse, shortestDelivery);

        return shortestDelivery;
    }

	public Delivery getNextDelivery(Drone drone){
        int deliveryCount = Math.min(DELIVERY_LOOKUP_COUNT, _deliveries.size());

        //choose the shortest delivery
        Delivery shortestDelivery = null;
        int shortestDeliveryDistance = Integer.MAX_VALUE;
        Warehouse shortestDeliveryWarehouse = null; //The products need to be reservered here
        Iterator deliveryIterator = _deliveries.iterator();
        for (int i = 0; i < deliveryCount; i++){
            //get the next delivery to check
            Delivery checkedDelivery = (Delivery) deliveryIterator.next();
            int totalDistance = 0;

            //get the closest warehouse
            Warehouse closestWarehouse = _warehouseManager.getClosestWarehouse(drone._curLocation, checkedDelivery._product, checkedDelivery._amount);

            //get the distance to the closest warehouse
            totalDistance += Utils.getDistance(drone._curLocation, closestWarehouse._location);

            //get the distance from the warehouse to the destination
            totalDistance += Utils.getDistance(closestWarehouse._location, checkedDelivery._order._destination);

            //replace the current shortest delivery if the distance is shorter
            if (totalDistance < shortestDeliveryDistance  && checkedDelivery.getWeight() + drone.getCurrentWeight() <= drone._maxWeight){
                shortestDeliveryDistance = totalDistance;
                shortestDelivery = checkedDelivery;
                shortestDeliveryWarehouse = closestWarehouse;
            }
        }
        
        //Abort when there are no deliveries found
        if (shortestDelivery == null){
            return null;
        }
        
        confirmDelivery(shortestDeliveryWarehouse, shortestDelivery);

        return shortestDelivery;
    }

    private void confirmDelivery(Warehouse warehouse, Delivery delivery){

        //Put closest warehouse to the delivery
        delivery._assignedWarehouse = warehouse;

        //Reserve the products in the warehouse
        int oldStock = warehouse._stock.get(delivery._product);
        warehouse._stock.put(delivery._product, oldStock - delivery._amount);

        //Remove delivery from list of available deliveries
        _deliveries.remove(delivery);

        //Refresh order status
        Order order = delivery._order;
        order._deliveries.remove(delivery);
        if (order._deliveries.isEmpty()){
            order._status = Order.OrderStatus.DONE;
        } else {
            order._status = Order.OrderStatus.IN_PROGRESS;
        }

    }


    public static Simulation getInstance(){
        if (_instance == null){
            _instance = new Simulation();
        }
        return _instance;
    }
}
