package org.bfroklage.bitcoind4j.commands;

import org.bfroklage.bitcoind4j.commands.dao.MultisigInfo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CreateMultisigCommand extends BitcoindCommand {

	public CreateMultisigCommand(int nrequired, String[] keys) {
		setMethod("createmultisig");
		addParam(nrequired);
		addParam(keys);	
	}	
	
	@Override
	public Object parseResponse(String responseText) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(responseText);
		JsonObject resultJsonObject = (JsonObject)jsonObject.get("result");
		String address = resultJsonObject.get("address").getAsString();
		String redeemScript = resultJsonObject.get("redeemScript").getAsString();
		
		return new MultisigInfo(address, redeemScript);
	}

}
