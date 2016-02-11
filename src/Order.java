import java.util.List;

/**
 * Created by Zopo on 11.02.2016.
 */
public class Order {
    Coordinate _destination;
    OrderStatus _status;
    List<Delivery> _deliveries;	// can contain multiple same deliveries, indicating how many of them are needed

    public Order(List<ProductType> products){	// can contain several same products, indicating how many of them are ordered
    }

    public enum OrderStatus{
        DONE, IN_PROGRESS, OPEN;
    }
}
