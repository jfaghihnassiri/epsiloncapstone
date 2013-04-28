package edu.ucsb.cs.epsilon.ucsb360;

/**
 * Class for storing augmentation information for a single target
 * 
 * @author Max Hinson
 */
public final class Augmentation {

	private int id;
	private String date;
	private String creator;
	private String privacy;
	private String message;
	private int views;
	private int likes;
	private int xPos;
	private int yPos;
	private double size;
	private String url;
	
	/**
	 * Constructor for Augmentation class
	 * 
	 * @author Max Hinson
	 * @param id augmentation identifier
	 * @param date date the augmentation was created
	 * @param creator user who created the augmentation
	 * @param privacy who can see this augmentation
	 * @param message message attached to augmentation
	 * @param views times the augmentation has been viewed
	 * @param likes number of likes for the augmentation
	 * @param xPos x-position to display the augmentation
	 * @param yPos y-position to display the augmentation
	 * @param size size of the augmentation
	 * @param url URL to get the augmentation image
	 */
	public Augmentation(int id, String date, String creator, String privacy, String message,
			int views, int likes, int xPos, int yPos, double size, String url) {
		this.id = id;
		this.date = date;
		this.creator = creator;
		this.message = message;
		this.views = views;
		this.likes = likes;
		this.xPos = xPos;
		this.yPos = yPos;
		this.size = size;
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
	public String getCreator() {
		return creator;
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
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
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @author Max Hinson
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @author Max Hinson
	 * @return the size
	 */
	public double getSize() {
		return size;
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
	public int incViews() {
		views = views + 1;
		return views;
	}
	
	/**
	 * @author Jhon Nassiri
	 * @return the likes
	 */
	public int incLikes() {
		likes = likes + 1;
		return likes;
	}

}
