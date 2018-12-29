package com.javawebapp.base.factory;

import com.javawebapp.model.Transaction;

public abstract class BaseTransactionFactory
{
	public final String CHECK_CANCEL = "checkCancel";
	public final String CHECK_CASH = "checkCash";
	public final String CHECK_CREATE = "checkCreate";
	public final String ESCROW_CANCELLATION = "escrowCancellation";
	public final String ESCROW_CREATION ="escrowCreation";
	public final String ESCROW_EXECUTION = "escrowExecution";
	public final String ORDER = "order";
	public final String ORDER_CANCELLATION = "orderCancellation";
	public final String PAYMENT = "payment";
	public final String PAYMENT_CHANNEL_CLAIM ="paymentChannelClaim";
	public final String PAYMENT_CHANNEL_CREATE = "paymentChannelCreate";
	public final String PAYMENT_CHANNEL_FUND = "paymentChannelFund";
	public final String SETTINGS = "settings";
	public final String TRUSTLINE = "trustline";
	public abstract Transaction createTransaction(String type);
}
