package edu.ucsb.cs.epsilon.ucsb360;

/**
 * Class for storing augmentation information for a single target
 * 
 * @author Max Hinson
 */
public final class Augmentation {

	private int id;
	private String date;
	private String creatorId;
	private String creatorName;
	private String privacy;
	private int views;
	private int likes;
	private boolean likedByUser;
	private String url;
	
	/**
	 * Constructor for Augmentation class
	 * 
	 * @author Max Hinson
	 * @param id augmentation identifier
	 * @param date date the augmentation was created
	 * @param creatorId id of the user who created the augmentation
	 * @param creatorName name of the user who created the augmentation
	 * @param privacy who can see this augmentation
	 * @param views times the augmentation has been viewed
	 * @param likes number of likes for the augmentation
	 * @param url URL to get the augmentation image
	 */
	public Augmentation(int id, String date, String creatorId, String creatorName, String privacy,
			int views, int likes, boolean likedByUser, String url) {
		this.id = id;
		this.date = date;
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.views = views;
		this.likes = likes;
		this.likedByUser = likedByUser;
		this.url = url;
	}

	/**
	 * @author Max Hinson
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @author Max Hinson
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @author Max Hinson
	 * @return the creator
	 */
	public String getCreatorId() {
		return creatorId;
	}
	
	/**
	 * @author Max Hinson
	 * @return the creator
	 */
	public String getCreatorName() {
		return creatorName;
	}
	
	/**
	 * @author Max Hinson
	 * @return the privacy setting
	 */
	public String getPrivacy() {
		return privacy;
	}

	/**
	 * @author Max Hinson
	 * @return the views
	 */
	public int getViews() {
		return views;
	}

	/**
	 * @author Max Hinson
	 * @return the likes
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * @author Max Hinson
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * @author Jhon Nassiri
	 * @return the views
	 */
	public void incViews() {
		views++;
	}
	
	/**
	 * @author Jhon Nassiri
	 * @return the likes
	 */
	public void incLikes() {
		likedByUser = true;
		likes++;
	}
	
	/**
	 * @author Max Hinson
	 * @return whether or not the user has liked the augmentation
	 */
	public boolean isLikedByUser() {
		return likedByUser;
	}

}
