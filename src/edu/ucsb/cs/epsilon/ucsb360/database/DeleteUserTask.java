package edu.ucsb.cs.epsilon.ucsb360.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;

import android.os.AsyncTask;

/**
 * Asynchronous task for deleting a user from the database
 * <p>
 * Usage: new DeleteUserTask().execute(username);
 * </p>
 * 
 * @author Max Hinson
 */
public final class DeleteUserTask extends AsyncTask<String, Void, Void> {

	@Override
	protected Void doInBackground(String... username) {

		// Initialize SQL statement
		String s = "DELETE FROM Users"
				+ " WHERE username = ?";

		try {

			// Prepare statement
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(s);

			// Insert input values
			statement.setString(1, username[0]);

			// Execute the statement
			statement.executeUpdate();

			// Clean up
			statement.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
