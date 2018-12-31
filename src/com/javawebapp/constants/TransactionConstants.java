package com.javawebapp.constants;

/**
 * A collection of all the constants needed for transactions
 * 
 * @author Evan
 *
 */
public class TransactionConstants
{
	// Transaction Types
	public static final String PAYMENT = "Payment";
	public static final String OFFER_CREATE = "OfferCreate";
	public static final String OFFER_CANCEL = "OfferCancel";
	public static final String TRUST_SET = "TrustSet";
	public static final String ACCOUNT_SET = "AccountSet";
	public static final String SET_REGULAR_KEY = "SetRegularKey";
	public static final String SIGNER_LIST_SET = "SignerListSet";
	public static final String ESCROW_CREATE = "EscrowCreate";
	public static final String ESCROW_FINISH = "EscrowFinish";
	public static final String ESCROW_CANCEL = "EscrowCancel";
	public static final String PAYMENT_CHANNEL_CREATE = "PaymentChannelCreate";
	public static final String PAYMENT_CHANNEL_FUND = "PaymentChannelFund";
	public static final String PAYMENT_CHANNEL_CLAIM = "PaymentChannelClaim";
	
	// JSON Fields
	// A reference can be found here:
	// https://developers.ripple.com/transaction-common-fields.html
	public static final String ACCOUNT = "Account";
	public static final String TRANSACTION_TYPE = "TransactionType";
	public static final String FEE = "Fee";
	public static final String SEQUENCE = "Sequence";
	public static final String ACCOUNT_TXN_ID = "AccountTxnID";
	public static final String FLAGS = "Flags";
	public static final String LAST_LEDGER_SEQUENCE = "LastLedgerSequence";
	public static final String MEMOS = "Memos";
	public static final String SIGNERS = "Signers";
	public static final String SOURCE_TAG = "SourceTag";
	public static final String SIGNING_PUBLIC_KEY = "SigningPubKey";
	public static final String TXN_SIGNATURE = "TxnSignature";
	
	/**
	 * The caller references the constants using
	 * <tt>TransactionConstants.PAYMENT</tt>, and so on. Thus, the caller should be
	 * prevented from constructing objects of this class, by declaring this private
	 * constructor.
	 */
	private TransactionConstants()
	{
		// this prevents even the native class from calling this constructor
		throw new AssertionError();
	}
}
