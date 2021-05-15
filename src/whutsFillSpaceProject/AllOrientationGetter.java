package whutsFillSpaceProject;

import java.util.HashSet;
import java.util.Stack;
/*
 * ,6,10,15,10 ( 19 ) space: 152
Stack:
  0,0,10,10,10
  0,1,10,11,10
  0,5,11,10,10
  0,0,10,10,12
  0,6,10,11,11
  0,6,10,12,10
  2,1,12,10,10
  0,3,10,11,12
  4,1,11,11,11
  1,0,11,12,10
  0,0,10,10,14
  4,7,10,11,13
  1,0,10,13,11
  10,3,10,14,10
  13,7,12,10,12
  5,1,14,10,10
  3,6,10,11,14
  0,6,10,13,12
  8,6,10,15,10
  5,2,10,15,10
 
 */
/*
 *Before bed:
 * 0,0,10,10,10
  0,1,11,10,10
  0,6,10,11,10
  1,0,10,11,11
  1,7,11,11,10
  0,0,10,10,12
  2,3,11,11,11
  2,1,12,11,10
  1,6,10,11,12
  1,5,11,10,12
  3,3,11,12,10
  0,6,10,13,10
  1,1,12,10,12
  1,0,10,13,11
  0,0,10,10,14
  2,3,11,11,13
  2,3,11,13,11
  3,6,10,11,14
  21,0,10,12,13
  8,6,10,13,12
  1,5,11,10,14
  2,1,11,14,10
  1,0,14,11,10
  1,0,13,12,10
  0,6,10,15,10
  0,0,15,10,10
  7,0,12,13,11
  0,0,13,11,12
  17,0,12,10,14
  2,0,15,11,10
  0,5,16,10,10
  2,3,10,15,11
  1,0,11,15,10
  0,0,10,10,16
  7,0,12,12,13
  16,0,14,11,12
  2,3,11,11,15
  16,0,11,15,11
  23,1,14,12,11
  3,6,10,11,16
  0,6,10,12,15
  0,6,10,14,13
  1,5,11,10,16
  0,0,14,10,13
  0,6,10,13,14
  21,0,10,16,11
  5,2,10,17,10
  8,0,12,13,13
  19,1,11,13,14
  21,0,11,12,15
  18,0,13,11,14
  16,4,16,11,11
  17,0,12,10,16
  9,6,10,17,11
  20,2,11,17,10
  0,1,13,15,10
  14,0,18,10,10
  0,4,10,10,18
 */
//TODO: order by num neighbours...
// and try the one with most neighbours at some dist...

public class AllOrientationGetter {

	public static final int SIZE = 100;
	public static boolean space[][][] = new boolean[SIZE][SIZE][SIZE];
	
	
	//TODO: should be 10, but for debugging, set 0:
	//public static int START_BUILD_LOCATION = 0;
	
	public static int ARRAY_MARGIN_SIZE =10;
	
	public static int NUMBER_OF_DIMENSIONS = 3;
	
	public static int debugNumSpacesFilled = 0;
	
	public static String debugCurStack = "";
	
	public static String debugStack[] = new String[100];
	public static long DEBUG_PRINT_PERIOD = 1000000;
	//public static long DEBUG_PRINT_PERIOD = 1;
	
