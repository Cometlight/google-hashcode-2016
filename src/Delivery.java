/**
 * Created by Zopo on 11.02.2016.
 */
public class Delivery {
    Order _order;
    ProductType _product;
    int _amount;
    boolean _isDone;

    public int getWeight(){
        return _product._weight * _amount;
    }
}
