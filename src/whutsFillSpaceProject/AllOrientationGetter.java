package whutsFillSpaceProject;

import java.util.HashSet;

public class AllOrientationGetter {

	public static boolean space[][][] = new boolean[100][100][100];
	
	//int START = 0;
	
	public static int NUMBER_OF_DIMENSIONS = 3;
	
	public static void main(String args[]) {
		
		//lazy strat:
		ShapeInOrientation initShap = testShape.setupShape187();
		
		int numIterDebug = 0;
		
		for(int dist = 0; dist < NUMBER_OF_DIMENSIONS * space.length; dist++) {
			for(int i=0; i<dist && i<space.length; i++) {
				for(int j=0; i + j < dist && i<space[0].length; j++) {
					
					
					int k = dist - i - j;
					if( k < 0) {
						break;
					} else if(k >= space[0][0].length) {
						continue;
					}
					
					
					if(numIterDebug % 3000 == 0) {
						System.out.println("Target space to fill: (" + i + ", " + j + ", " + k + ")");
					}
					numIterDebug++;
				}
			}
		}
		
		System.out.println("Done!");
		
	}
	
	
	
	
	public static void fillSpace(ShapeInOrientation shapeOrientation, int is, int js, int ks) {
		
		boolean table[][][] = shapeOrientation.getShape();
		
		
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				for(int k=0; k<table[0][0].length; k++) {
					
					if(table[i][j][k]) {
						/*int iToUse = i + (is - shapeOrientation.getCenteri());
						int jToUse = j + (js - shapeOrientation.getCenterj());
						int kToUse = k + (ks - shapeOrientation.getCenterk());
						*/
						int iToUse = i + is;
						int jToUse = j + js;
						int kToUse = k + ks;
						
						if(iToUse >= 0 && iToUse <  space.length
						&& jToUse >= 0 && jToUse <  space[0].length
						&& kToUse >= 0 && kToUse <  space[0][0].length ) {
							
							if(space[iToUse][jToUse][kToUse] == true) {
								System.out.println("ERROR: trying to fill already filled space!");
								System.exit(1);
							}
							
							space[iToUse][jToUse][kToUse] = true;
						}
					}
				}
			}
		}
		
	}
	
	public static void removeShape(ShapeInOrientation shapeOrientation, int is, int js, int ks) {
		
		boolean table[][][] = shapeOrientation.getShape();
		
		
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				for(int k=0; k<table[0][0].length; k++) {
					
					if(table[i][j][k]) {
						
						/*
						int iToUse = i + (is - shapeOrientation.getCenteri());
						int jToUse = j + (js - shapeOrientation.getCenterj());
						int kToUse = k + (ks - shapeOrientation.getCenterk());
*/
						int iToUse = i + is;
						int jToUse = j + js;
						int kToUse = k + ks;

						if(iToUse >= 0 && iToUse <  space.length
						&& jToUse >= 0 && jToUse <  space[0].length
						&& kToUse >= 0 && kToUse <  space[0][0].length ) {
							
							if(space[iToUse][jToUse][kToUse] == false) {
								System.out.println("ERROR: trying to remove shape that isn't there!");
								System.exit(1);
							}
							
							space[iToUse][jToUse][kToUse] = false;
						}
					}
				}
			}
		}
		
	}

	public static boolean isSpaceFillable(ShapeInOrientation shapeOrientation, int is, int js, int ks) {
		
		boolean table[][][] = shapeOrientation.getShape();
		
		
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				for(int k=0; k<table[0][0].length; k++) {
					
					if(table[i][j][k]) {
						/*
						int iToUse = i + (is - shapeOrientation.getCenteri());
						int jToUse = j + (js - shapeOrientation.getCenterj());
						int kToUse = k + (ks - shapeOrientation.getCenterk());
*/
						int iToUse = i + is;
						int jToUse = j + js;
						int kToUse = k + ks;

						if(iToUse >= 0 && iToUse <  space.length
						&& jToUse >= 0 && jToUse <  space[0].length
						&& kToUse >= 0 && kToUse <  space[0][0].length ) {
							
							if(space[iToUse][jToUse][kToUse] == true) {
								return false;
							}
						}
					}
				}
			}
		}
		
		return true;
	}
	
	
	
	
	public static ShapeInOrientation[] getAllOrientation(ShapeInOrientation orig) {
		

		//Expect 24... unless symmetries
		ShapeInOrientation ret[] = new ShapeInOrientation[24];
		
		int curNumFound = 0;
		int NUM_ROTATIONS = 4;
		
		for(int i=0; i<NUM_ROTATIONS; i++) {
			for(int j=0; j<NUM_ROTATIONS; j++) {
				for(int k=0; k<NUM_ROTATIONS; k++) {
					
					ShapeInOrientation tmp = ShapeInOrientation.rotateShape3d(orig, i, j, k);
					
					boolean alreadyFound = false;
					for(int m=0; m<curNumFound; m++) {
						if(tmp.equals(ret[m])) {
							alreadyFound = true;
							break;
						}
					}
					
					if( ! alreadyFound) {
						ret[curNumFound] = tmp;
						curNumFound++;
					}
					
					
				}
			}
		}
		
		System.out.println("Number of orientations found: " + curNumFound);
		
		
		return ret;
	}
	
	
	
}
