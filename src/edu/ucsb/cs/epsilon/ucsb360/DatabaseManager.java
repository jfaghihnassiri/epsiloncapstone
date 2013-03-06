package edu.ucsb.cs.epsilon.ucsb360;

import java.sql.*;

public final class DatabaseManager {

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
	private static Connection Connect() {
		
		// Initialize connection to null
		Connection connection = null;
		
		// Check to see if we can find the driver
		try {
			Class.forName(strDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
 
		// Try opening a connection to the database
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
	public static void createUser(String username, String name,
			String birthday, String gender) throws SQLException {
		
		// Initialize SQL statement
		String s = "INSERT INTO Users"
				+ " VALUES (?, ?, ?, ?, 0, 0)";

		// Connect to the database
		Connection connection = Connect();

		// Prepare statement
		PreparedStatement statement = connection.prepareStatement(s);

		// Insert input values
		statement.setString(1, username);
		statement.setString(2, name);
		statement.setString(3, birthday);
		statement.setString(4, gender);
		
		// Execute the statement
		statement.executeUpdate();
		
		// Clean up
		statement.close();
		connection.close();
	}
	
	/**
	 * Get a user's information
	 * 
	 * @author Max Hinson
	 * @param username user identifier
	 * @return array of user data
	 */
	public static String[] getUser(String username) throws SQLException {
		
		// Initialize SQL statement
		String s = "SELECT *"
				+ " FROM Users"
				+ " WHERE username = ?";

		// Connect to the database
		Connection connection = Connect();

		// Prepare statement
		PreparedStatement statement = connection.prepareStatement(s);

		// Insert input values
		statement.setString(1, username);

		// Execute the statement
		ResultSet rs = statement.executeQuery();

		// Read user info into array
		if(rs.first() == false)
			System.out.println("RESULT SET IS EMPTY");
		int cols = rs.getMetaData().getColumnCount();
		String[] ret = new String[cols];
		for(int i = 0; i < cols; i++)
			ret[i] = rs.getString(i+1);
		
		// Clean up
		statement.close();
		rs.close();
		connection.close();
		
		return ret;
	}
	
	/**
	 * Delete a user
	 * 
	 * @author Max Hinson
	 * @param username user identifier
	 */
	public static void deleteUser(String username) throws SQLException {
		
		// Initialize SQL statement
		String s = "DELETE FROM Users"
				+ " WHERE username = ?";
		
		// Connect to the database
		Connection connection = Connect();

		// Prepare statement
		PreparedStatement statement = connection.prepareStatement(s);
		
		// Insert input values
		statement.setString(1, username);
		
		// Execute the statement
		statement.executeUpdate();
		
		// Clean up
		statement.close();
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
	public static void createTarget(String id, String type,
			String date, String creator, String message) throws SQLException {

		// Initialize SQL statements
		String s = "INSERT INTO Targets"
				+ " VALUES (?, ?, ?, ?, ?, 0)";

		// Connect to the database
		Connection connection = Connect();
			
		// Initialize prepared statement
		PreparedStatement statement = connection.prepareStatement(s);

		// Insert variables into statement
		statement.setString(1, id);
		statement.setString(2, type);
		statement.setString(3, date);
		statement.setString(4, creator);
		statement.setString(5, message);

		// Execute the statement
		statement.executeUpdate();
		
		// Clean up
		statement.close();
		connection.close();
	}
	
	/**
	 * Get a target's information
	 * 
	 * @author Max Hinson
	 * @param id target identifier
	 * @return array of target data
	 */
	public static String[] getTarget(String id) throws SQLException {
		
		// Initialize SQL statement
		String s = "SELECT *"
				+ " FROM Targets"
				+ " WHERE id = ?";

		// Connect to the database
		Connection connection = Connect();

		// Prepare statement
		PreparedStatement statement = connection.prepareStatement(s);

		// Insert input values
		statement.setString(1, id);

		// Execute the statement
		ResultSet rs = statement.executeQuery();

		// Read user info into array
		if(rs.first() == false)
			System.out.println("RESULT SET IS EMPTY");
		int cols = rs.getMetaData().getColumnCount();
		String[] ret = new String[cols];
		for(int i = 0; i < cols; i++)
			ret[i] = rs.getString(i+1);
		
		// Clean up
		statement.close();
		rs.close();
		connection.close();
		
		return ret;
	}
	
	/**
	 * Delete a target from the database
	 * 
	 * @author Max Hinson
	 * @param id the target's id number
	 */
	public static void deleteTarget(String id) throws SQLException {

		// Initialize SQL statement
		String s = "DELETE FROM Targets"
				+ " WHERE id = ?";

		// Connect to the database
		Connection connection = Connect();

		// Prepare statement
		PreparedStatement statement = connection.prepareStatement(s);

		// Insert input values
		statement.setString(1, id);

		// Execute the statement
		statement.executeUpdate();

		// Clean up
		statement.close();
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
		String q = "SELECT " + column
				+ " FROM " + table
				+ " WHERE " + row + " = ?";

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
		
		// Clean up
		query.close();
		rs.close();
		connection.close();
		
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
		
		// Clean up
		update.close();
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
		
		// Clean up
		query.close();
		rs.close();
		update.close();
		connection.close();

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
		return increment("Targets", "views", "id", id);
	}
	
	/**
	 * Wrapper function for incrementing the number of augmentations a user has shared
	 * 
	 * @author Max Hinson
	 * @param username user identifier to increment views for
	 * @return number of views
	 */
	public static int incrementAugsShared(String username) throws SQLException {
		return increment("Users", "numAugsShared", "username", username);
	}

	/**
	 * Wrapper function for incrementing the number of augmentations a user has created
	 * 
	 * @author Max Hinson
	 * @param username user identifier to increment views for
	 * @return number of views
	 */
	public static int incrementAugsCreated(String username) throws SQLException {
		return increment("Users", "numAugsCreated", "username", username);
	}
	
}