	public static void main(String args[]) {
		
		//lazy strat:
		//Should fail:
		ShapeInOrientation initShape = testShape.setupShape187();
		//ShapeInOrientation initShape = testShape. setupPrism();
		
		//Should work:
		//ShapeInOrientation initShape = testShape.setupShapeCross();
				
				
		ShapeInOrientation allOrientations[] = getAllOrientation(initShape);
		
		String allOffset[][] = new String[allOrientations.length][];
		
		for(int i=0; i<allOrientations.length; i++) {
			allOffset[i] = allOrientations[i].getOffsets();
		}


		int curi = ARRAY_MARGIN_SIZE;
		int curj = ARRAY_MARGIN_SIZE;
		int curk = ARRAY_MARGIN_SIZE;
		
		String nextEmpty[] = getNextEmptySpace(curi, curj, curk).split(",");
		curi = Integer.parseInt(nextEmpty[0]);
		curj = Integer.parseInt(nextEmpty[1]);
		curk = Integer.parseInt(nextEmpty[2]);
		
		
		int debugStackSize = 0;
		
		Stack <String> stack = new Stack<String>();
		
		boolean retryingToFillSameSpot = false;

		long debugNum = 0;

		FILL_NEXT_SPOT:
		while(nextEmpty.equals("") == false) {
			
			int mStart=0;
			int nStart=0;
			
			//Get start m.n, if a few have already been tried:
			if(retryingToFillSameSpot) {


				debugStackSize--;


				if(debugStackSize == -1) {
					System.out.println("ERROR: stack size -1");
					//System.out.println()
				}

				if(debugStackSize < debugStack.length) {
					debugStack[debugStackSize] = "";
				}
				
				String undoDebug = stack.pop();

				//TODO: if stack size == 0:
				//it's over!
				if(debugStackSize == 0) {
					System.out.println("TODO: should be done because we should be able to start with a piece in any orientation! debugNum: " + debugNum);
					System.out.println(undoDebug);
					System.exit(1);
				}

				
				String undoTokens[] = undoDebug.split(",");
				
				mStart = Integer.parseInt(undoTokens[0]);
				nStart = Integer.parseInt(undoTokens[1]);

				
				curi = Integer.parseInt(undoTokens[2]);
				curj = Integer.parseInt(undoTokens[3]);
				curk = Integer.parseInt(undoTokens[4]);
				
				
				String offsets[] = allOffset[mStart][nStart].split(",");
				
				int io = Integer.parseInt(offsets[0]);
				int jo = Integer.parseInt(offsets[1]);
				int ko = Integer.parseInt(offsets[2]);
				
				int iToUse = curi - io;
				int jToUse = curj - jo;
				int kToUse = curk - ko;
				
				removeShape(allOrientations[mStart], iToUse, jToUse, kToUse);
				//System.out.println("Undo: " + undoDebug + "( " + stackSize + " )  space: " + debugNumSpacesFilled);

				
				retryingToFillSameSpot = false;

				// Iterate to the next value of n:
				nStart++;
				
				if(nStart >= allOffset[mStart].length) {
					mStart++;
					nStart = 0;
				}
				
			}
			
			
			TRY_A_SHAPE:
			for(int m = mStart; m<allOrientations.length; m++) {
				mStart = 0;
				
					
				for(int n = nStart; n<allOffset[m].length; n++) {
					nStart = 0;
					
					String offsets[] = allOffset[m][n].split(",");
					
					int io = Integer.parseInt(offsets[0]);
					int jo = Integer.parseInt(offsets[1]);
					int ko = Integer.parseInt(offsets[2]);
					
					int iToUse = curi - io;
					int jToUse = curj - jo;
					int kToUse = curk - ko;
					
					if(isSpaceFillable(allOrientations[m], iToUse, jToUse, kToUse)) {
						
						String debugAdd = m + "," + n + "," + curi + "," +  curj + "," +  curk;
						stack.add(debugAdd);
						if(debugStackSize < debugStack.length) {
							debugStack[debugStackSize] = debugAdd;
						}
						
						debugNum ++;
						debugStackSize++;
						
						fillSpace(allOrientations[m], iToUse, jToUse, kToUse);
						
						if(debugNum % DEBUG_PRINT_PERIOD == 0) {
							System.out.println("Stack add: " + debugAdd + " ( " + debugStackSize + " )" + " space: " + debugNumSpacesFilled);
							System.out.println("Stack:");
							for(int s=0; s<debugStackSize; s++) {
								System.out.println("  " + debugStack[s]);
							}
						}
						
						String tmp = getNextEmptySpace(curi, curj, curk);
						
						if(tmp.equals("")) {
							System.out.println("SPACE FILLED!");
							break FILL_NEXT_SPOT;
							
						} else if(tmp.equals("Nope")) {
							//System.out.println("Nope");
							break TRY_A_SHAPE;
						}
						
						int previ = curi;
						int prevj = curj;
						int prevk = curk;
						
						nextEmpty = getNextEmptySpace(curi, curj, curk).split(",");
						curi = Integer.parseInt(nextEmpty[0]);
						curj = Integer.parseInt(nextEmpty[1]);
						curk = Integer.parseInt(nextEmpty[2]);
						
						if((curi == previ && curj == prevj && curk == prevk)
								|| space[previ][prevj][prevk] == false) {
							System.out.println("ERROR: didn't fill current space!");
							System.exit(1);
						}
						//if(debugNum % DEBUG_PRINT_PERIOD == 0) {
						////	System.out.println("Next empty space: " +  getNextEmptySpace(curi, curj, curk) + "\nManhattan dist from start: "+ (curi + curj + curk - 3*ARRAY_MARGIN_SIZE));
						//}
						
						continue FILL_NEXT_SPOT;
					}
				}
			}
		
			//At this point, we couldn't fill it...
		
			retryingToFillSameSpot = true;
		
		}
		
		System.out.println("Done!");
		
	}
	
