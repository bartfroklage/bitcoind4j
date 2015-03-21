package org.bfroklage.bitcoind4j.wallet.domain;

public class AddressInfo {
	
	private String address;
	private String privateKey;
	
	public AddressInfo(String address, String privateKey) {
		this.address = address;
		this.privateKey = privateKey;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPrivateKey() {
		return privateKey;
	}
	
}
