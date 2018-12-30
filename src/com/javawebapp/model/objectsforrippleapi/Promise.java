package com.javawebapp.model.objectsforrippleapi;

public class Promise<E>
{
	String txJSON; // The prepared transaction in rippled JSON format.
	Object instructions; // The instructions for how to execute the transaction after adding automatic
							// defaults.
	Amount instructionsFee; // An exact fee to pay for the transaction. See Transaction Fees for more
							// information.
	Sequence instructionsSequence; // The initiating account's sequence number for this transaction.
	Integer instructionsMaxLedgerVersionInteger = null; // The highest ledger version that the transaction can be
														// included in. Set to null if there is no maximum.
	String instructionsMaxLedgerVersionString = null; // The highest ledger version that the transaction can be included
														// in. Set to null if there is no maximum.
	public Promise()
	{
		
	}
	
	public Promise(String txJSON, Object instructions, Amount instructionsFee, Sequence instructionsSequence,
			Integer instructionsMaxLedgerVersionInteger, String instructionsMaxLedgerVersionString)
	{
		this.txJSON = txJSON;
		this.instructions = instructions;
		this.instructionsFee = instructionsFee;
		this.instructionsSequence = instructionsSequence;
		this.instructionsMaxLedgerVersionInteger = instructionsMaxLedgerVersionInteger;
		this.instructionsMaxLedgerVersionString = instructionsMaxLedgerVersionString;
	}
	
	public String getTxJSON()
	{
		return txJSON;
	}
	
	public void setTxJSON(String txJSON)
	{
		this.txJSON = txJSON;
	}
	
	public Object getInstructions()
	{
		return instructions;
	}
	
	public void setInstructions(Object instructions)
	{
		this.instructions = instructions;
	}
	
	public Amount getInstructionsFee()
	{
		return instructionsFee;
	}
	
	public void setInstructionsFee(Amount instructionsFee)
	{
		this.instructionsFee = instructionsFee;
	}
	
	public Sequence getInstructionsSequence()
	{
		return instructionsSequence;
	}
	
	public void setInstructionsSequence(Sequence instructionsSequence)
	{
		this.instructionsSequence = instructionsSequence;
	}
	
	public Integer getInstructionsMaxLedgerVersionInteger()
	{
		return instructionsMaxLedgerVersionInteger;
	}
	
	public void setInstructionsMaxLedgerVersionInteger(Integer instructionsMaxLedgerVersionInteger)
	{
		this.instructionsMaxLedgerVersionInteger = instructionsMaxLedgerVersionInteger;
	}
	
	public String getInstructionsMaxLedgerVersionString()
	{
		return instructionsMaxLedgerVersionString;
	}
	
	public void setInstructionsMaxLedgerVersionString(String instructionsMaxLedgerVersionString)
	{
		this.instructionsMaxLedgerVersionString = instructionsMaxLedgerVersionString;
	}
}
