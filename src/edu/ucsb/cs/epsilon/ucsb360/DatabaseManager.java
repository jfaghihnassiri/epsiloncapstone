package edu.ucsb.cs.epsilon.ucsb360;

import java.sql.*;

public class DatabaseManager {

	// Member variables
	private static final String strDriver = ""; // TODO fix
	private static final String strConnection = "epsilondb.ccrbqzoyw7yg.us-east-1.rds.amazonaws.com:2013"; // TODO fix
	private static final String strUsername = "epsilon";
	private static final String strPassword = "epsilon2013";

	/**
	 * Connect to the database
	 * 
	 * @author Max Hinson
	 */
	public static Connection Connect() throws SQLException{
		Connection connection = null;
		
		try {
			Class.forName(strDriver);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
 
		try {
			connection = DriverManager.getConnection(strConnection, strUsername, strPassword);
			return connection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
				+ "(username, name, birthday, gender, numTargetsSeen, numTargetsShared, numTargetsCreated)"
				+ "(?, ?, ?, ?, 0, 0, 0)";

		// Connect to the database
		Connection connection = Connect();

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
	 * Create a new augmentation in the database
	 * 
	 * @author Max Hinson
	 * @param id target/augmentation identifier
	 * @param type type of target
	 * @param bitmap path to the augmentation bitmap
	 * @param location physical location of augmentation
	 * @param creator username for the creator of the augmentation
	 */
	public static void initAugmentationData(String id, String type, String bitmap,
			String location, String creator) throws SQLException {
		
		// Create prepared statement variable
		PreparedStatement statement = null;

		// Initialize SQL statements
		String s = "Insert Into Augmentations"
				+ "(id, type, bitmap, location, creator, views)"
				+ "(?, ?, ?, ?, ?, 0)";

		// Connect to the database
		Connection connection = Connect();
			
		// Initialize prepared statement
		statement = connection.prepareStatement(s);

		// Insert variables into statement
		statement.setString(1, id);
		statement.setString(2, type);
		statement.setString(3, bitmap);
		statement.setString(4, location);
		statement.setString(5, creator);

		// Execute the statement
		statement.executeUpdate();
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

		// Connect to the database
		Connection connection = Connect();

		// Initialize SQL statement
		String q = "Select ?"
				+ "From ?"
				+ "Where ? = ?";

		// Initialize prepared statement
		PreparedStatement query = connection.prepareStatement(q);

		// Insert variables into statement
		query.setString(1, column);
		query.setString(2, table);
		query.setString(3, row);
		query.setString(4, id);

		// Execute the query
		ResultSet rs = query.executeQuery();

		// Return the number of views
		return rs.getString(column);
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

		// Connect to the database
		Connection connection = Connect();

		String u = "Update ?"
				+ "Set ? = ?"
				+ "Where ? = ?";

		// Initialize prepared statement
		PreparedStatement update = connection.prepareStatement(u);

		// Insert variables into statement
		update.setString(1, table);
		update.setString(2, column);
		update.setString(3, value);
		update.setString(4, row);
		update.setString(5, id);

		// Execute the update
		update.executeUpdate();
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

		// Connect to the database
		Connection connection = Connect();

		// Initialize SQL statements
		String q = "Select ?"
				+ "From ?"
				+ "Where ? = ?";

		String u = "Update ?"
				+ "Set ? = ?"
				+ "Where ? = ?";

		// Initialize prepared statements
		PreparedStatement query = connection.prepareStatement(q);
		PreparedStatement update = connection.prepareStatement(u);

		// Insert variables into statement
		query.setString(1, column);
		query.setString(2, table);
		query.setString(3, row);
		query.setString(4, id);

		// Execute the query
		ResultSet rs = query.executeQuery();

		// Get and increment the number of views
		int n = rs.getInt(column);
		n++;

		// Insert variables into statement
		update.setString(1, table);
		update.setString(2, column);
		update.setInt(3, n);
		update.setString(4, row);
		update.setString(5, id);

		// Execute the update
		update.executeUpdate();

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
		return increment("Users", "numAugmentationsSeen", "username", username);
	}
	
	/**
	 * Wrapper function for incrementing the number of augmentations a user has shared
	 * 
	 * @author Max Hinson
	 * @param username user identifier to increment views for
	 * @return number of views
	 */
	public static int incrementAugmentationsShared(String username) throws SQLException {
		return increment("Users", "numAugmentationsShared", "username", username);
	}

	/**
	 * Wrapper function for incrementing the number of augmentations a user has created
	 * 
	 * @author Max Hinson
	 * @param username user identifier to increment views for
	 * @return number of views
	 */
	public static int incrementAugmentationsCreated(String username) throws SQLException {
		return increment("Users", "numAugmentationsCreated", "username", username);
	}

}