public class Driver {

  public static void main(String[] args) {

    System.out.println("Welcome to the RainForest");
	System.out.println("Please browse our music categories:");
	System.out.println();

	for (int i=0; i < args.length; i++) {
	
		System.out.println(i + ": " + args[i].toUpperCase());
	}
  }
}
