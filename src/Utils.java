
public class Utils {
	public static int getDistance(Coordinate coorA, Coordinate coorB) {
		
		double distance = Math.sqrt(Math.pow(coorA._row - coorB._row, 2) + Math.pow(coorA._column - coorB._column, 2));
		
		return (int) Math.ceil(distance);	// round up to the next integer
	}
}
