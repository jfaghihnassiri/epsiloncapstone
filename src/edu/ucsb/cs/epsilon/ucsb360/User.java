package edu.ucsb.cs.epsilon.ucsb360;

import java.sql.SQLException;

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
	 * Log in
	 * 
	 * @author Max Hinson
	 */
	public static Boolean logIn(String username) {
		try {
			String[] user = DatabaseManager.getUsr(username);
			
			// Populate the private members from database results
			User.loggedIn = true;
			User.username = user[0];
			User.name = user[1];
			User.birthday = user[2];
			User.gender = user[3];
			User.numAugsCreated = Integer.parseInt(user[4]);
			User.numAugsShared = Integer.parseInt(user[5]);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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
	 * Get the user's gender
	 * 
	 * @author Max Hinson
	 * @return user's gender
	 */
	public static String getGender() {
		return gender;
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
	 * Increment the number of targets created
	 * 
	 * @author Max Hinson
	 * @return number of targets created
	 */
	public static int incNumAugsCreated() {
		
		try {
			if(DatabaseManager.incUsrAugsCreated())
				return ++numAugsCreated;
			else
				return numAugsCreated;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
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
	 * Increment the number of targets created
	 * 
	 * @author Max Hinson
	 * @return number of targets created
	 */
	public static int incNumAugsShared() {
		
		try {
			if(DatabaseManager.incUsrAugsShared())
				return ++numAugsShared;
			else
				return numAugsShared;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
}