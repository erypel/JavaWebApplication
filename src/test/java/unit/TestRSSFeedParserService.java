package test.java.unit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.javawebapp.model.RSSFeed;
import com.javawebapp.service.RSSFeedParserService;

public class TestRSSFeedParserService
{
	@Test
	public void testReadFeed()
	{
		//this file is saved here:
		// JavaWebApplication\\src\\test\\java\\unit\\testFiles\\article.rss
		RSSFeedParserService parser = new RSSFeedParserService("http://www.vogella.com/article.rss");
		RSSFeed feed = parser.readFeed();
		assertTrue(true);		
	}
}
