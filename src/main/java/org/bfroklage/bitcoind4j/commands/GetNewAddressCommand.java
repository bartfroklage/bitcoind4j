package org.bfroklage.bitcoind4j.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetNewAddressCommand extends BitcoindCommand {

	public static final String ACCOUNT = "";
	
	public GetNewAddressCommand(String account) {
		setMethod("getnewaddress");
		addParam(account);
	}
	
	public GetNewAddressCommand() {
		this(GetNewAddressCommand.ACCOUNT);
	}
	
	@Override
	public Object parseResponse(String responseText) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(responseText);
		return jsonObject.get("result").getAsString();		
	}
	

}
