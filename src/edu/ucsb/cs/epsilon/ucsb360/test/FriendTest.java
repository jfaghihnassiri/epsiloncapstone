package edu.ucsb.cs.epsilon.ucsb360.test;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.ucsb.cs.epsilon.ucsb360.Friend;

/**
 * Test class for the Friend class
 * 
 * @author Max Hinson
 */
public class FriendTest {

	private String[] f = {"username", "Test McTest", "0"};
	Friend friend = new Friend(f[0], f[1], Integer.parseInt(f[2]));
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Friend#getUsername()}.
	 */
	@Test
	public void testGetUsername() {
		assertEquals(f[0], friend.getUsername());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Friend#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals(f[1], friend.getName());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Friend#getNumShares()}.
	 */
	@Test
	public void testGetNumShares() {
		assertEquals(f[2], friend.getNumShares());
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Friend#getNumShares()}.
	 */
	@Test
	public void testIncNumShares() {
		assertEquals(Integer.parseInt(f[2]) + 1, friend.incNumShares());
	}

}
