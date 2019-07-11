package com.thomsonreuters.cps.cpp.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//import org.apache.log4j.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.thomsonreuters.cps.cpp.model.ImageMetadataBlock;
import com.thomsonreuters.cps.cpp.service.LTCService;
import com.thomsonreuters.cps.cpp.util.XMLCreationUtil;

public class LTCServiceImpl implements LTCService {

	// Logger logger = CPSLoggerUtil.getLogger(this.getClass());


	@SuppressWarnings("finally")
	@Override
	public StreamResult createXML() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		StreamResult console = null;
		StreamResult file = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy h:mm:ss a");
			Date date = new Date(System.currentTimeMillis());
			System.out.println(formatter.format(date));

			String dataid = "$PROJECT-" + formatter.format(date);
			String gUid = "";
			String PROJECT = "novus";
			int count = 2;

			// Set POJO class parameters
			ImageMetadataBlock imb = new ImageMetadataBlock();
			imb.setgUid("");
			imb.setFormat("application/pdf");
			imb.setHeight("480");
			imb.setWidth("640");
			imb.setUnits("px");
			imb.setBytes("");
			imb.setDpi("400");

			XMLCreationUtil xmlCreationUtil = new XMLCreationUtil();
			Node ltc = xmlCreationUtil.getLtcXml(doc, dataid, gUid, imb);

			// for output to file, console
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// for pretty print
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);

			// write to console or file
			console = new StreamResult(System.out);
			/*
			 * Logger logger = CPSLoggerUtil.getLogger(this.getClass());
			 * logger.info("Writing to file at /Users/UX015454/novus.xml");
			 */
			file = new StreamResult(new File("/Users/UX015454" + File.separator + PROJECT + "." + count + ".xml"));

			// write data
			transformer.transform(source, console);
			transformer.transform(source, file);
			System.out.println("DONE");
			// logger.info("XML created");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return file;
		}

	}

	@Override
	public void setUpLTC(String FILE, String Vendor_id, String PROJECT, String COLLECTION, String primary_email,
			String param6, String param7) {
		String DSN1 = "wlimgp1";
		String DSN2 = "wlimgp2";
		Boolean SLEEP = false;
		String loginid = "wlimgr";
		String password = "wlimgr";
		String secondary_email = "";
		String temp = "";

		// Argument 6 can be either secondary email or delay - tracker8688 - Deepa

		if (param6.equals("delay"))
			SLEEP = true;
		else {
			secondary_email = param6;
			temp = param7;
			if (temp.equals("delay"))
				SLEEP = true;
		}

		// Create TEMP directory
		File tempDir = new File("/Users/UX015454/TEMPDIR");

		// if the directory does not exist, create it
		if (!tempDir.exists()) {
			System.out.println("creating directory: " + tempDir.getName());
			// logger.info("creating directory: " + tempDir.getName());

			boolean result = false;

			try {
				tempDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("DIR created");
				// logger.info("DIR created");
			}
		}
		try {

			File srcFile = new File("/Users/UX015454/LTC/Novus.2.xml");
			FileUtils.copyFileToDirectory(srcFile, tempDir);
		} catch (Exception e) {
		}

	}

	@Override
	public void processPDFImages() {
		

	}

	@Override
	public void processOtherImages() {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getNovusFtpInfo(String fileName, String collection) {

		// find the number of occurrence of a pattern inside a fil
		// $(grep -c "^$COLLECTION " $COMMON/novusloadinfo.txt)
		int count = 0;
		String USERPSD ="";
		String REMOTEDIR ="";
		String FTPSITELOC="";
		String sp[] = null;
		
		/*
		 * Scanner scanner = new Scanner(fileName); while (scanner.hasNextLine()) {
		 * String nextToken = scanner.next(); if
		 * (nextToken.equalsIgnoreCase(collection)) count++; }
		 */

		try (LineNumberReader r = new LineNumberReader(new FileReader(fileName))) {
			String line;
			while ((line = r.readLine()) != null) {
				for (String element : line.split(" ")) {
					if (element.equalsIgnoreCase(collection)) {
						count++;
						break;
					}
					
				}
				if(count > 0) {
					 sp=line.split(" ");
				}
			}
			
			/*USERPSD = sp[3];
			REMOTEDIR = sp[2];
			FTPSITELOC = sp[1];*/
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return sp;
	}

	@Override
	public void sendMessageToLtc() {
		FTPClient client = new FTPClient();
		FileInputStream fis = null;
		try {
			client.connect("c111gmneaasdx.int.westgroup.com");
			client.login("pcats", "!pcats");
			String filename = "/Users/UX015454/LTC/multiCompressed.zip"; // Create an  InputStream of the file to be upteded
            fis = new FileInputStream(filename);
			client.storeFile("/ep-data/pcats/multiCompressed.zip", fis); // Store file on server and logout
			client.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
