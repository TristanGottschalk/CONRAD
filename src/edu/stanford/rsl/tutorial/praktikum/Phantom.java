package edu.stanford.rsl.tutorial.praktikum;

import ij.ImageJ;
import edu.stanford.rsl.conrad.data.numeric.Grid2D;
import edu.stanford.rsl.conrad.data.numeric.InterpolationOperators;
import edu.stanford.rsl.conrad.data.numeric.NumericGrid;
import edu.stanford.rsl.conrad.data.numeric.NumericPointwiseOperators;

public class Phantom extends Grid2D {
	public Phantom(int width, int height, double [] spacing) {
		super(width, height);
		
		this.setSpacing(spacing);
		double origin [] = new double [2];
		origin[0] = -(width-1)*(spacing[0]/2);
		origin[1] = -(height-1)*(spacing[1]/2);
		this.setOrigin(origin);
		
		// circle for phantom
		int circleCenterX = width/2; //+width/8;
		int circleCenterY = height/2; //+height/10;
		int radius = width/2 - 200;
		for (int row = circleCenterY-radius; row < circleCenterY+radius; row++){
			for (int col = circleCenterX-radius; col < circleCenterX+radius; col++) {
				if (((col-circleCenterX)*(col-circleCenterX))+((row-circleCenterY)*(row-circleCenterY)) <= radius*radius){
					this.setAtIndex(col,row, .5f);
				}
			}
		}
		
/*		// ellipse for phantom
		int ellipseCenterX = width/4+width/8;
		int ellipseCenterY = height/2-height/8;
		int axisA = width/8;
		int axisB = height/4;
		for (int row = ellipseCenterY-axisB; row < ellipseCenterY+axisB; row++){
			for (int col = ellipseCenterX-axisA; col < ellipseCenterX+axisA; col++) {
				if (((col-ellipseCenterX)*(col-ellipseCenterX)*axisB*axisB)+((row-ellipseCenterY)*(row-ellipseCenterY)*axisA*axisA) <= axisA*axisA*axisB*axisB){
					this.setAtIndex(col,row, .2f);
				}
			}
		}*/
		
/*		// rectangle for phantom
		int edgeLength = width/4;
		int squareCenterX = width/2-edgeLength/6;
		int squareCenterY = height/2-edgeLength/2;
		for(int row = squareCenterY-edgeLength; 2 * row<squareCenterY+edgeLength; row++){
			for (int col = squareCenterX-edgeLength; col < squareCenterX+edgeLength; col++){
				this.setAtIndex(col,row, (.7f));
			}
		}*/
		
}

	public static void main(String[] args) {
		
		new ImageJ();
		double spacing [] = {1 , 1};
		Phantom a = new Phantom(256,256,spacing);
		Phantom b = new Phantom(256,256,spacing);
		
		//show Phantoms
		a.show();
		b.show();
		
		//Subtract Phantoms
		NumericGrid grid = NumericPointwiseOperators.subtractedBy(a, b);
		grid.show();
		
		double origin [] = a.getOrigin();
		System.out.println("Maximum intensity: " + NumericPointwiseOperators.min(a));
		System.out.println("Minimum intensity: " + NumericPointwiseOperators.max(a));
		System.out.println("Mean intensity: " + NumericPointwiseOperators.mean(a));
		System.out.println("Position of origin: " + origin[0] + " " + origin[1]);
		
	}

}
