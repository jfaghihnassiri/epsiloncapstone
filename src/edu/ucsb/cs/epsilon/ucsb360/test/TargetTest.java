package edu.ucsb.cs.epsilon.ucsb360.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.Target;

/**
 * Test class for the Target class
 * 
 * @author Max Hinson
 * @see Target
 */
public class TargetTest {

	private static Object[] augmentation = {"testUser", "11/11/1111", "teamepsilon",
		"hello world", 100, 100, 10, 10, 200, "qualcomm.com"};
	private static String[] target = {"tid", "1/1/0000", "teamepsilon"};
	private static Target t;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseManager.createTar(target[0], target[1], target[2]);
		t = new Target(target[0]);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DatabaseManager.deleteTar(target[0]);
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getId()}.
	 */
	@Test
	public void testId() {
		assertEquals(target[0], t.getId());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getDate()}.
	 */
	@Test
	public void testDate() {
		assertEquals(target[1], t.getDate());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getCreator()}.
	 */
	@Test
	public void testCreator() {
		assertEquals(target[2], t.getCreator());
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getNumAugs()}.
	 */
	@Test
	public void testNumAugs() {
		assertEquals(1, t.getNumAugs());
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugId()}.
	 */
	@Test
	public void testAugId() {
		assertEquals(1, t.getAugId(0));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugDate()}.
	 */
	@Test
	public void testAugDate() {
		assertEquals(1, t.getAugDate(0));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugCreator()}.
	 */
	@Test
	public void testAugCreator() {
		assertEquals(1, t.getAugCreator(0));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugMessage()}.
	 */
	@Test
	public void testAugMessage() {
		assertEquals(1, t.getAugMessage(0));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugViews()}.
	 */
	@Test
	public void testAugViews() {
		assertEquals(1, t.getAugViews(0));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugLikes()}.
	 */
	@Test
	public void testAugLikes() {
		assertEquals(1, t.getAugLikes(0));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugXPos()}.
	 */
	@Test
	public void testAugXPos() {
		assertEquals(1, t.getAugXPos(0));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugYPos()}.
	 */
	@Test
	public void testAugYPos() {
		assertEquals(1, t.getAugYPos(0));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugSize()}.
	 */
	@Test
	public void testAugSize() {
		assertEquals(1, t.getAugSize(0));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugUrl()}.
	 */
	@Test
	public void testAugUrl() {
		assertEquals(1, t.getAugUrl(0));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getIncAugViews()}.
	 */
	@Test
	public void testIncAugViews() {
		assertEquals(1, t.incAugViews(0));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getIncAugLikes()}.
	 */
	@Test
	public void testIncAugLikes() {
		assertEquals(1, t.incAugLikes(0));
	}
	
}