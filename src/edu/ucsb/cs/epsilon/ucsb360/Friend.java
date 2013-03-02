package edu.ucsb.cs.epsilon.ucsb360;

/**
 * Simple container for Friend information
 * 
 * @author Max Hinson
 */
public final class Friend {

	private String username;
	private String name;
	private int numShares;
	
	/**
	 * Constructor for Friend class
	 * 
	 * @author Max Hinson
	 * @param username Friend identifier
	 */
	public Friend(String username) {
		name = "";
		numShares = 0;
	}
	
	/**
	 * Get username for Friend
	 * 
	 * @author Max Hinson
	 * @param username Friend identifier
	 * @return Friend username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Get name for Friend
	 * 
	 * @author Max Hinson
	 * @param username Friend identifier
	 * @return Friend name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get shares for Friend
	 * 
	 * @author Max Hinson
	 * @param username Friend identifier
	 * @return Friend shares
	 */
	public int getNumShares() {
		return numShares;
	}
	
}