	public static String getNextEmptySpaceold5(int iinput, int jinput, int kinput) {
		
		String ret = "";
		int curDist = -1;
		
		OUT:
		for(int dist = iinput + jinput + kinput; dist <=NUMBER_OF_DIMENSIONS * space.length; dist++) {
			
			for(int i = iinput; i<=dist && i<space.length - ARRAY_MARGIN_SIZE; i++) {
				iinput = ARRAY_MARGIN_SIZE;
				
				for(int j = jinput; i + j <= dist && j<space[0].length - ARRAY_MARGIN_SIZE; j++) {
					jinput = ARRAY_MARGIN_SIZE;
					
					int k = dist - i - j;
					if( k < ARRAY_MARGIN_SIZE) {
						
						continue;
					} else if(k >= space[0][0].length - ARRAY_MARGIN_SIZE) {
						continue;
					} else if(space[i][j][k]) {
						continue;
					}
					
					
					if(space[i][j][k] == false) {
						ret = i + "," + j + "," + k;
						curDist = dist;
						break OUT;
					}
					
					
				}
			}
		}
		
		for(int dist = curDist; dist <=NUMBER_OF_DIMENSIONS * space.length && dist < curDist + 2; dist++) {
			
			for(int i = ARRAY_MARGIN_SIZE; i<=dist && i<space.length - ARRAY_MARGIN_SIZE; i++) {
				iinput = ARRAY_MARGIN_SIZE;
				
				for(int j = ARRAY_MARGIN_SIZE; i + j <= dist && j<space[0].length - ARRAY_MARGIN_SIZE; j++) {
					
					int k = dist - i - j;
					if( k < ARRAY_MARGIN_SIZE) {
						
						continue;
					} else if(k >= space[0][0].length - ARRAY_MARGIN_SIZE) {
						continue;
					} else if(space[i][j][k]) {
						continue;
					}
					
					
					int numNeighbours = 0;
					
					for(int tmp=-1; tmp<=1; tmp++) {
						if(tmp == 0) {
							continue;
						}
						if(space[i+tmp][j][k]) {
							numNeighbours++;
						}
						if(space[i][j+tmp][k]) {
							numNeighbours++;
						}
						if(space[i][j][k+tmp]) {
							numNeighbours++;
						}
					}
					if(numNeighbours == 6) {
						//System.out.println("Nope");
						return "Nope";
					}
					
					
					
				}
			}
		}
				
		return ret;
	}


	public static String getNextEmptySpaceOld2(int iinput, int jinput, int kinput) {
		
		
		for(int dist = iinput + jinput + kinput; dist <=NUMBER_OF_DIMENSIONS * space.length; dist++) {

			String best = "";
			int bestNumNeighbours = -1;
			int bestDistMargin = -1;
			
			for(int i = ARRAY_MARGIN_SIZE; i<=dist && i<space.length - ARRAY_MARGIN_SIZE; i++) {

				
				for(int j = ARRAY_MARGIN_SIZE; i + j <= dist && j<space[0].length - ARRAY_MARGIN_SIZE; j++) {
					
					int k = dist - i - j;
					if( k < ARRAY_MARGIN_SIZE) {
						
						continue;
					} else if(k >= space[0][0].length - ARRAY_MARGIN_SIZE) {
						continue;
					} else if(space[i][j][k]) {
						continue;
					}
					
					if(space[i][j][k] == false) {
						
						String cur = i + "," + j + "," + k;
						
						int numNeighbours = 0;
						
						for(int tmp=-1; tmp<=1; tmp++) {
							if(space[i+tmp][j][k]) {
								numNeighbours++;
							}
							if(space[i][j+tmp][k]) {
								numNeighbours++;
							}
							if(space[i][j][k+tmp]) {
								numNeighbours++;
							}
						}
						
						int curDistMargin = Math.min(i, Math.min(j, k)) - ARRAY_MARGIN_SIZE;
						
						if(numNeighbours == 6) {
							return "Nope";
						}
						
						if(best.equals("")
								|| curDistMargin > bestDistMargin
								|| (curDistMargin == bestDistMargin && numNeighbours > bestNumNeighbours) ) {
							best = cur;
							bestNumNeighbours = numNeighbours;
							bestDistMargin = curDistMargin;
							
						}
					}
					
					
				}
			}
			
			if(best.equals("") == false) {
				return best;
			}
		}
		
				
		return "";
	}
	
