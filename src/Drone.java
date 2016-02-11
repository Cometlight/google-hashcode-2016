import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zopo on 11.02.2016.
 */
public class Drone {
	int _id;
    Coordinate _curLocation;
    int _maxWeight;
    List<ProductType> _productStorage;
    Simulation _simulation; // needed to inform about actions taken, e.g. "load"
    List<Delivery> _deliveries = new LinkedList<>();	// FIFO
    int _remainingBusyTime = 0;
    boolean _active = true;

    public void performTimestep(){
    	if (!_active) {
    		
    	}
    	
        if (_remainingBusyTime > 0) {
        	// Drone is still doing something, like flying around like a maniac
        	--_remainingBusyTime;
        	return;
        }
        
        if (_deliveries.isEmpty()) {
        	// Drone needs a new delivery
        	_deliveries.add(Simulation.getInstance().getNextDelivery(_curLocation));
        	
        }
    }

    public int getCurrentWeight() {
    	int weight = 0;
    	
    	for (ProductType product : _productStorage) {
    		weight += product._weight;
    	}
    	
        return weight;
    }
}
