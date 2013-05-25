package edu.ucsb.cs.epsilon.ucsb360;

import java.util.ArrayList;

/**
 * Simple container for target attributes
 * 
 * @author Max Hinson
 */
public final class Target {

	private ArrayList<Augmentation> augmentations = new ArrayList<Augmentation>();
	private String id;
	private String date;
	private String creator;
	private String url;
	
	/**
	 * Constructor for Target class
	 * 
	 * @author Max Hinson
	 * @param id target id
	 * @param date date of target creation
	 * @param creator user who created the target
	 */
	public Target(String id, String date, String creator) {
		this.id = id;
		this.date = date;
		this.creator = creator;
		this.url = TargetManager.getUrl() + id + "/target.png";
	}
	
	/**
	 * Get the target's identifier
	 * 
	 * @author Max Hinson
	 * @return Target identifier
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Get the target's date of creation
	 * 
	 * @author Max Hinson
	 * @return Date target was created
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Get the user who created the target
	 * 
	 * @author Max Hinson
	 * @return Target creator
	 */
	public String getCreator() {
		return creator;
	}
	
	/**
	 * Get the URL for the target's image
	 * 
	 * @author Max Hinson
	 * @return target URL
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Get the number of views
	 * 
	 * @author Max Hinson
	 * @return Number of views
	 */
	public int getViews() {
		
		int views = 0;
		
		for(int i = 0; i < augmentations.size(); i++)
			views += augmentations.get(i).getViews();
		
		return views;
		
	}
	
	/**
	 * Get the number of augemntations of a target
	 * 
	 * @author Max Hinson
	 * @return Number of augmentations
	 */
	public int getNumAugs() {
		return augmentations.size();
	}
	
	/**
	 * Create a new instantiation of Augmentation class
	 * 
	 * @author Jhon Nassiri
	 * @param message The message that is associated with the Augmentation
	 * @param xPos the x position of the augmentation in reference to the target
	 * @param yPos the y postiion of the augmentation in reference to the target
	 * @param size the size of the augmentation in reference to the target
	 */
	public void createAug(int aug_id, String aug_date, String creatorId, String creatorName, String privacy, int views, int likes, boolean likedByUser, String url) {
		augmentations.add(new Augmentation(aug_id, aug_date, creatorId, creatorName, privacy, views, likes, likedByUser, url));
	}
	
	/**
	 * Get augmentation identifier
	 * 
	 * @author Jhon Nassiri
	 * @author Max Hinson
	 * @param augIndex the index of an augmentation into the ArrayList
	 * @return augmentation identifier
	 */
	public int getAugId(int augIndex) {
		return augmentations.get(augIndex).getId();
	}
	
	/**
	 * Get augmentation date
	 * 
	 * @author Jhon Nassiri
	 * @author Max Hinson
	 * @param augIndex the index of an augmentation into the ArrayList
	 * @return augmentation date
	 */
	public String getAugDate(int augIndex) {
		return augmentations.get(augIndex).getDate();
	}
	
	/**
	 * Get augmentation creator id
	 * 
	 * @author Jhon Nassiri
	 * @author Max Hinson
	 * @param augIndex the index of an augmentation into the ArrayList
	 * @return augmentation creator id
	 */
	public String getAugCreatorId(int augIndex) {
		return augmentations.get(augIndex).getCreatorId();
	}
	
	/**
	 * Get augmentation creator name
	 * 
	 * @author Max Hinson
	 * @param augIndex the index of an augmentation into the ArrayList
	 * @return augmentation creator name
	 */
	public String getAugCreatorName(int augIndex) {
		return augmentations.get(augIndex).getCreatorName();
	}
	
	/**
	 * Get augmentation privacy setting
	 * 
	 * @author Max Hinson
	 * @param augIndex the index of an augmentation into the ArrayList
	 * @return augmentation privacy
	 */
	public String getAugPrivacy(int augIndex) {
		return augmentations.get(augIndex).getPrivacy();
	}
	
	/**
	 * Get augmentation views
	 * 
	 * @author Jhon Nassiri
	 * @author Max Hinson
	 * @param augIndex the index of an augmentation into the ArrayList
	 * @return augmentation views
	 */
	public int getAugViews(int augIndex) {
		return augmentations.get(augIndex).getViews();
	}
	
	/**
	 * Get augmentation likes
	 * 
	 * @author Jhon Nassiri
	 * @author Max Hinson
	 * @param augIndex the index of an augmentation into the ArrayList
	 * @return augmentation likes
	 */
	public int getAugLikes(int augIndex) {
		return augmentations.get(augIndex).getLikes();
	}
	
	/**
	 * 
	 * @param augIndex the index of the augmentation into the ArrayList
	 * @return whether or not the augmentation has been liked by the user
	 */
	public boolean isAugLikedByUser(int augIndex) {
		return augmentations.get(augIndex).isLikedByUser();
	}
	
	/**
	 * Get augmentation url
	 * 
	 * @author Jhon Nassiri
	 * @author Max Hinson
	 * @param augIndex the index of an augmentation into the ArrayList
	 * @return augmentation url
	 */
	public String getAugUrl(int augIndex) {
		return augmentations.get(augIndex).getUrl();
	}
	
	/**
	 * Increment augmentation views
	 * 
	 * @author Jhon Nassiri
	 * @author Max Hinson
	 * @param augIndex the index of an augmentation into the ArrayList
	 * @return augmentation views
	 */
	public void incAugViews(int augIndex) {
		augmentations.get(augIndex).incViews();
	}
	
	/**
	 * Increment augmentation likes
	 * 
	 * @author Jhon Nassiri
	 * @author Max Hinson
	 * @param augIndex the index of an augmentation into the hashmap
	 * @return augmentation likes
	 */
	public void incAugLikes(int augIndex) {
		augmentations.get(augIndex).incLikes();
	}

}