	public static String getNextEmptySpaceWirdNeibours(int iinput, int jinput, int kinput) {
		
		
		for(int dist = iinput + jinput + kinput; dist <=NUMBER_OF_DIMENSIONS * space.length; dist++) {

			String best = "";
			int bestNumNeighbours = -1;
			
			for(int i = ARRAY_MARGIN_SIZE; i<=dist && i<space.length - ARRAY_MARGIN_SIZE; i++) {

				
				for(int j = ARRAY_MARGIN_SIZE; i + j <= dist && j<space[0].length - ARRAY_MARGIN_SIZE; j++) {
					
					int k = dist - i - j;
					if( k < ARRAY_MARGIN_SIZE) {
						
						continue;
					} else if(k >= space[0][0].length - ARRAY_MARGIN_SIZE) {
						continue;
					} else if(space[i][j][k]) {
						continue;
					}
					
					if(space[i][j][k] == false) {
						
						String cur = i + "," + j + "," + k;
						
						int numNeighboursSortOf = 0;
						
						for(int tmp=-1; tmp<=1; tmp++) {
							if(space[i+tmp][j][k]) {
								numNeighboursSortOf++;
							}
							if(space[i][j+tmp][k]) {
								numNeighboursSortOf++;
							}
							if(space[i][j][k+tmp]) {
								numNeighboursSortOf++;
							}
						}

						if(numNeighboursSortOf == 6) {
							return "Nope";
						}
						
						for(int tmp=-1; tmp<=1; tmp++) {
							for(int tmp2=-1; tmp2<=1; tmp2++) {
								if(space[i+tmp][j+tmp2][k]) {
									numNeighboursSortOf++;
								}
								if(space[i][j+tmp][k+tmp2]) {
									numNeighboursSortOf++;
								}
								if(space[i+tmp2][j][k+tmp]) {
									numNeighboursSortOf++;
								}
							}
						}
						
						
						
						if(best.equals("")
								|| numNeighboursSortOf > bestNumNeighbours  ) {
							best = cur;
							bestNumNeighbours = numNeighboursSortOf;
							
						}
					}
					
					
				}
			}
			
			if(best.equals("") == false) {
				return best;
			}
		}
		
		return "";
	}
		
	public static String getNextEmptySpaceOld3(int iinput, int jinput, int kinput) {
		
		
		for(int dist = iinput + jinput + kinput; dist <=NUMBER_OF_DIMENSIONS * space.length; dist++) {

			String best = "";
			int bestNumNeighbours = -1;
			int bestDistMargin = -1;
			
			for(int i = ARRAY_MARGIN_SIZE; i<=dist && i<space.length - ARRAY_MARGIN_SIZE; i++) {

				
				for(int j = ARRAY_MARGIN_SIZE; i + j <= dist && j<space[0].length - ARRAY_MARGIN_SIZE; j++) {
					
					int k = dist - i - j;
					if( k < ARRAY_MARGIN_SIZE) {
						
						continue;
					} else if(k >= space[0][0].length - ARRAY_MARGIN_SIZE) {
						continue;
					} else if(space[i][j][k]) {
						continue;
					}
					
					if(space[i][j][k] == false) {
						
						String cur = i + "," + j + "," + k;
						
						int numNeighbours = 0;
						
						for(int tmp=-1; tmp<=1; tmp++) {
							if(space[i+tmp][j][k]) {
								numNeighbours++;
							}
							if(space[i][j+tmp][k]) {
								numNeighbours++;
							}
							if(space[i][j][k+tmp]) {
								numNeighbours++;
							}
						}
						
						int curDistMargin = Math.min(i, Math.min(j, k)) - ARRAY_MARGIN_SIZE;
						
						if(numNeighbours == 6) {
							return "Nope";
						}
						
						if(best.equals("")
								|| numNeighbours > bestNumNeighbours 
								|| (numNeighbours == bestNumNeighbours && curDistMargin > bestDistMargin) ) {
							best = cur;
							bestNumNeighbours = numNeighbours;
							bestDistMargin = curDistMargin;
							
						}
					}
					
					
				}
			}
			
			if(best.equals("") == false) {
				return best;
			}
		}
		
				
		return "";
	}
	
