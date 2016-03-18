package it.iubar.fatturapa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

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
	
	private static String getHttpString(String url) throws Exception{
		URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            response.append(inputLine);

        in.close();

        return response.toString();
	}
	
	public static String getXmlString(String url) throws Exception{
		JSONObject jsonObject = new JSONObject(getHttpString(url));
		JSONObject data = jsonObject.getJSONObject(DATA_TAG);
		return data.getString(XML_TAG);
	}
	
	public static Document getXmlDocument(String url) throws Exception{
		
		String data = FatturaUtil.getXmlString(url);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	   	return builder.parse(new InputSource(new StringReader(data)));
	}
	
	public static void main(String[] args) throws Exception{
		String url = "http://www.fatturatutto.it/app/api/test/fattura-esempio";
	}
}
