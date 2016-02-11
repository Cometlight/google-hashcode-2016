/**
 * Created by Zopo on 11.02.2016.
 */
public class Delivery {
    Order _order;
    ProductType _product;
    int _amount;
    boolean _isDone = false;
    
    public Delivery(Order order, ProductType product, int amount) {
    	_order = order;
    	_product = product;
    	_amount = amount;
    }

    public int getWeight(){
        return _product._weight * _amount;
    }
}
