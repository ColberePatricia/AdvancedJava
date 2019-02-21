public class Track {

	// define fields
	protected String title;
	protected Duration duration;
	
	// define methods
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String aTitle) {
		title = aTitle;
	}
	
	public Duration getDuration() {
		return duration;
	}
	
	public void setDuration(Duration aDuration) {
		duration = aDuration;
	}
	
	//
	//  BONUS WORK
	//
	
	/**
	 *  Returns a formatted string:  title, duration
	 */
	public String toString() {
		
		String result = title + ", " + duration;
		
		return result;
	}
}