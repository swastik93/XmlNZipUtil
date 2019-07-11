package com.thomsonreuters.cps.cpp.service;

import javax.xml.transform.stream.StreamResult;

public interface LTCService {

	/**
	 * Create XML for LTC
	 * 
	 * @return XML file
	 */
	public StreamResult createXML();
	
	public void setUpLTC(String FILE, String Vendor_id,String PROJECT,String COLLECTION, String primary_email, String param6,  String param7);
	
	public void processPDFImages();
	
	public void processOtherImages();
	
	public String[] getNovusFtpInfo(String fileName, String collection);
	
	public void sendMessageToLtc();
	
	
	
}
