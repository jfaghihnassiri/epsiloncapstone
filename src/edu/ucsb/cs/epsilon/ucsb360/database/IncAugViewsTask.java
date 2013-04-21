package edu.ucsb.cs.epsilon.ucsb360.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import android.os.AsyncTask;
import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.TargetManager;

/**
 * Asynchronous task to increase the number of views for an augmentation
 * <p>
 * Usage: new IncAugViewsTask().execute(targetId, augId);
 * </p>
 * 
 * @author Max Hinson
 */
public final class IncAugViewsTask extends AsyncTask<String, Void, Boolean> {

	private String targetId;
	private int augIndex;
	private int augId;
	
	@Override
	protected Boolean doInBackground(String... params) {

		// Initialize SQL statement
		String s = "UPDATE Augmentations"
				+ " SET views = views + 1"
				+ " WHERE tarId = ? AND augId = ?";

		targetId = params[0];
		augIndex = Integer.parseInt(params[1]);
		augId = TargetManager.getTarget(targetId).getAugId(augIndex);
		
		try {

			// Prepare statement
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(s);

			// Insert input values
			statement.setString(1, targetId);
			statement.setInt(2, augId);
			
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
			TargetManager.incAugViews(targetId, augIndex);
		
	}

}
