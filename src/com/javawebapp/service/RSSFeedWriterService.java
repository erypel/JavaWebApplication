package com.javawebapp.service;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.javawebapp.model.RSSFeed;
import com.javawebapp.model.RSSFeedMessage;

/**
 * comes from vogella.com
 *
 */
//TODO this isn't a service
public class RSSFeedWriterService
{
	private String outputFile;
	private RSSFeed rssFeed;
	
	public RSSFeedWriterService(RSSFeed rssFeed, String outputFile)
	{
		this.rssFeed = rssFeed;
		this.outputFile = outputFile;
	}
	
	public void deleteMessage(RSSFeedMessage message)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(outputFile);
		Node root = document.getFirstChild(); // This will be the <rss> node
		NodeList rootChildren = root.getChildNodes();
		Node channel = null; // This will be the <channel> node
		for(int i = rootChildren.getLength() - 1; i >= 0; i--) // start from the bottom since that's where the items are
		{
			Node node = rootChildren.item(i);
			if("channel" == node.getNodeName())
			{
				channel = node;
				break;
			}
		}
		NodeList channelChildren = channel.getChildNodes();
		for(int i = 0; i < channelChildren.getLength(); i++)
		{
			Node node = channelChildren.item(i);
			if(node.hasChildNodes() && node.getNodeName() == "item")
			{
				String guid = node.getLastChild().getTextContent(); // last child should be guid
				if(guid.equals(message.getGuid()))
				{
					channel.removeChild(node);
					break;
				}
			}
		}
		
		DOMSource source = new DOMSource(document);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StreamResult result = new StreamResult(outputFile);
		transformer.transform(source, result);
	}
	
	// TODO this method works, but the xml is not formatted for a person to read
	// next step would be to format the xml neatly
	public void appendMessage(RSSFeedMessage message)
			throws XMLStreamException, ParserConfigurationException, SAXException, IOException, TransformerException
	{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(outputFile);
		Node root = document.getFirstChild(); // This will be the <rss> node
		NodeList rootChildren = root.getChildNodes();
		Node channel = null; // This will be the <channel> node
		for(int i = 0; i < rootChildren.getLength(); i++)
		{
			Node node = rootChildren.item(i);
			if("channel" == node.getNodeName())
			{
				channel = node;
				break;
			}
		}
		
		Node itemNode = document.createElement("item");
		
		Node titleNode = document.createElement("title");
		titleNode.appendChild(document.createTextNode((message.getTitle())));
		Node descriptionNode = document.createElement("description");
		descriptionNode.appendChild(document.createTextNode((message.getDescription())));
		Node linkNode = document.createElement("link");
		linkNode.appendChild(document.createTextNode((message.getLink())));
		Node authorNode = document.createElement("author");
		authorNode.appendChild(document.createTextNode((message.getAuthor())));
		Node guidNode = document.createElement("guid");
		guidNode.appendChild(document.createTextNode((message.getGuid())));
		
		itemNode.appendChild(titleNode);
		itemNode.appendChild(descriptionNode);
		itemNode.appendChild(linkNode);
		itemNode.appendChild(authorNode);
		itemNode.appendChild(guidNode);
		
		channel.appendChild(itemNode);
		
		DOMSource source = new DOMSource(document);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StreamResult result = new StreamResult(outputFile);
		transformer.transform(source, result);
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
		
		// Create content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		
		// Create End node
		EndElement endElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(endElement);
		eventWriter.add(end);
	}
}
