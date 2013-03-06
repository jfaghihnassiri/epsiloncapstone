package edu.ucsb.cs.epsilon.ucsb360.test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

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
	 * Set up by logging in
	 * 
	 * @author Max Hinson
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		User.logIn("teamepsilon");
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
		DatabaseManager.addFriend("friend1");
		FriendManager.populateFriends();
		
		assertEquals(1, FriendManager.getNumFriends());
		assertEquals(1, FriendManager.incNumShares("friend1"));

		// Add a second friend on the fly
		FriendManager.addFriend("friend2");
		
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