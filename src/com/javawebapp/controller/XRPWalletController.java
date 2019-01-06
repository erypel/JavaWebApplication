package com.javawebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javawebapp.model.LocalXRPLedger;
import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.SignedTransaction;
import com.javawebapp.service.PodcastService;
import com.javawebapp.service.XRPWalletService;

@Controller
public class XRPWalletController
{
	@Autowired
	XRPWalletService walletService;
	
	@Autowired
	PodcastService podcastService;
	
	@RequestMapping(value = "/xrpWallet", method = RequestMethod.GET)
	public ModelAndView showXRPWallet(HttpServletRequest request, HttpServletResponse response)
	{
		return generateBasicXRPWalletView(request, getUserID(request));
	}
	
	@RequestMapping(value = "/generateDestTag", method = RequestMethod.GET)
	public ModelAndView generateDestTagAndLoadView(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long userID = getUserID(request);
		ModelAndView mav = generateBasicXRPWalletView(request, userID);
		mav.addObject("destTag", walletService.mapDestinationTag(userID));
		return mav;
	}
	
	@RequestMapping(value = "/tip", method = RequestMethod.POST)
	public void tip(HttpServletRequest request, HttpServletResponse response, @RequestParam("podcastID") String podID) throws Exception
	{
		long podcastID = Long.valueOf(podID);
		long podcastOwnerID = podcastService.getPodcastOwnerID(podcastID);
		long userID = getUserID(request);
		Transaction tip = walletService.createXRPTipTransaction(userID, podcastOwnerID, "1.00"); //TODO make tip amount dynamic
		SignedTransaction signedTip = walletService.signTransaction(tip);
	}
	
	public long getUserID(HttpServletRequest request)
	{
		// Get session and user
		HttpSession session = request.getSession();
		long userID = Long.parseLong(session.getAttribute("userId").toString());
		return userID;
	}
	
	public ModelAndView generateBasicXRPWalletView(HttpServletRequest request, long userID)
	{
		// get user's wallet
		LocalXRPLedger wallet = walletService.getWallet(userID);
		
		// TODO add some null checks on wallet
		
		// return the view
		ModelAndView mav = new ModelAndView("xrpWallet");
		mav.addObject("userID", userID);
		mav.addObject("walletID", wallet.getWalletId());
		mav.addObject("balance", wallet.getFunds());
		return mav;
	}
}
