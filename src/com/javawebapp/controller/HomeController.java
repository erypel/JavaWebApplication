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
public class HomeController
{
	@Autowired
	XRPWalletService walletService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		Long userId = Long.parseLong(session.getAttribute("userId").toString());
		
		LocalXRPLedger wallet = walletService.getWallet(userId);
		
		ModelAndView mav = new ModelAndView("welcome");
		
		mav.addObject("firstname", session.getAttribute("user").toString());
		mav.addObject("walletId", wallet.getWalletId());
		mav.addObject("balance", wallet.getFunds());
		
		return mav;
	}
}
