package edu.ucsb.cs.epsilon.ucsb360.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.User;

import android.os.AsyncTask;

/**
 * Asynchronous task for getting user information
 * 
 * @author Max Hinson
 */
public final class UserLoginTask extends AsyncTask<String, Void, String[]> {

	private String username;
	private String name;
	private String birthday;
	
	@Override
	protected String[] doInBackground(String... u) {


		// Initialize SQL statement
		String s = "SELECT *"
				+ " FROM Users"
				+ " WHERE username = ?";

		username = u[0];
		name = u[1];
		birthday = u[2];
		
		try {

			// Prepare statement
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(s);

			// Insert input values
			statement.setString(1, username);

			// Execute the statement
			ResultSet rs = statement.executeQuery();

			// Read user info into array
			if(rs.first() == false)
				System.out.println("DEBUG: GetUserInfoTask: ResultSet is empty");
			int cols = rs.getMetaData().getColumnCount();
			String[] ret = new String[cols];
			for(int i = 0; i < cols; i++)
				ret[i] = rs.getString(i+1);

			// Clean up
			rs.close();
			statement.close();

			return ret;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	protected void onPostExecute(String[] data) {

		if(data == null)
			new CreateUserTask().execute(username, name, birthday, "");
		else {
			User.setLoggedIn();
			User.setUsername(data[0]);
			User.setName(data[1]);
			User.setBirthday(data[2]);
			User.setGender(data[3]);
			User.setNumAugsCreated(Integer.parseInt(data[4]));
			User.setNumAugsShared(Integer.parseInt(data[5]));
			System.out.println("DEBUG: User logged in as " + data[1] + " (" + data[0] + ")");
		}

	}

}
