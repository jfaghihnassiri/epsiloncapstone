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
	 * Log in
	 * 
	 * @author Max Hinson
	 * @param username User's Facebook ID
	 * @param name User's name
	 * @param birthday User's birthday
	 * @param lastOnline Last time user was online
	 * @param targetsSeen Number of targets the user has seen
	 * @param targetsCreated Number of targets the user has created
	 * @param augsSeen Number of augmentations a user has seen
	 * @param augsCreated Number of augmentations a user has created
	 * @param augsShared Number of augmentations a user has shared
	 */
	public static void logIn(String username, String name, String birthday, String lastOnline, int targetsSeen,
			int targetsCreated, int augsSeen, int augsCreated, int augsShared) {
		
		System.out.println("DEBUG: Setting user data for " + name);
		
		loggedIn = true;
		User.username = username;
		User.name = name;
		User.birthday = birthday;
		User.lastOnline = lastOnline;
		User.targetsSeen = targetsSeen;
		User.targetsCreated = targetsCreated;
		User.augsSeen = augsSeen;
		User.augsCreated = augsCreated;
		User.augsShared = augsShared;
		
		TargetManager.clearCache();
	}
	
	/**
	 * Log out
	 * 
	 * @author Max Hinson
	 */
	public static void logOut() {
		
		System.out.println("DEBUG: Logging out as " + name);
		
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
		
		TargetManager.clearCache();
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
	 * Get the user's username
	 * 
	 * @author Max Hinson
	 * @return User's username
	 */
	public static String getUsername() {
		return username;
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
	 * Get the user's birthday
	 * 
	 * @author Max Hinson
	 * @return user's birthday
	 */
	public static String getBirthday() {
		return birthday;
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
	 * Get the number of targets seen
	 * 
	 * @author Max Hinson
	 * @return number of targets seen
	 */
	public static int getTargetsSeen() {
		return targetsSeen;
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
	 * Increment the number of augmentations created
	 * 
	 * @author Max Hinson
	 */
	public static void incAugsShared() {
		augsShared++;
	}
	
}