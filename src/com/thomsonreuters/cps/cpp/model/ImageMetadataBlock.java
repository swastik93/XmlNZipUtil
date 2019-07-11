package com.thomsonreuters.cps.cpp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ImageMetadataBlock {
	
	private String gUid, mdGuid,format,height, width,units, bytes, dpi;

	public String getgUid() {
		return gUid;
	}

	public void setgUid(String gUid) {
		this.gUid = gUid;
	}

	public String getMdGuid() {
		return mdGuid;
	}

	public void setMdGuid(String mdGuid) {
		this.mdGuid = mdGuid;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getBytes() {
		return bytes;
	}

	public void setBytes(String bytes) {
		this.bytes = bytes;
	}

	public String getDpi() {
		return dpi;
	}

	public void setDpi(String dpi) {
		this.dpi = dpi;
	}

	
	
	

}
