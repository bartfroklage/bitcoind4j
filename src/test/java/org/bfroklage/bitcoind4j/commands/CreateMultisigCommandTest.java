package org.bfroklage.bitcoind4j.commands;

import org.bfroklage.bitcoind4j.commands.dao.MultisigInfo;

import junit.framework.TestCase;

public class CreateMultisigCommandTest extends TestCase {

	private BitcoindCommand createMultisigCommand = new CreateMultisigCommand(
			3,
			new String[] {
					"04e1443537df96fa52437b8672ff2f1c1c767d93d96f5087bef560e4333100daeb18a5022f69aaee83dc5e8736edacaf7eb1b947483a235d1b3185a0a51400296c",
					"042f737186888a8d2b8b21b9bf9cc6245a4d678228cde31a7a4c7d065c067b2822e5955445c73b9afd53493e4c7ce7732e4e09917f6a1bef225a1074d88cf1622f",
					"045fba8d8df51534092061683bb7306df43892ca95e35d6198714b3b9a0607cd5205bcca7fd81985a9f4dbf602b9265aba452b614cd944bf1f9cedaba422c61a65" });

	public void testToJson() {
		assertTrue(createMultisigCommand.toJson().contains("createmultisig"));
		assertTrue(createMultisigCommand.toJson().contains("3"));
	}

	public void testParseResponse() {
		String responseText = "{\"result\": {\"address\": \"1XXGP15WKuHwqP6MkTHKr6kzBn8VBrb1A\",\"redeemScript\": \"534104e1443537df96fa52437b8672ff2f1c1c767d93d96f5087bef560e4333100daeb18a5022f69aaee83dc5e8736edacaf7eb1b947483a235d1b3185a0a51400296c41042f737186888a8d2b8b21b9bf9cc6245a4d678228cde31a7a4c7d065c067b2822e5955445c73b9afd53493e4c7ce7732e4e09917f6a1bef225a1074d88cf1622f41045fba8d8df51534092061683bb7306df43892ca95e35d6198714b3b9a0607cd5205bcca7fd81985a9f4dbf602b9265aba452b614cd944bf1f9cedaba422c61a6553ae\"}, \"error\": null,\"id\": \"1\"}";
		MultisigInfo mi = (MultisigInfo) createMultisigCommand
				.parseResponse(responseText);
		assertEquals("1XXGP15WKuHwqP6MkTHKr6kzBn8VBrb1A", mi.getAddress());
	}

}
