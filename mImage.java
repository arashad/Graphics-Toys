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

import com.sun.org.apache.xalan.internal.xsltc.runtime.ErrorMessages_ja;

public class mImage {
	// This class is to hold graphics related methods and properties
	// TODO solve the problem related to copying object by reference
	protected static int height; 	// Image Height
	protected static int width; 	// Image Width
	protected static BufferedImage image;	// Image Data
	
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
		this.loadImage(fileName);
		
		height 	= image.getHeight();
		width 	= image.getWidth();
		
	}
	
	public mImage(int[] arr, int x, int y){
		image.setRGB(0, 0, x, y, arr, 0, x);
		height = y;
		width = x;
	}
	
	public mImage(mImage in){
		height = in.height;
		width = in.width;
		
		image = in.image.getSubimage(0, 0, width, height);//image;
		
	}
	// Methods Area
	public static BufferedImage getBufferedImage(){
		return image;
	}
	public static void displayImage(String caption){
		// Display the image with a caption
		JFrame frame = new JFrame(); 
		JLabel label = new JLabel(new ImageIcon(image)); 
		label.setText(caption);
		frame.getContentPane().add(label, BorderLayout.SOUTH); 
		frame.pack(); 
		frame.setVisible(true); 
	}
	
	public static void displayImage(){
		// Display the image with no caption
		JFrame frame = new JFrame(); 
		JLabel label = new JLabel(new ImageIcon(image)); 
		label.setText("Default");
		frame.getContentPane().add(label, BorderLayout.SOUTH); 
		frame.pack(); 
		frame.setVisible(true); 
	}
	
	public static void loadImage(String fileName) throws IOException{
		// Loads image to the object
		File file = new File(fileName); // Convert filename into a File object
		image = ImageIO.read(file); // Load image into Image object
		
		// Make sure image dimensions are updated
		height = image.getHeight();
		width = image.getWidth();
		}
	
	public static void loadImage(BufferedImage bi){
		// Loads buffered image to the object
		image = bi;
		height = image.getHeight();
		width = image.getWidth();
	}
	public static int[] getImagePixels()
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
	
	public static int getLength(){
		// Returns number of pixels for this image
		return height * width;
	}
	
	public static int getHeight(){
		// Returns image height
		return height;
	}
	
	public static int getWidth(){
		// Returns image width
		return width;
	}
	
	public static BufferedImage getChannel(int channel){
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
	
	public static mImage superImpose(mImage a, mImage b) throws Error {
		// returns the superimposed image from mImages a and b, assuming both are the same size
		// otherwise return an error
		a.displayImage("Image a in superImpose");
		b.displayImage("Image b in superImpose");
		if (	(a.getLength() != b.getLength())	||
				(a.getHeight() != b.getHeight())	||
				(a.getWidth()  != b.getWidth())) 
		{
			Error err = new Error("mImages a and b should be identical");
			throw err;			
		}
		int[] pa = new int[a.getLength()]; // blank Array to hold mImage a 
		int[] pb = new int[b.getLength()]; // blank Array to hold mImage b
		int[] po = new int[a.getLength()]; // blank Array to hold output mImage
		
		// Feed arrays with a and b values
		pa = a.getImagePixels();
		pb = b.getImagePixels();
		
		// Make operation
		for (int idx=0;idx<pa.length;idx++)
		{
			po[idx]=pa[idx]|pb[idx];
//			System.out.print("Superimpose : Red ="+((po[idx]>>RED)&0xff));
//			System.out.print(" Green ="+((po[idx]>>GREEN)&0xff));
//			System.out.println(" Blue =" +((po[idx]>>BLUE)&0xff));
		}
		
		mImage mo = new mImage(po,a.getWidth(),a.getHeight());
		return mo;
	}
}