package test.java.unit;

import java.util.ArrayList;
import com.javawebapp.dao.UserDao;
import com.javawebapp.dao.impl.UserDaoImpl;
import com.javawebapp.model.User;
import com.javawebapp.util.JavaWebAppUtils;

//TODO probably use some sort of testing framework. this is fine for now
// TODO will probably use Mockito
public class TestUserDao {
	public final static String TEST_USERNAME = "testUsername";
	public final static String TEST_PASSWORD = "testPassword";
	public final static String TEST_EMAIL = "testEmail@test.com";

	public static void main(String[] args) {
		System.out.println("===Running unit tests for UserDaoImpl.java===");
		System.out.println("Test insertUser(String, String, String, Long)");
		System.out.println("Test successful: " + testInsertUserStringStringStringLong());
		System.out.println("Test insertUser(String, String, String)");
		System.out.println("Test successful: " + testInsertUserStringStringString());
		System.out.println("Test updateUserPassword(Long)");
		System.out.println("Test successful: " + testUpdateUserPasswordLong());
		System.out.println("Test updateUserPassword(String)");
		System.out.println("Test successful: " + testUpdateUserPasswordString());
		System.out.println("Test updateUsernameLong(Long)");
		System.out.println("Test successful: " + testUpdateUsernameLong());
		System.out.println("Test updateUsername(String)");
		System.out.println("Test successful: " + testUpdateUsernameString());
		System.out.println("Test deleteUserLong(Long)");
		System.out.println("Test successful: " + testDeleteUserLong());
		System.out.println("Test deleteUser(String)");
		System.out.println("Test successful: " + testDeleteUserString());
		System.out.println("Test getUser(String)");
		System.out.println("Test successful: " + testGetUserString());
		System.out.println("Test getUser(Long)");
		System.out.println("Test successful: " + testGetUserLong());
		System.out.println("Test getUser(String, String)");
		System.out.println("Test successful: " + testGetUserUserNamePassword());
		System.out.println("Test getAllUsers()");
		System.out.println("Test successful: " + testGetAllUsers());
		System.out.println("===Finished running unit tests for UserDaoImpl.java===");
	}

	public static boolean testInsertUserStringStringStringLong() {
		UserDao userDao = new UserDaoImpl();
		Long id = JavaWebAppUtils.generateUniqueId();
		userDao.insertUser(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL, id);
		if (userDao.getUser(id) != null) {
			userDao.deleteUser(id);
			return true;
		}

		return false;
	}

	public static boolean testInsertUserStringStringString() {
		UserDao userDao = new UserDaoImpl();
		userDao.insertUser(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL);
		if (userDao.getUser(TEST_USERNAME) != null) {
			userDao.deleteUser(TEST_USERNAME);
			return true;
		}

		return false;
	}

	public static boolean testUpdateUserPasswordLong() {
		UserDao userDao = new UserDaoImpl();
		Long id = JavaWebAppUtils.generateUniqueId();
		String newPassword = "testNewPassword";
		userDao.insertUser(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL, id);
		userDao.updateUserPassword(id, newPassword);
		User user = userDao.getUser(id);
		if (user.getPassword().equals(newPassword)) {
			userDao.deleteUser(id);
			return true;
		}
		return false;
	}

	public static boolean testUpdateUserPasswordString() {
		UserDao userDao = new UserDaoImpl();
		Long id = JavaWebAppUtils.generateUniqueId();
		String newPassword = "testNewPassword";
		userDao.insertUser(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL, id);
		userDao.updateUserPassword(TEST_USERNAME, newPassword);
		User user = userDao.getUser(TEST_USERNAME);
		if (user.getPassword().equals(newPassword)) {
			userDao.deleteUser(TEST_USERNAME);
			return true;
		}
		return false;
	}

	public static boolean testUpdateUsernameLong() {
		UserDao userDao = new UserDaoImpl();
		Long id = JavaWebAppUtils.generateUniqueId();
		String newUsername = "testNewUsername";
		userDao.insertUser(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL, id);
		userDao.updateUsername(id, newUsername);
		User user = userDao.getUser(id);
		if (user.getUserName().equals(newUsername)) {
			userDao.deleteUser(id);
			return true;
		}
		return false;
	}

