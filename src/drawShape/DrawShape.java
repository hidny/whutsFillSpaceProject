package drawShape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import whutsFillSpaceProject.ShapeInOrientation;
import whutsFillSpaceProject.testShape;



//TODO: borrow from prob199Draw

public class DrawShape  extends JPanel {


	
	public static void main(String[] args) {
		
		 DrawShape drawing = new DrawShape();
		 
		//1. Create the frame.
		JFrame frame = new JFrame("FrameDemo");
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		frame.setSize(WIDTH_FRAME, HEIGHT_FRAME);
		
		HelloWorld helloWorld= new HelloWorld(drawing);
		
		frame.addKeyListener(helloWorld);
		
		frame.add(drawing);
		drawing.drawing();
	}
	
	
	
	//Plan make 3d shape like in dr brain.
	
	//TODO: paint the back 1st, then the front.
	
	int HEIGHT = 50;
	int WIDTH = 50;
	
	//private boolean shape[][][];
	
	//TODO: colour left
	//colour right
	//colour up
	//marginDist (let the cube not touch 100%)
	
	
	
	public static double R = 1.0/Math.sqrt(Math.PI);

	public void rotateDrawing(int rot1, int rot2, int rot3) {
		
		curShape = curShape.rotateShape3d(curShape, rot1, rot2, rot3);
		this.repaint();
	}
	
	
	int NUM_ROTATION = 4;
	//private ShapeInOrientation rotations[][][] = new  ShapeInOrientation()[NUM_ROTATION][NUM_ROTATION][NUM_ROTATION];
	
	
	//private ShapeInOrientation curShape = testShape.setupShape187();
	private ShapeInOrientation curShape = testShape.setupShapeCross();
	
	public void drawing() {
		repaint();
	}
	
	
	
	//TODO: make it more rotatable:
	public static int GRID_SIZE = 10;
	//TODO: top XY points...
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//TODO
		
		
		g.setColor(Color.BLUE);
		
		//g.drawPolygon(xPoints, yPoints, nPoints);
		
		g.drawOval(100, 100, 10, 10);
		
		int xPoints[] = new int[]{0, 200, 200, 0};
		int yPoints[] = new int[]{0, 100, 300, 200};
		
		g.drawPolygon(xPoints, yPoints, 4);
		
		//drawCube(g, 0, 0, 0);
		g.drawOval(MIDDLE_X, MIDDLE_Y, 10, 10);
		
		g.drawLine(MIDDLE_X, 0, MIDDLE_X, WIDTH_FRAME);
		g.drawLine(0, MIDDLE_Y, HEIGHT_FRAME, MIDDLE_Y);
		
