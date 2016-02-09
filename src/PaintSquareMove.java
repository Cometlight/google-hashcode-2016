
public class PaintSquareMove extends Move{

	public int R;
	public int C;
	public int S;
	
	public PaintSquareMove(int R, int C, int S) {
		super(1);
		this.R = R;
		this.C = C;
		this.S = S;
	}

	@Override
	public void apply(Binary2dField binary2dField) {
		
	}

	@Override
	public String toString() {
		return "PAINT_SQUARE " + R + " " + C + " " + S;
	}

}
