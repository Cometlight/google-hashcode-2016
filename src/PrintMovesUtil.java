import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collection;
import java.util.LinkedList;

public class PrintMovesUtil {
	public static void printToScreen(Collection<Move> moves) {
		Collection<String> lines = createLines(moves);
		
		for(String line : lines) {
			System.out.println(line);
		}
	}
	
	public static void printToFile(Collection<Move> moves, File file) throws IOException {
		Files.write(file.toPath(), createLines(moves), Charset.forName("UTF-8"));
	}
	
	private static Collection<String> createLines(Collection<Move> moves) {
		LinkedList<String> lines = new LinkedList<>();
		lines.add("" + moves.size());
		
		for(Move move : moves) {
			lines.add(move.toString());
		}
		
		return lines;
	}
}
