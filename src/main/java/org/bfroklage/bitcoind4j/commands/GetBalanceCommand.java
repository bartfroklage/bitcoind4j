package org.bfroklage.bitcoind4j.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class GetBalanceCommand extends BitcoindCommand {
	
	public static final String ACCOUNT = "";
	
	public GetBalanceCommand(String account) {
		setMethod("getbalance");	
		addParam("");
	}	
	
	public GetBalanceCommand() {
		this(GetBalanceCommand.ACCOUNT);
	}
	
	@Override
	public Object parseResponse(String responseText) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(responseText);
		return new Double(jsonObject.get("result").getAsDouble());		
	}

}
