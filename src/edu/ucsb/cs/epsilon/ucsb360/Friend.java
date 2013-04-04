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
	 * @author Jhon Nassiri
	 * @param username Friend identifier
	 * @param name Friend full name
	 * @param numShares number of shares between User and Friend
	 */
	public Friend(String username, String name, int numShares) {
		this.username = username;
		this.name = name;
		this.numShares = numShares; 
	}
	
	/**
	 * Get username for Friend
	 * 
	 * @author Max Hinson
	 * @return Friend username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Get name for Friend
	 * 
	 * @author Max Hinson
	 * @return Friend name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get shares for Friend
	 * 
	 * @author Max Hinson
	 * @return Friend shares
	 */
	public int getNumShares() {
		return numShares;
	}

	/**
	 * Increment shares for Friend
	 * 
	 * @author Jhon Nassiri
	 * @return Friend shares
	 */
	public int incNumShares() {
		numShares++;
		return numShares;
	}
}
