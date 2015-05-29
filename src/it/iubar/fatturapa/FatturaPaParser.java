package it.iubar.fatturapa;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.time.StopWatch;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FatturaPaParser implements Runnable {

	public static void parser(Document doc) throws ParserConfigurationException, SAXException, IOException{

			
		NodeList nList2 = doc.getElementsByTagName("IdTrasmittente");
		Element eElement2 = (Element) nList2.item(0);
		String s2 = eElement2.getElementsByTagName("IdCodice").item(0).getTextContent();
		System.out.println(s2);
		
		NodeList nList3 = doc.getElementsByTagName("CedentePrestatore");
		Element eElement3 = (Element) nList3.item(0);
		String s3 = eElement3.getElementsByTagName("Denominazione").item(0).getTextContent();
		System.out.println(s3);
		
		
		
//		CedentePrestatore cedente = new CedentePrestatore();
//		cedente.setDenominazione(s3);
		
		// ......
		
		System.out.println("To do....");
		
	}

	@Override
	public void run() {
    	StopWatch clock = new StopWatch();
    	clock.start();
    	
    	System.out.println("");

    	//		  System.out.println("This is currently running on a separate thread, " +  
    	//		            "the id is: " + Thread.currentThread().getId());  
    	try {
    		FatturaPaParser.parser(FatturaPaValidator.document);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	clock.stop();
    	// double seconds = clock.getTime() / 1000.0;
    	System.out.println("");
    	// System.out.println( "It takes " + seconds + " seconds" );
    	System.out.println( "Done in " + clock.toString() + " (hh:mm:ss:mm)");
	}
	
 
	
}
