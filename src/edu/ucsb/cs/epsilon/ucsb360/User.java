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
	private static int numTargetsSeen;
	private static int numTargetsCreated;
	private static int numTargetsShared;
	
	/**
	 * Log in
	 * 
	 * @author Max Hinson
	 */
	public static void logIn() {
		loggedIn = true;
	}
	
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
	 * @param userUsername User identifier
	 */
	public static void setUsername(String username) {
		User.username = username;
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
	 * @param name user's name
	 */
	public static void setName(String name) {
		User.name = name;
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
	 * @param birthday user's birthday
	 */
	public static void setBirthday(String birthday) {
		User.birthday = birthday;
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
	 * @param gender user's gender
	 */
	public static void setGender(String gender) {
		User.gender = gender;
	}

	/**
	 * Get the number of targets seen
	 * 
	 * @author Max Hinson
	 * @return number of targets seen
	 */
	public static int getNumTargetsSeen() {
		return numTargetsSeen;
	}

	/**
	 * Set the number of targets seen
	 * 
	 * @author Max Hinson
	 * @param numTargetsSeen number of targets seen
	 */
	public static void setNumTargetsSeen(int numTargetsSeen) {
		User.numTargetsSeen = numTargetsSeen;
	}

	/**
	 * Get the number of targets created
	 * 
	 * @author Max Hinson
	 * @return number of targets created
	 */
	public static int getNumTargetsCreated() {
		return numTargetsCreated;
	}

	/**
	 * Set the number of targets created
	 * 
	 * @author Max Hinson
	 * @param numTargetsCreated number of targets created
	 */
	public static void setNumTargetsCreated(int numTargetsCreated) {
		User.numTargetsCreated = numTargetsCreated;
	}

	/**
	 * Get the number of targets shared
	 * 
	 * @author Max Hinson
	 * @return number of targets shared
	 */
	public static int getNumTargetsShared() {
		return numTargetsShared;
	}

	/**
	 * Set the number of targets shared
	 * 
	 * @author Max Hinson
	 * @param numTargetsShared number of targets shared
	 */
	public static void setNumTargetsShared(int numTargetsShared) {
		User.numTargetsShared = numTargetsShared;
	}

}