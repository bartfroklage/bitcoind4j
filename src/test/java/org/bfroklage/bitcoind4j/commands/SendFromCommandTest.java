package org.bfroklage.bitcoind4j.commands;

import java.io.IOException;
import java.net.URISyntaxException;

import junit.framework.TestCase;
import org.apache.http.client.ClientProtocolException;

public class SendFromCommandTest extends TestCase {

	private BitcoindCommand sendFromCommand = new SendFromCommand("", "1XXX5n1Y3CZnvAXdavykbgCQfYuVvQgeT", 10);

	public void testToJson() throws ClientProtocolException, URISyntaxException, IOException {
		String json = sendFromCommand.toJson();
		assertTrue(json.contains("sendfrom"));
		assertTrue(json.contains("params"));
		assertTrue(json.contains("\"1XXX5n1Y3CZnvAXdavykbgCQfYuVvQgeT\""));
	}
	

	public void testParseResponse() {
		String responseText = "{\"result\":\"XXeDCPf6B3MDTD7PLF29SSMWP5LT6HdT5YSFCGbeCSAPACaAb7BEAYB55LOSdGE3\",\"error\":null,\"id\":\"1\"}";
		Object object = sendFromCommand.parseResponse(responseText);
		assertEquals("XXeDCPf6B3MDTD7PLF29SSMWP5LT6HdT5YSFCGbeCSAPACaAb7BEAYB55LOSdGE3", object);		
	}	

}

