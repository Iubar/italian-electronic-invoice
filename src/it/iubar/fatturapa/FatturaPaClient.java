package it.iubar.fatturapa;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class FatturaPaClient {

	private Client client;
	
	public FatturaPaClient(){
		initializeClient() ;
	}
	
	private void initializeClient() {
		this.client = ClientBuilder.newClient();
	}

	public void readData() {
		String url = "http://192.168.0.131/fatture/api/xml-fattura-esempio";
		String input = "pippo";

		String username = null;
		String password = null;
		WebTarget target = client.target(url);

		
		// https://jersey.java.net/apidocs/2.16/jersey/org/glassfish/jersey/client/authentication/HttpAuthenticationFeature.html
		 HttpAuthenticationFeature feature = HttpAuthenticationFeature.universalBuilder()
			      .credentialsForBasic(username, password)
			   //   .credentials(username, password)
			      .build();
		 
		this.client.register(feature); 

		String s = target
				//.register(FilterForExampleCom.class)
				//.path("server.php")
				.queryParam("data", input)
				// .request(MediaType.TEXT_PLAIN_TYPE)
				.request("application/json")
				.header("some-header", "true")
				.get(String.class);

		System.out.println(s);
	}


}
