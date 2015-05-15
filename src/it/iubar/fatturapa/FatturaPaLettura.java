package it.iubar.fatturapa;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class FatturaPaLettura {

	private static void readXml() throws ParserConfigurationException, SAXException, IOException {
		String path = "C:/Users/Matteo/workspace/italian-electronic-invoice/src/it/iubar/fatturapa/xml";
		String fileName = path + "/IT01234567890_11001.xml";
		File fXmlFile = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		dbFactory.setValidating(true);            // and validating parser features
		dbFactory.setIgnoringElementContentWhitespace(true);		
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);


//		doc.getDocumentElement().normalize();

		// System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("IdTrasmittente");

				// BUG: FatturaPaLettura.print(nList);
				
				
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


    private static void print(NodeList nList) {
    	for (int temp = 0; temp < nList.getLength(); temp++) {
   		 
			Node nNode = nList.item(temp);
			
			System.out.println("nodi: " + nList.getLength());
			
			String s = nNode.getNodeName() + ": " + FatturaPaLettura.toString(nNode);
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
            switch (node.getNodeType()) {
            case Node.TEXT_NODE:

                s =  node.getNodeName() + ": " +  node.getNodeValue().trim();

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
    
	public static void main(String argv[]) {
		try {
			FatturaPaLettura.readXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	
	
	

}


