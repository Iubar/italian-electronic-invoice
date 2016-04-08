package it.iubar.fatturapa;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import it.iubar.fatturapa.exceptions.AuthException;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class FatturaUtil {
	
	public static final String DATA_TAG = "data";
	public static final String CODE_TAG = "code";
	public static final String RESPONSE_TAG = "response";
	public static final String XML_TAG = "xml";
	
	private static final String USER_PARAM = "user";
	private static final String TIMESTAMP_PARAM = "ts";
	private static final String SIGNATURE_PARAM = "hash";
	
	private static String API_KEY = setApi();
	private static String USER = "user@user.it";
	private static String URL_DEST = "http://localhost";
	
	private static String getUser() {
		return USER;
	}

	public static void setUser(String user) {
		USER = user;
	}
	
	private static String getApi(){
		return API_KEY;
	}
	
	private static String getUrl(){
		return URL_DEST;
	}
	
	public static void setUrl(String url){
		URL_DEST = url;
	}
	
	private static String setApi(){ // this will simply gets api key from file ini
		Properties prop = new Properties();
		InputStream input = null;
		
		try{
			input = new FileInputStream("src/main/resources/apikey.ini");
			prop.load(input);
			return prop.getProperty("apikey");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
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

	private static String getResponse() throws AuthException{
		//Initialization jersey client
		Client client = Client.create();
		//set destination url
		WebResource webResource = client.resource(getUrl());		
				
		//execution query
		ClientResponse response = webResource.queryParam(USER_PARAM, getUser()).queryParam(TIMESTAMP_PARAM, getTimeStamp()).queryParam(SIGNATURE_PARAM, getSignature()).accept("application/json").get(ClientResponse.class);
				
		//if code is not 200, throw exception
		int stat = response.getStatus();
		if (stat != 200) {
			throw new AuthException(new JSONObject(response.getEntity(String.class)).getString(RESPONSE_TAG), stat);
		}
		return response.getEntity(String.class); //return string
	}
	
	public static String getInfo(String tag) throws AuthException{
		//getting string from previous method
		JSONObject jsonObject = new JSONObject(FatturaUtil.getResponse());
		
		//if I need nested tags or I get parameters that are not strings, this will solve everything
		if(tag.equalsIgnoreCase(XML_TAG)){
			JSONObject data = jsonObject.getJSONObject(DATA_TAG);
			return data.getString(tag);
		}else if(tag.equalsIgnoreCase(CODE_TAG)){
			return String.valueOf(jsonObject.getInt(CODE_TAG));
		}else if(tag.equalsIgnoreCase(DATA_TAG)){
			JSONObject data = jsonObject.getJSONObject(tag);
			return data.toString();
		}
		return jsonObject.getString(tag);
	}
	
	public static Document getXmlDocument() throws Exception{ //gets xml document formatted and clean
		
		String data = FatturaUtil.getInfo(XML_TAG);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	   	return builder.parse(new InputSource(new StringReader(data)));
	}
	
	private static String getPayLoad(){ //generation of concatenated string
		return FatturaUtil.getUrl() + FatturaUtil.getUser() + getTimeStamp() + FatturaUtil.getApi();
	}
	
	private static String getTimeStamp(){ //get correct timestamp (sever pattern)
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date());
	}

	private static String getSignature() { //gets signature value with HmacSHA256
		String payload = FatturaUtil.getPayLoad();
		String algo = "HmacSHA256";
		String keyString = FatturaUtil.getApi();
		try{
			Mac sha256_HMAC = Mac.getInstance(algo);
		     SecretKeySpec secret_key = new SecretKeySpec(keyString.getBytes(), algo);
		     sha256_HMAC.init(secret_key);
		     String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(payload.getBytes()));
		     return hash;
		}catch (Exception e){
			return null;
		}
	}
	
	public static void saveFile(String savepath) throws Exception{ //save xml got from data JSON string
		Document d = getXmlDocument();
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(new File(savepath));
		Source input = new DOMSource(d);

		transformer.transform(input, output);
	}
}
