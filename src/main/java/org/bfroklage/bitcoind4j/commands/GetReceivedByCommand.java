package org.bfroklage.bitcoind4j.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetReceivedByCommand extends BitcoindCommand {
	
	private static final int MIN_CONFIRMATION  = 3;
	private static final String ACCOUNT = "";
	
	public GetReceivedByCommand(int minConfirmationCount, String account) {
		setMethod("getreceivedbyaccount");
		addParam(account);
		addParam(minConfirmationCount);		
	}
	
	public GetReceivedByCommand(int minConfirmationCount) {
		this(minConfirmationCount, GetReceivedByCommand.ACCOUNT);
	}
	
	public GetReceivedByCommand(String account) {
		this(GetReceivedByCommand.MIN_CONFIRMATION, account);
	}
	
	public GetReceivedByCommand() {
		this(GetReceivedByCommand.MIN_CONFIRMATION, GetReceivedByCommand.ACCOUNT);
	}
	
	

		
	@Override
	public Object parseResponse(String responseText) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(responseText);
		return jsonObject.get("result").getAsDouble();
	}

}
