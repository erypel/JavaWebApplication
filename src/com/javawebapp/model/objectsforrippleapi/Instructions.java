package com.javawebapp.model.objectsforrippleapi;

/**
 * Transaction instructions indicate how to execute a transaction, complementary
 * with the transaction specification.
 * 
 * Ripple recommends that you specify a maxLedgerVersion so that you can quickly
 * determine that a failed transaction will never succeeed in the future. It is
 * impossible for a transaction to succeed after the XRP Ledger's
 * consensus-validated ledger version exceeds the transaction's
 * maxLedgerVersion. If you omit maxLedgerVersion, the "prepare*" method
 * automatically supplies a maxLedgerVersion equal to the current ledger plus 3,
 * which it includes in the return value from the "prepare*" method.
 * 
 * @author Evan
 *
 */
public class Instructions
{
	Value fee; // Optional An exact fee to pay for the transaction. See Transaction Fees for
				// more information.
	Value maxFee; // Optional Deprecated: Use maxFeeXRP in the RippleAPI constructor instead. The
					// maximum fee to pay for this transaction. If this exceeds maxFeeXRP, maxFeeXRP
					// will be used instead. See Transaction Fees for more information.
	Integer maxLedgerVersionInteger = null; // Optional The highest ledger version that the transaction can be included
											// in. If this option and maxLedgerVersionOffset are both omitted, the
											// maxLedgerVersion option will default to 3 greater than the current
											// validated ledger version (equivalent to maxLedgerVersionOffset=3). Use
											// null to not set a maximum ledger version.
	String maxLedgerVersionString = null; // Optional The highest ledger version that the transaction can be included
											// in. If this option and maxLedgerVersionOffset are both omitted, the
											// maxLedgerVersion option will default to 3 greater than the current
											// validated ledger version (equivalent to maxLedgerVersionOffset=3). Use
											// null to not set a maximum ledger version.
	Integer maxLedgerVersionOffset; // Optional Offset from current validated ledger version to highest ledger
									// version that the transaction can be included in.
	Sequence sequence; // Optional Number of signers that will be signing this transaction.
	int signersCount; // Optional Number of signers that will be signing this transaction.
	
	public Value getFee()
	{
		return fee;
	}
	
	public void setFee(Value fee)
	{
		this.fee = fee;
	}
	
	public Value getMaxFee()
	{
		return maxFee;
	}
	
	public void setMaxFee(Value maxFee)
	{
		this.maxFee = maxFee;
	}
	
	public Integer getMaxLedgerVersionInteger()
	{
		return maxLedgerVersionInteger;
	}
	
	public void setMaxLedgerVersionInteger(Integer maxLedgerVersionInteger)
	{
		this.maxLedgerVersionInteger = maxLedgerVersionInteger;
	}
	
	public String getMaxLedgerVersionString()
	{
		return maxLedgerVersionString;
	}
	
	public void setMaxLedgerVersionString(String maxLedgerVersionString)
	{
		this.maxLedgerVersionString = maxLedgerVersionString;
	}
	
	public Integer getMaxLedgerVersionOffset()
	{
		return maxLedgerVersionOffset;
	}
	
	public void setMaxLedgerVersionOffset(Integer maxLedgerVersionOffset)
	{
		this.maxLedgerVersionOffset = maxLedgerVersionOffset;
	}
	
	public Sequence getSequence()
	{
		return sequence;
	}
	
	public void setSequence(Sequence sequence)
	{
		this.sequence = sequence;
	}
	
	public int getSignersCount()
	{
		return signersCount;
	}
	
	public void setSignersCount(int signersCount)
	{
		this.signersCount = signersCount;
	}
}
