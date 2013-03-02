package edu.ucsb.cs.epsilon.ucsb360.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ucsb.cs.epsilon.ucsb360.User;

/**
 * Test class for the User class
 * 
 * @author Max Hinson
 */
public class UserTest {

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#logIn()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#logOut()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#isLoggedIn()}.
	 */
	@Test
	public void testLoggedInStatus() {
		assertFalse(User.isLoggedIn());
		User.logIn();
		assertTrue(User.isLoggedIn());
		User.logOut();
		assertFalse(User.isLoggedIn());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getUsername()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#setUsername(java.lang.String)}.
	 */
	@Test
	public void testUsername() {
		User.setUsername("user");
		assertEquals("user", User.getUsername());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getName()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#setName(java.lang.String)}.
	 */
	@Test
	public void testName() {
		User.setName("name");
		assertEquals("name", User.getName());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getBirthday()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#setBirthday(java.lang.String)}.
	 */
	@Test
	public void testBirthday() {
		User.setBirthday("birthday");
		assertEquals("birthday", User.getBirthday());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getGender()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#setGender(java.lang.String)}.
	 */
	@Test
	public void testGender() {
		User.setGender("gender");
		assertEquals("gender", User.getGender());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getNumTargetsSeen()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#setNumTargetsSeen(int)}.
	 */
	@Test
	public void testNumTargetsSeen() {
		User.setNumTargetsSeen(1);
		assertEquals(1, User.getNumTargetsSeen());
	}


	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getNumTargetsCreated()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#setNumTargetsCreated(int)}.
	 */
	@Test
	public void testNumTargetsCreated() {
		User.setNumTargetsCreated(1);
		assertEquals(1, User.getNumTargetsCreated());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#getNumTargetsShared()}.
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.User#setNumTargetsShared(int)}.
	 */
	@Test
	public void testNumTargetsShared() {
		User.setNumTargetsShared(1);
		assertEquals(1, User.getNumTargetsShared());
	}

}
