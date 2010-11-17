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
//<<<<<<< HEAD
	}
	
	public arrayImage()	{
		// Blank object
		aImage = null;
		height = 0;
		width = 0;
//	}
//=======
		System.out.println("New arrayImage height:"+height+" width:"+width+" Array size:"+aImage.length);
	}
	
//	public arrayImage()	{
//		// Blank object
//		aImage = null;
//		height = 0;
//		width = 0;
//	}
//>>>>>>> clean
	
	// Methods area
	public int[] getBox(int x1, int y1, int x2, int y2){
		// Returns a portion from the original array 
		// bound by points (x1,y1) and (x2,y2)
//<<<<<<< HEAD
		//int[] retArray=null;
		// TODO define algorithm to return part of array
		
//=======
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
//>>>>>>> clean
		return retArray;
	}
}
