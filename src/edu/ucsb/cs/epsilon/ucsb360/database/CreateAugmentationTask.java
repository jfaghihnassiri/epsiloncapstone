//package edu.ucsb.cs.epsilon.ucsb360.database;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Calendar;
//
//import edu.ucsb.cs.epsilon.ucsb360.DatabaseManager;
//import edu.ucsb.cs.epsilon.ucsb360.TargetManager;
//import edu.ucsb.cs.epsilon.ucsb360.User;
//import android.os.AsyncTask;
//
///**
// * Asynchronous task to create a new augmentation
// * <p>
// * Usage: new CreateAugmentationTask().execute(targetId, message, xPos, yPos, size);
// * </p>
// * 
// * @author Max Hinson
// */
//public final class CreateAugmentationTask extends AsyncTask<String, Void, Integer> {
//
//	private String targetId;
//	private String date;
//	private String message;
//	private int xPos;
//	private int yPos;
//	private double size;
//	
//	@Override
//	protected Integer doInBackground(String... params) {
//
//		// Initialize SQL statement
//		String s = "INSERT INTO Augmentations"
//				+ " (tarId, date, creator, message, views, likes, xPos, yPos, size)"
//				+ " VALUES (?, ?, ?, ?, 0, 0, ?, ?, ?)";
//
//		targetId = params[0];
//		date = Calendar.getInstance().getTime().toString();
//		message = params[1];
//		xPos = Integer.parseInt(params[2]);
//		yPos = Integer.parseInt(params[3]);
//		size = Double.parseDouble(params[4]);
//		
//		try {
//
//			// Prepare statement
//			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
//
//			// Insert input values
//			statement.setString(1, targetId);
//			statement.setString(2, date);
//			statement.setString(3, User.getUsername());
//			statement.setString(4, message);
//			statement.setInt(5, xPos);
//			statement.setInt(6, yPos);
//			statement.setDouble(7, size);
//
//			// Execute the statement
//			statement.executeUpdate();
//
//			int augId = -1;
//			ResultSet rs = statement.getGeneratedKeys();
//			if(rs.next())
//				augId = rs.getInt(1);
//
//			// Clean up
//			rs.close();
//			statement.close();
//
//			return augId;
//
//		} catch(SQLException e) {
//			e.printStackTrace();
//			return -1;
//		}
//
//	}
//	
//	@Override
//	protected void onPostExecute(Integer augId) {
//		String full_url = TargetManager.getUrl() + targetId + "/" + augId + TargetManager.getExt();
//		TargetManager.getTarget(targetId).createAug(augId, date, User.getUsername(), message, 0, 0, xPos, yPos, size, full_url);
//	}
//
//}
