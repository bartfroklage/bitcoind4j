package org.bfroklage.bitcoind4j.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AddMultisigAddressCommand extends BitcoindCommand {


	public AddMultisigAddressCommand(int nrequired, String[] keys) {	
		setMethod("addmultisigaddress");
		addParam(nrequired);
		addParam(keys);	
	}
	
	
	@Override
	public Object parseResponse(String responseText) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(responseText);
		String result = jsonObject.get("result").getAsString();
		return result;
	}

}
