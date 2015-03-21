package org.bfroklage.bitcoind4j.wallet;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Observable;

import org.apache.http.client.ClientProtocolException;
import org.bfroklage.bitcoind4j.client.BitcoindClient;
import org.bfroklage.bitcoind4j.commands.AddMultisigAddressCommand;
import org.bfroklage.bitcoind4j.commands.BitcoindCommand;
import org.bfroklage.bitcoind4j.commands.CreateMultisigCommand;
import org.bfroklage.bitcoind4j.commands.DumpPrivateKeyCommand;
import org.bfroklage.bitcoind4j.commands.GetBalanceCommand;
import org.bfroklage.bitcoind4j.commands.GetNewAddressCommand;
import org.bfroklage.bitcoind4j.commands.GetReceivedByAddressCommand;
import org.bfroklage.bitcoind4j.commands.ListAddressesCommand;
import org.bfroklage.bitcoind4j.commands.ListTransactionsCommand;
import org.bfroklage.bitcoind4j.commands.SendFromCommand;
import org.bfroklage.bitcoind4j.commands.SetTxFeeCommand;
import org.bfroklage.bitcoind4j.commands.dao.ListTransactionsItem;
import org.bfroklage.bitcoind4j.commands.dao.MultisigInfo;
import org.bfroklage.bitcoind4j.wallet.domain.AddressInfo;
import org.bfroklage.bitcoind4j.wallet.domain.Deposit;
import org.bfroklage.bitcoind4j.wallet.domain.MultisigContract;
import org.bfroklage.bitcoind4j.wallet.exceptions.WalletException;
import org.bfroklage.bitcoind4j.wallet.exceptions.WalletInitialisationException;

public class Wallet extends Observable implements Runnable  {
		
	private final BitcoindClient client;
	private final String accountName;
	private final long pollingInterval;
	private final double transactionFee;
	private String lastSeenTxid;		
	
	private Thread pollingThread;
	
	private Wallet(WalletBuilder builder) throws WalletInitialisationException {
		this.accountName = builder.accountName;
		this.pollingInterval = builder.pollingInterval;
		this.transactionFee = builder.transactionFee;
		this.lastSeenTxid = builder.lastSeenTxid;	
		client = new BitcoindClient.BitcoindClientBuilder(builder.endpoint, builder.username, builder.password).build();
		
		try {				
			initializeTxFee();
		} catch (Exception cpex) {
			throw new WalletInitialisationException("Error initializing");
		}
	}
	
	private void initializeTxFee() throws ClientProtocolException, URISyntaxException, IOException {
		client.invoke(new SetTxFeeCommand(transactionFee));
	}

	public void start() {
		startPolling();
	}
	
	public void stop() {
		stopPolling();
	}
	
	private void startPolling() {
		if (pollingThread == null) {
			pollingThread = new Thread(this);
		}		
		pollingThread.start();
	}
	
	private void stopPolling() {
		if (pollingThread != null) {
			pollingThread.interrupt();
		}
	}
	
	public void run() {
		boolean polling = true;
		while (polling) {
			checkTransactions();		
			try {			
				Thread.sleep(pollingInterval);
			} catch(InterruptedException ex) {
				polling = false;
			}
		}	
	}
	
	private void checkTransactions() {
		boolean checkedAll = false;
		boolean sawLastTransaction = false;
		int count = 10, offset = 0;
		String currentLastSeenTxid = "";
		
		while (!checkedAll && !sawLastTransaction) {
			List<ListTransactionsItem> transactions = getTransactions(count, offset);			
			for(ListTransactionsItem tx: transactions) {				
				if (currentLastSeenTxid.equals("")) {
					currentLastSeenTxid = tx.getTxid();
				}				
				sawLastTransaction = sawLastTransaction || lastSeenTxid.equals(tx.getTxid());
				
				if (!sawLastTransaction) {
					handleTransaction(tx);
				}	
			}
			offset += count;
			checkedAll = (transactions.size() < count);		
		}
		lastSeenTxid = currentLastSeenTxid;		
	}
			
