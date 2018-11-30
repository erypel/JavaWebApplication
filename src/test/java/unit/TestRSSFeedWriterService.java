package test.java.unit;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.Test;

import com.javawebapp.model.RSSFeed;
import com.javawebapp.model.RSSFeedMessage;
import com.javawebapp.service.RSSFeedWriterService;

/**
 * 
 * comes from Vogella.com
 * http://www.vogella.com/tutorials/RSSFeed/article.html
 *
 */
public class TestRSSFeedWriterService
{
	@Test
	public void testWrite()
	{
		// Create the rss feed
		String copyright = "Copyright hold by Lars Vogel";
        String title = "Eclipse and Java Information";
        String description = "Eclipse and Java Information";
        String language = "en";
        String link = "http://www.vogella.com";
        Calendar cal = new GregorianCalendar();
        Date creationDate = cal.getTime();
        SimpleDateFormat date_format = new SimpleDateFormat(
                "EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
        String pubdate = date_format.format(creationDate);
        RSSFeed rssFeeder = new RSSFeed(title, link, description, language,
                copyright, pubdate);
        
        // now add one example entry
        RSSFeedMessage feed = new RSSFeedMessage();
        feed.setTitle("RSSFeed");
        feed.setDescription("This is a description");
        feed.setAuthor("nonsense@somewhere.de (Lars Vogel)");
        feed.setGuid("http://www.vogella.com/tutorials/RSSFeed/article.html");
        feed.setLink("http://www.vogella.com/tutorials/RSSFeed/article.html");
        rssFeeder.getMessages().add(feed);

        // now write the file
        RSSFeedWriterService writer = new RSSFeedWriterService(rssFeeder, "./src/test/java/unit/testFiles/writeArticles.rss");
        try {
            writer.write();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
        assertTrue(true);
	}
}
