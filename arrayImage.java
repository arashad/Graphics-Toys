package ImSB;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;


public class arrayImage {
	//This class is to hold a set of operations required to handle images casted into a one dimensional integer array
	
	private int[] image;		// Image Array - Holds the Image
	private int height; 		//image height
	private int width; 			//image width

	// Constructor
	public arrayImage(int[] initialImage, int x, int y){
		// initialize object with the three basic variables
		image = initialImage;
		height = y;
		width  = x;
	}

	public arrayImage(BufferedImage bufferedImage){
		// Initialize the object using a buffered image
		PixelGrabber grabber;
		height = bufferedImage.getHeight();
		width  = bufferedImage.getWidth();
		image = new int[width*height];

		grabber = new PixelGrabber(bufferedImage,0,0,width,height,image,0,width);

		try {
			grabber.grabPixels(0);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		} catch (Exception exception){
			exception.printStackTrace();
		}
	}

	public arrayImage()	{
		// Blank object
		image = null;
		height = 0;
		width = 0;
		System.out.println("New arrayImage height:"+height+" width:"+width+" Array size:"+image.length);
	}

	public int[] getBox(int x1, int y1, int x2, int y2){
		// Returns a portion from the original array  bound by points (x1,y1) and (x2,y2)
		int[] subImage=new int[(x2-x1+1)*(y2-y1+1)]; //image dimensions		
		int imageArrayIndex=0; 	// Index for main array
		int subImageArrayIndex=0; 		// Index for return array
		
		System.out.println("firstArrayIndex= "+ imageArrayIndex);
		
		return getSubImage(x1, y1, x2, y2, subImage, subImageArrayIndex);
	}

	private int[] getSubImage(int x1, int y1, int x2, int y2, int[] subImage, int subImageArrayIndex) {
		int imageArrayIndex;
		int xCurser;
		for (imageArrayIndex=y1*width+x1;imageArrayIndex<y2*width;){
			for(xCurser=x1;xCurser<x2+1;xCurser++){
				subImage[subImageArrayIndex++]=image[imageArrayIndex++];
			}
			imageArrayIndex+=width-(x2-x1);
		}
		return subImage;
	}

	public int[] getHorizontalLine(int y){
		// Returns an array with all pixels on a certain y coordinate
		int[] horizonalLine = new int[width];
		int horizontalLineIndex=0;

		for (int idx=y*width;idx<(y+1)*width;idx++) {	
			horizonalLine[horizontalLineIndex++]=image[idx];
		}
		return horizonalLine;
	}

	public void setHLine(int y,int[] Line){
		// Sets the value of a horizontal line as input Line at a certain y coordinate
		int horizontalLineIndex=0;
		for (int imageIndex=y*width;imageIndex<(y+1)*width;imageIndex++){
			image[imageIndex]=Line[horizontalLineIndex++];
		}
		System.out.println("Exit set H Line");
	}

	public int[] getVerticalLine(int x){
		// Returns an array with all pixels on a certain x coordinate
		int[] virticalLine = new int[height];
		int virticalLineIndex=0;

		for (int imageIndex=x;imageIndex<(height - 1)* width + x;imageIndex+=width){
			virticalLine[virticalLineIndex++]=image[imageIndex];
		}
		return virticalLine;
	}

	public void setVLine(int x, int[] Line){
		// Sets the value of a vertical line as input Line at a certain x coordinate
		int virticalLineIndex=0;
		for (int imageIndex=x;imageIndex<(height - 1)* width + x;imageIndex+=width) {
			image[imageIndex]=Line[virticalLineIndex++];
		}
	}
}
