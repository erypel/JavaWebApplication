package com.javawebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javawebapp.model.LocalXRPLedger;
import com.javawebapp.service.XRPWalletService;

@Controller
public class XRPWalletController
{
	@Autowired
	XRPWalletService walletService;
	
	@RequestMapping(value = "/xrpWallet", method = RequestMethod.GET)
	public ModelAndView showRegisterPodcastHome(HttpServletRequest request, HttpServletResponse response)
	{
		// Get session and user
		HttpSession session = request.getSession();
		long userID = Long.parseLong(session.getAttribute("userId").toString());
		
		// get user's wallet
		LocalXRPLedger wallet = walletService.getWallet(userID); 
		
		//TODO add some null checks on wallet
		
		// return the view
		ModelAndView mav = new ModelAndView("xrpWallet");
		mav.addObject("userID", userID);
		mav.addObject("walletID", wallet.getWalletId());
		mav.addObject("balance", wallet.getFunds());
		return mav;
	}
}