		//drawShape(g, testShape.setupFilledShape());
		drawShape(g, this.curShape);
	}

	
	
	public void drawShape(Graphics g, ShapeInOrientation shapeInOrientation) {
		
		boolean shape[][][] = shapeInOrientation.getShape();
		
		int maxDist = (shape.length - 1) + (shape[0].length - 1) + (shape[0][0].length - 1);
		
		for(int dist = maxDist; dist >=0; dist--) {
			
			for(int i=0; i<shape.length; i++) {
				for(int j=0; j<shape[0].length; j++) {
					
					int k = dist - i - j;
					if( k < 0) {
						break;
					} else if( k >= shape[0][0].length) {
						continue;
					}
					
					if(shape[i][j][k]) {
						System.out.println(i+ "," + j + "," + k);
						drawCube(g, i, j, k);
					}
				}
			}
		}
		
	}
	
	public static int leftXPoints[] = new int[]{-20, 0, 0, -20};
	public static int leftYPoints[] = new int[]{-10, 0, 20, 10};

	public static int rightXPoints[] = new int[]{0, 20, 20, 0};
	public static int rightYPoints[] = new int[]{0, -10, 10, 20};
	

	public static int topXPoints[] = new int[]{-20, 0, 20, 0};
	public static int topYPoints[] = new int[]{-10, -20, -10, 0};
	
	public static int WIDTH_FRAME = 600;
	public static int HEIGHT_FRAME = 600;
	
	public static int MIDDLE_X = WIDTH_FRAME / 2;
	public static int MIDDLE_Y = HEIGHT_FRAME / 2;
	
	public static int NUM_VERTICES = 4;
	
	public void drawCube(Graphics g, int i, int j, int k) {
		
		int startX = MIDDLE_X + 2 * GRID_SIZE * i - 2*GRID_SIZE * k;
		int startY = MIDDLE_Y + 2 * GRID_SIZE * j - GRID_SIZE * i - GRID_SIZE * k;
		

		
		int topx[] = new int[NUM_VERTICES];
		int topy[] = new int[NUM_VERTICES];
		int rightx[] = new int[NUM_VERTICES];
		int righty[] = new int[NUM_VERTICES];
		int leftx[] = new int[NUM_VERTICES];
		int lefty[] = new int[NUM_VERTICES];
		
		for(int m=0; m<NUM_VERTICES; m++) {

			topx[m] = startX + topXPoints[m];
			topy[m] = startY + topYPoints[m];
			

			rightx[m] = startX + rightXPoints[m];
			righty[m] = startY + rightYPoints[m];
			

			leftx[m] = startX + leftXPoints[m];
			lefty[m] = startY + leftYPoints[m];
			
			
		}
		g.setColor(Color.GREEN);
		//g.drawPolygon(topx, topy, NUM_VERTICES);
		g.fillPolygon(topx, topy, NUM_VERTICES);
		
		g.setColor(Color.ORANGE);
		//g.drawPolygon(leftx, lefty, NUM_VERTICES);
		g.fillPolygon(leftx, lefty, NUM_VERTICES);
		
		g.setColor(Color.YELLOW);
		//g.drawPolygon(rightx, righty, NUM_VERTICES);
		g.fillPolygon(rightx, righty, NUM_VERTICES);
		

		g.setColor(Color.BLACK);
		g.drawPolygon(topx, topy, NUM_VERTICES);
		g.drawPolygon(leftx, lefty, NUM_VERTICES);
		g.drawPolygon(rightx, righty, NUM_VERTICES);
		
		
	}
	
	
		//https://www.artima.com/forums/flat.jsp?forum=1&thread=361
		// Override the isFocusTraversable method and return true
		public boolean isFocusTraversable ( ) {
		return true ;
		}
	
	private class MyKeyListener extends KeyAdapter {
		public void keyPressed (KeyEvent e) {
		System.out.println(e.getKeyText (e.getKeyCode()));
		}
	
		public void keyReleased (KeyEvent e) {
		System.out.println(e.getKeyText(e.getKeyCode()));
		}
	
		public void keyTyped (KeyEvent e) {
		System.out.println(e.getKeyChar());
		}
	}
	
}


class HelloWorld extends JPanel implements KeyListener{
	
	private DrawShape drawShape;
	public HelloWorld(DrawShape drawShape) {
		super();
		this.drawShape = drawShape;
	}
	
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped: "+e);
        
        if(e.getKeyChar() == '1') {
        	drawShape.rotateDrawing(1, 0, 0);
        	
        } else if(e.getKeyChar() == '9') {
        	drawShape.rotateDrawing(3, 0, 0);
        	
        } else if(e.getKeyChar() == '4') {
        	drawShape.rotateDrawing(0, 1, 0);
        	
        } else if(e.getKeyChar() == '6') {
        	drawShape.rotateDrawing(0, 3, 0);
        	
        } else if(e.getKeyChar() == '7') {
	    	drawShape.rotateDrawing(0, 0, 1);
	    	
	    } else if(e.getKeyChar() == '3') {
	    	drawShape.rotateDrawing(0, 0, 3);
	    	
    }
    }
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed: "+e);
    }
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased: "+e);
    }
}
