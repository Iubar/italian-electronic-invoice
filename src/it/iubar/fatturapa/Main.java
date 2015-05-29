package it.iubar.fatturapa;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Main   
{  
	
	static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
	final static public String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	final static public String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	
	final static public String XML_PATH = "C:/Users/Matteo/workspace/italian-electronic-invoice/src/it/iubar/fatturapa/xml";
	static public String xmlFileName = null;
	static public String schemaFileName = null;
	public static String schemaUrl = "http://localhost/fatturapa_v1.1.xsd";
	
	public static void main (String[] args)  
	{  
//		System.out.println("This is currently running on the main thread, " +  
//				"the id is: " + Thread.currentThread().getId());

		
		xmlFileName = XML_PATH + "/examples/IT01234567890_11001.xml";		
		schemaFileName = XML_PATH + "/fatturapa_v1.1.xsd";
		 
		///////////////////////////////
		
		FatturaPaPrint paPrint = new FatturaPaPrint();  
		paPrint.print();
		///////////////////////////////
		
		FatturaPaValidator worker2 = new FatturaPaValidator();
		Thread thread2 = new Thread(worker2);
		thread2.start();
		wait(thread2);
		///////////////////////////////
		
		FatturaPaParser worker3 = new FatturaPaParser();		
		Thread thread3 = new Thread(worker3);
		thread3.start();
		wait(thread3);
		///////////////////////////////
		
		DomEcho2 d = null;
		try {
			d = DomEcho2.factory();
		    d.echo();
		    d.printlnCommon();			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();	
		}
	    
	    ///////////////////////////////
	    
		try {
			FatturaPaValidator.saxParser();
			System.out.println("ok!");
		} catch (SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
		
	    ///////////////////////////////
 
		FatturaPaWrite w = new FatturaPaWrite();
		w.start();
	    ///////////////////////////////
		
		FatturaPaClient paClient = new FatturaPaClient();
		paClient.readData();		
	    ///////////////////////////////
	}

	private static void wait(Thread thread) {
		System.out.println("Sto elaborando...");
		int max = 60; // tempo massimo di attesa: 60 loop da mezzo secondo (500 ms), ovvero 30 secondi
		for (int i = 0; i < max; i++) {
			if(!thread.isAlive() || thread.isInterrupted()){
				break;
			}else{
				System.out.print("#");
				try {
					Thread.sleep(500); // attendo 1/2 secondo
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		}
		System.out.println("");
	}


} 