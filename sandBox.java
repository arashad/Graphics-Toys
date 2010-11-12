package ImSB;

import java.io.IOException;

public class sandBox{
	public static void main(String[] args){
		mImage mIm=null;
		try {
			mIm = new mImage("/home/ahmad/Pictures/ZRV.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		int[] pixels = new int[mIm.getLength()];
//		pixels=mIm.getImagePixels();
		System.out.println("Before green extract");
		mImage greenImage = new mImage(mIm.getChannel(mImage.GREEN));
		
		greenImage.displayImage();
	}
}