package org.bfroklage.bitcoind4j.commands.dao;

public class MultisigInfo {
	private String address;
	private String redeemScript;
	
	public MultisigInfo(String address, String redeemScript) {
		this.address = address;
		this.redeemScript = redeemScript;
	}

	public String getAddress() {
		return address;
	}

	public String getRedeemScript() {
		return redeemScript;
	}

}
