package ImSB;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.sql.Time;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class mImage {
	// This class is to hold graphics related methods and properties
	
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
		height 	= 0;
		width 	= 0;
		image 	= im;
	}
	
	public mImage(BufferedImage im,int x,int y){
		// Constructor for mImage with Image input
		System.out.println("inside "+this.toString()+" Constructor");
		height 	= y;
		width 	= x;
		image 	= im;
	}
	
	public mImage(String fileName) throws IOException{
		// Constructor for mImage with Image input
		System.out.println("inside "+this.toString()+" Constructor");
		this.loadImage(fileName);
		
		height 	= image.getHeight();
		width 	= image.getWidth();
		
	}
	// Methods Area
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
	
	public BufferedImage getChannel(int channel){
		// Extracts a channel from an mImage
		System.out.println("inside "+this.toString()+" Get Channel");
		int[] pixel = new int[height * width];
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		// lay ARGB pixels into int[] 
		System.out.println("Before getImagePixels inside Get Channel");
		pixel = getImagePixels();
		
		// Filter all channels except desired
		System.out.println("Before Channel Extraction Loop inside Get Channel");
		for (int idx=0;idx<pixel.length;idx++)
		{
			pixel[idx]= pixel[idx]&(0xff<<channel);
			//System.out.println(pixel[idx]);
		}
		
		// Convert int[] back into a buffered image
		System.out.println("Before Converting int[] to BufferedImage");
		bi.setRGB(0, 0, width, height, pixel, 0, width);
		System.out.println("After Converting int[] to BufferedImage");
		return bi;
	}
}