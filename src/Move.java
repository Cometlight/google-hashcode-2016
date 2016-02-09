/**
 * Created by Zopo on 09.02.2016.
 */
public abstract class Move implements Comparable<Move> {
	
	private int _priority;
	
	public Move(int priority) {
		_priority = priority;
	}
	
	public abstract void apply(Binary2dField binary2dField);
	
	public abstract String toString();
	
	public int getPriority() {
		return _priority;
	}	
	
	@Override
	public int compareTo(Move o) {
		return _priority - o._priority;
	}
}
