package edu.ucsb.cs.epsilon.ucsb360;

/**
 * Static class to keep track of user information
 * 
 * @author Max Hinson
 */
public final class User {

	private static Boolean loggedIn = false;
	private static String username = "";
	private static String name = "";
	private static String birthday = "";
	private static String lastOnline = "";
	private static int targetsSeen = 0;
	private static int targetsCreated = 0;
	private static int augsSeen = 0;
	private static int augsCreated = 0;
	private static int augsShared = 0;
	
	/**
	 * Log out
	 * 
	 * @author Max Hinson
	 */
	public static void logOut() {
		System.out.println("DEBUG: Logging out user " + username);
		loggedIn = false;
		username = "";
		name = "";
		birthday = "";
		lastOnline = "";
		targetsSeen = 0;
		targetsCreated = 0;
		augsSeen = 0;
		augsCreated = 0;
		augsShared = 0;
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
	 * Get the date the user was last online
	 * 
	 * @author Max Hinson
	 * @return date the user was last online
	 */
	public static String getLastOnline() {
		return lastOnline;
	}
	
	/**
	 * Set the date the user was last online
	 * 
	 * @author Max Hinson
	 * @param lo date the user was last online
	 */
	public static void setLastOnline(String lo) {
		lastOnline = lo;
	}

	/**
	 * Get the number of targets seen
	 * 
	 * @author Max Hinson
	 * @return number of targets seen
	 */
	public static int getTargetsSeen() {
		return targetsSeen;
	}
	
	/**
	 * Set the number of targets seen
	 * 
	 * @author Max Hinson
	 * @param ts number of targets seen
	 */
	public static void setTargetsSeen(int ts) {
		targetsSeen = ts;
	}
	
	/**
	 * Increment the number of targets seen
	 * 
	 * @author Max Hinson
	 */
	public static void incTargetsSeen() {
		targetsSeen++;
	}
	
	/**
	 * Get the number of targets created
	 * 
	 * @author Max Hinson
	 * @return number of targets created
	 */
	public static int getTargetsCreated() {
		return targetsCreated;
	}
	
	/**
	 * Set the number of targets created
	 * 
	 * @author Max Hinson
	 * @param tc number of targets created
	 */
	public static void setTargetsCreated(int tc) {
		targetsCreated = tc;
	}
	
	/**
	 * Increment the number of targets created
	 * 
	 * @author Max Hinson
	 */
	public static void incTargetsCreated() {
		targetsCreated++;
	}

	/**
	 * Get the number of augmentations seen
	 * 
	 * @author Max Hinson
	 * @return number of augmentations seen
	 */
	public static int getAugsSeen() {
		return augsSeen;
	}
	
	/**
	 * Set the number of augmentations seen
	 * 
	 * @author Max Hinson
	 * @param ts number of augmentations seen
	 */
	public static void setAugsSeen(int as) {
		augsSeen = as;
	}
	
	/**
	 * Increment the number of augmentations seen
	 * 
	 * @author Max Hinson
	 */
	public static void incAugsSeen() {
		augsSeen++;
	}

	/**
	 * Get the number of augmentations created
	 * 
	 * @author Max Hinson
	 * @return number of augmentations created
	 */
	public static int getAugsCreated() {
		return augsCreated;
	}
	
	/**
	 * Set the number of augmentations created
	 * 
	 * @author Max Hinson
	 * @param ac number of augmentations created
	 */
	public static void setAugsCreated(int ac) {
		augsCreated = ac;
	}
	
	/**
	 * Increment the number of augmentations created
	 * 
	 * @author Max Hinson
	 */
	public static void incAugsCreated() {
		augsCreated++;
	}

	/**
	 * Get the number of augmentations shared
	 * 
	 * @author Max Hinson
	 * @return number of augmentations shared
	 */
	public static int getAugsShared() {
		return augsShared;
	}
	
	/**
	 * Set the number of augmentations shared
	 * 
	 * @author Max Hinson
	 * @param s number of augmentations shared
	 */
	public static void setAugsShared(int s) {
		augsShared = s;
	}

	/**
	 * Increment the number of augmentations created
	 * 
	 * @author Max Hinson
	 */
	public static void incAugsShared() {
		augsShared++;
	}
	
}