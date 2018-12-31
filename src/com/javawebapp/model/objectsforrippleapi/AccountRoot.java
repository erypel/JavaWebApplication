package com.javawebapp.model.objectsforrippleapi;

/**
 * The AccountRoot object type describes a single account, its settings, and XRP
 * balance
 * 
 * Reference can be found here: https://developers.ripple.com/accountroot.html
 * 
 * @author Evan
 *
 */
public class AccountRoot
{
	Long ledgerEntryType; // The value 0x0061, mapped to the string AccountRoot, indicates that this is an
							// AccountRoot object.
	Address account; // The identifying address of this account, such as
						// rf1BiGeXwwQoi8Z2ueFYTEXSwuJYfV2Jpn.
	Amount balance; // The account's current XRP balance in drops, represented as a string.
	long flags; // A bit-map of boolean flags enabled for this account.
	long ownerCount; // The number of objects this account owns in the ledger, which contributes to
						// its owner reserve.
	String previousTxnID; // The identifying hash of the transaction that most recently modified this
							// object.
	long previousTxnLgrSeq; // The index of the ledger that contains the transaction that most recently
							// modified this object.
	Sequence sequence; // The sequence number of the next valid transaction for this account. (Each
						// account starts with Sequence = 1 and increases each time a transaction is
						// made.)
	String accountTxnID; // (Optional) The identifying hash of the transaction most recently submitted by
							// this account.
	String domain; // (Optional) A domain associated with this account. In JSON, this is the
					// hexadecimal for the ASCII representation of the domain.
	String emailHash; // (Optional) The md5 hash of an email address. Clients can use this to look up
						// an avatar through services such as Gravatar.
	String messageKey; // (Optional) A public key that may be used to send encrypted messages to this
						// account. In JSON, uses hexadecimal. No more than 33 bytes.
	Address regularKey; // (Optional) The address of a keypair that can be used to sign transactions for
						// this account instead of the master key. Use a SetRegularKey transaction to
						// change this value.
	int tickSize; // (Optional) How many significant digits to use for exchange rates of Offers
					// involving currencies issued by this address. Valid values are 3 to 15,
					// inclusive. (Requires the TickSize amendment.)
	long transferRate; // (Optional) A transfer fee to charge other users for sending currency issued
						// by this account to each other.
	// String walletLocator; deprecated, don't use
	// long walletSize; deprecated, don't use
	
	public Long getLedgerEntryType()
	{
		return ledgerEntryType;
	}
	
	public void setLedgerEntryType(Long ledgerEntryType)
	{
		this.ledgerEntryType = ledgerEntryType;
	}
	
	public Address getAccount()
	{
		return account;
	}
	
	public void setAccount(Address account)
	{
		this.account = account;
	}
	
	public Amount getBalance()
	{
		return balance;
	}
	
	public void setBalance(Amount balance)
	{
		this.balance = balance;
	}
	
	public long getFlags()
	{
		return flags;
	}
	
	public void setFlags(long flags)
	{
		this.flags = flags;
	}
	
	public long getOwnerCount()
	{
		return ownerCount;
	}
	
	public void setOwnerCount(long ownerCount)
	{
		this.ownerCount = ownerCount;
	}
	
	public String getPreviousTxnID()
	{
		return previousTxnID;
	}
	
	public void setPreviousTxnID(String previousTxnID)
	{
		this.previousTxnID = previousTxnID;
	}
	
	public long getPreviousTxnLgrSeq()
	{
		return previousTxnLgrSeq;
	}
	
	public void setPreviousTxnLgrSeq(long previousTxnLgrSeq)
	{
		this.previousTxnLgrSeq = previousTxnLgrSeq;
	}
	
	public Sequence getSequence()
	{
		return sequence;
	}
	
	public void setSequence(Sequence sequence)
	{
		this.sequence = sequence;
	}
	
	public String getAccountTxnID()
	{
		return accountTxnID;
	}
	
	public void setAccountTxnID(String accountTxnID)
	{
		this.accountTxnID = accountTxnID;
	}
	
	public String getDomain()
	{
		return domain;
	}
	
	public void setDomain(String domain)
	{
		this.domain = domain;
	}
	
	public String getEmailHash()
	{
		return emailHash;
	}
	
	public void setEmailHash(String emailHash)
	{
		this.emailHash = emailHash;
	}
	
	public String getMessageKey()
	{
		return messageKey;
	}
	
	public void setMessageKey(String messageKey)
	{
		this.messageKey = messageKey;
	}
	
	public Address getRegularKey()
	{
		return regularKey;
	}
	
	public void setRegularKey(Address regularKey)
	{
		this.regularKey = regularKey;
	}
	
	public int getTickSize()
	{
		return tickSize;
	}
	
	public void setTickSize(int tickSize)
	{
		this.tickSize = tickSize;
	}
	
	public long getTransferRate()
	{
		return transferRate;
	}
	
	public void setTransferRate(long transferRate)
	{
		this.transferRate = transferRate;
	}
}
