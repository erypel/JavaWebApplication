package com.javawebapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javawebapp.model.Login;
import com.javawebapp.model.User;
import com.javawebapp.model.Wallet;
import com.javawebapp.service.UserService;
import com.javawebapp.service.WalletService;

@Controller
public class LoginController
{
	@Autowired
	UserService userService;
	
	@Autowired
	WalletService walletService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("/resources/jsp/login.jsp");
		mav.addObject("login", new Login());
		return mav;
	}
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login)
	{
		ModelAndView mav = null;
		
		//getUser can return null
		User user = userService.getUser(login.getUsername(), login.getPassword());
		
		if(user != null)
		{
			Wallet wallet = walletService.getWallet(user.getId()); 
			
			// create a wallet if the user does not have one for some reason
			// this could occur if the user was created before wallet was implemented
			if(wallet == null)
				wallet = walletService.register(user.getId());
			
			mav = new ModelAndView("/resources/jsp/welcome.jsp");
			mav.addObject("firstname", user.getUserName());
			mav.addObject("walletId", wallet.getWalletId());
			mav.addObject("publicKey", wallet.getPublicKey());
			
			//Add a cookie
			HttpSession session = request.getSession();
			session.setAttribute("user", user.getUserName());
			session.setAttribute("userId", String.valueOf(user.getId()));
			// setting session to expire in 30 minutes
			session.setMaxInactiveInterval(30 * 60);
			Cookie cookie = new Cookie("user", user.getUserName());
			response.addCookie(cookie);
		}
		else
		{
			mav = new ModelAndView("/resources/jsp/login.jsp");
			mav.addObject("message", "Username or Password is wrong!!");
		}
		
		return mav;
	}
}
