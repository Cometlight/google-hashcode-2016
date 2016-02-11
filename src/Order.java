import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zopo on 11.02.2016.
 */
public class Order {
    Coordinate _destination;
    OrderStatus _status = OrderStatus.OPEN;
    List<Delivery> _deliveries = new LinkedList<>();	// can contain multiple same deliveries, indicating how many of them are needed

    public Order(HashMap<ProductType, Integer> products){	// can contain several same products, indicating how many of them are ordered
//    	for (ProductType product : products) {
//    		Delivery newDelivery = new Delivery(this, product, );
    		
//    		_deliveries.add()
//    	}
    }

    public enum OrderStatus{
        DONE, IN_PROGRESS, OPEN;
    }
}
