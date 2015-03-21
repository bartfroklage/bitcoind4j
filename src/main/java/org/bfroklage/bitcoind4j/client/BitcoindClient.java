package org.bfroklage.bitcoind4j.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bfroklage.bitcoind4j.commands.BitcoindCommand;

public class BitcoindClient {
	
	private final String username;
	private final String password;
	private final URL endpoint;
	
	private BitcoindClient(BitcoindClientBuilder builder) {
		username = builder.username;
		password = builder.password;
		endpoint = builder.endpoint;				
	}	
	
	private String createAuthString() {
		String authString = username + ":" + password;		
		return new String(Base64.encodeBase64(authString.getBytes()));		
	}
	
	private CloseableHttpClient createClient() {
		CloseableHttpClient client = HttpClients.createDefault();
		return client;
	}
	
	private HttpPost createPostRequest(String body) throws URISyntaxException {
		HttpPost postRequest = new HttpPost(endpoint.toURI());
		postRequest.setHeader("Authorization", "Basic " + createAuthString());
		StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
		postRequest.setEntity(entity);
		return postRequest;
	}
	
	private String sendRequest(String body) throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient client = createClient();
		try {			
			HttpPost post = createPostRequest(body);
			HttpResponse response = client.execute(post);		
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}
	
	public Object invoke(BitcoindCommand command) throws URISyntaxException, ClientProtocolException, IOException {
		String response = sendRequest(command.toJson());
		return command.parseResponse(response);        
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {		
		return password;
	}
	
	public URL getEndpoint() {
		return endpoint;
	}	
	
	public static class BitcoindClientBuilder {
		private final String username; 
		private final String password;
		private final URL endpoint; 
		
		public BitcoindClientBuilder(URL endpoint, String username, String password) {
			this.endpoint = endpoint;
			this.username = username;
			this.password = password;
		}		
		
		public BitcoindClient build() {
			return new BitcoindClient(this);
		}		
	}
}
