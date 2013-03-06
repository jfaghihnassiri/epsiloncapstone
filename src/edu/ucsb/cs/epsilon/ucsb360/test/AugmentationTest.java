package edu.ucsb.cs.epsilon.ucsb360.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.ucsb.cs.epsilon.ucsb360.Augmentation;

/**
 * Test class for the Augmentation class
 * 
 * @author Max Hinson
 */
public class AugmentationTest {

	private Augmentation augmentation = new Augmentation(1, "11/11/1111", "maxwellhinson",
			"hello world", 100, 100, 10, 10, 200, "qualcomm.com");
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#getId()}.
	 */
	@Test
	public void testGetId() {
		assertEquals(1, augmentation.getId());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#getDate()}.
	 */
	@Test
	public void testGetDate() {
		assertEquals("11/11/1111", augmentation.getDate());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#getCreator()}.
	 */
	@Test
	public void testGetCreator() {
		assertEquals("maxwellhinson", augmentation.getCreator());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#getMessage()}.
	 */
	@Test
	public void testGetMessage() {
		assertEquals("hello world", augmentation.getMessage());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#getViews()}.
	 */
	@Test
	public void testGetViews() {
		assertEquals(100, augmentation.getViews());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#getLikes()}.
	 */
	@Test
	public void testGetLikes() {
		assertEquals(100, augmentation.getLikes());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#getxPos()}.
	 */
	@Test
	public void testGetxPos() {
		assertEquals(10, augmentation.getxPos());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#getyPos()}.
	 */
	@Test
	public void testGetyPos() {
		assertEquals(10, augmentation.getyPos());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#getSize()}.
	 */
	@Test
	public void testGetSize() {
		assertEquals(200, augmentation.getSize());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#getUrl()}.
	 */
	@Test
	public void testGetUrl() {
		assertEquals("qualcomm.com", augmentation.getUrl());
	}

}
