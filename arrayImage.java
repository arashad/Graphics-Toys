package ImSB;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

public class arrayImage {
// This class is to hold a set of operations required to handle images casted into
// a one dimensional integer array
	
	private int[] aImage;		// This is the main variable
								// we contain the image inside it
	
	private int height, width; 	// the two dimensions of the image
	
	// Constructors area
	public arrayImage(int[] ain, int x, int y){
		// initialize object with the three basic variables
		aImage = ain;
		height = y;
		width  = x;
	}
	
	public arrayImage(BufferedImage bi){
		// Initialize the object using a buffered image
		PixelGrabber grabber;
		height = bi.getHeight();
		width = bi.getWidth();
		
		aImage = new int[width*height];
		grabber = new PixelGrabber(bi,0,0,width,height,aImage,0,width);
		
		try {
			grabber.grabPixels(0);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public arrayImage()	{
		// Blank object
		aImage = null;
		height = 0;
		width = 0;
		System.out.println("New arrayImage height:"+height+" width:"+width+" Array size:"+aImage.length);
	}
	
	public int[] getBox(int x1, int y1, int x2, int y2){
		// Returns a portion from the original array 
		// bound by points (x1,y1) and (x2,y2)

		int[] retArray=new int[(x2-x1+1)*(y2-y1+1)];

		// Done: define algorithm to return part of array
		int idx=y1*width; 	// Index for main array
		int idx2=0; 		// Index for return array
		int xCurser;		// Current position in x axis
		for (;idx<(y2*width)+x2;){
			//xCurser=0;
			idx+=x1;
			for (xCurser=x1;xCurser<(x2+1);xCurser++)
				retArray[idx2++]=aImage[idx++];
			idx+=width-x2;
		}

		return retArray;
	}
	
	public int[] getHorizontalLine(int y){
		// Returns an array with all pixels on a certain y coordinate
		int[] retArray = new int[width];
		int idx2=0;
		
		for (int idx=y*width;idx<(y+1)*width;idx++) 
			retArray[idx2++]=aImage[idx];
		
		return retArray;
	}
	
	public void setHLine(int y,int[] Line){
		// Sets the value of a horizontal line as input Line at a certain y coordinate
		int idx2=0;
		for (int idx=y*width;idx<(y+1)*width;idx++)
			aImage[idx]=Line[idx2++];
	}
	
	public int[] getVerticalLine(int x){
		// Returns an array with all pixels on a certain x coordinate
		int[] retArray = new int[height];
		int idx2=0;
		
		for (int idx=x;idx<(height - 1)* width + x;idx+=width)
			retArray[idx2++]=aImage[idx];
		
		return retArray;
	}
	
	public void setVLine(int x, int[] Line){
		// Sets the value of a vertical line as input Line at a certain x coordinate
		int idx2=0;
		for (int idx=x;idx<(height - 1)* width + x;idx+=width) 
			aImage[idx]=Line[idx2++];
	}
}
