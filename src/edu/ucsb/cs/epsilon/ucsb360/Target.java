package edu.ucsb.cs.epsilon.ucsb360;

import java.sql.*;

/**
 * Simple container for target attributes
 * 
 * @author Max Hinson
 */
public final class Target {

	private String id;
	private String type;
	private String date;
	private String creator;
	private String message;
	private int views;
	
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
			type = targetInfo[1];
			date = targetInfo[2];
			creator = targetInfo[3];
			message = targetInfo[4];
			views = Integer.parseInt(targetInfo[5]);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * Get the target ID
	 * 
	 * @author Max Hinson
	 * @return Target ID
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Get the target type
	 * 
	 * @author Max Hinson
	 * @return Target type
	 */
	public String getType() {
		return type;
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
	 * Get the target's message
	 * 
	 * @author Max Hinson
	 * @return Target message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Get the number of views
	 * 
	 * @author Max Hinson
	 * @return Number of views
	 */
	public int getViews() {
		return views;
	}

}
