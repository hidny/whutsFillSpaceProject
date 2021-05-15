package whutsFillSpaceProject;

public class testShape {

	
	public static void main(String args[]) {
		
		System.out.println("Start");
		ShapeInOrientation initShap = setupShape187();
		
		First3dShapeFiller.getAllOrientation(initShap);
		
		System.out.println("Done");
		//TODO: draw shape
	}
	
	public static boolean[][][] clearShape() {
		
		boolean testShape[][][] = new boolean[ShapeInOrientation.DIM_SIZE][ShapeInOrientation.DIM_SIZE][ShapeInOrientation.DIM_SIZE];
		
		for(int i=0; i<testShape.length; i++) {
			for(int j=0; j<testShape[0].length; j++) {
				for(int k=0; k<testShape[0][0].length; k++) {
					testShape[i][j][k] = false;
				}
			}
		}
		
		return testShape;
	}
	
	public static ShapeInOrientation setupShape187() {

		
		//Try 187:
		boolean shape[][][] = clearShape();
		
		shape[0][1][1] = true;
		shape[1][1][1] = true;
		shape[2][1][1] = true;
		shape[3][1][1] = true;

		shape[2][2][1] = true;
		shape[3][0][1] = true;

		//down
		shape[0][1][0] = true;
		
		//up the page:
		shape[2][2][2] = true;
		
		int centerI = 2;
		int centerJ = 1;
		int centerK = 1;
		
		if(shape[centerI][centerJ][centerK] == false) {
			System.out.println("Error: invalid center in 187!");
			System.exit(1);
		}
		
		ShapeInOrientation ret = new ShapeInOrientation(shape, "Shape 187");
		
		return ret;
	}
	
	public static ShapeInOrientation setupShapeCross() {

		
		//Try cross (Should fill space):
		boolean shape[][][] = clearShape();
		
		shape[1][1][0] = true;
		

		shape[1][0][1] = true;
		
		shape[0][1][1] = true;
		shape[1][1][1] = true;
		shape[2][1][1] = true;
		shape[3][1][1] = true;
		
		shape[1][2][1] = true;
		
		shape[1][1][2] = true;

		
		ShapeInOrientation ret = new ShapeInOrientation(shape, "Dali Cross");
		
		return ret;
	}

	public static ShapeInOrientation setupFilledShape() {
		boolean shape[][][] = clearShape();
		
		for(int i=0; i<shape.length; i++) {
			for(int j=0; j<shape[0].length; j++) {
				for(int k=0; k<shape[0][0].length; k++) {
					shape[i][j][k] = true;
				}
			}
		}
		
		int centerI=0;
		int centerJ=0;
		int centerK = 0;
		ShapeInOrientation ret = new ShapeInOrientation(shape, "Basic Prism");
		
		return ret;
	}
	
	

	public static ShapeInOrientation setupPrism() {

		
		//Try 187:
		boolean shape[][][] = clearShape();
		
		shape[0][1][1] = true;
		
		ShapeInOrientation ret = new ShapeInOrientation(shape, "Single cube. Should fill space.");
		
		return ret;
	}
}
