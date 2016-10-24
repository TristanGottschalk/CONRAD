package edu.stanford.rsl.tutorial.praktikum;

import ij.ImageJ;
import edu.stanford.rsl.conrad.data.numeric.Grid2D;
import edu.stanford.rsl.conrad.data.numeric.NumericPointwiseOperators;

public class Phantom extends Grid2D {
	public Phantom(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		
		// build square
		int edgeLength = width/4;
		int squareCenterX = width/2-edgeLength/6;
		int squareCenterY = height/2-edgeLength/2;
		for(int row = squareCenterY-edgeLength; 2 * row<squareCenterY+edgeLength; row++){
			for (int col = squareCenterX-edgeLength; col < squareCenterX+edgeLength; col++){
				this.setAtIndex(col,row, (.2f));
			}
		}
		
		// circle
		int circleCenterX = width/2+width/8;
		int circleCenterY = height/2+height/10;
		int radius = width/3;
		for (int row = circleCenterY-radius; row < circleCenterY+radius; row++){
			for (int col = circleCenterX-radius; col < circleCenterX+radius; col++) {
				if (((col-circleCenterX)*(col-circleCenterX))+((row-circleCenterY)*(row-circleCenterY)) <= radius*radius){
					this.setAtIndex(col,row, .5f);
				}
			}
		}
		
		// ellipse
		int ellipseCenterX = width/4+width/8;
		int ellipseCenterY = height/2-height/8;
		int axisA = width/10;
		int axisB = height/5;
		for (int row = ellipseCenterY-axisB; row < ellipseCenterY+axisB; row++){
			for (int col = ellipseCenterX-axisA; col < ellipseCenterX+axisA; col++) {
				if (((col-ellipseCenterX)*(col-ellipseCenterX)*axisB*axisB)+((row-ellipseCenterY)*(row-ellipseCenterY)*axisA*axisA) <= axisA*axisA*axisB*axisB){
					this.setAtIndex(col,row, .7f);
				}
			}
		}
}

	public static void main(String[] args) {
		
		new ImageJ();
		Phantom a = new Phantom(512,512);
		a.show();

	}

}
