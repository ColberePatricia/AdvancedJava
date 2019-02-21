public class Driver {

	public static void main(String[] args) {
	
		Duration firstDuration = new Duration(109);
		String[] psychoCast = {"Anthony Perkins", "Janet Leigh"};
							  
		VideoRecording firstRecording = 
			new VideoRecording("Alfred Hitchcock", psychoCast,
							   "R", 1960,
							   "Psycho", 20.99,
							   "Horror", "", firstDuration);
							  
							  
		Duration secondDuration = new Duration(98);
		String[] caddyCast = {"Chevy Chase", "Rodney Dangerfield", "Bill Murray",
							  "Michael O'Keefe", "Ted Knight"};
							  
		VideoRecording secondRecording = 
			new VideoRecording("Harold Ramis", caddyCast,
							   "R", 1980,
							   "Caddyshack", 12.99,
							   "Comedy", "", secondDuration);
		
		System.out.println(firstRecording);
		System.out.println();
		System.out.println(secondRecording);
	}
	
}