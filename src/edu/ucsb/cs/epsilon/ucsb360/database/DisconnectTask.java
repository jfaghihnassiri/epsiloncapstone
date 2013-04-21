package edu.ucsb.cs.epsilon.ucsb360.database;

import java.sql.SQLException;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;

import android.os.AsyncTask;

/**
 * Asynchronous task for disconnecting from the database
 * <p>
 * Usage: new DisconnectTask().execute();
 * </p>
 * 
 * @author Max Hinson
 */
public final class DisconnectTask extends AsyncTask<Void, Void, Void> {

	@Override
	protected Void doInBackground(Void... params) {
		
		try {
			DatabaseManager.getConnection().close();
		    System.out.println("DEBUG: Database connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		    System.out.println("DEBUG: Database connection close error");
		}
		
		return null;
		
	}

}
