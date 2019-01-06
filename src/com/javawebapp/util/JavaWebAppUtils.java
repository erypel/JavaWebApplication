package com.javawebapp.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.eclipsesource.v8.V8;

public class JavaWebAppUtils
{
	/**
	 * as per wikipedia:
	 * https://en.wikipedia.org/wiki/Universally_unique_identifier#Random_UUID_probability_of_duplicates
	 * [A]fter generating 1 billion UUIDs every second for the next 100 years, the
	 * probability of creating just one duplicate would be about 50%
	 * 
	 * Ours won't be that secure since UUID is 128 bit and long is only 64 bit, but
	 * this is good enough for now
	 * 
	 * @return an instance of UUID as a Long
	 */
	public static Long generateUniqueId()
	{
		return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
	}
	
	/**
	 * This method is designed to be used with the Ripple API since that was build in
	 * Node.js. The purpose of this method is ultimately to invoke the sign, submit, and 
	 * verify methods that the API provides for transactions
	 * 
	 * Possible methods are:
	 * sign()
	 * submit()
	 * verify()
	 * @return
	 * @throws IOException 
	 * @throws ScriptException 
	 * @throws NoSuchMethodException 
	 */
	public static Object invokeTransactionMethodJavaScript(String function, String jsonTx, String secret, Object...params) throws ScriptException, IOException, NoSuchMethodException
	{
		/*
		 * This didn't work for Node.js
		 * 
		ScriptEngineManager manager = new ScriptEngineManager();
		//Using "JavaScript" as script engine doesn't support const in file
		ScriptEngine engine = manager.getEngineByName("nashorn");
		
		//read the script file
		engine.eval(Files.newBufferedReader(Paths.get("C:/Users/Evan/workspace/JavaWebApplication/src/main/webapp/resources/js/RippleAPI/TransactionMethods.js"), StandardCharsets.UTF_8));
		
		Invocable inv = (Invocable) engine;
		// call the function from the script file
		Object obj = inv.invokeFunction(function, params);
		return obj;
		*/
		
		/*
		 * Can't use libraries with J2V8 very easily. 
		 */
		/*
		V8 runtime = V8.createV8Runtime();
		Object st= runtime.executeObjectScript("const RippleAPI = require('ripple-lib').RippleAPI;\r\n" + 
				"const api = new RippleAPI({\r\n" + 
				"	server: \"wss://s.altnet.rippletest.net:51233\" // test server\r\n" + 
				"});\r\n" + 
				"\r\n" + 
				"api.on('error', (errorCode, errorMessage) => {\r\n" + 
				"	  console.log(errorCode + ': ' + errorMessage);\r\n" + 
				"	});\r\n" + 
				"api.on('connected', () => {\r\n" + 
				"	  console.log('connected');\r\n" + 
				"	});\r\n" + 
				"\r\n" + 
				"api.on('disconnected', (code) => {\r\n" + 
				"	// code - [close\r\n" + 
				"	// code](https://developer.mozilla.org/en-US/docs/Web/API/CloseEvent) sent\r\n" + 
				"	// by the server\r\n" + 
				"	// will be 1000 if this was normal closure\r\n" + 
				"	console.log('disconnected, code:', code);\r\n" + 
				"	});\r\n" + 
				"	\r\n" + 
				"api.connect().then(() => {\r\n" + 
				"	  \r\n" + 
				"	}).then(() => {\r\n" + 
				"	  return api.disconnect();\r\n" + 
				"	}).catch(console.error);\r\n" + 
				"\r\n" + 
				"	api.sign("+jsonTx+", "+secret+");\r\n");
				*/
		return null;
	}
}
