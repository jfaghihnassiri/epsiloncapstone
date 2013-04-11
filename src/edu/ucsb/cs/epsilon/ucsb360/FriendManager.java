package edu.ucsb.cs.epsilon.ucsb360;

import java.sql.SQLException;
import java.util.*;

/**
 * Manager for Friend objects
 * 
 * @author Max Hinson
 * @author Jhon Nassiri
 */
public final class FriendManager {

	private static HashMap<String, Friend> friends = new HashMap<String, Friend>();
	
	/**
	 * Fetch Friend list field from SQL User database and use it to populate the HashMap
	 * 
	 * @author Max Hinson
	 * @author Jhon Nassiri
	 * @param username User identifier
	 */
	public static void populateFriends() {

		try
		{
			ArrayList<ArrayList<String>> list = DatabaseManager.getFriends();
			for(int i = 0; i < list.size(); i++) {
				ArrayList<String> a = list.get(i);
				friends.put(a.get(0), new Friend(a.get(0), a.get(1), Integer.parseInt(a.get(2))));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Add a Friend to the SQL User database and to the local HashMap
	 * 
	 * @author Max Hinson
	 * @param username Friend identifier
	 */
	public static void addFriend(String username, String name) {
		
		try {
			DatabaseManager.addFriend(username);
			friends.put(username, new Friend(username, name, 0));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Delete a Friend from the Friend list field of the SQL User database and from the local HashMap
	 * 
	 * @author Max Hinson
	 * @author Jhon Nassiri
	 * @param username Friend identifier
	 */
	public static void deleteFriend(String username) {
		
		try {
			DatabaseManager.delFriend(username);
			friends.remove(username);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Get the entire collection of Friends from the HashMap
	 * 
	 * @author Max Hinson
	 * @author Jhon Nassiri
	 * @return a list containing the Friend objects in the HashMap
	 */
	public static Collection<Friend> getFriends() {
		return friends.values();
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
	
	/**
	 * Increment the number of items shared with a Friend
	 * 
	 * @author Jhon Nassiri
	 * @param username Friend identifier
	 * @return number of items shared
	 */
	public static int incNumShares(String username) {
		
		try {
			if(DatabaseManager.incFriendNumShares(username))
				return friends.get(username).incNumShares();
			else
				return friends.get(username).getNumShares();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
}