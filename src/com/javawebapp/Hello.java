package com.javawebapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Hello
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		// loading the definitions from the given XML file
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		HelloWorldService service = (HelloWorldService) context.getBean("helloWorldService");
		WalletService wallet = (WalletService) context.getBean("walletService");
		String message = service.sayHello();
		long walletId = wallet.getId();
		System.out.println(message + " your wallet id is " + walletId + " and your public key is " + wallet.getPublicKey() + " and your private key is " + wallet.getPrivateKey());
		
		//set a new name
		service.setName("Spring");
		message = service.sayHello();
		System.out.println(message);
	}
}
