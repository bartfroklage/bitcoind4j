package org.bfroklage.bitcoind4j.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SetTxFeeCommand extends BitcoindCommand {
	
	public SetTxFeeCommand(double amount) {
		setMethod("settxfee");
		addParam(amount);
	}

	@Override
	public Object parseResponse(String responseText) {		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(responseText);
		return jsonObject.get("result").getAsBoolean();		
	}

}
