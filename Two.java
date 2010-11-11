package ImSB;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
//import java.awt.image.WritableRaster;
//import java.io.*;
//import java.io.BufferedInputStream;
import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Two {
	
	public static int[] getImagePixels(BufferedImage image)
	{
		// Return the int[] of an image
		
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
	
	public static BufferedImage getImageFromFile(String fileName) throws IOException{
				
		File file = new File(fileName); // Convert filename into a File object
		Image image = ImageIO.read(file); // Load image into Image object
		BufferedImage bIm= (BufferedImage) image; //bufferImage(image,BufferedImage.TYPE_INT_RGB);
		return bIm;
	}
	
	public static void dumpArray(int[] data, int x, int y){
		int idx = 0;
		
		for (int i=0;i<x;i++)
		{
			for (int j=0;j<y;j++)
			{
				System.out.print(data[idx]+" ");
				idx++;
			}
			System.out.println();
		}
	}
	
	public static void displayImage(Image dImage, String caption){
		JFrame frame = new JFrame(); 
		JLabel label = new JLabel(new ImageIcon(dImage)); 
		label.setText(caption);
		frame.getContentPane().add(label, BorderLayout.SOUTH); 
		frame.pack(); 
		frame.setVisible(true); 
	}
	//ssalem: added return type, for loop fix
	public static int[] getRed(int[] pixel){
		// Extract Red Component Only without  Green or Blue but with Alpha
		for (int idx=0;idx<pixel.length;idx++)
			{
				pixel[idx]=(((pixel[idx]>>24) & 0xff )<<24)+(((pixel[idx]>>16) & 0xff )<<16);
				//System.out.println(idx+" - "+pixel[idx]);
			}
		//System.out.println("After Loop");
		return pixel;
	}
	
	public static int[] getGreen(int[] pixel){
		// Extract Green Component Only without  Green or Blue but with Alpha
		for (int idx=0;idx<pixel.length;idx++)
			{
				pixel[idx]=(((pixel[idx]>>24) & 0xff )<<24)+(((pixel[idx]>>8) & 0xff )<<8);
	//			System.out.println(idx+" - "+pixel[idx]);
			}
		//System.out.println("After Loop");
		return pixel;
		}
	public static int[] getBlue(int[] pixel){
		// Extract Green Component Only without  Green or Blue but with Alpha
		for (int idx=0;idx<pixel.length;idx++)
			{
				pixel[idx]=(((pixel[idx]>>24) & 0xff )<<24)+(pixel[idx] & 0xff );
	//			System.out.println(idx+" - "+pixel[idx]);
			}
		//System.out.println("After Loop");
		return pixel;
	
	}
	
	
	public static int[] Hack(int[] pixel){
		// Extract Green Component Only without  Green or Blue but with Alpha
		int alpha=0;
		int red=0;
		int green=0;
		int blue=0;
		
		int threshold=0x73;
		int threshold2=0xf2;
		
		for (int idx=0;idx<pixel.length;idx++)
			{
			//pixel[idx]=pixel[idx]&0xfff8f8f8;
			// Feed ARGB
			alpha	=(((pixel[idx]>>24) & 0xff )<<24);
			red		=(((pixel[idx]>>16) & 0xff )<<16);
			green	=(((pixel[idx]>>8) & 0xff )<<8);
			blue	=(((pixel[idx]) & 0xff ));
			
			// Decide new values
			int flat =((red>>16)+(green>>8)+blue)/3;
			
			if ( ((flat) < threshold) && ((red >> 16) < threshold2) ) {
				red =  (flat << 16);//(red) >> 16;
				blue = flat>>3; //( blue) << 16;
				green = (flat << 8) ; //0xff<<8;
				alpha = ((0xff - flat*2)) << 24;
			}
//			if ((red>>16)	>threshold) red = red >> 8;
//			if ((green>>8)	>threshold) green = green << 8;
//			if (blue		>threshold) blue	=0xff;
			
			
			//alpha=(0xff-flat)<<24;
			// Aggregate Colors 
			pixel[idx]=alpha+red+green+blue;
			
			// System.out.println(idx+" - "+pixel[idx]);
			}
		//System.out.println("After Loop");
		return pixel;
	}
	public static void main(String[] args){
		
		BufferedImage mainImage = null;
		
		int x,y;
		
		// Try to load image into mainImage
		try {
			mainImage = getImageFromFile("/home/ahmad/Pictures/ZRV.jpg");
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		// Collect Image dimentions
		x = mainImage.getWidth();
		y = mainImage.getHeight();
		
		BufferedImage redImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
		
		// Resize pixel array to the size of mainImage
		int[] pixel 	= new int[x * y ];
		int[] redPixel 	= new int[x * y ];
		pixel = getImagePixels(mainImage);
		
		// To test the contents of the pixel array
		// dumpArray(pixel,x,y);
		
		// Extract Red Only from pixel
		redPixel = Hack(pixel);
		redImage.setRGB(0, 0, x, y, redPixel, 0, x);
		displayImage(redImage,"Hack");
		displayImage(mainImage,"Original");
		
		// Extract Green Only from pixel		
		// Attempt to feed the matrix to the same image
		//mainImage.setRGB(0, 0, x, y, redPixel, 0, x);	
	}

}
