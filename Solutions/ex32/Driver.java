public class Driver {

	public static void main(String[] args) {
	
		Track[] myTrackList = new Track[3];
		
		Duration firstDuration = new Duration(0, 3, 48);
		myTrackList[0] = new Track("Watching the Wheels", firstDuration);
		
		Duration secondDuration = new Duration(0, 3, 30);
		myTrackList[1] = new Track("Beautiful Boy", secondDuration);

		Duration thirdDuration = new Duration(0, 4, 15);
		myTrackList[2] = new Track("Starting Over", thirdDuration);
		
		MusicRecording myMusicRecording 
					= new MusicRecording("John Lennon", myTrackList, 
										 "Double Fantasy", 12.99, 
										 "Rock", "");
										 
		System.out.println(myMusicRecording);
		System.out.println("Total Duration = " + myMusicRecording.getDuration());
		
	}
}