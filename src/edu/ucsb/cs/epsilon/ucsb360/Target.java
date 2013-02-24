package edu.ucsb.cs.epsilon.ucsb360;

import java.io.IOException;
import java.net.*;
import java.awt.image.*;
import javax.imageio.*;
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
	private URL augUrl;
	private BufferedImage augmentation;
	
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
			name = target[1];
			type = target[2];
			location = target[4];
			date = target[5];
			creator = target[6];
			views = Integer.parseInt(target[7]);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		// Create URL object for augmentation location
		try {
			augUrl = new URL(url + id + ext);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		
		// Read image from URL into BufferedImage
		try {
			augmentation = ImageIO.read(augUrl);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Getter for augmentation
	 * 
	 * @author Max Hinson
	 * @return Augmentation as BufferedStream
	 */
	public BufferedImage getAugmentation() {
		return augmentation;
	}

}
