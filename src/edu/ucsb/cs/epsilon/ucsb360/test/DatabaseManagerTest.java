package edu.ucsb.cs.epsilon.ucsb360.test;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import java.sql.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Max Hinson
 */
public class DatabaseManagerTest {

	private static final int userCols = 7;
	private static final int targetCols = 6;
	private static String[] user = {"tuser", "Test McTest", "12/31/1999", "Male", "0", "0", "0"};
	private static String[] target = {"tid", "ttype", "1/1/0000", "tuser", "this is a message", "0"};

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		DatabaseManager.createUser(user[0], user[1], user[2], user[3]);
		DatabaseManager.createTarget(target[0], target[1], target[2], target[3], target[4]);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void tearDown() throws Exception {
		DatabaseManager.deleteTarget(target[0]);
		DatabaseManager.deleteUser(user[0]);
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#initUserData(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
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
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#initAugmentationData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
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
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incrementViews(java.lang.String)}.
	 */
	@Test
	public void testIncrementViews() {
		String[] t = new String[targetCols];
		int views = -1;
		try {
			t = DatabaseManager.getTarget(target[0]);
			views = Integer.parseInt(t[5]);
			DatabaseManager.incrementViews(target[0]);
			t = DatabaseManager.getTarget(target[0]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		assertEquals(views+1, Integer.parseInt(t[5]));
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incrementAugmentationsSeen(java.lang.String)}.
	 */
	@Test
	public void testIncrementTargetsSeen() {
		String[] t = new String[userCols];
		int seen = -1;
		try {
			t = DatabaseManager.getUser(user[0]);
			seen = Integer.parseInt(t[4]);
			DatabaseManager.incrementTargetsSeen(user[0]);
			t = DatabaseManager.getUser(user[0]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		assertEquals(seen+1, Integer.parseInt(t[4]));
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incrementAugmentationsShared(java.lang.String)}.
	 */
	@Test
	public void testIncrementTargetsShared() {
		String[] t = new String[userCols];
		int shared = -1;
		try {
			t = DatabaseManager.getUser(user[0]);
			shared = Integer.parseInt(t[5]);
			DatabaseManager.incrementTargetsShared(user[0]);
			t = DatabaseManager.getUser(user[0]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		assertEquals(shared+1, Integer.parseInt(t[5]));
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.DatabaseManager#incrementAugmentationsCreated(java.lang.String)}.
	 */
	@Test
	public void testIncrementTargetsCreated() {
		String[] t = new String[userCols];
		int created = -1;
		try {
			t = DatabaseManager.getUser(user[0]);
			created = Integer.parseInt(t[6]);
			DatabaseManager.incrementTargetsCreated(user[0]);
			t = DatabaseManager.getUser(user[0]);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
		assertEquals(created+1, Integer.parseInt(t[6]));
	}

}
