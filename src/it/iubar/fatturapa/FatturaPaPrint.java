package it.iubar.fatturapa;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.lang3.time.StopWatch;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.File;
import java.io.IOException;

public class FatturaPaPrint {

	

	private static int livello = -1;

	private static void readXml() throws ParserConfigurationException, SAXException, IOException {
		File fXmlFile = new File(Main.xmlFileName);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		dbFactory.setNamespaceAware(true);
		
		dbFactory.setIgnoringElementContentWhitespace(true);	// ATTENZIONE: funziona solo se uso setSchema(xs) e setValidating(true) 
		dbFactory.setValidating(false);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		dBuilder.setErrorHandler(new SimpleErrorHandler());	
		
		
		Document doc = dBuilder.parse(fXmlFile);


//		doc.getDocumentElement().normalize();

		System.out.println("");
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		System.out.println("");
		
		NodeList nList = doc.getElementsByTagName("IdTrasmittente");

		FatturaPaPrint.print(nList);					
				
	}


    private static void print(NodeList nList) {
    	FatturaPaPrint.livello++;
    	for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			
			String tabs = "";
			for (int i = 0; i < FatturaPaPrint.livello; i++) {
				tabs = tabs + "\t";				
			}
			
			System.out.println(tabs + "Nodo " + (temp + 1)  + "/" + nList.getLength());
			String s = FatturaPaPrint.toString(nNode);			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				System.out.print("\t" + s);
				NodeList nList2 = nNode.getChildNodes();
				// System.out.print("\t\t\t(figli: " + nList2.getLength() + ")");
				System.out.println("");
				print(nList2);
			}else{
				if(nNode.getNodeValue().trim().equals("") && nNode.getNodeName().equals("#text")){
					// skip white space between tags					
				}else{
					System.out.println("\t\t" + s);
				}
			}
		}
		FatturaPaPrint.livello--;
	}

    static private String toString(Node node) {
        String s = "";
        if (node != null) {
        	short type = node.getNodeType();
        	if(type>0){
            switch (type) {

            case Node.TEXT_NODE:
            	
            	if(node.getNodeValue().trim().equals("")){
            		s =  node.getNodeName().trim() + "/" + type + ": " +  node.getNodeValue().trim();
            	}else{
            		s =  node.getNodeValue().trim();
            	}

                break;

            case Node.COMMENT_NODE:
                s = "<!--" + node.getNodeValue().trim() + "-->";

                break;

            case Node.CDATA_SECTION_NODE:
                s = "<![CDATA[" + node.getNodeValue().trim() + "]]>";

                break;

            case Node.PROCESSING_INSTRUCTION_NODE:
                s = "<?" + node.getNodeName() + " " + node.getNodeValue() + "?>";

                break;

            case Node.ELEMENT_NODE:
                s = "<" + node.getNodeName();

                if (node.hasAttributes()) {
                    NamedNodeMap attributes = node.getAttributes();

                    for (int i = 0; i < attributes.getLength(); i++) {
                        Node attr = attributes.item(i);
                        s += (" " + attr.getNodeName() + "=\"" +
                        attr.getNodeValue() + "\"");
                    }
                }

                s += ">";
                break;

            default:

                String v = node.getNodeValue();

                if (v != null) {
                    v = v.trim();
                }

                System.out.println("Type not suppoerted: " +
                    typeName(node.getNodeType()) + "       name=\"" +
                    node.getNodeName() + "\"  value=\"" + v + "\"");
         
            }
    
        }
        }
        return s;
    }
    
    static private String typeName(int typeNum) {
        switch (typeNum) {
        case Node.CDATA_SECTION_NODE:
            return "CDATA_SECTION_NODE";

        case Node.COMMENT_NODE:
            return "COMMENT_NODE";

        case Node.ELEMENT_NODE:
            return "ELEMENT_NODE";

        case Node.PROCESSING_INSTRUCTION_NODE:
            return "PROCESSING_INSTRUCTION_NODE";

        case Node.TEXT_NODE:
            return "TEXT_NODE";

        default:
            return "not considered";
        }
    }


    public void print()   
    {  
  
    	try {
    		FatturaPaPrint.readXml();
    	} catch (ParserConfigurationException | SAXException | IOException e) {
    		e.printStackTrace();
    	}
 

    }
	


}


