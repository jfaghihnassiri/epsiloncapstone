/**
 * 
 */
package edu.ucsb.cs.epsilon.ucsb360.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.Target;

/**
 * @author Max Hinson
 */
public class TargetTest {

	private static String[] target = {"tid", "ttype", "1/1/0000", "teamepsilon", "this is a message", "0"};
	private static Target t;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseManager.createTarget(target[0], target[1], target[2], target[3], target[4]);
		t = new Target(target[0]);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DatabaseManager.deleteTarget(target[0]);
	}

	@Test
	public void testId() {
		assertEquals(target[0], t.getId());
	}

	@Test
	public void testType() {
		assertEquals(target[1], t.getType());
	}
	
	@Test
	public void testDate() {
		assertEquals(target[2], t.getDate());
	}
	
	@Test
	public void testCreator() {
		assertEquals(target[3], t.getCreator());
	}
	
	@Test
	public void testMessage() {
		assertEquals(target[4], t.getMessage());
	}
	
	@Test
	public void testViews() {
		assertEquals(Integer.parseInt(target[5]), t.getViews());
	}
	
}