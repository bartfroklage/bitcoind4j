package org.bfroklage.bitcoind4j.commands;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ListAddressesCommand extends BitcoindCommand {

	public static final String ACCOUNT = "";
	
	private String account;
	
	public ListAddressesCommand(String account) {
		setMethod("listreceivedbyaddress");
		addParam(0);
		addParam(true);
		this.account = account;
	}
	
	public ListAddressesCommand() {
		this(ListAddressesCommand.ACCOUNT);
	}
	
	@Override
	public Object parseResponse(String responseText) {	
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(responseText);
		JsonArray jsonResultArray = jsonObject.getAsJsonArray("result");
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < jsonResultArray.size(); i++) {			
			JsonObject jsonItem = jsonResultArray.get(i).getAsJsonObject();
			String elementAddress = jsonItem.get("address").getAsString();
			String elementAccount = jsonItem.get("account").getAsString();
			
			if (elementAccount.equals(account)) {
				result.add(elementAddress);
			}						
		}
		return result;
	}

}
