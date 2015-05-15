package it.iubar.fatturapa;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

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

public class FatturaPaRead implements Runnable {

	
	private static void readXml() throws ParserConfigurationException, SAXException, IOException {
		String path = "C:/Users/Matteo/workspace/italian-electronic-invoice/src/it/iubar/fatturapa/xml";
		String xmlFileName = path + "/IT01234567890_11001.xml";
		String schemaFileName = path + "/fatturapa_v1.1.xsd";
		File fXmlFile = new File(xmlFileName);
		SchemaFactory xsf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema xs = xsf.newSchema(new StreamSource(schemaFileName));
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		dbFactory.setIgnoringElementContentWhitespace(true); 
		dbFactory.setSchema(xs);		
		dbFactory.setValidating(true);            // and validating parser features
		
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		dBuilder.setErrorHandler(new ErrorHandler()
		{
		    @Override
		    public void fatalError(SAXParseException exception) throws SAXException
		    {
		        System.err.println("fatalError: " + exception);
		    }

		    @Override
		    public void error(SAXParseException exception) throws SAXException
		    {
		        System.err.println("error: " + exception);
		    }

		    @Override
		    public void warning(SAXParseException exception) throws SAXException
		    {
		        System.err.println("warning: " + exception);
		    }
		});
		
		
		Document doc = dBuilder.parse(fXmlFile);


//		doc.getDocumentElement().normalize();

		// System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("IdTrasmittente");

				FatturaPaRead.print(nList);
				
				if(false){
				
				NodeList nList2 = doc.getElementsByTagName("IdTrasmittente");
				Element eElement2 = (Element) nList2.item(0);
				String s2 = eElement2.getElementsByTagName("IdCodice").item(0).getTextContent();
				System.out.println(s2);
				
				NodeList nList3 = doc.getElementsByTagName("CedentePrestatore");
				Element eElement3 = (Element) nList3.item(0);
				String s3 = eElement3.getElementsByTagName("Denominazione").item(0).getTextContent();
				System.out.println(s3);
//				CedentePrestatore cedente = new CedentePrestatore();
//				cedente.setDenominazione(s3);
				
				}
				
				
	}


    private static void print(NodeList nList) {
    	System.out.println("\nprint()\n");
    	for (int temp = 0; temp < nList.getLength(); temp++) {
   		 
			Node nNode = nList.item(temp);
			
			System.out.println("nodo: " + (temp + 1)  + "/" + nList.getLength());
			
			String s = FatturaPaRead.toString(nNode);
			System.out.println(s);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								
				NodeList nList2 = nNode.getChildNodes();
				System.out.println("figli: " + nList2.getLength());
			 
				print(nList2);
				
			}
			
		}
	}

	static String toString(Node node) {
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

	static String toString2(Node node, String indent) {
        String s = "";
        if (node != null) {
            switch (node.getNodeType()) {
            case Node.TEXT_NODE:

                String txt = node.getNodeValue().trim();

                if (!txt.equals("")) {
                    s += (indent + txt + "\n");
                }

                break;

            case Node.COMMENT_NODE:
                s += (indent + "<!--" + node.getNodeValue().trim() + "-->\n");

                break;

            case Node.CDATA_SECTION_NODE:
                s += (indent + "<![CDATA[" + node.getNodeValue().trim() +
                "]]>\n");

                break;

            case Node.PROCESSING_INSTRUCTION_NODE:
                s += (indent + "<?" + node.getNodeName() + " " +
                node.getNodeValue() + "?>\n");

                break;

            case Node.ELEMENT_NODE:
                s += (indent + "<" + node.getNodeName());

                if (node.hasAttributes()) {
                    NamedNodeMap attributes = node.getAttributes();

                    for (int i = 0; i < attributes.getLength(); i++) {
                        Node attr = attributes.item(i);
                        s += (" " + attr.getNodeName() + "=\"" +
                        attr.getNodeValue() + "\"");
                    }
                }

                s += ">\n";
                s += toString2(node.getFirstChild(), indent + "  ");
                s += (indent + "</" + node.getNodeName() + ">\n");

                break;

            default:

                String v = node.getNodeValue();

                if (v != null) {
                    v = v.trim();
                }

                System.out.println("Type not suppoerted: " +
                    typeName(node.getNodeType()) + "       name=\"" +
                    node.getNodeName() + "\"  value=\"" + v + "\"");
                s += toString2(node.getFirstChild(), indent + "  ");
            }

            s += toString2(node.getNextSibling(), indent);
            
        }

        return s;
    }
    
    static String typeName(int typeNum) {
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
    
 

	  @Override  
	  public void run()   
	  {  
//		  System.out.println("This is currently running on a separate thread, " +  
//		            "the id is: " + Thread.currentThread().getId());  
		  try {
			FatturaPaRead.readXml();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	
	
	

}


