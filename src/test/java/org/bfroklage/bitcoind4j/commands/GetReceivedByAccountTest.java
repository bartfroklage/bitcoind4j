package org.bfroklage.bitcoind4j.commands;

import java.io.IOException;
import java.net.URISyntaxException;
import junit.framework.TestCase;
import org.apache.http.client.ClientProtocolException;

public class GetReceivedByAccountTest extends TestCase {

	public void testToJson() throws ClientProtocolException,
			URISyntaxException, IOException {
		String json = new GetReceivedByCommand().toJson();
		assertTrue(json.contains("getreceivedbyaccount"));
		assertTrue(json.contains("params"));
	}

	public void testParseResponse() {
		String response = "{\"result\":0.20884859,\"error\":null,\"id\":\"1\"}";
		assertEquals(0.20884859,
				new GetReceivedByCommand().parseResponse(response));
	}
}
