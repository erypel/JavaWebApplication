package com.javawebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javawebapp.dao.impl.UserDaoImpl;
import com.javawebapp.dao.impl.WalletDaoImpl;
import com.javawebapp.model.User;
import com.javawebapp.model.Wallet;

@Controller
public class HomeController
{
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response)
	{
		/*
		UserDaoImpl userDao = new UserDaoImpl();
		
		User user = userDao.getUser(userName);
		WalletDaoImpl walletDao = new WalletDaoImpl();
		Wallet wallet = walletDao.getWallet(user.getId());
		
		
		mav.addObject("firstname", user.getUserName());
		mav.addObject("walletId", wallet.getWalletId());
		mav.addObject("publicKey", wallet.getPublicKey());
		
		*/
		ModelAndView mav = new ModelAndView("welcome");
		return mav;
	}
}
