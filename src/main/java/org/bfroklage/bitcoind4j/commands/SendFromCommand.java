package org.bfroklage.bitcoind4j.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SendFromCommand extends BitcoindCommand {

	public SendFromCommand(String account, String address, double amount) {
		setMethod("sendfrom");
		addParam(account);
		addParam(address);
		addParam(amount);	
	}
	
	@Override
	public Object parseResponse(String responseText) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(responseText);
		return jsonObject.get("result").getAsString();		
	}

}
