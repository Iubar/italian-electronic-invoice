package it.iubar.fatturapa;

import java.io.StringReader;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class FatturaUtil {
	
	public static final String DATA_TAG = "data";
	public static final String CODE_TAG = "code";
	public static final String RESPONSE_TAG = "response";
	public static final String XML_TAG = "xml";
	
	private static String getResponse(String url){
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			 return response.getEntity(String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Deprecated
	//Non utile dopo Debug
	public static String getXmlString(String url) throws Exception{
		JSONObject jsonObject = new JSONObject(FatturaUtil.getResponse(url));
		JSONObject data = jsonObject.getJSONObject(DATA_TAG);
		return data.getString(XML_TAG);
	}
	
	public static Document getXmlDocument(String url) throws Exception{
		
		String data = FatturaUtil.getResponse(url);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	   	return builder.parse(new InputSource(new StringReader(data)));
	}


}
