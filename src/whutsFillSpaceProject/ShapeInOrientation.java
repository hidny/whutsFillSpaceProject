package whutsFillSpaceProject;

public class ShapeInOrientation {

	
	public static int DIM_SIZE = 6;
	public static int OTHER_END_INDEX = DIM_SIZE - 1;
	
	private boolean shape[][][] = new boolean[DIM_SIZE][DIM_SIZE][DIM_SIZE];
	
	
	/*private int centeri = -1;
	private int centerj = -1;
	private int centerk = -1;
	*/
	
	public boolean[][][] getShape() {
		return shape;
	}
/*
	//TODO: use later:
	public int getCenteri() {
		return centeri;
	}

	//TODO: use later:
	public int getCenterj() {
		return centerj;
	}

	//TODO: use later:
	public int getCenterk() {
		return centerk;
	}
*/
	
	public ShapeInOrientation(boolean shape[][][], int centeri, int centerj, int centerk) {
		hardCopyShape(shape);
		
		/*this.centeri = centeri;
		this.centerj = centerj;
		this.centerk = centerk;*/
	}
	
	private void hardCopyShape(boolean shapeIn[][][]) {
		shape = new boolean[DIM_SIZE][DIM_SIZE][DIM_SIZE];
		
		for(int i=0; i<shape.length; i++) {
			for(int j=0; j<shape[0].length; j++) {
				for(int k=0; k<shape[0][0].length; k++) {
					shape[i][j][k] = shapeIn[i][j][k];
				}
			}
		}
		
		
	}
	
	//TODO: rotate
	
	public static ShapeInOrientation rotateShape3d(ShapeInOrientation input, int rot1, int rot2, int rot3) {
		
		
		boolean retShape[][][] = new boolean[DIM_SIZE][DIM_SIZE][DIM_SIZE];
		
		int newCenterI = -1;
		int newCenterJ = -1;
		int newCenterk = -1;
		//Idea:
		//dim1 <-[0]
		//dim2 < -[1]
		
		//3rd dim staus the same
		
		
		for(int i=0; i<input.shape.length; i++) {
			for(int j=0; j<input.shape[0].length; j++) {
				for(int k=0; k<input.shape[0][0].length; k++) {
					
					//there's probably a systematic way, but whatever:

					int newi = -1;
					int newj = -1;
					int newk = k;
					
					if(rot1 == 0) {
						newi = i;
						newj = j;
						
					} else if(rot1 == 1) {
						newi = OTHER_END_INDEX - j;
						newj = i;
						
					} else if(rot1 == 2) {
						newi = OTHER_END_INDEX - i;
						newj = OTHER_END_INDEX - j;
						
					} else if(rot1 == 3) {
						newi = j;
						newj = OTHER_END_INDEX - i;
						
					}

					int newi2 = -1;
					int newj2 = newj;
					int newk2 = -1;
					
					if(rot2 == 0) {
						newi2 = newi;
						newk2 = newk;
						
					} else if(rot2 == 1) {
						newi2 = OTHER_END_INDEX - newk;
						newk2 = newi;
						
					} else if(rot2 == 2) {
						newi2 = OTHER_END_INDEX - newi;
						newk2 = OTHER_END_INDEX - newk;
						
					} else if(rot2 == 3) {
						newi2 = newk;
						newk2 = OTHER_END_INDEX - newi;
						
					}
					

					int newi3 = newi2;
					int newj3 = -1;
					int newk3 = -1;
					
					if(rot3 == 0) {
						newj3 = newj2;
						newk3 = newk2;
						
					} else if(rot3 == 1) {
						newj3 = OTHER_END_INDEX - newk2;
						newk3 = newj2;
						
					} else if(rot3 == 2) {
						newj3 = OTHER_END_INDEX - newj2;
						newk3 = OTHER_END_INDEX - newk2;
						
					} else if(rot3 == 3) {
						newj3 = newk2;
						newk3 = OTHER_END_INDEX - newj2;
						
					}
					
					/*if(input.centeri == newi3
							&& input.centerj == newj3
							&& input.centerk == newk3 ) {

						newCenterI = i;
						newCenterJ = j;
						newCenterk = k;
					}*/
					
					retShape[i][j][k] = input.shape[newi3][newj3][newk3];
				}
			}
		}
		
		return new ShapeInOrientation(retShape, newCenterI, newCenterJ, newCenterk);
	}
	
	
	public boolean equals(Object o) {
		
		ShapeInOrientation other = (ShapeInOrientation)o;
		
		for(int i=0; i<other.shape.length; i++) {
			for(int j=0; j<other.shape.length; j++) {
				for(int k=0; k<other.shape.length; k++) {
					
					if(this.shape[i][j][k] != other.shape[i][j][k]) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	//TODO: insert into space based on (centeri, centerj, centerk)
}
