package edu.ucsb.cs.epsilon.ucsb360;

import java.sql.*;

public class DatabaseManager {

	// Member variables
	static Connection connection;
	static String strConnection = "epsilondb.ccrbqzoyw7yg.us-east-1.rds.amazonaws.com:2013";
	static String strUsername = "epsilon";
	static String strPassword = "epsilon2013";
	
	public DatabaseManager() {
		
	}
	
	/**
	 * @param args
	 */
	public static void Connect() throws SQLException{
		connection = DriverManager.getConnection(strConnection, strUsername, strPassword);
	}
	
	/**
	 * @param username
	 * @param name
	 * @param birthday
	 * @param gender
	 */
	public static void initUserData(String username, String name, String birthday, String gender) throws SQLException {
		
		// Set up SQL variables
		PreparedStatement statement;
		String s = "Insert Into Users"
				+ "(username, name, birthday, gender, numTargetsSeen, numTargetsShared, numTargetsCreated)"
				+ "(?, ?, ?, ?, 0, 0, 0)";
		
		statement = connection.prepareStatement(s);
		
		// Insert input values
		statement.setString(1, username);
		statement.setString(2, name);
		statement.setString(3, birthday);
		statement.setString(4, gender);
		
		// Execute the statement
		statement.executeUpdate();
		
		}
	
	/**
	 * @param id
	 * @param type
	 * @param bitmap
	 * @param location
	 * @param creator
	 */
	public static void initAugmentationData(String id, String type, String bitmap, String location, String creator) throws SQLException {
		
		// Set up SQL variables
		PreparedStatement statement;
		String s = "Insert Into Augmentations"
				+ "(id, type, bitmap, location, creator, views)"
				+ "(?, ?, ?, ?, ?, 0)";
		
		statement = connection.prepareStatement(s);
		
		// Insert input values
		statement.setString(1, id);
		statement.setString(2, type);
		statement.setString(3, bitmap);
		statement.setString(4, location);
		statement.setString(5, creator);
		
		// Execute the statement
		statement.executeUpdate();
		
		}
	
		public static void incrementViews(String id) throws SQLException {
			
		}

}