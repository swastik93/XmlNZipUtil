package com.thomsonreuters.cps.cpp.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.thomsonreuters.cps.cpp.model.ImageMetadataBlock;

public class XMLCreationUtil implements XMLElementConstants{
	
	public static Node getLtcXml(Document doc, String dataid, String gUid, ImageMetadataBlock imb) {
        //create root element
		Element root = doc.createElement(NLOAD);

        //set dataid attribute
        root.setAttribute("dataid", dataid);
        doc.appendChild(root);

        //create n-document element sub element of n-load add to parent node n-load
        Element mainChild = doc.createElement(NDOCUMENT);
        mainChild.setAttribute("guid", gUid);
        root.appendChild(mainChild);
      //create n-metadata element sub element of n-document and add to parent node n-document
        Element childR0 = doc.createElement(NMETADATA);
        mainChild.appendChild(childR0);
        
      //create image.metadata.block element sub element of n-metadata and add to parent node n-metadata
        Element subChild0 = doc.createElement(IMAGE_METADATA_BLOCK);
        childR0.appendChild(subChild0);
        
        //Create object of ImageMetadaBlock 
        //imb = new ImageMetadataBlock();
        
        //create md.blobref.guid element
        subChild0.appendChild(getEmpLtcNodeElements(doc, subChild0, MD_BLOBREF_GUID, imb.getgUid()));

        //create md.image.format element
        subChild0.appendChild(getEmpLtcNodeElements(doc, subChild0, MD_IMAGE_FORMAT, imb.getFormat()));

        //create gender element
        subChild0.appendChild(getEmpLtcNodeElements(doc, subChild0, MD_IMAGE_HEIGHT, imb.getHeight()));
        
        //create md.image.width element
        subChild0.appendChild(getEmpLtcNodeElements(doc, subChild0, MD_IMAGE_WIDTH, imb.getWidth()));
        
      //create md.image.units element
        subChild0.appendChild(getEmpLtcNodeElements(doc, subChild0, MD_IMAGE_UNITS, imb.getUnits()));
        
      //create md.image.bytes element
        subChild0.appendChild(getEmpLtcNodeElements(doc, subChild0, MD_IMAGE_BYTES, imb.getBytes()));
        
      //create md.image.dpi element
        subChild0.appendChild(getEmpLtcNodeElements(doc, subChild0, MD_IMAGE_DPI, imb.getDpi()));
        
      //create n-metadata element sub element of n-document and add to parent node n-document
        Element childR1 = doc.createElement("n-blobref");
        childR1.setAttribute("filename", "");
        childR1.setAttribute("type", "application/pdf");
        mainChild.appendChild(childR1);
        

        return root;
    };


    //utility method to create text node
     @SuppressWarnings("unused")
	private static Node getEmpLtcNodeElements(Document document, Element element, String name, String value) {
        Element node = document.createElement(name);
        node.appendChild(document.createTextNode(value));
        return node;
    }

}
