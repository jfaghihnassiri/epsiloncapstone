package edu.ucsb.cs.epsilon.ucsb360.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import android.os.AsyncTask;
import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.TargetManager;

/**
 * Asynchronous task for incrementing the number of likes for an augmentation
 * <p>
 * Usage: new IncAugLikesTask().execute(targetId, augId);
 * </p>
 * 
 * @author Max Hinson
 */
public final class IncAugLikesTask extends AsyncTask<String, Void, Boolean> {

	private String targetId;
	private int augIndex;

	@Override
	protected Boolean doInBackground(String... params) {

		// Initialize SQL statement
		String s = "UPDATE Augmentations"
				+ " SET likes = likes + 1"
				+ " WHERE tarId = ? AND augId = ?";

		targetId = params[0];
		augIndex = Integer.parseInt(params[1]);

		try {

			// Prepare statement
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(s);

			// Insert input values
			statement.setString(1, targetId);
			statement.setInt(2, augIndex);

			// Execute the statement
			int rowsUpdated = statement.executeUpdate();

			// Clean up
			statement.close();

			// Make sure the entry was updated
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
			TargetManager.incAugLikes(targetId, augIndex);

	}

}
