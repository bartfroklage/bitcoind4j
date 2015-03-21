package org.bfroklage.bitcoind4j.commands;

import junit.framework.TestCase;

public class GetNewAddressCommandTest extends TestCase {

	private BitcoindCommand getNewAddressCommand = new GetNewAddressCommand();

	public void testToJson() {
		String json = getNewAddressCommand.toJson();
		assertNotNull(json);
		assertTrue(json.contains("getnewaddress"));
		assertTrue(json.contains("[\"\"]"));
		assertTrue(json.contains("params"));
	}

	public void testParseResponse() {
		String resonse = "{\"result\":\"1XXiLXYg34YU92iWrTKboCJp7RN6nfspc\",\"error\":null,\"id\":\"1\"}";
		Object object = getNewAddressCommand.parseResponse(resonse);
		assertEquals("1XXiLXYg34YU92iWrTKboCJp7RN6nfspc", object);
	}
}
