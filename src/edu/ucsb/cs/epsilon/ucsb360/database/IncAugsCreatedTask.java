package edu.ucsb.cs.epsilon.ucsb360.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import android.os.AsyncTask;
import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.User;

/**
 * Asynchronous task for increasing the number of augmentations a user has created
 * <p>
 * Usage: new IncAugsCreatedTask().execute(username);
 * </p>
 * 
 * @author Max Hinson
 */
public final class IncAugsCreatedTask extends AsyncTask<Void, Void, Boolean> {

	@Override
	protected Boolean doInBackground(Void... params) {

		// Initialize SQL statement
		String s = "UPDATE Users"
				+ " SET numAugsCreated = numAugsCreated + 1"
				+ " WHERE username = ?";

		try {

			// Prepare statement
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(s);

			// Insert input values
			statement.setString(1, User.getUsername());

			// Execute the statement
			int rowsUpdated = statement.executeUpdate();

			// Clean up
			statement.close();

			// Make sure the entry is updated
			if(rowsUpdated == 1)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	@Override
	protected void onPostExecute(Boolean databaseUpdated) {
		if(databaseUpdated)
			User.incNumAugsCreated();
	}

}
