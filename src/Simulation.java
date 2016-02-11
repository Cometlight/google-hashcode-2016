import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Zopo on 11.02.2016.
 */
public class Simulation {
    List<Drone> _drones;
    List<Order> _orders;
    PriorityQueue<Delivery> _deliveries;
    List<Warehouse> _warehouses;
    List<ProductType> _products;
    WarehouseManager _warehouseManager;

    int _curTime;
    int _maxTime;

    int _width;
    int _height;

    int _commandCount;

    public void performTimestep(){

    }

    public Delivery getNextDelivery(Coordinate curLocation){
        return null; //todo
    }

    public void run(){

    }
}
