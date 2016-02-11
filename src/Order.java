import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Created by Zopo on 11.02.2016.
 */
public class Order {
    Coordinate _destination;
    OrderStatus _status = OrderStatus.OPEN;
    List<Delivery> _deliveries = new LinkedList<>();	// can contain multiple same deliveries, indicating how many of them are needed

    public Order(HashMap<ProductType, Integer> products){	// can contain several same products, indicating how many of them are ordered
    	for (Entry<ProductType, Integer> entry : products.entrySet()) {
    		int capacity = Simulation.getInstance()._droneCapacity;
    		int totalWeight = entry.getValue() * entry.getKey()._weight;
    		if (totalWeight <= capacity) {
    			// We don't have to split it into multiple deliveries
    			_deliveries.add(new Delivery(this, entry.getKey(), entry.getValue()));
    		} else {
    			// Split it
    			int maxNrPossibleOfProduct = capacity / entry.getKey()._weight;
    			int weightOfBiggestDeliveryPossible = maxNrPossibleOfProduct * entry.getKey()._weight;
    			int totalWeightRemaining = totalWeight;
    			while (totalWeightRemaining > weightOfBiggestDeliveryPossible) {
    				_deliveries.add(new Delivery(this, entry.getKey(), entry.getValue()));
    				totalWeightRemaining -= weightOfBiggestDeliveryPossible;
    			}
    			int amountOfFinalDelivery = totalWeightRemaining / entry.getKey()._weight;
    			_deliveries.add(new Delivery(this, entry.getKey(), amountOfFinalDelivery));
    		}
    	}
    }

    public enum OrderStatus{
        DONE, IN_PROGRESS, OPEN;
    }
}
