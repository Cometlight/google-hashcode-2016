import java.util.List;

/**
 * Created by Zopo on 11.02.2016.
 */
public class Order {
    Coordinate _destination;
    OrderStatus _status;
    List<Delivery> _deliveries;

    public Order(List<ProductType> products){
    }

    public enum OrderStatus{
        DONE, IN_PROGRESS, OPEN;
    }
}
