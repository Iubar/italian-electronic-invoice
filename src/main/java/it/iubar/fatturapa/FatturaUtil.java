package it.iubar.fatturapa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
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
	
	public static String API_KEY;
	
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

	public static String hMacCrypt(String msg, String keyString, String algo) {
		String digest = null;
	    try {
	    	SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
	    	Mac mac = Mac.getInstance(algo);
	    	mac.init(key);

	    	byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

	    	StringBuffer hash = new StringBuffer();
	    	for (int i = 0; i < bytes.length; i++) {
	    		String hex = Integer.toHexString(0xFF & bytes[i]);
	    		if (hex.length() == 1) {
	    			hash.append('0');
	    		}
	        	hash.append(hex);
	      	}
	      	digest = hash.toString();
	    } catch (Exception e) {
	    }
	    return digest;
	  }
	
	private static void getApi(){
		Properties prop = new Properties();
		InputStream input = null;
		
		try{
			input = new FileInputStream("src/main/resources/apikey.ini");
			prop.load(input);
			API_KEY = prop.getProperty("apikey");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
