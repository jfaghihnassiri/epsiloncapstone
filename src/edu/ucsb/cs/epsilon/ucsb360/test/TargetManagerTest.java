package edu.ucsb.cs.epsilon.ucsb360.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.TargetManager;

/**
 * Test class for TargetManager
 * 
 * @author Max Hinson
 * @see TargetManager
 */
public class TargetManagerTest {
	
	// Dummy Targets to test against
	private static final String[] target1 = {"id1", "type", "1/1/0000", "teamepsilon", "message", "0"};
	private static final String[] target2 = {"id2", "type2", "12/31/1999", "teamepsilon", "another message", "1"};
	private static final String[] target3 = {"id3", "type3", "12/21/2012", "teamepsilon", "yet another message", "2"};

	// Augmentation URL information
	private static final String url = "https://s3-us-west-1.amazonaws.com/teamepsilon/augmentations/";
	private static final String ext = ".jpg";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// Add new dummy Targets to the database
		DatabaseManager.createTarget(target1[0], target1[1], target1[2], target1[3], target1[4]);
		DatabaseManager.createTarget(target2[0], target2[1], target2[2], target2[3], target2[4]);
		DatabaseManager.createTarget(target3[0], target3[1], target3[2], target3[3], target3[4]);
		
		// Make sure these Targets don't already exist
		assertFalse(TargetManager.checkTarget(target1[0]));
		assertFalse(TargetManager.checkTarget(target2[0]));
		assertFalse(TargetManager.checkTarget(target3[0]));
		
		// Add the Targets
		TargetManager.addTarget(target1[0]);
		TargetManager.addTarget(target2[0]);
		TargetManager.addTarget(target3[0]);
		
		// Make sure the Targets exist
		assertTrue(TargetManager.checkTarget(target1[0]));
		assertTrue(TargetManager.checkTarget(target2[0]));
		assertTrue(TargetManager.checkTarget(target3[0]));
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		// Delete dummy Targets to the database
		DatabaseManager.deleteTarget(target1[0]);
		DatabaseManager.deleteTarget(target2[0]);
		DatabaseManager.deleteTarget(target3[0]);
		
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.TargetManager#getType(java.lang.String)}.
	 */
	@Test
	public void testGetType() {
		assertEquals(target1[1], TargetManager.getType(target1[0]));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.TargetManager#getDate(java.lang.String)}.
	 */
	@Test
	public void testGetDate() {
		assertEquals(target1[2], TargetManager.getDate(target1[0]));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.TargetManager#getCreator(java.lang.String)}.
	 */
	@Test
	public void testGetCreator() {
		assertEquals(target1[3], TargetManager.getCreator(target1[0]));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.TargetManager#getMessage(java.lang.String)}.
	 */
	@Test
	public void testGetMessage() {
		assertEquals(target1[4], TargetManager.getMessage(target1[0]));
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.TargetManager#getViews(java.lang.String)}.
	 */
	@Test
	public void testGetViews() {
		assertEquals(Integer.parseInt(target1[5]), TargetManager.getViews(target1[0]));
	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.TargetManager#getAugmentationUrl(java.lang.String)}.
	 */
	@Test
	public void testGetAugmentationUrl() {
		
		// Make sure we can get a URL for a good Target
		assertEquals(url + target1[0] + ext, TargetManager.getAugmentationUrl(target1[0]));
		
		// Make sure a request for a bad Target fails
		assertEquals(null, TargetManager.getAugmentationUrl("doesnotexist"));
		
	}
	
	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.TargetManager#clearTarget(java.lang.String)}.
	 */
	@Test
	public void testClearTarget() {
		
		// Make sure a Target exists
		assertTrue(TargetManager.checkTarget(target3[0]));
		
		// Remove the Target
		TargetManager.clearTarget(target3[0]);
		
		// Make sure the Target was removed, but other Targets are still there
		assertFalse(TargetManager.checkTarget(target3[0]));
		assertTrue(TargetManager.checkTarget(target1[0]));
		assertTrue(TargetManager.checkTarget(target2[0]));

	}

	/**
	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.TargetManager#clearAllTargets()}.
	 */
	@Test
	public void testClearAllTargets() {
		
		// Make sure Targets exist
		assertTrue(TargetManager.checkTarget(target1[0]));
		assertTrue(TargetManager.checkTarget(target2[0]));
		
		// Remove all Targets
		TargetManager.clearAllTargets();
		
		// Make sure all targets were removed
		assertFalse(TargetManager.checkTarget(target1[0]));
		assertFalse(TargetManager.checkTarget(target2[0]));
		
		// Add a Target back to the list
		TargetManager.addTarget(target1[0]);
		assertTrue(TargetManager.checkTarget(target1[0]));
		
	}

}