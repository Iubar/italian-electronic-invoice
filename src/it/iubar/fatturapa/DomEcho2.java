package it.iubar.fatturapa;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.DOMEcho;
import org.apache.MyErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomEcho2 extends DOMEcho {

	static Document doc;
	DomEcho2(PrintWriter out) {
		super(out);
	}

	public static DomEcho2 factory () throws ParserConfigurationException, SAXException, IOException{

	    String filename = Main.xmlFileName;
	    boolean dtdValidate = false;
	    boolean xsdValidate = true;
	    String schemaSource = Main.schemaFileName;

	    boolean ignoreWhitespace = true;
	    boolean ignoreComments = true;
	    boolean putCDATAIntoText = true;
	    boolean createEntityRefs = true;

	    // Step 1: create a DocumentBuilderFactory and configure it
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	    // Set namespaceAware to true to get a DOM Level 2 tree with nodes
	    // containing namesapce information.  This is necessary because the
	    // default value from JAXP 1.0 was defined to be false.
	    dbf.setNamespaceAware(true);

	    // Set the validation mode to either: no validation, DTD
	    // validation, or XSD validation
	    dbf.setValidating(dtdValidate || xsdValidate);
	    if (xsdValidate) {
	        try {
	            dbf.setAttribute(Main.JAXP_SCHEMA_LANGUAGE, Main.W3C_XML_SCHEMA);
	        } catch (IllegalArgumentException x) {
	            // This can happen if the parser does not support JAXP 1.2
	            System.err.println(
	                "Error: JAXP DocumentBuilderFactory attribute not recognized: "
	                + Main.JAXP_SCHEMA_LANGUAGE);
	            System.err.println(
	                "Check to see if parser conforms to JAXP 1.2 spec.");
	            System.exit(1);
	        }
	    }

	    // Set the schema source, if any.  See the JAXP 1.2 maintenance
	    // update specification for more complex usages of this feature.
	    if (schemaSource != null) {
	        dbf.setAttribute(Main.JAXP_SCHEMA_SOURCE, new File(schemaSource));
	    }

	    // Optional: set various configuration options
	    dbf.setIgnoringComments(ignoreComments);
	    dbf.setIgnoringElementContentWhitespace(ignoreWhitespace);
	    dbf.setCoalescing(putCDATAIntoText);
	    // The opposite of creating entity ref nodes is expanding them inline
	    dbf.setExpandEntityReferences(!createEntityRefs);

	    // Step 2: create a DocumentBuilder that satisfies the constraints
	    // specified by the DocumentBuilderFactory
	    DocumentBuilder db = dbf.newDocumentBuilder();

	    // Set an ErrorHandler before parsing
	    OutputStreamWriter errorWriter = new OutputStreamWriter(System.err, DOMEcho.outputEncoding);
	    db.setErrorHandler( new MyErrorHandler(new PrintWriter(errorWriter, true)));

	    // Step 3: parse the input file
	    doc = db.parse(new File(filename));

	    // Print out the DOM tree
	    OutputStreamWriter outWriter = new OutputStreamWriter(System.out, outputEncoding);
	    
	    DomEcho2 domEcho = new DomEcho2(new PrintWriter(outWriter, true));

	    return domEcho;
	}
	
	public void echo(){
	     // Node root = doc.getDocumentElement(); oppure...
		String nodeName = "IdTrasmittente";
		System.out.println("echo(" + nodeName + ")...");
		 NodeList nodes = doc.getElementsByTagName(nodeName);
		Node node = nodes.item(0);
		super.echo(node);
	}
	public void printlnCommon(){
	     // Node root = doc.getDocumentElement(); oppure...
		String nodeName = "IdTrasmittente";
		System.out.println("printlnCommon(" + nodeName + ")...");		
		 NodeList nodes = doc.getElementsByTagName(nodeName);
		Node node = nodes.item(0);
		super.printlnCommon(node);
	}
	
	
}
