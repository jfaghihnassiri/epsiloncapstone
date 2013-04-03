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
		DatabaseManager.createUsr(user[0], user[1], user[2], user[3]);
		User.logIn(user[0]);
		DatabaseManager.addFriend(friend[1]);
		DatabaseManager.createTar(target[0], target[1], target[2]);
		DatabaseManager.createAug(target[0], aug[2], aug[3], Integer.parseInt(aug[7]),
				Integer.parseInt(aug[8]), Double.parseDouble(aug[9]));
		
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		// Delete dummy data sets
		DatabaseManager.deleteAug(target[0], Integer.parseInt(aug[1]));
		DatabaseManager.deleteTar(target[0]);
		DatabaseManager.delFriend(friend[1]);
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
			u = DatabaseManager.getUsr(user[0]);
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
			u = DatabaseManager.getUsr(user[0]);
			views = Integer.parseInt(u[4]);
			DatabaseManager.incUsrAugsShared();
			u = DatabaseManager.getUsr(user[0]);
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
			u = DatabaseManager.getUsr(user[0]);
			created = Integer.parseInt(u[5]);
			DatabaseManager.incUsrAugsCreated();
			u = DatabaseManager.getUsr(user[0]);
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
			t = DatabaseManager.getTar(target[0]);
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
		
		try {
			ResultSet rs = DatabaseManager.getAugs(target[0]);
			rs.next();
			assertEquals(aug[0], rs.getString(1));
			assertEquals(aug[1], rs.getString(2));
			assertEquals(aug[2], rs.getString(3));
			assertEquals(aug[3], rs.getString(4));
			assertEquals(aug[4], rs.getString(5));
			assertEquals(aug[5], rs.getInt(6));
			assertEquals(aug[6], rs.getInt(7));
			assertEquals(aug[7], rs.getInt(8));
			assertEquals(aug[8], rs.getInt(9));
			assertEquals(aug[9], rs.getDouble(10));
			rs.close();
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incAugViews(java.lang.String)}.
	 */
	@Test
	public void testIncAugViews() {
		
		int views = -1;
		try {
			ResultSet rs = DatabaseManager.getAugs(target[0]);
			views = rs.getInt(6);
			DatabaseManager.incAugViews(target[0], Integer.parseInt(aug[1]));
			rs = DatabaseManager.getAugs(target[0]);
			assertEquals(views+1, rs.getInt(6));
			rs.close();
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incAugLikes(java.lang.String)}.
	 */
	@Test
	public void testIncAugLikes() {
		
		int likes = -1;
		try {
			ResultSet rs = DatabaseManager.getAugs(target[0]);
			likes = rs.getInt(7);
			DatabaseManager.incAugLikes(target[0], Integer.parseInt(aug[1]));
			rs = DatabaseManager.getAugs(target[0]);
			assertEquals(likes+1, rs.getInt(7));
			rs.close();
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#createFriend()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#getFriend()}
	 */
	@Test
	public void testCreateFriend() {

		try {
			ResultSet rs = DatabaseManager.getFriends();
			rs.next();
			assertEquals(friend[0], rs.getString(1));
			assertEquals(friend[1], rs.getString(2));
			assertEquals(friend[2], rs.getString(3));
			rs.close();
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incFriendNumShares(java.lang.String)}.
	 */
	@Test
	public void testIncFriendNumShares() {

		int shares = -1;
		try {
			ResultSet rs = DatabaseManager.getFriends();
			shares = rs.getInt(3);
			DatabaseManager.incFriendNumShares(friend[1]);
			rs = DatabaseManager.getFriends();
			assertEquals(shares+1, rs.getInt(3));
			rs.close();
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		
	}
	
}
