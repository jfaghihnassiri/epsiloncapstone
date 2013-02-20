package edu.ucsb.cs.epsilon.ucsb360;

import java.sql.*;

// TODO fix "DATE" type for database and input functions

public class DatabaseManager {

	// Member variables
	private static final String strDriver = "com.mysql.jdbc.Driver";
	private static final String strConnection = "jdbc:mysql://epsilondb.ccrbqzoyw7yg.us-east-1.rds.amazonaws.com:2013/epsilondb1";
	private static final String strUsername = "epsilon";
	private static final String strPassword = "epsilon2013";

	/**
	 * Connect to the database
	 * 
	 * @author Max Hinson
	 */
	public static Connection Connect() {
		Connection connection = null;
		
		try {
			Class.forName(strDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
 
		try {
			connection = DriverManager.getConnection(strConnection, strUsername, strPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

	/**
	 * Create a new user in the database upon first login
	 * 
	 * @author Max Hinson
	 * @param username user identifier
	 * @param name full name
	 * @param birthday birthday
	 * @param gender gender
	 */
	public static void initUserData(String username, String name,
			String birthday, String gender) throws SQLException {

		// Set up SQL variables
		PreparedStatement statement = null;
		
		// Initialize SQL statement
		String s = "Insert Into Users"
				+ " Values (?, ?, ?, ?, 0, 0, 0)";

		// Connect to the database
		Connection connection = Connect();

		// Prepare statement
		statement = connection.prepareStatement(s);

		// Insert input values
		statement.setString(1, username);
		statement.setString(2, name);
		statement.setString(3, birthday);
		statement.setString(4, gender);
		
		// Execute the statement
		statement.executeUpdate();
		
		// Close the connection
		connection.close();
	}

	/**
	 * Create a new augmentation in the database
	 * 
	 * @author Max Hinson
	 * @param id target/augmentation identifier
	 * @param name file name in the Vuforia cloud database
	 * @param type type of target
	 * @param bitmap path to the augmentation bitmap
	 * @param location physical location of augmentation
	 * @param date date target was created
	 * @param creator username for the creator of the augmentation
	 */
	public static void initAugmentationData(String id, String name, String type, String bitmap,
			String location, String date, String creator) throws SQLException {
		
		// Create prepared statement variable
		PreparedStatement statement = null;

		// Initialize SQL statements
		String s = "Insert Into Augmentations"
				+ " Values (?, ?, ?, ?, ?, ?, ?, 0)";

		// Connect to the database
		Connection connection = Connect();
			
		// Initialize prepared statement
		statement = connection.prepareStatement(s);

		// Insert variables into statement
		statement.setString(1, id);
		statement.setString(2, name);
		statement.setString(3, type);
		statement.setString(4, bitmap);
		statement.setString(5, location);
		statement.setString(6, date);
		statement.setString(7, creator);

		// Execute the statement
		statement.executeUpdate();
		
		// Close the connection
		connection.close();
	}
	
	/**
	 * Generic function for getting an attribute
	 * 
	 * @author Max Hinson
	 * @param table the table to check
	 * @param column the field to check
	 * @param row the row to check
	 * @param id the primary key identifier
	 * @return the value of the attribute
	 */
	private static String get(String table, String column, String row, String id) throws SQLException {

		// Initialize SQL statement
		String q = "Select " + column
				+ " From " + table
				+ " Where " + row + " = ?";
		
		// Connect to the database
		Connection connection = Connect();

		// Initialize prepared statement
		PreparedStatement query = connection.prepareStatement(q);

		// Insert variables into statement
		query.setString(1, id);

		// Execute the query
		ResultSet rs = query.executeQuery();

		// Get value
		if(rs.first() == false)
			System.out.println("RESULT SET IS EMPTY");
		String ret = rs.getString(column);
		
		// Close the connection
		connection.close();
		
		// Return the value
		return ret;
	}
	
	/**
	 * Generic function for setting any field
	 * 
	 * @author Max Hinson
	 * @param table the table to modify
	 * @param column the field to modify
	 * @param row the row to modify
	 * @param id the target/augmentation identifier
	 * @param value the new value of the attribute
	 * @return the number of times that the augmentation has been viewed
	 */
	private static void set(String table, String column, String row, String id, String value) throws SQLException {

		// Initialize the SQL statement
		String u = "Update " + table
				+ " Set " + column + " = ?"
				+ " Where " + row + " = ?";
		
		// Connect to the database
		Connection connection = Connect();

		// Initialize prepared statement
		PreparedStatement update = connection.prepareStatement(u);

		// Insert variables into statement
		update.setString(1, value);
		update.setString(2, id);

		// Execute the update
		update.executeUpdate();
		
		// Close the connection
		connection.close();
	}

	/**
	 * Generic function for incrementing any field
	 * 
	 * @author Max Hinson
	 * @param table the table to modify
	 * @param column the field to modify
	 * @param row the row to modify
	 * @param id the target/augmentation identifier
	 * @return the number of times that the augmentation has been viewed
	 */
	private static int increment(String table, String column, String row, String id) throws SQLException {

		// Initialize SQL statements
		String q = "Select " + column
				+ " From " + table
				+ " Where " + row + " = ?";

		String u = "Update " + table
				+ " Set " + column + " = ?"
				+ " Where " + row + " = ?";
		
		// Connect to the database
		Connection connection = Connect();

		// Initialize prepared statements
		PreparedStatement query = connection.prepareStatement(q);
		PreparedStatement update = connection.prepareStatement(u);

		// Insert variables into statement
		query.setString(1, id);

		// Execute the query
		ResultSet rs = query.executeQuery();

		// Get and increment the number of views
		if(rs.first() == false)
			System.out.println("RESULT SET IS EMPTY");
		int n = rs.getInt(column);
		n++;

		// Insert variables into statement
		update.setInt(1, n);
		update.setString(2, id);

		// Execute the update
		update.executeUpdate();
		
		// Close the connection
		connection.close();

		// Return the number of views
		return n;
	}

	/**
	 * Wrapper function for incrementing augmentation views
	 * 
	 * @author Max Hinson
	 * @param id augmentation identifier to increment views for
	 * @return number of views
	 */
	public static int incrementViews(String id) throws SQLException {
		return increment("Augmentations", "views", "id", id);
	}
	
	/**
	 * Wrapper function for incrementing the number of augmentations a user has viewed
	 * 
	 * @author Max Hinson
	 * @param username user identifier to increment views for
	 * @return number of views
	 */
	public static int incrementAugmentationsSeen(String username) throws SQLException {
		return increment("Users", "numTargetsSeen", "username", username);
	}
	
	/**
	 * Wrapper function for incrementing the number of augmentations a user has shared
	 * 
	 * @author Max Hinson
	 * @param username user identifier to increment views for
	 * @return number of views
	 */
	public static int incrementAugmentationsShared(String username) throws SQLException {
		return increment("Users", "numTargetsShared", "username", username);
	}

	/**
	 * Wrapper function for incrementing the number of augmentations a user has created
	 * 
	 * @author Max Hinson
	 * @param username user identifier to increment views for
	 * @return number of views
	 */
	public static int incrementAugmentationsCreated(String username) throws SQLException {
		return increment("Users", "numTargetsCreated", "username", username);
	}
	
	/**
	 * Wrapper function for getting the bitmap for an augmentation
	 * 
	 * @author Max Hinson
	 * @param username user identifier to increment views for
	 * @return bitmap
	 */
	public static String getBitmap(String id) throws SQLException {
		return get("Augmentations", "bitmap", "id", id);
	}
	
}