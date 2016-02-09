
public class EraseCellMove extends Move{

	public int C;
	public int R;
	
	public EraseCellMove(int R, int C) {
		super(0);
		this.C = C;
		this.R = R;
	}

	@Override
	public void apply(Binary2dField binary2dField) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "ERASE_CELL " + R + " " + C;
	}

}
