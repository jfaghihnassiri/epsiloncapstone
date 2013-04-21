package edu.ucsb.cs.epsilon.ucsb360;

/**
 * Class to keep track of user information
 * 
 * @author Max Hinson
 */
public final class User {

	private static Boolean loggedIn = false;
	private static String username;
	private static String name;
	private static String birthday;
	private static String gender;
	private static int numAugsCreated;
	private static int numAugsShared;
	
	/**
	 * Log out
	 * 
	 * @author Max Hinson
	 */
	public static void logOut() {
		loggedIn = false;
	}
	
	/**
	 * Check if the user is logged in
	 * 
	 * @author Max Hinson
	 * @return whether or not the user is logged in
	 */
	public static Boolean isLoggedIn() {
		return loggedIn;
	}
	
	/**
	 * Set logged in
	 * 
	 * @author Max Hinson
	 */
	public static void setLoggedIn() {
		loggedIn = true;
	}
	
	/**
	 * Get the user's username
	 * 
	 * @author Max Hinson
	 * @return User's username
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * Set the user's username
	 * 
	 * @author Max Hinson
	 * @param u User's username
	 */
	public static void setUsername(String u) {
		username = u;
	}
	
	/**
	 * Get the user's name
	 * 
	 * @author Max Hinson
	 * @return user's name
	 */
	public static String getName() {
		return name;
	}
	
	/**
	 * Set the user's name
	 * 
	 * @author Max Hinson
	 * @param n user's name
	 */
	public static void setName(String n) {
		name = n;
	}

	/**
	 * Get the user's birthday
	 * 
	 * @author Max Hinson
	 * @return user's birthday
	 */
	public static String getBirthday() {
		return birthday;
	}
	
	/**
	 * Set the user's birthday
	 * 
	 * @author Max Hinson
	 * @param b user's birthday
	 */
	public static void setBirthday(String b) {
		birthday = b;
	}

	/**
	 * Get the user's gender
	 * 
	 * @author Max Hinson
	 * @return user's gender
	 */
	public static String getGender() {
		return gender;
	}
	
	/**
	 * Set the user's gender
	 * 
	 * @author Max Hinson
	 * @param g user's gender
	 */
	public static void setGender(String g) {
		gender = g;
	}

	/**
	 * Get the number of targets created
	 * 
	 * @author Max Hinson
	 * @return number of targets created
	 */
	public static int getNumAugsCreated() {
		return numAugsCreated;
	}
	
	/**
	 * Set the number of targets created
	 * 
	 * @author Max Hinson
	 * @param c number of targets created
	 */
	public static void setNumAugsCreated(int c) {
		numAugsCreated = c;
	}
	
	/**
	 * Increment the number of targets created
	 * 
	 * @author Max Hinson
	 * @return number of targets created
	 */
	public static void incNumAugsCreated() {
		numAugsCreated++;
	}

	/**
	 * Get the number of targets shared
	 * 
	 * @author Max Hinson
	 * @return number of targets shared
	 */
	public static int getNumAugsShared() {
		return numAugsShared;
	}
	
	/**
	 * Set the number of targets shared
	 * 
	 * @author Max Hinson
	 * @param s number of targets shared
	 */
	public static void setNumAugsShared(int s) {
		numAugsShared = s;
	}

	/**
	 * Increment the number of targets created
	 * 
	 * @author Max Hinson
	 * @return number of targets created
	 */
	public static void incNumAugsShared() {
		numAugsShared++;
	}
	
}