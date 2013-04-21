package edu.ucsb.cs.epsilon.ucsb360.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;

import android.os.AsyncTask;

/**
 * Asynchronous task for deleting an augmentation
 * <p>
 * Usage: new DeleteAugmentationTask().execute(targetId, augId);
 * </p>
 * 
 * @author Max Hinson
 */
public final class DeleteAugmentationTask extends AsyncTask<String, Void, Void> {

	@Override
	protected Void doInBackground(String... params) {

		// Initialize SQL statement
		String s = "DELETE FROM Augmentations"
				+ " WHERE tarId = ? AND augId = ?";

		try {

			// Prepare statement
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(s);

			// Insert input values
			statement.setString(1, params[0]);
			statement.setInt(2, Integer.parseInt(params[1]));

			// Execute the statement
			statement.executeUpdate();

			// Clean up
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
