/**
 *  This class represents a Recording.  It describes the recording
 *  title, price, category, imageName and duration.  <p>
 *
 *  Subclasses must implement the method <code>getDuration()</code> to
 *  return the total duration for the recording.
 *
 *  @author Development Team
 */
public abstract class Recording {

	//
	//  DATA MEMBERS
	//
	
	/**
	 *  The recording title 
	 */
	protected String title;

	/**
	 *  The recording price 
	 */
	protected double price;
	
	/**
	 *  The recording category
	 */
	protected String category;
	
	/**
	 *  The recording image name
	 */
	protected String imageName;
	
	/**
	 *  The recording duration / running time
	 */
	protected Duration duration;


	//
	//  CONSTRUCTORS
	//
	
	/** 
	 *  Basic default constructor  
	 */
	public Recording() {
		// default constructor
	}

	
	/**
	 *  Constructs a recording w/ given parameter values
	 */
	public Recording(String theTitle, double thePrice, 
					 String theCategory, String theImageName,
					 Duration theDuration) {
	
		title = theTitle;
		price = thePrice;
		category = theCategory;
		imageName = theImageName;
		duration = theDuration;
	}

	/**
	 *  Constructs a recording w/ given parameter values.
	 *  This version allows you to create a recording w/ an empty duration.
	 */
	public Recording(String theTitle, double thePrice, 
					 String theCategory, String theImageName) {

		this(theTitle, thePrice, theCategory, theImageName, new Duration());	
	}


	//
	//  GETTER / SETTER METHODS
	//
	
	/**
	 *  Returns the recording title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 *  Sets the recording title
	 */
	public void setTitle(String theTitle) {
		title = theTitle;
	}


	/**
	 *  Returns the recording price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 *  Sets the recording price
	 */
	public void setPrice(double thePrice) {
		price = thePrice;
	}


	/**
	 *  Returns the recording category
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 *  Sets the recording category
	 */
	public void setCategory(String theCategory) {
		category = theCategory;
	}


	/**
	 *  Returns the recording image name
	 */
	public String getImageName() {
		return imageName;
	}
	
	/**
	 *  Sets the recording image name
	 */
	public void setImageName(String theImageName) {
		imageName = theImageName;
	}
	
	
	//
	//  ABSTRACT METHOD(S)
	//
	
	/**
	 *  Returns the recording duration.  Subclasses must
	 *  override this method to return the total duration.
	 */
	public abstract Duration getDuration();	
	
}