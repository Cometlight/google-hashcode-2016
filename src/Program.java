import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Zopo on 09.02.2016.
 */
public class Program {
    public static void main(String[] args) throws IOException {

        //get file
        List<String> lines = Files.readAllLines(Paths.get("logo.in"));
        lines.remove(0); //not needed


        boolean[][] field = linesToBooleanArray(lines);
        //init playground

        //run
        }

    private static boolean[][] linesToBooleanArray(List<String> lines) {
        boolean[][] array = new boolean[lines.size()][];

        for(int i = 0; i < lines.size(); ++i) {
            array[i] = new boolean[lines.get(i).length()];
            for()
        }
    }
}
