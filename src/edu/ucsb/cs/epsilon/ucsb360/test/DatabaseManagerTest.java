package edu.ucsb.cs.epsilon.ucsb360.test;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.User;

import java.sql.*;
import static org.junit.Assert.*;
import org.junit.*;
import javax.sql.rowset.CachedRowSet;

/**
 * Test class for DatabaseManager class
 * 
 * @author Max Hinson
 * @see DatabaseManager
 */
public class DatabaseManagerTest {

	private static String[] user = {"myusername", "Test McTest", "12/31/1999", "Male", "0", "0"};
	private static String[] friend = {user[0], user[0], "0"};
	private static String[] target = {"tid", "1/1/0000", user[0]};
	private static String[] aug = {target[0], "1", "1/1/1111", user[0], "hello world",
								"0", "0", "10", "10", "200"};
	
	private static int userCols = user.length;
	private static int targetCols = target.length;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// Create dummy data sets
		DatabaseManager.Connect();
		assertTrue(DatabaseManager.isConnected());
		DatabaseManager.createUsr(user[0], user[1], user[2], user[3]);
		User.logIn(user[0]);
		DatabaseManager.addFriend(friend[1]);
		DatabaseManager.createTar(target[0], target[1], target[2]);
		int id = DatabaseManager.createAug(target[0], aug[2], aug[4], Integer.parseInt(aug[7]),
				Integer.parseInt(aug[8]), Double.parseDouble(aug[9]));
		aug[1] = String.valueOf(id);
		
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
		DatabaseManager.Disconnect();
		assertFalse(DatabaseManager.isConnected());
		
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#createUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#getUser()}
	 */
	@Test
	public void testCreateUser() {
		
		try {
			String[] u = DatabaseManager.getUsr(user[0]);
			for(int i = 0; i < userCols; i++)
				assertEquals(u[i], user[i]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}

	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incUserAugsShared(java.lang.String)}.
	 */
	@Test
	public void testIncUserAugsShared() {
		
		try {
			String[] u = DatabaseManager.getUsr(user[0]);
			int views = Integer.parseInt(u[5]);
			
			DatabaseManager.incUsrAugsShared();
			
			u = DatabaseManager.getUsr(user[0]);
			assertEquals(views+1, Integer.parseInt(u[5]));
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incUserAugsCreated(java.lang.String)}.
	 */
	@Test
	public void testIncUserAugsCreated() {
		
		try {
			String[] u = DatabaseManager.getUsr(user[0]);
			int created = Integer.parseInt(u[4]);
			
			DatabaseManager.incUsrAugsCreated();
			
			u = DatabaseManager.getUsr(user[0]);
			assertEquals(created+1, Integer.parseInt(u[4]));
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#createTarget(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#getTarget()}.
	 */
	@Test
	public void testCreateTarget() {
		
		try {
			String[] t = DatabaseManager.getTar(target[0]);
			for(int i = 0; i < targetCols; i++)
				assertEquals(t[i], target[i]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}

	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#createAug(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#getAug()}.
	 */
	@Test
	public void testCreateAug() {
		
		try {
			CachedRowSet crs = DatabaseManager.getAugs(target[0]);
			crs.next();
			assertEquals(aug[0], crs.getString(1));
			assertEquals(aug[1], crs.getString(2));
			assertEquals(aug[2], crs.getString(3));
			assertEquals(aug[3], crs.getString(4));
			assertEquals(aug[4], crs.getString(5));
			assertEquals(Integer.parseInt(aug[5]), crs.getInt(6));
			assertEquals(Integer.parseInt(aug[6]), crs.getInt(7));
			assertEquals(Integer.parseInt(aug[7]), crs.getInt(8));
			assertEquals(Integer.parseInt(aug[8]), crs.getInt(9));
			assertTrue(Double.parseDouble(aug[9]) - crs.getDouble(10) < 0.01);
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
		
		try {
			CachedRowSet crs = DatabaseManager.getAugs(target[0]);
			crs.next();
			int views = crs.getInt(6);
			
			DatabaseManager.incAugViews(target[0], Integer.parseInt(aug[1]));
			
			crs = DatabaseManager.getAugs(target[0]);
			crs.next();
			assertEquals(views+1, crs.getInt(6));
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
		
		try {
			CachedRowSet crs = DatabaseManager.getAugs(target[0]);
			crs.next();
			int likes = crs.getInt(7);
			
			DatabaseManager.incAugLikes(target[0], Integer.parseInt(aug[1]));
			
			crs = DatabaseManager.getAugs(target[0]);
			crs.next();
			assertEquals(likes+1, crs.getInt(7));
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
			CachedRowSet crs = DatabaseManager.getFriends();
			crs.next();
			assertEquals(friend[0], crs.getString(1));
			assertEquals(friend[1], crs.getString(2));
			assertEquals(friend[2], crs.getString(3));
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

		try {
			CachedRowSet crs = DatabaseManager.getFriends();
			crs.next();
			int shares = crs.getInt(3);
			
			DatabaseManager.incFriendNumShares(friend[1]);
			
			crs = DatabaseManager.getFriends();
			crs.next();
			assertEquals(shares+1, crs.getInt(3));
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		
	}
	
}