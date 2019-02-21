public class VideoRecording extends Recording {

	/**
	 *  Data members
	 */
	protected String producer;
	protected String[] actors;
	protected String rating;
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

	public int getYearReleased() {
		return yearReleased;
	}
	
	public String getRating() {
		return rating;
	}
	public String getProducer() {
		return producer;
	}
	
	public String[] getActors() {
		return actors;
	}
	
	public Duration getDuration() {
	
		return duration;
	}
	
	/**
	 *  Returns the producer's name and title of recording
	 */
	public String toString() {
		return title;
	}
	
	
}