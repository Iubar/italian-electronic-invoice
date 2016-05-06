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

	/**
	 * Tag for JSON data response
	 */
	public static final String DATA_TAG = "data";
	/**
	 * Tag for JSON server code response
	 */
	public static final String CODE_TAG = "code";
	/**
	 * Tag for JSON server message response
	 */
	public static final String RESPONSE_TAG = "response";
	/**
	 * Tag for JSON XML request
	 */
	public static final String XML_TAG = "xml";
	
	private static final String USER_PARAM = "user";
	private static final String TIMESTAMP_PARAM = "ts";
	private static final String SIGNATURE_PARAM = "hash";
	
	private static final String API_KEY = setApi();
	private static final String API_KEY_DIR = "src/main/resources/apikey.ini";
	private static final String API_KEY_VALUE = "apikey";

	private static String user = "user@user.it";

	private static final String DEST_URL = "http://www.fatturatutto.it/app/api/test/";
	private static String XSD_SCHEMA = "http://www.fatturatutto.it/app/public/resources/xml/1.1/fatturapa_v1.1.xsd";
	private static final String GET_FATTURA = "fattura-esempio/";

	private static String getUser() {
		return user;
	}

	/**
	 * Set the username for the request to the server. Default is <code>user@user.it</code>.
	 * @param u Username
     */
	public static void setUser(String u) {
		user = u;
	}

	/**
	 * Set the url where the parse model XSD is located
	 * @param url destination url
     */
	public static void setXsdUrl(String url) {
		XSD_SCHEMA = url;
	}
	
	private static String getApi(){
		return API_KEY;
	}

	private static String setApi(){ // this will simply gets api key from file ini
		Properties prop = new Properties();
		InputStream input = null;
		
		try{
			input = new FileInputStream(API_KEY_DIR);
			prop.load(input);
			return prop.getProperty(API_KEY_VALUE);
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

	/**
	 * Returns the formatted XML {@link Document} of the given {@link String} invoice code.
	 *
	 * @param numeroFattura the invoice number called for download
	 * @return the XML doc aleready validated
	 * @throws AuthException the web server rejected your data. User, password or numeroFattura not valid
	 * @throws ParseNonriuscito the invoice got from the server is not well-formatted as xsd
	 * @throws XmlNotvalid the invoice got from the server is not complete or bad formatted
	 */
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

	private static boolean isValid(Document document){
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		Source source = new StreamSource(XSD_SCHEMA);
		Schema schema;
		try {
			schema = schemaFactory.newSchema(source);
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		}

		try {
			Validator validator = schema.newValidator();
			validator.validate(new DOMSource(document));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static void xsltExecute(File xml, File xslt, String output){
		Source xmlInput = new StreamSource(xml);
		Source xslInput = new StreamSource(xslt);
		Result xmlOutput = new StreamResult(new File(output));

		try{
			Transformer transformer = TransformerFactory.newInstance().newTransformer(xslInput);
			transformer.transform(xmlInput, xmlOutput);
		} catch (TransformerException e){
			e.printStackTrace();
		}
	}

	/**
	 * Saves a xml file where you specify with savePath parameter.
	 * Call the invoice by code using numeroFattura.
	 *
	 * @param savePath where you want to save the file
	 * @param numeroFattura what invoice do you want
	 * @throws TransformerException file not saved, probably the savePath is wrong
	 * @throws AuthException the web server rejected your data. User, password or numeroFattura not valid
	 * @throws ParseNonriuscito the invoice got from the server is not well-formatted as xsd
	 * @throws XmlNotvalid the invoice got from the server is not complete or bad formatted
     */
	public static void saveFile(String savePath, String numeroFattura) throws TransformerException, AuthException, XmlNotvalid, ParseNonriuscito {

		Document d = d = FatturaUtil.getFattura(numeroFattura);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(new File(savePath));
		Source input = new DOMSource(d);

		transformer.transform(input, output);
	}
}
