package org.bfroklage.bitcoind4j.commands;

import junit.framework.TestCase;

public class DumpPrivateKeyCommandTest extends TestCase {

	public void testToJson() {
		String json = new DumpPrivateKeyCommand(
				"1XXiLXYg34YU92iWrTKboCJp7RN6nfspc").toJson();
		assertNotNull(json);
		assertTrue(json.contains("dumpprivkey"));
		assertTrue(json.contains("params"));
		assertTrue(json.contains("[\"1XXiLXYg34YU92iWrTKboCJp7RN6nfspc\"]"));
	}

	public void testParseResponse() {
		String responseText = "{\"result\":\"5KNgsuC4Zs9ywME7afRxcHrsohWUg8Gy8p2WDkp2emzsEw7QZ9L\",\"error\":null,\"id\":\"1\"}";
		DumpPrivateKeyCommand cmd = new DumpPrivateKeyCommand(
				"1XXiLXYg34YU92iWrTKboCJp7RN6nfspc");
		assertEquals("5KNgsuC4Zs9ywME7afRxcHrsohWUg8Gy8p2WDkp2emzsEw7QZ9L",
				cmd.parseResponse(responseText));
	}
}
