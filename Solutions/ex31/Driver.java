public class Driver {

	public static void main(String[] args) {
	
		// create the track
		Track myTrack = new Track();
		
		// set the track's title
		myTrack.setTitle("Watching The Wheels");
		
		// set the track's duration
		Duration myDuration = new Duration(0, 3, 48);
		myTrack.setDuration(myDuration);
		
		// now display the information
		System.out.println("Title = " + myTrack.getTitle());
		System.out.println("Duration = " + myTrack.getDuration());
	}
}