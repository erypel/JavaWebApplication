package com.javawebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javawebapp.dao.impl.UserDaoImpl;
import com.javawebapp.dao.impl.WalletDaoImpl;
import com.javawebapp.model.User;
import com.javawebapp.model.Wallet;
import com.javawebapp.service.WalletService;

@Controller
public class HomeController
{
	@Autowired
	WalletService walletService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		Long userId = Long.parseLong(session.getAttribute("userId").toString());
		
		Wallet wallet = walletService.getWallet(userId);
		
		ModelAndView mav = new ModelAndView("welcome");
		
		mav.addObject("firstname", session.getAttribute("user").toString());
		mav.addObject("walletId", wallet.getWalletId());
		mav.addObject("publicKey", wallet.getPublicKey());
		
		return mav;
	}
}
