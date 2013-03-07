package edu.ucsb.cs.epsilon.ucsb360.test;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.ucsb.cs.epsilon.ucsb360.Augmentation;

/**
 * Test class for the Augmentation class
 * 
 * @author Max Hinson
 * @see Augmentation
 */
public class AugmentationTest {

	private Augmentation augmentation = new Augmentation(1, "11/11/1111", "maxwellhinson",
			"hello world", 100, 100, 10, 10, 200, "qualcomm.com");

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
	public void testGetXPos() {
		assertEquals(10, augmentation.getxPos());
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#getyPos()}.
	 */
	@Test
	public void testGetYPos() {
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
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#incViews()}.
	 */
	@Test
	public void testIncViews() {
		assertEquals(augmentation.getViews() + 1, augmentation.incViews());
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Augmentation#incLikes()}.
	 */
	@Test
	public void testIncLikes() {
		assertEquals(augmentation.getLikes() + 1, augmentation.incLikes());
	}

}