	//best so far:
	public static String getNextEmptySpace(int iinput, int jinput, int kinput) {
		
		for(int dist = iinput + jinput + kinput; dist <=NUMBER_OF_DIMENSIONS * space.length; dist++) {
			
			for(int i = iinput; i<=dist && i<space.length - ARRAY_MARGIN_SIZE; i++) {
				iinput = ARRAY_MARGIN_SIZE;
				
				for(int j = jinput; i + j <= dist && j<space[0].length - ARRAY_MARGIN_SIZE; j++) {
					jinput = ARRAY_MARGIN_SIZE;
					
					int k = dist - i - j;
					if( k < ARRAY_MARGIN_SIZE) {
						
						continue;
					} else if(k >= space[0][0].length - ARRAY_MARGIN_SIZE) {
						continue;
					} else if(space[i][j][k]) {
						continue;
					}
					
					if(space[i][j][k] == false) {
						return i + "," + j + "," + k;
					}
					
					
				}
			}
		}
		
				
		return "";
	}
	
	
	public static void fillSpace(ShapeInOrientation shapeOrientation, int is, int js, int ks) {
		
		boolean table[][][] = shapeOrientation.getShape();
		
		
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				for(int k=0; k<table[0][0].length; k++) {
					
					if(table[i][j][k]) {

						int iToUse = i + is;
						int jToUse = j + js;
						int kToUse = k + ks;
						
						//if(iToUse >= 0 && iToUse <  space.length
						//&& jToUse >= 0 && jToUse <  space[0].length
						//&& kToUse >= 0 && kToUse <  space[0][0].length ) {
							
							if(space[iToUse][jToUse][kToUse] == true) {
								System.out.println("ERROR: trying to fill already filled space!");
								System.exit(1);
							}
							
							//System.out.println("Filling: " + iToUse + "," + jToUse + "," + kToUse);
							space[iToUse][jToUse][kToUse] = true;
							debugNumSpacesFilled++;
						//} else {
						//	System.out.println("AH: Filling source: " + is + "," + js + "," + ks);
						//	System.out.println("AH: Filling: " + iToUse + "," + jToUse + "," + kToUse);
						//	System.exit(1);
						//}
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
						
						int iToUse = i + is;
						int jToUse = j + js;
						int kToUse = k + ks;

						//if(iToUse >= 0 && iToUse <  space.length
						//&& jToUse >= 0 && jToUse <  space[0].length
						//&& kToUse >= 0 && kToUse <  space[0][0].length ) {
							
							if(space[iToUse][jToUse][kToUse] == false) {
								System.out.println("ERROR: trying to remove shape that isn't there!");
								System.exit(1);
							}
							
							space[iToUse][jToUse][kToUse] = false;
							debugNumSpacesFilled--;
						//} else {
						//	System.out.println("AH: removing: " + iToUse + "," + jToUse + "," + kToUse);
						//	System.exit(1);
						//}
					}
				}
			}
		}
		
	}

	//TODO: don't scan tru table, just use list of numbers...
	public static boolean isSpaceFillable(ShapeInOrientation shapeOrientation, int is, int js, int ks) {
		
		boolean table[][][] = shapeOrientation.getShape();
		
		
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				for(int k=0; k<table[0][0].length; k++) {
					
					if(table[i][j][k]) {

						int iToUse = i + is;
						int jToUse = j + js;
						int kToUse = k + ks;

						//I created marins, so w can skip tis cck:
						//if(iToUse >= 0 && iToUse <  space.length
						//&& jToUse >= 0 && jToUse <  space[0].length
						//&& kToUse >= 0 && kToUse <  space[0][0].length ) {
							
							if(space[iToUse][jToUse][kToUse] == true) {
								return false;
							}
						//}
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
		
		if(curNumFound < ret.length) {
			
			ShapeInOrientation ret2[] = new ShapeInOrientation[curNumFound];
			
			for(int i=0; i<ret2.length; i++) {
				ret2[i] = ret[i];
			}
			
			return ret;
			
		} else {
		
			return ret;
		}
	}
	
	
	
}
