package com.javawebapp.service;

import java.io.FileOutputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.javawebapp.model.RSSFeed;
import com.javawebapp.model.RSSFeedMessage;
/**
 * comes from vogella.com
 *
 */
public class RSSFeedWriterService
{
	private String outputFile;
	private RSSFeed rssFeed;
	
	public RSSFeedWriterService(RSSFeed rssFeed, String outputFile)
	{
		this.rssFeed = rssFeed;
		this.outputFile = outputFile;
	}
	
	public void write() throws Exception
	{
		// Create an XMLOutputFactory
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		
		// Create XMLEventWriter
		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(outputFile));
	
		// Create EventFactory
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
	
		// Create and write Start Tag
		StartDocument startDocument = eventFactory.createStartDocument();
		
		eventWriter.add(startDocument);
		
		// Create open tag
		eventWriter.add(end);
		
		StartElement rssStart = eventFactory.createStartElement("", "", "rss");
		eventWriter.add(rssStart);
		eventWriter.add(eventFactory.createAttribute("version", "2.0"));
		eventWriter.add(end);
		
		eventWriter.add(eventFactory.createStartElement("", "", "channel"));
		eventWriter.add(end);
		
		// Write the different nodes
		createNode(eventWriter, "title", rssFeed.getTitle());
		createNode(eventWriter, "link", rssFeed.getLink());
		createNode(eventWriter, "description", rssFeed.getDescription());
		createNode(eventWriter, "language", rssFeed.getLanguage());
		createNode(eventWriter, "copyright", rssFeed.getCopyright());
		createNode(eventWriter, "pubDate", rssFeed.getPublishDate());

		for(RSSFeedMessage entry : rssFeed.getMessages())
		{
			eventWriter.add(eventFactory.createStartElement("", "", "item"));
			eventWriter.add(end);
			createNode(eventWriter, "title", entry.getTitle());
			createNode(eventWriter, "description", entry.getDescription());
			createNode(eventWriter, "link", entry.getLink());
			createNode(eventWriter, "author", entry.getAuthor());
			createNode(eventWriter, "guid", entry.getGuid());
			eventWriter.add(end);
			eventWriter.add(eventFactory.createEndElement("", "", "item"));
			eventWriter.add(end);
		}
		
		// Close up the XML
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndElement("", "", "channel"));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndElement("", "", "rss"));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}
	
	public void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException
	{
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		
		// Create Start node
		StartElement startElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(startElement);
		
		//Create content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		
		// Create End node
		EndElement endElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(endElement);
		eventWriter.add(end);
	}
}
