package org.bfroklage.bitcoind4j.commands;

import java.lang.reflect.Type;
import java.util.List;

import org.bfroklage.bitcoind4j.commands.dao.ListTransactionsItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class ListTransactionsCommand extends BitcoindCommand {

	public static final String ACCOUNT = "";
	public static final int OFFSET = 0;
	public static final int COUNT = 10;
		
	public ListTransactionsCommand(String account,  int count, int offset) {
		setMethod("listtransactions");
		addParam(account);
		addParam(count);
		addParam(offset);
	}
	
	public ListTransactionsCommand(String account) {
		this(account, ListTransactionsCommand.COUNT, ListTransactionsCommand.OFFSET);
	}
	
	public ListTransactionsCommand(String account, int count) {
		this(account, count, ListTransactionsCommand.OFFSET);
	}
	
	public ListTransactionsCommand(int count) {
		this(ListTransactionsCommand.ACCOUNT, count, ListTransactionsCommand.OFFSET);
	}
	
	public ListTransactionsCommand(int count, int offset) {
		this(ListTransactionsCommand.ACCOUNT, count, offset);
	}
	
	public ListTransactionsCommand() {
		this(ListTransactionsCommand.ACCOUNT, ListTransactionsCommand.COUNT, ListTransactionsCommand.OFFSET);
	}	
	
	@Override
	public Object parseResponse(String responseText) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(responseText);	
		JsonArray transactionsJson = jsonObject.getAsJsonArray("result");
		Type collectionType = new TypeToken<List<ListTransactionsItem>>() { }.getType();
		Gson gson = new GsonBuilder().create();
		List<ListTransactionsItem> transactionList = gson.fromJson(transactionsJson, collectionType);	
		return transactionList;	
	}
}
