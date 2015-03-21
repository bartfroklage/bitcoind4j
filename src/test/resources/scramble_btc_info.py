""""
This script is to scramble the bitcoin addresses and transaction ids used in the unit tests.
""""

import os, os.path
import re
import random
import string
import fileinput

btcAddressPattern = re.compile('"([13][a-zA-Z0-9]{26,33})\\\\?"')
btcTranscationPattern = re.compile('"([a-fA-F0-9]{64})\\\\?"')

btcAddressesDict = dict()
btcTransactionsDict = dict()

def generateBtcAddress( ): 
  r = ''.join([random.choice('123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz') for n in range(30)])
  r = '1XX' + r
  return r

def generateBtcTransactionId( ):
  r = ''.join([random.choice('0123456789abcdefABCDEFGHIJKLMNOPQRSTUVWXYZS') for n in range(63)])
  r = 'X' + r
  return r

def replaceInFile(filepath, replacements):
  for line in fileinput.FileInput(filepath,inplace=1):
    for word in replacements.keys():
      line = line.replace(word, replacements[word])
    line = line.rstrip('\r\n')
    print (line)  
  return

for root, _, files in os.walk('..'):
  for f in files:
    fullpath = os.path.join(root, f)
    finput = open(fullpath, 'r')
    line = finput.readline()
    while line:
      line = finput.readline()
      btcAddressesIter = re.finditer(btcAddressPattern, line)
      for btcAddressesMatch in btcAddressesIter:
        btcAddress = btcAddressesMatch.group(1)
        if (btcAddress not in btcAddressesDict) :
          btcAddressesDict[btcAddressesMatch.group(1)] = generateBtcAddress()

      btcTransactionIter = re.finditer(btcTranscationPattern, line)

      for btcTransactionMatch in btcTransactionIter:
        btcTransaction = btcTransactionMatch.group(1)
        if (btcTransaction not in btcTransactionsDict) :
          btcTransactionsDict[btcTransactionMatch.group(1)] = generateBtcTransactionId()

replacements = dict()
replacements.update(btcTransactionsDict);
replacements.update(btcAddressesDict);

print('Found', len(btcAddressesDict), 'addresses and', len(btcTransactionsDict), 'transactions, total', len(replacements), 'replacements.')  

for root, _, files in os.walk('..'):
  for f in files:
    fullpath = os.path.join(root, f)
    replaceInFile(fullpath, replacements)