	public static boolean testUpdateUsernameString() {
		UserDao userDao = new UserDaoImpl();
		Long id = JavaWebAppUtils.generateUniqueId();
		String newUsername = "testNewUsername";
		userDao.insertUser(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL, id);
		userDao.updateUsername(TEST_USERNAME, newUsername);
		User user = userDao.getUser(newUsername);
		if (user.getUserName().equals(newUsername)) {
			userDao.deleteUser(id);
			return true;
		}
		return false;
	}

	public static boolean testDeleteUserLong() {
		UserDao userDao = new UserDaoImpl();
		Long id = JavaWebAppUtils.generateUniqueId();
		userDao.insertUser(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL, id);
		userDao.deleteUser(id);
		if (userDao.getUser(id) == null) {
			return true;
		}
		return false;
	}

	public static boolean testDeleteUserString() {
		UserDao userDao = new UserDaoImpl();
		Long id = JavaWebAppUtils.generateUniqueId();
		userDao.insertUser(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL, id);
		userDao.deleteUser(TEST_USERNAME);
		if (userDao.getUser(TEST_USERNAME) == null) {
			return true;
		}
		return false;
	}

	public static boolean testGetUserUserNamePassword() {
		UserDao userDao = new UserDaoImpl();
		Long id = JavaWebAppUtils.generateUniqueId();
		userDao.insertUser(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL, id);
		User user = userDao.getUser(TEST_USERNAME, TEST_PASSWORD);
		if (user.getUserName().equals(TEST_USERNAME) && user.getPassword().equals(TEST_PASSWORD)
				&& user.getEmail().equals(TEST_EMAIL) && user.getId() == id) {
			userDao.deleteUser(TEST_USERNAME);
			return true;
		}
		return false;
	}

	public static boolean testGetUserString() {
		UserDao userDao = new UserDaoImpl();
		Long id = JavaWebAppUtils.generateUniqueId();
		userDao.insertUser(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL, id);
		User user = userDao.getUser(TEST_USERNAME);
		if (user.getUserName().equals(TEST_USERNAME) && user.getPassword().equals(TEST_PASSWORD)
				&& user.getEmail().equals(TEST_EMAIL) && user.getId() == id) {
			userDao.deleteUser(TEST_USERNAME);
			return true;
		}
		return false;
	}

	public static boolean testGetUserLong() {
		UserDao userDao = new UserDaoImpl();
		Long id = JavaWebAppUtils.generateUniqueId();
		userDao.insertUser(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL, id);
		User user = userDao.getUser(id);
		if (user.getUserName().equals(TEST_USERNAME) && user.getPassword().equals(TEST_PASSWORD)
				&& user.getEmail().equals(TEST_EMAIL) && user.getId() == id) {
			userDao.deleteUser(id);
			return true;
		}
		return false;
	}

	// TODO won't really be able to test until Mockito is up and running
	public static boolean testGetAllUsers() {
		UserDao userDao = new UserDaoImpl();
		Long id1 = JavaWebAppUtils.generateUniqueId();
		Long id2 = JavaWebAppUtils.generateUniqueId();
		// in case we somehow miraculously get two identical values
		while (id1 == id2) {
			id1 = JavaWebAppUtils.generateUniqueId();
		}
		userDao.insertUser(TEST_USERNAME + "_1", TEST_PASSWORD, TEST_EMAIL, id1);
		userDao.insertUser(TEST_USERNAME + "_2", TEST_PASSWORD, TEST_EMAIL, id2);
		User user1 = userDao.getUser(id1);
		User user2 = userDao.getUser(id2);
		ArrayList<User> users = (ArrayList<User>) userDao.getAllUsers();

		User copyUser1 = users.get(1);
		User copyUser2 = users.get(0);

		if (copyUser1.getId() == user1.getId() && copyUser2.getId() == user2.getId()) {
			userDao.deleteUser(id1);
			userDao.deleteUser(id2);
			return true;
		}

		return false;
	}
}
