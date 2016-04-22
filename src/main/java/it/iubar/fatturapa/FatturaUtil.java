package it.iubar.fatturapa;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import it.iubar.fatturapa.exceptions.AuthException;
import it.iubar.fatturapa.exceptions.ParseNonriuscito;
import it.iubar.fatturapa.exceptions.XmlNotvalid;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
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
	
	private static final String API_KEY = setApi();
	private static String user = "user@user.it";

	private static final String DEST_URL = "http://www.fatturatutto.it/app/api/test/";
	private static final String XSD_SCHEMA = "http://www.fatturatutto.it/app/public/resources/xml/1.1/fatturapa_v1.1.xsd";

	private static final String GET_FATTURA = "fattura-esempio/";
	
	private static String getUser() {
		return user;
	}

	public static void setUser(String u) {
		u = user;
	}
	
	private static String getApi(){
		return API_KEY;
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

	public static Document getFattura(String numeroFattura) throws AuthException, ParseNonriuscito, XmlNotvalid {
		JSONObject jsonObject = new JSONObject(callToHost(DEST_URL + GET_FATTURA + numeroFattura));
		JSONObject data = jsonObject.getJSONObject(DATA_TAG);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		Document document = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new InputSource(new StringReader(data.getString(XML_TAG))));
		} catch (Exception e) {
			e.printStackTrace();
		}

		isValid(document);
		return document;
	}

	private static String callToHost(String url) throws AuthException {
		//Initialization jersey client
		Client client = Client.create();
		//set destination url
		WebResource webResource = client.resource(url);

		//execution query
		ClientResponse response = webResource.queryParam(USER_PARAM, getUser()).queryParam(TIMESTAMP_PARAM, getTimeStamp()).queryParam(SIGNATURE_PARAM, getSignature(url)).accept("application/json").get(ClientResponse.class);

		//if code is not 200, throw exception
		int stat = response.getStatus();
		if (stat != 200) {
			throw new AuthException(new JSONObject(response.getEntity(String.class)).getString(RESPONSE_TAG), stat);
		}
		return response.getEntity(String.class); //return string
	}
	
	private static String getPayLoad(){ //generation of concatenated string
		return FatturaUtil.getUser() + getTimeStamp() + FatturaUtil.getApi();
	}
	
	private static String getTimeStamp(){ //get correct timestamp (sever pattern)
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date());
	}

	private static String getSignature(String url) { //gets signature value with HmacSHA256
		String payload = url + FatturaUtil.getPayLoad();
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

	public static void isValid(Document document) throws XmlNotvalid {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		Source source = new StreamSource(XSD_SCHEMA);
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(source);
		} catch (SAXException e) {
			e.printStackTrace();
		}

		Validator validator = schema.newValidator();
		try {
			validator.validate(new DOMSource(document));
		} catch (SAXException e) {
			throw new XmlNotvalid(e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void saveFile(String savePath, Document d) throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(new File(savePath));
		Source input = new DOMSource(d);

		transformer.transform(input, output);
	}
}
