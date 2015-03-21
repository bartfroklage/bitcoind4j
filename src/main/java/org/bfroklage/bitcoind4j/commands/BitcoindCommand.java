package org.bfroklage.bitcoind4j.commands;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class BitcoindCommand {
	
	protected String method;
	protected List<Object> params = new ArrayList<Object>();	
	protected String id = "1";
	protected String jsonrcp = "2.0";
	
	public String toJson() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this, BitcoindCommand.class);		
	}
	
	protected void setMethod(String method) {
		this.method = method;
	}
	
	protected void addParam(Object param) {
		params.add(param);
	}
	
	public abstract Object parseResponse(String responseText);		

}
