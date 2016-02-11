import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Created by Zopo on 11.02.2016.
 */
public class Drone {
	int _id;
    Coordinate _curLocation;
    int _maxWeight;
    HashMap<ProductType, Integer /* amount */> _productStorage;
    Simulation _simulation; // needed to inform about actions taken, e.g. "load"
    List<Delivery> _deliveries = new LinkedList<>();	// FIFO
    int _remainingBusyTime = 0;
    boolean _active = true;

    public void performTimestep(){
    	if (!_active) {
    		wait(1);
    		return;
    	}
    	
        if (_remainingBusyTime > 0) {
        	// Drone is still doing something, like flying around like a maniac
        	--_remainingBusyTime;
        	return;
        }
        
        if (_deliveries.isEmpty()) {	
        	// Drone needs a new delivery
        	Delivery newDelivery = Simulation.getInstance().getNextDelivery(this);
        	if (newDelivery == null) {
        		_active = false;	// this drone isn't needed anymore, there are no more deliveries left
        		return;
        	}
        	_deliveries.add(newDelivery);
        }
        
        Delivery curDelivery = _deliveries.get(0);
        
        if (!containsNeededProductForCurrentDelivery()) {
        	// We need to fly and load at the warehouse
        	load(curDelivery._assignedWarehouse, curDelivery._product, curDelivery._amount);
        	_productStorage.put(curDelivery._product, _productStorage.get(curDelivery._product) + curDelivery._amount);
        	int distance = Utils.getDistance(_curLocation, curDelivery._assignedWarehouse._location);
        	// distance == time to fly + loading command
        	_remainingBusyTime = distance + 1;
        	return;
        }
        
        if (!_curLocation.equals(curDelivery._assignedWarehouse._location)) {
        	// We just loaded the stuff needed for the current delivery, but let's see if we have some space left
        	while (getCurrentWeight() < _maxWeight) {
        		Delivery newDelivery = Simulation.getInstance().getNextDelivery(this);
        		if (newDelivery == null) {
        			// no applicable delivery exists anymore at the moment
        			break;
        		}
        		_deliveries.add(newDelivery);
        		load(newDelivery._assignedWarehouse, newDelivery._product, newDelivery._amount);
        		_productStorage.put(curDelivery._product, _productStorage.get(curDelivery._product) + curDelivery._amount);
        		// We know we are already at the correct position, so no need to fly
        		_remainingBusyTime = 1;	// time to load
        		return;
        	}
        }
        
        // We have all we need, let's deliver it to the customer
        deliver(curDelivery);
        int distance = Utils.getDistance(_curLocation, curDelivery._order._destination);
        // distance == time to fly + deliver command
        _remainingBusyTime = distance + 1;
        _productStorage.put(curDelivery._product, _productStorage.get(curDelivery._product) - curDelivery._amount);
    }

    public int getCurrentWeight() {
    	int weight = 0;
    	
    	for (Entry<ProductType, Integer> entry : _productStorage.entrySet()) {
    		weight += entry.getKey()._weight * entry.getValue();
    	}
    	
        return weight;
    }
    
    private boolean containsNeededProductForCurrentDelivery() {
    	Delivery curDelivery = _deliveries.get(0);
    	for (Entry<ProductType, Integer> entry : _productStorage.entrySet()) {
    		if (entry.getKey()._id == curDelivery._product._id && 
    				entry.getValue() >= curDelivery._amount) {
    			// We have enough of the correct product type
    			return true;
    		}
    	}
    	return false;
    }
    
    private void wait(int turns) {
    	System.out.println(_id  + " W " + turns);
    }
    
    private void load(Warehouse warehouse, ProductType product, int amount) {
    	System.out.println(_id + " L " + warehouse._id + " " + product._id + " " + amount);
    }
    
    private void deliver(Delivery delivery) {
    	System.out.println(_id + " D " + delivery._order._id + " " + delivery._product._id + " " + delivery._amount);
    }
}
