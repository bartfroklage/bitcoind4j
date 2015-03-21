package org.bfroklage.bitcoind4j.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DumpPrivateKeyCommand extends BitcoindCommand {
	
	public DumpPrivateKeyCommand(String address) {
		setMethod("dumpprivkey");
		addParam(address);
	}

	@Override
	public Object parseResponse(String responseText) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(responseText);
		return jsonObject.get("result").getAsString();		
	}
}
