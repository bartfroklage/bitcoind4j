package org.bfroklage.bitcoind4j.commands;

import junit.framework.TestCase;

public class GetBalanceCommandTest extends TestCase {

	public void testToJson() {
		String json = new GetBalanceCommand().toJson();
		assertNotNull(json);
		assertTrue(json.contains("getbalance"));
    	assertTrue(json.contains("params"));
		assertTrue(json.contains("[\"\"]"));
	}

	public void testParseResponse() {
		String responseText = "{\"result\":0.17788752,\"error\":null,\"id\":\"1\"}";
		GetBalanceCommand cmd = new GetBalanceCommand();
		assertEquals(new Double(0.17788752), cmd.parseResponse(responseText));
	}
}
