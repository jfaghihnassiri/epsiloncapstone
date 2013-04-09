package edu.ucsb.cs.epsilon.ucsb360.test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import java.sql.SQLException;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.FriendManager;
import edu.ucsb.cs.epsilon.ucsb360.User;

/**
 * Test class for FriendManager class
 * 
 * @author Max Hinson
 */
public class FriendManagerTest {
	
	/**
	 * Set up by connecting and logging in
	 * 
	 * @author Max Hinson
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseManager.Connect();
		DatabaseManager.createUsr("friend1", "Friend McFriend", "12/12/1212", "Male");
		DatabaseManager.createUsr("friend2", "Friends McFriends", "11/11/1111", "Female");
		User.logIn("teamepsilon");
	}
	
	/**
	 * Tear down by disconnecting and logging out
	 * 
	 * @author Max Hinson
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DatabaseManager.deleteUsr("friend1");
		DatabaseManager.deleteUsr("friend2");
		DatabaseManager.Disconnect();
	}
	
	/**
	 * Main test routine
	 * 
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.FriendManager#addFriend(java.lang.String)}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.FriendManager#deleteFriend(java.lang.String)}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.FriendManager#getFriends(java.lang.String)}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.FriendManager#getNumFriends()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.FriendManager#getName(java.lang.String)}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.FriendManager#getNumShares(java.lang.String)}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.FriendManager#incNumShares(java.lang.String)}.
	 * 
	 * @author Max Hinson
	 */
	@Test
	public void mainTest() {

		// Add a test friend to the database first, then pull friends
		try {
			DatabaseManager.addFriend("friend1");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		FriendManager.populateFriends();

		assertEquals(1, FriendManager.getNumFriends());
		assertEquals(1, FriendManager.incNumShares("friend1"));

		// Add a second friend on the fly
		FriendManager.addFriend("friend2", "Friends McFriends");
		
		assertEquals(2, FriendManager.getNumFriends());
		FriendManager.incNumShares("friend2");
		assertEquals(1, FriendManager.getNumShares("friend2"));
		
		// Delete a friend
		FriendManager.deleteFriend("friend2");
		
		assertEquals(1, FriendManager.getNumFriends());
		FriendManager.incNumShares("friend1");
		assertEquals(2, FriendManager.getNumShares("friend1"));
		
		// Delete the last friend
		FriendManager.deleteFriend("friend1");
		
		assertEquals(0, FriendManager.getNumFriends());
	}

}