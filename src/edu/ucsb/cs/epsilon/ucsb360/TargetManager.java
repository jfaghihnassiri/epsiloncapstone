package edu.ucsb.cs.epsilon.ucsb360;

import java.util.HashMap;

/**
 * This class will hold and manage all open Target objects
 * 
 * @author Max Hinson
 * @see Target
 */
public final class TargetManager {

	private static HashMap<String, Target> targets = new HashMap<String, Target>();
	private static final String url = "https://s3-us-west-1.amazonaws.com/teamepsilon/augmentations/";
	private static final String ext = ".jpg";
	
	/**
	 * Add another Target object to keep track of
	 * 
	 * @author Max Hinson
	 * @param targetId Identifier of the Target that will be added
	 */
	public static void addTarget(String targetId) {
		targets.put(targetId, new Target(targetId));
	}
	
	/**
	 * Check whether or not we have loaded a Target
	 * 
	 * @author Max Hinson
	 * @param targetId Identifier of the Target to check
	 * @return true/false
	 */
	public static Boolean checkTarget(String targetId) {
		return targets.containsKey(targetId);
	}
	
	/**
	 * Get the type for a Target
	 * 
	 * @author Max Hinson
	 * @param targetId Target identifier
	 * @return Target type
	 */
	public static String getType(String targetId) {
		return targets.get(targetId).getType();
	}
	
	/**
	 * Get the date for a Target
	 * 
	 * @author Max Hinson
	 * @param targetId Target identifier
	 * @return Target date
	 */
	public static String getDate(String targetId) {
		return targets.get(targetId).getDate();
	}
	
	/**
	 * Get the creator for a Target
	 * 
	 * @author Max Hinson
	 * @param targetId Target identifier
	 * @return Target creator
	 */
	public static String getCreator(String targetId) {
		return targets.get(targetId).getCreator();
	}
	
	/**
	 * Get the message for a Target
	 * 
	 * @author Max Hinson
	 * @param targetId Target identifier
	 * @return Target message
	 */
	public static String getMessage(String targetId) {
		return targets.get(targetId).getMessage();
	}
	
	/**
	 * Get the views for a Target
	 * 
	 * @author Max Hinson
	 * @param targetId Target identifier
	 * @return Target views
	 */
	public static int getViews(String targetId) {
		return targets.get(targetId).getViews();
	}
	
	/**
	 * Clear a target
	 * 
	 * @author Max Hinson
	 * @param targetId Identifier of the Target to clear
	 */
	public static void clearTarget(String targetId) {
		targets.remove(targetId);
	}
	
	/**
	 * Clear all loaded Targets
	 * 
	 * @author Max Hinson
	 */
	public static void clearAllTargets() {
		targets.clear();
	}
	
	/**
	 * Get the augmentation URL for a Target
	 * 
	 * @author Max Hinson
	 * @param targetId Identifier of the Target to get URL for
	 * @return null if target ID has not been loaded, correct URL otherwise
	 */
	public static String getAugmentationUrl(String targetId) {
		if(targets.containsKey(targetId))
			return url + targetId + ext;
		else
			return null;
	}
	
}
