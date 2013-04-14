package edu.ucsb.cs.epsilon.ucsb360;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Application;

/**
 * Class that manages global application state
 * 
 * @author Max Hinson
 */
public class Global extends Application {

	private static boolean appVisible;
	private static Timer timer = new Timer();
	
	public static boolean isApplicationVisible() {
		return appVisible;
	}  

	/**
	 * Called when an activity in the application has been resumed
	 * 
	 * @author Max Hinson
	 */
	public static void applicationResumed() {

		appVisible = true;
		if(!DatabaseManager.isConnected()) {
			DatabaseManager.Connect();
			System.out.println("Database connection created");
		}
		
	}

	/**
	 * Called when an activity in the application has been paused
	 * 
	 * @author Max Hinson
	 */
	public static void applicationPaused() {
		
		appVisible = false;
		
		// Schedule a 5 second timeout
		timer.schedule(new TimerTask() {
		     @Override
		     public void run() {
		    	 if(!appVisible) {
		    		 DatabaseManager.Disconnect();
		    		 System.out.println("Database connection terminated");
		    	 }
		     }
		}, 5000);

	}

}