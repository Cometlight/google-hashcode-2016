import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zopo on 11.02.2016.
 */
public class SolutionLogger {
    public static void main(String[] args) throws IOException {
        String filename = "busy_day.in";
        Simulation sim = parseFile(new File(filename));
        sim.run();
    }

    public static Simulation parseFile(File file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file.toURI()));
        Simulation sim = new Simulation();

        //First Line - General Info
        String[] firstLine = (lines.remove(0)).split(" ");
        sim._width = Integer.parseInt(firstLine[0]);
        sim._height = Integer.parseInt(firstLine[1]);
        int droneCount = Integer.parseInt(firstLine[2]);
        sim._maxTime = Integer.parseInt(firstLine[3]);
        int droneCapacity = Integer.parseInt(firstLine[4]);

        //Second Line - Product Count
        String secondLine = lines.remove(0);
        int productCount = Integer.parseInt(secondLine);

        //Third Line - Product weights
        String[] thirdLine = (lines.remove(0)).split(" ");
        sim._products = new HashSet<>(productCount);
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
            String[] locationLine = lines.remove(0).split(" ");
            warehouse._location = new Coordinate(Integer.parseInt(locationLine[0]), Integer.parseInt(locationLine[1]));

            warehouse._stock = new HashMap<>(productCount);
            String[] stockLine = lines.remove(0).split(" ");
            for (int j = 0; j < stockLine.length; j++){

            }
        }

        return sim;
    }
}
