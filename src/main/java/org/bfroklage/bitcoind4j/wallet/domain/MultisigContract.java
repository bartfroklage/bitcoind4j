package org.bfroklage.bitcoind4j.wallet.domain;

public class MultisigContract {
	
	private String address;
	private String redeemScript;
	
	public MultisigContract(String address, String redeemScript) {
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