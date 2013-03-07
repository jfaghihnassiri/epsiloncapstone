package edu.ucsb.cs.epsilon.ucsb360;

import java.sql.*;
import java.util.HashMap;

/**
 * Simple container for target attributes
 * 
 * @author Max Hinson
 */
public final class Target {

	private static HashMap<String, Augmentation> augmentations = new HashMap<String, Augmentation>();
	private String id;
	private String date;
	private String creator;
	
	/**
	 * Constructor for Target class
	 * 
	 * @author Max Hinson
	 * @param targetId Target identifier
	 */
	public Target(String targetId) {
		
		try {
			// Get target information from database
			String[] targetInfo = DatabaseManager.getTarget(targetId);
			
			// Store database fields in local variables
			id = targetInfo[0];
			date = targetInfo[2];
			creator = targetInfo[3];
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * Different constructor for a NEW target
	 * 
	 * @author Max Hinson
	 * @param targetId Target identifier
	 */
	public Target(String targetId, String date, String Creator) {
		// TODO
	}
	
	/**
	 * Get the target's date of creation
	 * 
	 * @author Max Hinson
	 * @return Date target was created
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Get the user who created the target
	 * 
	 * @author Max Hinson
	 * @return Target creator
	 */
	public String getCreator() {
		return creator;
	}
	
	/**
	 * Get the number of views
	 * 
	 * @author Max Hinson
	 * @return Number of views
	 */
	public int getViews() {
		// TODO
		return -1;
	}
	
	/**
	 * Get the number of augemntations of a target
	 * 
	 * @author Max Hinson
	 * @return Number of augmentations
	 */
	public int getNumAugs() {
		// TODO
		return -1;
	}
	
	/**
	 * Create a new instantiation of Augmentation class
	 * 
	 * @author Jhon Nassiri
	 * @param message The message that is associated with the Augmentation
	 * @param xPos the x position of the augmentation in reference to the target
	 * @param yPos the y postiion of the augmentation in reference to the target
	 * @param size the size of the augmentation in reference to the target
	 */
	public void createAug(String message, int xPos, int yPos, double size) {
		// TODO
	}
	
	/**
	 * Get augmentation identifier
	 * 
	 * @author Jhon Nassiri
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation identifier
	 */
	public int getAugId(int augIndex) {
		// TODO
		return -1;
	}
	
	/**
	 * Get augmentation date
	 * 
	 * @author Jhon Nassiri
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation date
	 */
	public String getAugDate(int augIndex) {
		// TODO
		return "stub";
	}
	
	/**
	 * Get augmentation creator
	 * 
	 * @author Jhon Nassiri
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation creator
	 */
	public String getAugCreator(int augIndex) {
		// TODO
		return "stub";
	}
	
	/**
	 * Get augmentation message
	 * 
	 * @author Jhon Nassiri
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation message
	 */
	public String getAugMessage(int augIndex) {
		// TODO
		return "stub";
	}
	
	/**
	 * Get augmentation views
	 * 
	 * @author Jhon Nassiri
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation views
	 */
	public int getAugViews(int augIndex) {
		// TODO
		return -1;
	}
	
	/**
	 * Get augmentation likes
	 * 
	 * @author Jhon Nassiri
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation likes
	 */
	public int getAugLikes(int augIndex) {
		// TODO
		return -1;
	}
	
	/**
	 * Get augmentation x position
	 * 
	 * @author Jhon Nassiri
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation x position
	 */
	public int getAugXPos(int augIndex) {
		// TODO
		return -1;
	}
	
	/**
	 * Get augmentation y position
	 * 
	 * @author Jhon Nassiri
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation x position
	 */
	public int getAugYPos(int augIndex) {
		// TODO
		return -1;
	}
	
	/**
	 * Get augmentation size
	 * 
	 * @author Jhon Nassiri
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation size
	 */
	public int getAugSize(int augIndex) {
		// TODO
		return -1;
	}
	
	/**
	 * Get augmentation url
	 * 
	 * @author Jhon Nassiri
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation url
	 */
	public String getAugUrl(int augIndex) {
		// TODO
		return "stub";
	}
	
	/**
	 * Increment augmentation views
	 * 
	 * @author Jhon Nassiri
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation views
	 */
	public int incAugViews(int augIndex) {
		// TODO
		return -1;
	}
	
	/**
	 * Increment augmentation likes
	 * 
	 * @author Jhon Nassiri
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation likes
	 */
	public int incAugLikes(int augIndex) {
		// TODO
		return -1;
	}
	
	
	// JFN HERE


}
