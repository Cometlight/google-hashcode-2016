import java.util.List;

/**
 * Created by Zopo on 11.02.2016.
 */
public class Drone {
    Coordinate _curLocation;
    int _maxWeight;
    List<ProductType> _productStorage;
    Simulation _simulation; // needed to inform about actions taken, e.g. "load"
    Delivery _curDelivery;
    int _remainingBusyTime;

    public void performTimestep(){
        //TODO
    }

    public int getCurrentWeight() {
        return 0; // TODO
    }
}
