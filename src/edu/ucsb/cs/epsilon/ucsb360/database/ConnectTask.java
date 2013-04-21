package edu.ucsb.cs.epsilon.ucsb360.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;

import android.os.AsyncTask;

/**
 * Asynchronous task for connecting to the database
 * <p>
 * Usage: new ConnectTask().execute();
 * </p>
 * 
 * @author Max Hinson
 */
public final class ConnectTask extends AsyncTask<Void, Void, Connection> {

	@Override
	protected Connection doInBackground(Void... params) {
		
		Connection connection = null;
		
		// Check to see if we can find the driver
		try {
			Class.forName(DatabaseManager.getDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DEBUG: JDBC driver not found");
		}

		// Try opening a connection to the database
		try {
			connection = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUsername(), DatabaseManager.getPassword());
			System.out.println("DEBUG: Database connection opened");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DEBUG: Database connection failed");
		}

		return connection;
		
	}
	
	@Override
	protected void onPostExecute(Connection connection) {
		DatabaseManager.setConnection(connection);
	}

}