package ImSB;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class mImage {
	// This class is to hold graphics related methods and properties
	
	protected int height; 	// Image Height
	protected int width; 	// Image Width
	protected BufferedImage image;	// Image Data
	
	// the four channels of ARGB color space
	public static final int ALPHA = 24;
	public static final int RED   = 16;
	public static final int GREEN =  8;
	public static final int BLUE  =  0;
	
	// Constructors Area
	public mImage(){
		// Constructor for mImage with no values
		System.out.println("inside "+this.toString()+" Constructor");
		height 	= 0;
		width 	= 0;
		image 	= null;
	}

	public mImage(BufferedImage im){
		// Constructor for mImage with Image input
		System.out.println("inside "+this.toString()+" Constructor");
		height 	= im.getHeight();
		width 	= im.getWidth();
		image 	= im.getSubimage(0, 0, width, height);
	}
	
	public mImage(BufferedImage im,int x,int y){
		// Constructor for mImage with Image input
		System.out.println("inside "+this.toString()+" Constructor");
		height 	= y;
		width 	= x;
		image 	= im.getSubimage(0, 0, width, height);//image 	= im;
	}
	
	public mImage(String fileName) throws IOException{
		// Constructor for mImage with Image input
		System.out.println("inside "+this.toString()+" Constructor");
		loadImage(fileName);
		
		height 	= image.getHeight();
		width 	= image.getWidth();
		
	}
	
	public mImage(int[] arr, int x, int y){
		System.out.println("D01");
		image = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
		image.setRGB(0, 0, x, y, arr, 0, x);
		System.out.println("D02");
		height = y;
		System.out.println("D03");
		width = x;
		System.out.println("D04");
	}
	
	public mImage(mImage in){
		height = in.height;
		width = in.width;
		
		image = in.image.getSubimage(0, 0, width, height);//image;
		
	}
	// Methods Area
	public BufferedImage getBufferedImage(){
		return image;
	}
	public void displayImage(String caption){
		// Display the image with a caption
		JFrame frame = new JFrame(); 
		JLabel label = new JLabel(new ImageIcon(image)); 
		label.setText(caption);
		frame.getContentPane().add(label, BorderLayout.SOUTH); 
		frame.pack(); 
		frame.setVisible(true); 
	}
	
	public void displayImage(){
		// Display the image with no caption
		JFrame frame = new JFrame(); 
		JLabel label = new JLabel(new ImageIcon(image)); 
		label.setText("Default");
		frame.getContentPane().add(label, BorderLayout.SOUTH); 
		frame.pack(); 
		frame.setVisible(true); 
	}
	
	public void loadImage(String fileName) throws IOException{
		// Loads image to the object
		File file = new File(fileName); // Convert filename into a File object
		image = ImageIO.read(file); // Load image into Image object
		
		// Make sure image dimensions are updated
		height = image.getHeight();
		width = image.getWidth();
		}
	
	public void loadImage(BufferedImage bi){
		// Loads buffered image to the object
		image = bi;
		height = image.getHeight();
		width = image.getWidth();
	}
	
	public int[] getImagePixels()
	{
		// Return the int[] of this image
		
	    PixelGrabber grabber;
	    int[] pixels = new int[image.getWidth() * image.getHeight()];
	    try
	    {
	        grabber = new PixelGrabber(image, 0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
	        grabber.grabPixels(0);

	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    return pixels;
	}
	
	public int getLength(){
		// Returns number of pixels for this image
		return height * width;
	}
	
	public int getHeight(){
		// Returns image height
		return height;
	}
	
	public int getWidth(){
		// Returns image width
		return width;
	}
	
	public BufferedImage getChannel(int channel){
		// Extracts a channel from an mImage
		System.out.println("inside Get Channel");
		int[] pixel = new int[height * width];
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		// lay ARGB pixels into int[] 
		System.out.println("Before getImagePixels inside Get Channel");
		pixel = getImagePixels();
		
		// Filter all channels except desired
		System.out.println("Before Channel Extraction Loop inside Get Channel");
		for (int idx=0;idx<pixel.length;idx++)
		{
			pixel[idx]= pixel[idx]&((0xff<<channel)|(0xff<<ALPHA));
			//System.out.println(pixel[idx]);
		}
		
		// Convert int[] back into a buffered image
		System.out.println("Before Converting int[] to BufferedImage");
		bi.setRGB(0, 0, width, height, pixel, 0, width);
		System.out.println("After Converting int[] to BufferedImage");
		return bi;
	}
	

	
	public void superImpose(mImage a) throws Error {
		// superimpose image with another image passed
		System.out.println("B01");
		a.displayImage("Image a in superImpose");
		System.out.println("B02");
		if (	(a.getLength() != height * width)	||
				(a.getHeight() != height)	||
				(a.getWidth()  != width)) 
		{
			System.out.println("BE01");
			Error err = new Error("mImages a and b should be identical");
			throw err;			
		}
		System.out.println("B05");
		int[] pa = new int[a.getLength()]; // blank Array to hold mImage a 
		int[] pb = new int[this.getLength()]; // blank Array to hold mImage b
		int[] po = new int[a.getLength()]; // blank Array to hold output mImage
		System.out.println("B10");
		// Feed arrays with a and b values
		pa = a.getImagePixels();
		pb = this.getImagePixels();
		System.out.println("B13");
		// Make operation
		for (int idx=0;idx<pa.length;idx++)
		{
			po[idx]=pa[idx]|pb[idx];
//			System.out.print("Superimpose : Red ="+((po[idx]>>RED)&0xff));
//			System.out.print(" Green ="+((po[idx]>>GREEN)&0xff));
//			System.out.println(" Blue =" +((po[idx]>>BLUE)&0xff));
		}
		System.out.println("B20");
		image.setRGB(0, 0, width, height, po, 0, width); 
		System.out.println("B21");
	}
	
	public void setPixels(mImage in){
		// sets the BufferedImage inside the mImage with the int[] values 
		System.out.println("C01");
		int x=in.getWidth();
		int y=in.getHeight();
		System.out.println("C05");
		int[] arr  = new int[x*y];
		int[] arq  = new int[x*y];
		System.out.println("C10");
		arq = in.getImagePixels();
		System.out.println("C11");
		for (int idx=0;idx<x*y;idx++) arr[idx] = arq[idx];
		System.out.println("C15");
		image = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
		image.setRGB(0, 0, x, y, arr, 0, x);
		System.out.println("C20");
		width = x;
		height = y;
		System.out.println("C25");
	}
	
	public void resize(double d){
		// to resize the image by factor passed
		// 
		// TODO handle factor (d) > 1
		PixelGrabber grabber;
		
		int xo=0;
		int yo=0;
		int step =  (int) (1/d);
		int alphaSum,redSum,greenSum,blueSum;
		
		// a cleaner way of representing new dimensions 
		int newX=(int) (width * d);
		int newY=(int) (height * d);
		
		int[] newImage = new int[(int) (height * width * d * d*5)];
		int[] buffer = new int[step * step];
		
		int[] imageHolder = new int[width*height];
		// TODO collect the whole image into imageHolder 
		grabber = new PixelGrabber(image,0,0,width,height,imageHolder,0,width);
		try {
			grabber.grabPixels(0);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Now we have array extracted from the image
		// Done: take the whole image into a single int[] and do operations later
		arrayImage ai = new arrayImage(imageHolder,width,height);
		
		int nIidx=0;
		// fixed a problem in traversing the image by switching loops
		System.out.println("About to resize image");
		// TODO take the whole image into a single int[] and do operations later
		for (int y=0;y< newY;y+= step)
		{
			//System.out.println("Progress.."+(float)(100*(y/newY))+"\t%");
			for (int x=0;x< newX; x+= step)
			{

				alphaSum=0;
				redSum=0;
				greenSum=0;
				blueSum=0;
				
				xo = (int) (x/d);
				yo = (int) (y/d);
				buffer = null;
				buffer=ai.getBox(xo, yo, xo+step, yo+step);
				
				// DONE Get average of each channel from 1/factor x 1/factor squares 
				//      and save into an array with dimensions width * factor x height * factor
				for (int idx=0;idx<buffer.length;idx++)
				{
					//System.out.println("\t\t"+());
					// Get Sum of all channels
					alphaSum+=(buffer[idx]>>ALPHA	)&0xff;
					redSum	+=(buffer[idx]>>RED		)&0xff;
					greenSum+=(buffer[idx]>>GREEN	)&0xff;
					blueSum	+=(buffer[idx]>>BLUE	)&0xff;
				}
				
				// Average Channels
				alphaSum=alphaSum/buffer.length;
				redSum	=redSum/buffer.length;
				greenSum=greenSum/buffer.length;
				blueSum	=blueSum/buffer.length;
				
				newImage[nIidx++]=	(alphaSum<<ALPHA)+
									(redSum<<RED)+
									(greenSum<<GREEN)+
									(blueSum<<BLUE);
				
			}
			
		}
		
		// Done: save new array to this image
		width = (int) ( width * d);
		height = (int) ( height * d);
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		image.setRGB(0, 0, width, height, newImage, 0, width);
	}
}