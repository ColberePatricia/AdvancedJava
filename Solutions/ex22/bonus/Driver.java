public class Driver {

	public static void main(String[] args) {
	
		Duration firstDuration = new Duration(1, 15, 45);
		
		System.out.println(firstDuration);
		System.out.println("Hours = " + firstDuration.getHours());
		
		System.out.println("Minutes = " + firstDuration.getMinutes());
		System.out.println("Seconds = "  + firstDuration.getSeconds());
		
		System.out.println("Total Seconds = " + firstDuration.getTotalSeconds());
		
		Duration secondDuration = new Duration(2700);
		System.out.println(secondDuration);
		
		Duration sum = firstDuration.add(secondDuration);
		System.out.println("Sum = " + sum);
		
		//
		//  BEGIN BONUS WORK
		//
		
		//  subtract the durations
		//
		Duration diff= firstDuration.subtract(secondDuration);
		System.out.println("Difference = " + diff);
		System.out.println();
		
		// check to see if there is a parameter for the third Duration object
		//
		if (args.length == 1) {
			String totalSecsStr = args[0];
			int totalSecs = Integer.parseInt(totalSecsStr);
			Duration thirdDuration = new Duration(totalSecs);
			System.out.println("3rd Duration = " + thirdDuration);
			System.out.println();
		}

		// create an array of Duration objects and print them out
		//
		int[] secondsArray = {2345, 3333, 5412, 8822, 34};
		Duration[] durationArray = new Duration[5];
		
		int totalSeconds;
		for (int i=0; i < secondsArray.length; i++) {
			totalSeconds = secondsArray[i];
			durationArray[i] = new Duration(totalSeconds);
			System.out.println(i + ": " + durationArray[i]);
		}
	}
}