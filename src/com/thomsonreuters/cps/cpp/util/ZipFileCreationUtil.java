package com.thomsonreuters.cps.cpp.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.apache.tools.tar.TarOutputStream;


public class ZipFileCreationUtil {

	public static File createZIPFile(String type) {
		
		String file1 = "/Users/UX015454/LTC/sample.sh";
		String file2 = "/Users/UX015454/LTC/jenkins.PNG";
		List<String> srcFiles = Arrays.asList(file1, file2);
		FileOutputStream fos = null;
		ZipOutputStream zipOut = null;
		// Create a TarOutputStream
		TarOutputStream tarOut = null; 
		File outpuFile = null;
		if(type.equals("zip")) {
		try {
			fos = new FileOutputStream("/Users/UX015454/LTC/ltc.zip");

			zipOut = new ZipOutputStream(fos);
			for (String srcFile : srcFiles) {
				File fileToZip = new File(srcFile);
				FileInputStream fis = new FileInputStream(fileToZip);
				ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
				zipOut.putNextEntry(zipEntry);
				System.out.println("Files ziped");
				byte[] bytes = new byte[1024];
				int length;
				while ((length = fis.read(bytes)) >= 0) {
					zipOut.write(bytes, 0, length);
				}
				fis.close();
				ChunkedZippedOutputStream czos = new ChunkedZippedOutputStream("/Users/UX015454/LTC/", "NOVUS");
				czos.addEntry(zipEntry);
			}
			zipOut.close();
			fos.close();
			outpuFile = new File("/Users/UX015454/LTC/ltc.zip");
			System.out.println("length of zip file :"+outpuFile.length());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else if(type.equals("tar")){
			try {
				fos = new FileOutputStream("/Users/UX015454/LTC/ltc.tar");
				//TarArchive tar = new TarArchive(fos);
				tarOut = new TarOutputStream(fos);
				for(String srcFile : srcFiles){
					File fileToTar = new File(srcFile);
					FileInputStream fis = new FileInputStream(fileToTar);
					TarEntry tarEntry = new TarEntry(fileToTar.getName());
					tarOut.putNextEntry(tarEntry);
					BufferedInputStream origin = new BufferedInputStream(new FileInputStream( fileToTar ));

				      int count;
				      byte data[] = new byte[2048];
				      while((count = origin.read(data)) != -1) {
				    	  tarOut.write(data, 0, count);
				      }

				      tarOut.flush();
				      origin.close();
					/*System.out.println("Files tared");
					byte[] bytes = new byte[2048];
					int length;
					while ((length = fis.read(bytes)) >= 0) {
						tarOut.write(bytes, 0, length);
					}*/
					fis.close();
					tarOut.close();
					fos.close();
					outpuFile = new File("/Users/UX015454/LTC/ltc.tar");
					System.out.println("length of tar file :"+outpuFile.length());
				}
			

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
			
		}else {
			System.out.println("Invalid Input"); 
		}
		return outpuFile;
		
	}
	
	public static void splitZIPFile(File fileToSplit) {
		//File fileToSplit = createZIPFile();
		
	}
	
	
	
}
