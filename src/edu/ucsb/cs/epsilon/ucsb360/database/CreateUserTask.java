package edu.ucsb.cs.epsilon.ucsb360.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.User;

import android.os.AsyncTask;

/**
 * Asynchronous task for creating a user in the database
 * <p>
 * Usage: new CreateUserTask().execute(username, name, birthday, gender);
 * </p>
 * 
 * @author Max Hinson
 */
public final class CreateUserTask extends AsyncTask<String, Void, Void> {

	private String username;
	private String name;
	private String birthday;
	private String gender;
	
	@Override
	protected Void doInBackground(String... user) {

		// Initialize SQL statement
		String s = "INSERT INTO Users"
				+ " VALUES (?, ?, ?, ?, 0, 0)";
		
		try {

		// Prepare statement
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(s);

		// Set member variables
		username = user[0];
		name = user[1];
		birthday = user[2];
		gender = user[3];
		
		// Insert input values
		statement.setString(1, username);
		statement.setString(2, name);
		statement.setString(3, birthday);
		statement.setString(4, gender);

		// Execute the statement
		statement.executeUpdate();

		// Clean up
		statement.close();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	protected void onPostExecute(Void test) {
		
		User.setLoggedIn();
		User.setUsername(username);
		User.setName(name);
		User.setBirthday(birthday);
		User.setGender(gender);
		User.setNumAugsCreated(0);
		User.setNumAugsShared(0);
		System.out.println("DEBUG: User logged in as " + name + " (" + username + ")");
		
	}

}
