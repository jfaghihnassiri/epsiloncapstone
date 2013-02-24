package edu.ucsb.cs.epsilon.ucsb360;

import java.sql.*;

/**
 * @author Max Hinson
 */
public final class Target {

	private static final String url = "https://s3-us-west-1.amazonaws.com/teamepsilon/augmentations/";
	private static final String ext = ".jpg";
	private String id;
	private String name;
	private String type;
	private String location;
	private String date;
	private String creator;
	private int views;
	private String augUrl;
	
	/**
	 * Constructor for Target class
	 * 
	 * @author Max Hinson
	 * @param tId Target identifier
	 */
	public Target(String tId) {
		
		// Get target information from database
		try {
			String[] target = DatabaseManager.getTarget(tId);
			id = target[0];
			augUrl = url + id + ext;
			name = target[1];
			type = target[2];
			location = target[4];
			date = target[5];
			creator = target[6];
			views = Integer.parseInt(target[7]);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * Getter for augmentation URL
	 * 
	 * @author Max Hinson
	 * @return Augmentation URL
	 */
	public String getAugUrl() {
		return augUrl;
	}

}
