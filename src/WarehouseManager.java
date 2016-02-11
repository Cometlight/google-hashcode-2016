/**
 * Created by Zopo on 11.02.2016.
 */
public class WarehouseManager {
	Simulation _simulation;
	
    public Warehouse getClosestWarehouse(Coordinate curLocation, ProductType product, int amount){
    	Warehouse bestWarehouse = null;
    	int shortestDistance = Integer.MAX_VALUE;
    	int highestAmount = 0;
    	
    	for (Warehouse warehouse : _simulation._warehouses) {
    		int amountInWarehouse = warehouse._stock.get(product);
    		if (amountInWarehouse >= highestAmount) {
    			int distanceToWarehouse = Utils.getDistance(curLocation, warehouse._location);
    			if (distanceToWarehouse < shortestDistance) {
    				bestWarehouse = warehouse;
    				shortestDistance = distanceToWarehouse;
    				highestAmount = Math.min(amount, amountInWarehouse);	// We don't need more than amount
    			}
    		}
    	}
    	
        return bestWarehouse;
    }
}