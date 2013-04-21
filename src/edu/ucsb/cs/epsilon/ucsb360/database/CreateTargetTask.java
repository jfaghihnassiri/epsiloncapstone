package edu.ucsb.cs.epsilon.ucsb360.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
import edu.ucsb.cs.epsilon.ucsb360.Target;
import edu.ucsb.cs.epsilon.ucsb360.TargetManager;
import edu.ucsb.cs.epsilon.ucsb360.User;

import android.os.AsyncTask;

/**
 * Asynchronous task for creating a new target
 * <p>
 * Usage: new CreateTargetTask().execute(targetId);
 * </p>
 * 
 * @author Max Hinson
 */
public final class CreateTargetTask extends AsyncTask<String, Void, Void> {

	private String targetId;
	private String date;
	private String creator;
	
	@Override
	protected Void doInBackground(String... params) {

		// Initialize SQL statements
		String s = "INSERT INTO Targets"
				+ " VALUES (?, ?, ?)";

		targetId = params[0];
		date = Calendar.getInstance().getTime().toString();
		creator = User.getUsername();
		
		try {

			// Initialize prepared statement
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(s);

			// Insert variables into statement
			statement.setString(1, targetId);
			statement.setString(2, date);
			statement.setString(3, creator);

			// Execute the statement
			statement.executeUpdate();

			// Clean up
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void v) {
		TargetManager.addTarget(new Target(targetId, date, creator));
	}

}