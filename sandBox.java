package ImSB;

import java.io.IOException;

public class sandBox{
	public static void main(String[] args){
		mImage mIm=null;
		try {
			mIm = new mImage("/home/ahmad/Pictures/ABRV2.jpg");
		} catch (IOException e) {
			// nothing to do Auto-generated catch block
			e.printStackTrace();
		}
		
//		mImage greenImage = null;
//		mImage redImage = null;
//		mImage compositeImage = null;
//		
//		//greenImage.setPixels(mIm);
//		System.out.println("A5");
//		greenImage = new mImage(mIm.getChannel(mImage.GREEN));
//		redImage = new mImage(mIm.getChannel(mImage.RED));
//		System.out.println("A10");
//		//compositeImage.setPixels(greenImage);
//		compositeImage = new mImage(greenImage.getImagePixels(),greenImage.getWidth(),greenImage.getHeight());
//		System.out.println("A11");
//		compositeImage.superImpose(redImage);
//		System.out.println("A12");
//		greenImage.displayImage("Green");
//		redImage.displayImage("Red");
		mIm.displayImage("Original");
//		compositeImage.displayImage("Composite");
		mIm.resize(0.75);
		mIm.displayImage("Resized");
	}
}