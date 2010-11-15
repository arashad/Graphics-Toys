package ImSB;

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
	
	public arrayImage()	{
		// Blank object
		aImage = null;
		height = 0;
		width = 0;
	}
	
	// Methods area
	public int[] getBox(int x1, int y1, int x2, int y2){
		// Returns a portion from the original array 
		// bound by points (x1,y1) and (x2,y2)
		int[] retArray=null;
		// TODO define algorithm to return part of array
		
		return retArray;
	}
}
