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
	
	/**
	 * Log in
	 * 
	 * @author Max Hinson
	 * @param username User's Facebook ID
	 * @param name User's name
	 * @param birthday User's birthday
	 */
	public static void logIn(String username, String name, String birthday) {
		
		System.out.println("DEBUG: Setting user data for " + name);
		
		loggedIn = true;
		User.username = username;
		User.name = name;
		User.birthday = birthday;
		
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
	
}