package edu.ucsb.cs.epsilon.ucsb360;

import java.sql.ResultSet;
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
	 * @author Jhon Nassiri
	 * @param username User identifier
	 */
	public static void populateFriends() {
		Friend to_add;
		try
		{
			ResultSet sql_flist = DatabaseManager.getFriends();
			while( sql_flist.next() )
			{
				//to_add = new Friend(sql_augs.getInt(2),"their name here",sql_augs.getInt(3));
				//friends.add(to_add); 
				// TODO issues with code above, needs to be fixed and need a method for looking up their name based on their username
			}
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
			// TODO
	}
	
	/**
	 * Add a Friend to the SQL User database and to the local HashMap
	 * 
	 * @author Max Hinson
	 * @param username Friend identifier
	 */
	public static void addFriend(String username) {
		// DEPR friends.put(username, new Friend(username));
		// TODO
	}
	
	/**
	 * Delete a Friend from the Friend list field of the SQL User database and from the local HashMap
	 * 
	 * @author Jhon Nassiri
	 * @param username Friend identifier
	 */
	public static void deleteFriend(String username) {
		// TODO
	}
	
	/**
	 * Get the entire collection of Friends from the HashMap
	 * 
	 * @author Jhon Nassiri
	 * @return a list containing the Friend objects in the HashMap
	 */
	public static Collection<Friend> getFriends() {
		Collection<Friend> friend_list = new ArrayList<Friend>();
		// TODO
		return friend_list;
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
		// TODO
		return -1;
	}
	
}
