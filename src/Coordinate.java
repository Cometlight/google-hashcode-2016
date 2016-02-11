/**
 * Created by Zopo on 11.02.2016.
 */
public class Coordinate {
    int _row;
    int _column;

    public Coordinate (int row, int column){
        _row = row;
        _column = column;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _column;
		result = prime * result + _row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (_column != other._column)
			return false;
		if (_row != other._row)
			return false;
		return true;
	}
}
