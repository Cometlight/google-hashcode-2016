
public class PaintLine extends Move{

	public int C1;
	public int R1;
	
	public int C2;
	public int R2;
	
	public PaintLine(int R1, int C1, int R2, int C2) {
		super(1);
		this.C1 = C1;
		this.R1 = R1;
		this.C2 = C2;
		this.R2 = R2;
	}

	@Override
	public void apply(Binary2dField binary2dField) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "PAINT_LINE " + this.R1+ " " + this.C1 + " " + this.R2 + " " + this.C2; 
	}

}
