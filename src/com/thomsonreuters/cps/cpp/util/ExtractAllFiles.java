package com.thomsonreuters.cps.cpp.util;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ExtractAllFiles {
	
	public ExtractAllFiles() {
		
		try {
			// Initiate ZipFile object with the path/name of the zip file.
			ZipFile zipFile = new ZipFile("c:\\ZipTest\\CreateSplitZipFile.zip");
			
			// Extracts all files to the path specified
			zipFile.extractAll("c:\\ZipTest");
			
		} catch (ZipException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ExtractAllFiles();
	}

}