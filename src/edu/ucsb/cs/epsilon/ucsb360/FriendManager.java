package edu.ucsb.cs.epsilon.ucsb360;

import java.util.HashMap;

/**
 * Manager for Friend objects
 * 
 * @author Max Hinson
 */
public final class FriendManager {

	private static HashMap<String, Friend> friends = new HashMap<String, Friend>();
	
	/**
	 * Add a Friend
	 * 
	 * @author Max Hinson
	 * @param username Friend identifier
	 */
	public static void addFriend(String username) {
		friends.put(username, new Friend(username));
	}
	
	/**
	 * Get the number of friends the user has
	 * 
	 * @author Max Hinson
	 * @return number of friends
	 */
	public static int getNumFriends() {
		return friends.size();
	}
	
	/**
	 * Get a Friend's name
	 * 
	 * @author Max Hinson
	 * @param username Friend identifier
	 * @return Friend's name
	 */
	public static String getName(String username) {
		return friends.get(username).getName();
	}
	
	/**
	 * Get the number of items shared with a Friend
	 * 
	 * @author Max Hinson
	 * @param username Friend identifier
	 * @return number of items shared
	 */
	public static int getNumShares(String username) {
		return friends.get(username).getNumShares();
	}
	
}
