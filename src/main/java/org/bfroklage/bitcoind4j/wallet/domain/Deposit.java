package org.bfroklage.bitcoind4j.wallet.domain;

public class Deposit {
	
	private String txid;
	private String address;
	private double amount;
	
	public Deposit(String txid, String address, double amount) {
		this.txid = txid;
		this.address = address;
		this.amount = amount;
	}
	
	public String getTxid() {
		return txid;
	}
	
	public String getAddress() {
		return address;
	}
	
	public double getAmount() {
		return amount;
	}

}
