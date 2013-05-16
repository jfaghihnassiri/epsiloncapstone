package edu.ucsb.cs.epsilon.ucsb360;

import java.util.Calendar;
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
	private static boolean connecting = false;
	private static boolean loggingIn = false;
	private static Timer timer = new Timer();
	private static String date;
	
	public static void setConnecting(boolean isConnecting) {
		connecting = isConnecting;
	}
	
	public static void setLoggingIn(boolean isLoggingIn) {
		loggingIn = isLoggingIn;
	}
	
	public static boolean isLoggingIn() {
		return loggingIn;
	}
	
	private static void setDate() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);
		date = month + "/" + day + "/" + year;
	}
	
	public static String getDate() {
		return date;
	}
	
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
		if(!DatabaseManager.isConnected() && !connecting) {
			DatabaseManager.connect();
			setDate();
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
		    		 if(DatabaseManager.isConnected())
		    			 DatabaseManager.disconnect();
		    	 }
		     }
		}, 5000);

	}

}