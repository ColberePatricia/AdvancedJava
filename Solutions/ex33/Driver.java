import java.util.*;

public class Driver {

	public static void main(String args[]) {
	
		Duration tempDuration = null;
		Track[] myTrackList = new Track[3];

		//
		//  1.  Create a music recording for MILES DAVIS														
		//
		tempDuration = new Duration(314);
		myTrackList[0] = new Track("Move", tempDuration);
	
		tempDuration = new Duration(222);
		myTrackList[1] = new Track("Jeru", tempDuration);
	
		tempDuration = new Duration(351);
		myTrackList[2] = new Track("Moon Dreams", tempDuration);
		
		MusicRecording miles = new MusicRecording("Miles Davis", myTrackList, 
													"Birth of the Cool", 15.99, "Jazz", "");
		
		//
		//  2.  Create a music recording for KENNY G
		//		
		myTrackList = new Track[3];		
		tempDuration = new Duration(240);
		myTrackList[0] = new Track("The Joy Of Life", tempDuration);
	
		tempDuration = new Duration(241);
		myTrackList[1] = new Track("Forever In Love", tempDuration);
	
		tempDuration = new Duration(228);
		myTrackList[2] = new Track("In The Rain", tempDuration);
		
		MusicRecording kenny = new MusicRecording("Kenny G", myTrackList, 
													"Double Fantasy", 15.99, "Jazz", "");		
		
		//
		//  TO DO:  START ADDING YOUR CODE HERE
		//
		
		// create the array list
		ArrayList musicArrayList = new ArrayList();
		
		MusicDataAccessor myDataAccessor = new MusicDataAccessor();
		musicArrayList = myDataAccessor.getRecordings("Jazz");
		
		// get an iterator for the array list
		Iterator iterator = musicArrayList.iterator();
		
		// iterate thru the list and print out the music recording
		MusicRecording tempRecording = null;
		while (iterator.hasNext()) {
		
			tempRecording = (MusicRecording) iterator.next();
			System.out.println(tempRecording);
			
			System.out.println("Total Duration: " + tempRecording.getDuration());
			System.out.println();
		}
		
		
	
	}
}