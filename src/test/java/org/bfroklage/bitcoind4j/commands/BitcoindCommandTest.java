package org.bfroklage.bitcoind4j.commands;

import junit.framework.TestCase;

public class BitcoindCommandTest extends TestCase {

	public void testToJson() {

		BitcoindCommand cmd = new BitcoindCommand() {
			@Override
			public Object parseResponse(String responseText) {
				return null;
			}
		};

		cmd.setMethod("testmethod");
		cmd.addParam("testparam");
		String json = cmd.toJson();
		assertNotNull(json);
		assertTrue(json.contains("\"method\":\"testmethod\""));
		assertTrue(json.contains("\"params\":[\"testparam\"]"));
	}
}
