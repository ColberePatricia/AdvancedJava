public class Driver {

	public static void main(String[] args) {
	
		//
		//  BONUS WORK
		//
		
		// make sure they pass in arguments
		if (args.length != 3) {
		
			System.out.println("Usage:  java Driver title durationMinutes durationSeconds");
			System.exit(0);
		}
		
		// create the track
		Track myTrack = new Track();
		
		// get the title as the first argument
		String theTitle = args[0];
		
		// set the track's title
		myTrack.setTitle(theTitle);
		
		
		// get the duration minutes
		String minStr = args[1];
		
		// convert the string to an int
		int min = Integer.parseInt(minStr);
		
		// get the duration seconds
		String secStr = args[2];
		
		// convert the string to an int
		int sec = Integer.parseInt(secStr);
		
		// set the track's duration
		Duration myDuration = new Duration(0, min, sec);
		myTrack.setDuration(myDuration);
		
		// now display the information...use the bonus toString() method in the Track class
		System.out.println(myTrack);
		System.out.println();
	}
}