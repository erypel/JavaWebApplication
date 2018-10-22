package com.javawebapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;

import com.javawebapp.logging.CustomConfigurationFactory;

public class App
{
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		// start a factory
		CustomConfigurationFactory cfgFactory = new CustomConfigurationFactory();
		
		ConfigurationSource configurationSource = new ConfigurationSource(new FileInputStream(new File("C:\\Users\\Evan\\workspace\\JavaWebApplication\\output\\logs\\emptyConfiguration.xml")));
		LoggerContext context = new LoggerContext("JavaWebAppContext");
		
		// build the configuration
		Configuration configuration = cfgFactory.getConfiguration(context, configurationSource);
	
		context.start(configuration);
		
		Logger logger = context.getLogger("com");
		
		// LogEvent of DEBUG message
    	logger.log(Level.FATAL, "Logger Name :: "+logger.getName()+" :: Passed Message ::");
 
    	// LogEvent of Error message for Logger configured as FATAL
    	logger.log(Level.ERROR, "Logger Name :: "+logger.getName()+" :: Not Passed Message ::");
 
    	// LogEvent of ERROR message that would be handled by Root
    	logger.getParent().log(Level.ERROR, "Root Logger :: Passed Message As Root Is Configured For ERROR Level messages");
	}
	
}
