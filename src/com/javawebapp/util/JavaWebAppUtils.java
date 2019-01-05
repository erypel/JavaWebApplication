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
	public static Object invokeTransactionMethodJavaScript(String function, Object...params) throws ScriptException, IOException, NoSuchMethodException
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		//Using "JavaScript" as script engine doesn't support const in file
		ScriptEngine engine = manager.getEngineByName("nashorn");
		
		//read the script file
		engine.eval(Files.newBufferedReader(Paths.get("C:/Users/Evan/workspace/JavaWebApplication/src/main/webapp/resources/js/RippleAPI/TransactionMethods.js"), StandardCharsets.UTF_8));
		
		Invocable inv = (Invocable) engine;
		// call the function from the script file
		Object obj = inv.invokeFunction(function, params);
		return obj;
	}
}
