package com.javawebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javawebapp.model.objectsforrippleapi.Address;
import com.javawebapp.model.objectsforrippleapi.Amount;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Payment;
import com.javawebapp.model.objectsforrippleapi.Sequence;
import com.javawebapp.model.objectsforrippleapi.Value;
import com.javawebapp.service.TransactionService;

@Controller
public class TransactionController
{
	@Autowired 
	TransactionService service;
	
	@RequestMapping( value = "/paymentTransaction", method = RequestMethod.GET)
	public JSONObject getPaymentTransactionJSON(HttpServletRequest request, HttpServletResponse response)
	{
		Payment testPayment = new Payment();
		testPayment.setAllowPartialPayment(false);
		testPayment.setDestination("rntmtrrtSGS9dJD84krKvutJLeQ6mADgQp");
		testPayment.setDestinationAddressReceive(new Address("rntmtrrtSGS9dJD84krKvutJLeQ6mADgQp"));
		testPayment.setDestinationAddressSendTo(new Address("rntmtrrtSGS9dJD84krKvutJLeQ6mADgQp"));
		testPayment.setDestinationAmount(new Amount(new Value("100")));
		testPayment.setDestinationMinAmount(new Amount(new Value("100")));
		testPayment.setSourceAddress(new Address("rwYQjHp9HZiKKpZB4i4fvc8eQvAtA7vdY6"));
		Instructions testInstr = new Instructions();
		testInstr.setFee(new Value("1"));
		testInstr.setSequence(new Sequence("rntmtrrtSGS9dJD84krKvutJLeQ6mADgQp"));
		testInstr.setMaxLedgerVersionString(null);
		JSONObject json = service.buildPaymentJson("rwYQjHp9HZiKKpZB4i4fvc8eQvAtA7vdY6", testPayment, testInstr);
		return json;
	}
}
