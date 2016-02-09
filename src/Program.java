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
        Playground playground = new Playground();
        playground.setField(field);
        //init playground

        //run
        }

    private static boolean[][] linesToBooleanArray(List<String> lines) {
        boolean[][] array = new boolean[lines.size()][];

        for(int i = 0; i < lines.size(); ++i) {
            char[] curLine = lines.get(i).toCharArray();
            array[i] = new boolean[curLine.length];

            for(int j = 0; j < curLine.length; j++){
                if (curLine[j] == '#'){
                    array[i][j] = true;
                } else {
                    array[i][j] = false;
                }
            }
        }

        return array;
    }
}
