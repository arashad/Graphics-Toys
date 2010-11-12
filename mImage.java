package ImSB;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
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
	protected static BufferedImage image;	// Image Data
	
	// Constructors Area
	public mImage(){
		// Constructor for mImage with no values
		height 	= 0;
		width 	= 0;
		image 	= null;
	}

	public mImage(BufferedImage im){
		// Constructor for mImage with Image input
		height 	= 0;
		width 	= 0;
		image 	= im;
	}
	
	public mImage(BufferedImage im,int x,int y){
		// Constructor for mImage with Image input
		height 	= y;
		width 	= x;
		image 	= im;
	}
	
	public mImage(String fileName) throws IOException{
		// Constructor for mImage with Image input
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
		}
}