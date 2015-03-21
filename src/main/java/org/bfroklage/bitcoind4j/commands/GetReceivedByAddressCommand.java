package org.bfroklage.bitcoind4j.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetReceivedByAddressCommand extends BitcoindCommand {
	
	public GetReceivedByAddressCommand(String address) {
		setMethod("getreceivedbyaddress");
		addParam(address);		
	}

	
	@Override
	public Object parseResponse(String responseText) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(responseText);
		return new Double(jsonObject.get("result").getAsDouble());		
	}

}
