package com.thomsonreuters.cps.cpp.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


public class Demo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("/Users/UX015454/LTC/jenkins.PNG");
		
	    ImageInputStream iis = ImageIO.createImageInputStream(file);
	        Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

	        if (readers.hasNext()) {
	          //Get the first available ImageReader
	            ImageReader reader = readers.next();
	            reader.setInput(iis, true);

	            System.out.println("Format : " + reader.getFormatName());
	            System.out.println("Width : " + reader.getWidth(0) + " pixels");
	            System.out.println("Height : " + reader.getHeight(0) + " pixels");
	        }
		
	}

}