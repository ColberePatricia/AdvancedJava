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
	}
}