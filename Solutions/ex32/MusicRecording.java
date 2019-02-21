public class MusicRecording extends Recording {

	protected String artist;
	protected Track[] trackList;
	
	public MusicRecording(String theArtist, Track[] theTrackList,
							String theTitle, double thePrice,
							String theCategory, String theImageName,
							Duration theDuration) {
	
		super(theTitle, thePrice, theCategory, theImageName, theDuration);
		
		artist = theArtist;
		trackList = theTrackList;
	}

	public MusicRecording(String theArtist, Track[] theTrackList,
							String theTitle, double thePrice,
							String theCategory, String theImageName) {
	
		this(theArtist, theTrackList,
			 theTitle, thePrice, theCategory, theImageName, new Duration());
	}
	
	public String getArtist() {
		return artist;
	}
	
	public Track[] getTrackList() {
		return trackList;
	}
	
	public String toString() {
		String result = artist + ", " + title + "\n";
		
		for (int i=0; i < trackList.length; i++) {
			result = result + trackList[i].toString() + "\n";
		}
		
		return result;
	}
	
	public Duration getDuration() {
		
		Duration totalDuration = new Duration();
		Duration tempDuration;
				
		for (int i=0; i < trackList.length; i++) {
			tempDuration = trackList[i].getDuration();	
			totalDuration = totalDuration.add(tempDuration);
		}
		
		return totalDuration;
	}
}