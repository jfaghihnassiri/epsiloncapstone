//package edu.ucsb.cs.epsilon.ucsb360.test;
//
//import static org.junit.Assert.*;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
//import edu.ucsb.cs.epsilon.ucsb360.Target;
//
///**
// * Test class for the Target class
// * 
// * @author Max Hinson
// * @see Target
// */
//public class TargetTest {
//
//	private static String[] aug = {"testUser", "11/11/1111", "teamepsilon",
//		"hello world", "100", "100", "10", "10", "200", "qualcomm.com"};
//	private static String[] target = {"tid", "1/1/0000", "teamepsilon"};
//	private static Target t;
//	
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		DatabaseManager.Connect();
//		DatabaseManager.createTar(target[0], target[1], target[2]);
//		t = new Target(target[0]);
//		t.createAug(99, aug[1], aug[2], aug[3], Integer.parseInt(aug[4]), Integer.parseInt(aug[5]),
//				Integer.parseInt(aug[6]), Integer.parseInt(aug[7]), Double.parseDouble(aug[8]), aug[9]);
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//		DatabaseManager.deleteTar(target[0]);
//		DatabaseManager.Disconnect();
//	}
//
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getId()}.
//	 */
//	@Test
//	public void testId() {
//		assertEquals(target[0], t.getId());
//	}
//
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getDate()}.
//	 */
//	@Test
//	public void testDate() {
//		assertEquals(target[1], t.getDate());
//	}
//
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getCreator()}.
//	 */
//	@Test
//	public void testCreator() {
//		assertEquals(target[2], t.getCreator());
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getNumAugs()}.
//	 */
//	@Test
//	public void testNumAugs() {
//		assertEquals(1, t.getNumAugs());
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugId()}.
//	 */
//	@Test
//	public void testAugId() {
//		assertEquals(99, t.getAugId(0));
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugDate()}.
//	 */
//	@Test
//	public void testAugDate() {
//		assertEquals(aug[1], t.getAugDate(0));
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugCreator()}.
//	 */
//	@Test
//	public void testAugCreator() {
//		assertEquals(aug[2], t.getAugCreator(0));
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugMessage()}.
//	 */
//	@Test
//	public void testAugMessage() {
//		assertEquals(aug[3], t.getAugMessage(0));
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugViews()}.
//	 */
//	@Test
//	public void testAugViews() {
//		assertEquals(Integer.parseInt(aug[4]), t.getAugViews(0));
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugLikes()}.
//	 */
//	@Test
//	public void testAugLikes() {
//		assertEquals(Integer.parseInt(aug[5]), t.getAugLikes(0));
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugXPos()}.
//	 */
//	@Test
//	public void testAugXPos() {
//		assertEquals(Integer.parseInt(aug[6]), t.getAugXPos(0));
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugYPos()}.
//	 */
//	@Test
//	public void testAugYPos() {
//		assertEquals(Integer.parseInt(aug[7]), t.getAugYPos(0));
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugSize()}.
//	 */
//	@Test
//	public void testAugSize() {
//		assertEquals(Double.parseDouble(aug[8]), t.getAugSize(0), .01);
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getAugUrl()}.
//	 */
//	@Test
//	public void testAugUrl() {
//		assertEquals(aug[9], t.getAugUrl(0));
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getIncAugViews()}.
//	 */
//	@Test
//	public void testIncAugViews() {
//		int views = t.getAugViews(0);
//		assertEquals(views+1, t.incAugViews(0));
//	}
//	
//	/**
//	 * Test method for {@link edu.ucsb.cs.epsilon.ucsb360.Target#getIncAugLikes()}.
//	 */
//	@Test
//	public void testIncAugLikes() {
//		int likes = t.getAugLikes(0);
//		assertEquals(likes+1, t.incAugLikes(0));
//	}
//	
//}