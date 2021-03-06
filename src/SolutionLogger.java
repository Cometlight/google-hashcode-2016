import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Zopo on 11.02.2016.
 */
public class SolutionLogger {
    public static void main(String[] args) throws IOException {
        String filename = "redundancy.in";
        Simulation sim = parseFile(new File(filename));
        System.out.println("Dronecount: " + sim._drones.size());
        System.out.println("Productcount: " + sim._products.size());
        System.out.println("Warehousecount: " + sim._warehouses.size());
        System.out.println("Ordercount: " + sim._orders.size());

        sim.run();
    }

    public static Simulation parseFile(File file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file.toURI()));
        Simulation sim = Simulation.getInstance();

        //First Line - General Info
        String[] firstLine = (lines.remove(0)).split(" ");
        sim._width = Integer.parseInt(firstLine[0]);
        sim._height = Integer.parseInt(firstLine[1]);
        int droneCount = Integer.parseInt(firstLine[2]);
        sim._maxTime = Integer.parseInt(firstLine[3]);
        int droneCapacity = Integer.parseInt(firstLine[4]);
        sim._droneCapacity = droneCapacity;

        //Second Line - Product Count
        String secondLine = lines.remove(0);
        int productCount = Integer.parseInt(secondLine);

        //Third Line - Product weights
        String[] thirdLine = (lines.remove(0)).split(" ");
        sim._products = new LinkedList<>();
        int id = 0;
        for (String singleItem : thirdLine){
            ProductType product = new ProductType();
            product._id = id++;
            product._weight = Integer.parseInt(singleItem);
            sim._products.add(product);
        }

        //Fourth Line - Warehouse Count
        String fourthLine = lines.remove(0);
        int warehouseCount = Integer.parseInt(fourthLine);

        //Line Five to X - Warehouse Content
        sim._warehouses = new LinkedList<>();
        for (int i = 0; i < warehouseCount; i++){
            Warehouse warehouse = new Warehouse();
            warehouse._id = i;
            String[] locationLine = lines.remove(0).split(" ");
            warehouse._location = new Coordinate(Integer.parseInt(locationLine[0]), Integer.parseInt(locationLine[1]));

            warehouse._stock = new HashMap<>(productCount);
            String[] stockLine = lines.remove(0).split(" ");
            for (int j = 0; j < stockLine.length; j++){
                ProductType product = sim._products.get(j);
                warehouse._stock.put(product, Integer.parseInt(stockLine[j]));
            }
            sim._warehouses.add(warehouse);
        }

        //Line X - Delivery Count
        String xLine = lines.remove(0);
        int deliveryCount = Integer.parseInt(xLine);

        //Line X+1 to End - Delivery Details
        sim._orders = new LinkedList<>();
        for (int i = 0; i < deliveryCount; i++){
            String[] locationLine = lines.remove(0).split(" ");
            Coordinate orderDestination = new Coordinate(Integer.parseInt(locationLine[0]), Integer.parseInt(locationLine[1]));

            String itemCountLine = lines.remove(0);
            int itemCount = Integer.parseInt(itemCountLine);

            String[] itemLine = lines.remove(0).split(" ");
            HashMap<ProductType, Integer> orderProducts = new HashMap<>();
            for (int j = 0; j < itemCount; j++){
                ProductType product = sim._products.get(Integer.parseInt(itemLine[j]));
                if (orderProducts.containsKey(product)){
                    orderProducts.put(product, orderProducts.get(product) + 1);
                } else {
                    orderProducts.put(product, 1);
                }
            }
            Order order = new Order(orderProducts);
            order._id = i;
            order._destination = orderDestination;
            sim._orders.add(order);
        }

        sim._drones = new LinkedList<>();
        Coordinate initialWarehousePosition = sim._warehouses.get(0)._location;
        for (int i = 0; i < droneCount; i++){
            Drone d = new Drone();
            d._id = i;
            d._maxWeight = droneCapacity;
            d._remainingBusyTime = 0;
            d._simulation = sim;
            d._curLocation = new Coordinate(initialWarehousePosition._row, initialWarehousePosition._column);
            sim._drones.add(d);
        }

        return sim;
    }
}