	private void handleTransaction(ListTransactionsItem tx) {
		if ("receive".equals(tx.getCategory()) && accountName.equals(tx.getAccount())) {
			setChanged();			
			notifyObservers(new Deposit(tx.getTxid(), tx.getAddress(), tx.getAmount()));
		}	
	}	

	@SuppressWarnings("unchecked")
	private List<ListTransactionsItem> getTransactions(int count, int offset) {
		List<ListTransactionsItem> result = null;
		try {	
			result = (List<ListTransactionsItem>)client.invoke(
					new ListTransactionsCommand(accountName, count, offset));		
		} catch (Exception ex) {
			
		}
		return result;
	}
	
	public AddressInfo generateAddress() throws WalletException {
		try {
			String address = (String)client.invoke(new GetNewAddressCommand(accountName));
			String privateKey = (String)client.invoke(new DumpPrivateKeyCommand(address));
			return new AddressInfo(address, privateKey);
		} catch (Exception ex) {			
			throw new WalletException("Couldnot generate address: " + ex.getMessage());
		}
	}
	
	public MultisigContract generateMultisigContract(int requiredSignatureCount, 
			String publicKey1, String publicKey2, String publicKey3) throws WalletException {
		try {
			String[] keys = new String[] {publicKey1, publicKey2, publicKey3};
			BitcoindCommand cmd = new CreateMultisigCommand(requiredSignatureCount, keys);
			MultisigInfo multisigInfo = (MultisigInfo)client.invoke(cmd);
			cmd = new AddMultisigAddressCommand(requiredSignatureCount, keys);
			client.invoke(cmd);			
			return new MultisigContract(multisigInfo.getAddress(), multisigInfo.getRedeemScript());			
		} catch (Exception ex) {
			throw new WalletException("Couldnot generate multisig contract." + ex.getMessage());
		}	
	}
	
	public double getBalance() throws WalletException {
		try {
			return (Double)client.invoke(new GetBalanceCommand(accountName));
		} catch (Exception ex) {			
			throw new WalletException("Couldnot check balance: " + ex.getMessage());
		}
	}
	
	public double getBalance(String address) throws WalletException {
		try {
			return (Double)client.invoke(new GetReceivedByAddressCommand(address));
		} catch (Exception ex) {			
			throw new WalletException("Couldnot check balance: " + ex.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String>listAddresses() throws WalletException {
		try {
			return (List<String>)client.invoke(new ListAddressesCommand(accountName));
		} catch (Exception ex) {
			throw new WalletException("Couldnot list the addresses: " + ex.getMessage());
		}		
	}
	
	public String withdraw(double amount, String address) throws WalletException {
		try {	
			return (String) client.invoke(new SendFromCommand(accountName, address, amount));
		} catch(Exception ex) {
			throw new WalletException("Couldnot withdraw: " + ex.getMessage());
		}
	}
	
		
	public static class WalletBuilder {
		private final String username; 
		private final String password;
		private final URL endpoint;
		private String accountName = "";
		private double transactionFee = 0.0d;
		private long pollingInterval = 1000l;
		private String lastSeenTxid = "";
		
		public WalletBuilder(URL endpoint, String username, String password) {
			this.endpoint = endpoint;
			this.username = username;
			this.password = password;
		}
		
		public WalletBuilder accountName(String accountName) {
			this.accountName = accountName;
			return this;
		}
		
		public WalletBuilder pollingInterval(int pollingInterval) {
			this.pollingInterval = pollingInterval;
			return this;
		}
		
		public WalletBuilder lastSeenTxid(String lastSeenTxid) {
			this.lastSeenTxid = lastSeenTxid;
			return this;
		}
		
		public WalletBuilder transactionFee(double transactionFee) {
			this.transactionFee = transactionFee;
			return this;
		}
						
		public Wallet build() throws WalletInitialisationException {
			return new Wallet(this);
		}		
	}
}