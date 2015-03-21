# bitcoind4j

## Introduction

This Java-lib provides you with the basic fuctions for a bitcoin-wallet like deposit and withdrawal.  
It also provides you with some more advanged functions like multisig.

## Architecture

I found that native bitcoin-implementations like bitcoinj are not always stable. Therefore I choose to depend on the official bitcoind application.
This application has a json-rpc interface.

The interaction with this interface is done via the command-pattern [Command-pattern](http://en.wikipedia.org/wiki/Command_pattern)
To hide the complexity of the native bitcoind json-rpc interface there is a Wallet class. This object has simple functions like withdraw and createAddress.

To make sure the Wallet is only configured once it can only be constructed via a builder [Builder-pattern](http://en.wikipedia.org/wiki/Builder_pattern).

## Example

```
URL endpoint = new URL("localhost:8333");
String username = "username";
String password = "password";
Wallet wallet = new Wallet.WalletBuilder(endpoint, username, password).build();

wallet.withdraw(0.25, "1JDd889QQSTDw5PfPtTxmczrn54R8gkCuG");
```