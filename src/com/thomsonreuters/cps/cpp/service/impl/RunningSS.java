package com.thomsonreuters.cps.cpp.service.impl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;

import com.thomsonreuters.cps.cpp.service.LTCService;
import com.thomsonreuters.cps.cpp.util.ZipFileCreationUtil;
import com.thomsonreuters.cps.cpp.service.impl.TAR;

public class RunningSS {
 public static void main(String[] args) throws FileNotFoundException {

	 try {
		 LTCService ltcService =  new LTCServiceImpl();
		 //ltcService.createXML();
		 //ltcService.sendMessageToLtc();
		 //File fileName = new File("/Users/UX015454/test.txt");
		 String fileName = "/Users/UX015454/LTC/ltc.tar";
		 String collection= "wlftpprod.ha.westgroup.com";
		 
		 String file1 = "/Users/UX015454/LTC/sample.sh";
		 File f1 = new File(file1);
		 
		String file2 = "/Users/UX015454/LTC/jenkins.PNG";
		File f2 = new File(file2);
		 int count = 0;
		 
		 //ZipFileCreationUtil.createZIPFile("tar");
		 
		 File srcFiles[] = new File[] {f1, f2};
		 
		 TAR.compress(fileName,srcFiles);
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 
 }
}