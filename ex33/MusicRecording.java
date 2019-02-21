/**
 *  This class represents a music recording.  It contains additional
 *  data members for the artist name and a list of tracks (ie songs).
 *
 *   <pre>
 *     Usage Example:
 *
 *        MusicRecording myRecording = new MusicRecording("John Lennon", myTrackList, "Double Fantasy",
 *                                                         12.99, "Rock", "my_image.gif");
 *
 *   </pre>
 *
 *   @author App Development Team
 */
public class MusicRecording extends Recording {

	/**
	 *  Data members
	 */
	protected String artist;
	protected Track trackList[];
	
	/**
	 *  Creates a MusicRecording object with given parameter values
	 */
	public MusicRecording(String theArtist, Track[] theTrackList,
						  String theTitle, double thePrice, 
						  String theCategory, String theImageName,
						  Duration theDuration) {
						  
		super(theTitle, thePrice, theCategory, theImageName, theDuration);
	
		artist = theArtist;
		trackList = theTrackList;
	}

	/**
	 *  Creates a MusicRecording object with given parameter values
	 *  This version creates a MusicRecording w/ an empty duration
	 *
	 */
	public MusicRecording(String theArtist, Track[] theTrackList,
						  String theTitle, double thePrice, 
						  String theCategory, String theImageName) {

		super(theTitle, thePrice, theCategory, theImageName);

		artist = theArtist;
		trackList = theTrackList;
	}

	public String getArtist() {
		return artist;
	}
	
	public Track[] getTrackList() {
		return trackList;
	}
	
	/**
	 *  Returns the recording duration.  
	 *  Overrides the method from Recording.
	 *
	 *  Iterates over the list of tracks and keeps a running
	 *  total of each track's duration.
	 */
	public Duration getDuration() {

		Track tempTrack;	
		Duration tempDuration;
		int total = 0;

		for (int i=0; i < trackList.length; i++) {
			tempTrack = trackList[i];
			tempDuration = 	tempTrack.getDuration();
			total += tempDuration.getTotalSeconds();
		}
		
		return new Duration(total);
	}

	/**
	 *  Returns the artist name and title of recording
	 */
	public String toString() {
		String result = artist + ", " + title + "\n";
		
		for (int i=0; i < trackList.length; i++) {
			result = result + trackList[i].toString() + "\n";
		}
		
		return result;
	}

	/** 
	 *  Allow us to sort the recordings by artist name
	 */
	public int compareTo(Object object) {
	
		MusicRecording recording = (MusicRecording) object;
		String targetArtist = recording.getArtist();
		
		return artist.compareTo(targetArtist);
	}

}