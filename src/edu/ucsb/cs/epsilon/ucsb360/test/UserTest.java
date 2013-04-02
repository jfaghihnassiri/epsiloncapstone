package edu.ucsb.cs.epsilon.ucsb360.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.User;

/**
 * Test class for the User class
 * 
 * @author Max Hinson
 */
public class UserTest {
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseManager.createUsr("testUser", "Test McTest", "11/11/1111", "Male");
		assertFalse(User.isLoggedIn());
		assertTrue(User.logIn("testUser"));
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		User.logOut();
		assertFalse(User.isLoggedIn());
		DatabaseManager.deleteUsr("testUser");
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#logIn()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#logOut()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#isLoggedIn()}.
	 */
	@Test
	public void testLoggedInStatus() {
		assertTrue(User.isLoggedIn());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getUsername()}.
	 */
	@Test
	public void testUsername() {
		assertEquals("testUser", User.getUsername());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getName()}.
	 */
	@Test
	public void testName() {
		assertEquals("Test McTest", User.getName());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getBirthday()}.
	 */
	@Test
	public void testBirthday() {
		assertEquals("11/11/1111", User.getBirthday());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getGender()}.
	 */
	@Test
	public void testGender() {
		assertEquals("Male", User.getGender());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getnumAugsCreated()}.
	 */
	@Test
	public void testnumAugsCreated() {
		assertEquals(0, User.getNumAugsCreated());
		User.incNumAugsCreated();
		assertEquals(1, User.getNumAugsCreated());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getnumAugsShared()}.
	 */
	@Test
	public void testnumAugsShared() {
		assertEquals(0, User.getNumAugsShared());
		User.incNumAugsShared();
		User.incNumAugsShared();
		assertEquals(2, User.getNumAugsShared());
	}

}