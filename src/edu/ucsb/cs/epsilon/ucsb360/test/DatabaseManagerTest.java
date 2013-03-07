package edu.ucsb.cs.epsilon.ucsb360.test;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.User;

import java.sql.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Test class for DatabaseManager class
 * 
 * @author Max Hinson
 * @see DatabaseManager
 */
public class DatabaseManagerTest {

	private static String[] user = {"myusername", "Test McTest", "12/31/1999", "Male", "0", "0"};
	private static String[] friend = {user[0], "friend1", "0"};
	private static String[] target = {"tid", "1/1/0000", user[0]};
	private static String[] aug = {target[0], "1", "1/1/1111", user[0], "hello world",
								"0", "0", "10", "10", "200"};
	
	private static int userCols = user.length;
	private static int friendCols = friend.length;
	private static int targetCols = target.length;
	private static int augCols = aug.length;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// Create dummy data sets
		DatabaseManager.createUser(user[0], user[1], user[2], user[3]);
		User.logIn(user[0]);
		DatabaseManager.addFriend(friend[1]);
		DatabaseManager.createTarget(target[0], target[1], target[2]);
		DatabaseManager.createAug(target[0], aug[2], Integer.parseInt(aug[7]),
				Integer.parseInt(aug[8]), Double.parseDouble(aug[9]));
		
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		// Delete dummy data sets
		DatabaseManager.deleteAug(target[0], Integer.parseInt(aug[1]));
		DatabaseManager.deleteTarget(target[0]);
		DatabaseManager.deleteFriend(friend[1]);
		DatabaseManager.deleteUsr(user[0]);
		
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#createUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#getUser()}
	 */
	@Test
	public void testCreateUser() {
		String[] u = new String[userCols];
		try {
			u = DatabaseManager.getUser(user[0]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		for(int i = 0; i < userCols; i++)
			assertEquals(u[i], user[i]);
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incUserAugsShared(java.lang.String)}.
	 */
	@Test
	public void testIncUserAugsShared() {
		String[] u = new String[userCols];
		int views = -1;
		try {
			u = DatabaseManager.getUser(user[0]);
			views = Integer.parseInt(u[4]);
			DatabaseManager.incUserAugsShared();
			u = DatabaseManager.getUser(user[0]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		assertEquals(views+1, Integer.parseInt(u[4]));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incUserAugsCreated(java.lang.String)}.
	 */
	@Test
	public void testIncUserAugsCreated() {
		String[] u = new String[userCols];
		int created = -1;
		try {
			u = DatabaseManager.getUser(user[0]);
			created = Integer.parseInt(u[5]);
			DatabaseManager.incUserAugsCreated();
			u = DatabaseManager.getUser(user[0]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		assertEquals(created+1, Integer.parseInt(u[5]));
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#createTarget(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#getTarget()}.
	 */
	@Test
	public void testCreateTarget() {
		String[] t = new String[targetCols];
		try {
			t = DatabaseManager.getTarget(target[0]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		for(int i = 0; i < targetCols; i++)
			assertEquals(t[i], target[i]);
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#createAug(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#getAug()}.
	 */
	@Test
	public void testCreateAug() {
		String[] a = new String[augCols];
		try {
			a = DatabaseManager.getAugs(target[0], aug[1]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		for(int i = 0; i < augCols; i++)
			assertEquals(a[i], aug[i]);
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incAugViews(java.lang.String)}.
	 */
	@Test
	public void testIncAugViews() {
		String[] a = new String[augCols];
		int views = -1;
		try {
			a = DatabaseManager.getAug(target[0], aug[1]);
			views = Integer.parseInt(a[5]);
			DatabaseManager.incAugViews(target[0], aug[1]);
			a = DatabaseManager.getAug(target[0], aug[1]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		assertEquals(views+1, Integer.parseInt(a[5]));
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incAugLikes(java.lang.String)}.
	 */
	@Test
	public void testIncAugLikes() {
		String[] a = new String[augCols];
		int likes = -1;
		try {
			a = DatabaseManager.getAug(target[0], aug[1]);
			likes = Integer.parseInt(a[6]);
			DatabaseManager.incAugViews(target[0], aug[1]);
			a = DatabaseManager.getAug(target[0], aug[1]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		assertEquals(likes+1, Integer.parseInt(a[6]));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#createFriend()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#getFriend()}
	 */
	@Test
	public void testCreateFriend() {
		String[] f = new String[friendCols];
		try {
			f = DatabaseManager.getFriend(friend[1]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		for(int i = 0; i < friendCols; i++)
			assertEquals(f[i], user[i]);
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incFriendNumShares(java.lang.String)}.
	 */
	@Test
	public void testIncFriendNumShares() {
		String[] f = new String[friendCols];
		int shares = -1;
		try {
			f = DatabaseManager.getFriend(friend[1]);
			shares = Integer.parseInt(f[2]);
			DatabaseManager.incFriendNumShares(friend[1]);
			f = DatabaseManager.getAug(friend[1]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		assertEquals(shares+1, Integer.parseInt(f[2]));
	}
	
}
