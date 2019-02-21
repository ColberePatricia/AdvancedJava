/**
 *  This class represents a video recording.  The video recording has the 
 *  following additional properties: <p>
 *
 *  <ul>
 *      <li>producer
 *      <li>list of actors/actresses
 *      <li>rating
 *      <li>year released
 *  </ul>
 *
 *  @author, App Development Team
 *
 */
public class VideoRecording extends Recording {

	/**
	 *  The name of the movie producer
	 */
	protected String producer;
	
	/**
	 *  A list of actors/actresses in the video
	 */
	protected String[] actors;
	
	/**
	 *  Rating:  G, PG, R, XXX
	 */
	protected String rating;
	
	/**
	 *  The year the video was released
	 */
	protected int yearReleased;
	
	/**
	 *  Creates a VideoRecording object with given parameter values
	 */
	public VideoRecording(String theProducer, String[] theActors, String theRating, int theYearReleased,
						  String theTitle, double thePrice, 
						  String theCategory, String theImageName,
						  Duration theDuration) {
						  
		super(theTitle, thePrice, theCategory, theImageName, theDuration);

		producer = theProducer;
		actors = theActors;
		rating = theRating;
		yearReleased = theYearReleased;
	}

	/**
	 *  Returns the year the video was released.  The year is a 4-digit year.
	 */
	public int getYearReleased() {
		return yearReleased;
	}
	
	/**
	 *  Returns the rating for this video (ie G, PG, R, XXX)
	 */
	public String getRating() {
		return rating;
	}
	
	/**
	 *  Returns the name of the producer
	 */
	public String getProducer() {
		return producer;
	}
	
	/**
	 *  Returns a list of actors/actresses
	 */
	public String[] getActors() {
		return actors;
	}
	
	/**
	 *  Returns the duration for the video
	 */
	public Duration getDuration() {
	
		return duration;
	}
	
	/**
	 *  Returns the title of recording
	 */
	public String toString() {
		return title;
	}
	
	
}