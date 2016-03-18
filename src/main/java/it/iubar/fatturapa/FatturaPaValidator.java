package it.iubar.fatturapa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.lang3.time.StopWatch;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


public class FatturaPaValidator implements Runnable {

	public static Document document;

	private static final int option = 4; 	
	// 1 == ERROR (Implementazione errata perchè mischia metodi per la validazione "interna" ed "esterna")
	// 2 == OK (xsd da file)
	// 3 == OK (xsd online)
	// 4 == OK (uso Validator con xsd da file)
	// 5 == OK (uso Validator xsd online)
	
	static private boolean domValidator(){

		boolean b = false;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();				
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true); // ha effetto solo se uso setValidating(true)

		try {

			if(option==1){
				dbf.setValidating(true); 	// Validate using internal Schema		
				dbf.setNamespaceAware(true);			
				dbf.setFeature("http://apache.org/xml/features/validation/schema", true); // elimina l'errore "Documento non valido: nessuna grammatica trovata"				
				SchemaFactory xsf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				Schema xs = xsf.newSchema(new File(Main.schemaFileName));
				dbf.setSchema(xs); 			// Validate using external Schema
				System.out.println("" + dbf.getSchema());
			}else if(option==2){	
				dbf.setValidating(true);	// Validate using internal Schema
				dbf.setAttribute(Main.JAXP_SCHEMA_LANGUAGE, Main.W3C_XML_SCHEMA);
				dbf.setAttribute(Main.JAXP_SCHEMA_SOURCE, new File(Main.schemaFileName));

			}else if(option==3){
				dbf.setValidating(true); // Validate using internal Schema
				dbf.setAttribute(Main.JAXP_SCHEMA_LANGUAGE, Main.W3C_XML_SCHEMA);
				dbf.setAttribute(Main.JAXP_SCHEMA_SOURCE, Main.schemaUrl);
			}else if((option==4) || (option == 5)){
				dbf.setValidating(false);	// Validate using external Schema
			}

			DocumentBuilder builder = dbf.newDocumentBuilder();
			builder.setErrorHandler(new SimpleErrorHandler());
			FatturaPaValidator.document = builder.parse(new File(Main.xmlFileName));
			
			Schema schema = null;
			if((option == 4) || (option == 5)){

				// create a SchemaFactory capable of understanding WXS schemas
				SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

				// load a WXS schema, represented by a Schema instance
				if(option == 4){
					Source schemaFile = new StreamSource(new File(Main.schemaFileName));
					schema = factory.newSchema(schemaFile);
				}if(option == 5){
					Source schemaFile = new StreamSource(Main.schemaFileName);
					schema = factory.newSchema(schemaFile);
					System.out.println("Schema: " + schema);
				}

				// create a Validator instance, which can be used to validate an instance document
				Validator validator = schema.newValidator();

				// validate the DOM tree
				validator.validate(new DOMSource(document));

			}

			b = true;

		} catch (SAXException e) {
			// instance document is invalid!
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	public static void saxParser() throws SAXException, ParserConfigurationException, IOException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);
 
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

		factory.setSchema(schemaFactory.newSchema(new Source[] {new StreamSource(Main.schemaFileName)}));

		SAXParser parser = factory.newSAXParser();
		
		XMLReader reader = parser.getXMLReader();
		reader.setErrorHandler(new SimpleErrorHandler());
		reader.parse(new InputSource(Main.xmlFileName));		
		
	}
	
	static public void domTreeParser()  {

		boolean result = domValidator();
		System.out.println("");
		System.out.println("it is validate result: " + result);

		if (result == false) {
			System.out.println("XML isn't valid");
		}else{
			System.out.println("XML is  valid");
		}
	}


	@Override
	public void run() {

		StopWatch clock = new StopWatch();
		clock.start();

		//		  System.out.println("This is currently running on a separate thread, " +  
		//		            "the id is: " + Thread.currentThread().getId());  
		try {
			FatturaPaValidator.domTreeParser();
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
