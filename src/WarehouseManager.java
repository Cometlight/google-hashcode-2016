/**
 * Created by Zopo on 11.02.2016.
 */
public class WarehouseManager {
    public static Warehouse getClosestWarehouse(Coordinate curLocation, ProductType product, int amount){
    	Warehouse bestWarehouse = null;
    	int shortestDistance = Integer.MAX_VALUE;
    	int highestAmount = 0;
    	
    	for (Warehouse warehouse : Simulation.getInstance()._warehouses) {
    		int amountInWarehouse = warehouse._stock.get(product);
//    		if (amountInWarehouse >= highestAmount) {
    		if (amountInWarehouse >= amount) {	// FIXME
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
