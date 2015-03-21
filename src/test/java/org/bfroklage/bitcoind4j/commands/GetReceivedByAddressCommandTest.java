package org.bfroklage.bitcoind4j.commands;

import junit.framework.TestCase;

public class GetReceivedByAddressCommandTest extends TestCase {

	private BitcoindCommand getReceivedByAddressCmd = new GetReceivedByAddressCommand(
			"1XXGP15WKuHwqP6MkTHKr6kzBn8VBrb1A");

	public void testToJson() {

		assertTrue(getReceivedByAddressCmd.toJson().contains(
				"1XXGP15WKuHwqP6MkTHKr6kzBn8VBrb1A"));

		assertTrue(getReceivedByAddressCmd.toJson().contains(
				"getreceivedbyaddress"));

	}

	public void testParseResponse() {
		String responseText = "{\"result\": 0, \"error\": null,\"id\": \"1\"}";
		assertEquals(0.0, getReceivedByAddressCmd.parseResponse(responseText));
	}

}
