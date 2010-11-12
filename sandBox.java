package ImSB;

import java.io.IOException;

public class sandBox{
	public static void main(String[] args){
		mImage mIm=null;
		try {
			mIm = new mImage("/home/ahmad/Pictures/ABRV2.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		int[] pixels = new int[mIm.getLength()];
//		pixels=mIm.getImagePixels();
		System.out.println("Before green extract");
		mImage greenImage = new mImage(mIm.getChannel(mImage.GREEN));
		//greenImage.displayImage("Green");
		greenImage.displayImage("Green");
		
		System.out.println("Before red extract");
		try {
			greenImage.displayImage("Green");
			mIm.loadImage("/home/ahmad/Pictures/ABRV2.jpg");
			greenImage.displayImage("Green");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mImage redImage = new mImage(mIm.getChannel(mImage.RED));
		//redImage.displayImage("Red");
		redImage.displayImage("Red");
		greenImage.displayImage("Green");
		mImage compositImage = new mImage(mImage.superImpose(greenImage,redImage).getBufferedImage());
		
		System.out.println("Before displaying composit");
		compositImage.displayImage("composite");
		
		//greenImage.displayImage("green");
		// redImage.displayImage("red");
	}
}