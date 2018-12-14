package test.java.unit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.javawebapp.dao.PodcastDao;
import com.javawebapp.dao.UserDao;
import com.javawebapp.dao.impl.PodcastDaoImpl;
import com.javawebapp.dao.impl.UserDaoImpl;
import com.javawebapp.model.Podcast;
import com.javawebapp.util.JavaWebAppUtils;

public class TestPodcastDao
{
	public final static String TEST_PODCAST = "test_podcast";
	public final static String TEST_DESCRIPTION = "test_description";
	public final static String TEST_PATH = "test_path";
	
	public final static String TEST_USERNAME = "testUsername";
	public final static String TEST_PASSWORD = "testPassword";
	public final static String TEST_EMAIL = "testEmail@test.com";
	
	@Test
	public void testGetUserLong() {
		//Podcasts have a foreign key dependency on a user that is an owner
		UserDao userDao = new UserDaoImpl();
		Long ownerId = JavaWebAppUtils.generateUniqueId();
		userDao.insertUser(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL, ownerId);
		
		PodcastDao podcastDao = new PodcastDaoImpl();
		
		Podcast podcastToInsert = new Podcast("test_podcast", "test_description", "test_path", ownerId);
		Long id = podcastToInsert.getID();
		podcastDao.insertPodcast(podcastToInsert);
		Podcast podcast = podcastDao.getPodcast(id);
		assertTrue(podcast != null);
		assertTrue(podcast.getEpisodeName().equals(TEST_PODCAST));
		assertTrue(podcast.getDescription().equals(TEST_DESCRIPTION));
		assertTrue(id == podcast.getID());
		assertTrue(podcast.getPath().equals(TEST_PATH));
		podcastDao.deletePodcast(id);
		userDao.deleteUser(ownerId);
	}
}
