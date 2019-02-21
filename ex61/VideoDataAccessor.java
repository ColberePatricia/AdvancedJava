import java.util.*;
import java.io.*;
import java.net.*;

/**
 *  This class implements a trivial database for video recordings.  
 *  Methods are available to get a list of the video categories and a 
 *  list of recordings.   <p>
 *
 *  Usage Example:
 *  <pre>
 *
 *    // create and load the data accessor
 *    DataAccessor myDataAccessor = new VideoDataAccessor();  
 *
 *    // get a list of available categories;
 *    ArrayList cats = myDataAccessor.getCategories();
 *    ... 
 *
 *    // get a list of action videos
 *    ArrayList jazzRecordingList = myDataAccessor.getRecordings("Action");
 *    ... 
 *
 *    // add a new recording
 *    myDataAccessor.add(hotRecording);
 *    ... 
 *
 *    // save the information to the data source
 *    myDataAccessor.save();
 *    ... 
 *
 *  </pre>
 *
 *  @author App Development Team
 */
public class VideoDataAccessor extends DataAccessor {


	//////////////////////////////////////////////////////
	//
	//  DATA FILE FORMAT:
	//
	//  The data file has the following format:
	//
	//    producer, video title, category, image name, rating, number minutes, year released, number of actors/actresses
	//    actor/actress #1
	//    actor/actress #2
	//    ----------------------------
	//
	//
	//  Here is a sample file:
	//
	//    Mel Gibson, Braveheart, Action & Adventure, braveheart.gif, R, 177, 2
	//    Mel Gibson
	//    Patrick McGoohan
	//    -----------------
	//
	//
	//  Note that each recording is ended w/ a line separator.  The contents
	//  of the separator is ignored during the read.  The reader simply consumes
	//  an extra line feed after reading a recording.
	//
	
	//
	/**
	 *  The name of the database file to read/write.
	 */
	protected static final String FILE_NAME = "video.db";
	
	/**
	 *  Marker used to separate the video recordings in the data file.
	 */
	protected static final String RECORD_SEPARATOR = "----------";
	
	/**
	 *  Constructs the data accessor and calls the load() method
	 *  to load data.
	 *  
	 */
	public VideoDataAccessor() throws IOException{
	
		// load the data into the table
		load();
	}
	
	String debug;
	
	/**
	 *  Loads the data from a storage device.
	 */
	public void load() throws IOException {
	
		ArrayList videoArrayList = null;
		StringTokenizer st = null;

		VideoRecording myRecording;
		String line = "";

		String producer, title;
		String category, imageName;
		String rating;
		int numberOfMinutes;
		int yearReleased;
		
		int numberOfActors;
		int basePrice;
		double price;  		// all of the recordings are the same LOW price :-)

		String[] actorList;
		
		try
		{
			// use the getResource() method for reading locally or over a network
			URL dataFileUrl = this.getClass().getResource(FILE_NAME);
			log("Loading File: " + dataFileUrl + "...");
			InputStream dataFileInputStream = dataFileUrl.openStream();
			BufferedReader inputFromFile = new BufferedReader(new InputStreamReader(dataFileInputStream));
			
			// read until end-of-file
			while ( (line = inputFromFile.readLine()) != null ) {			
			
				// create a tokenizer for a comma delimited line
				st = new StringTokenizer(line, ",");
		
				//  Parse the info line to read following items formatted as
				//  -  the artist, title, category, imageName, rating, number of minutes, yearReleased, number of actors
				//
				producer = st.nextToken().trim();
				title = st.nextToken().trim();
				debug = title;
				category = st.nextToken().trim();
				imageName = st.nextToken().trim();
				rating = st.nextToken().trim();
				numberOfMinutes = Integer.parseInt(st.nextToken().trim());
				yearReleased = Integer.parseInt(st.nextToken().trim());
				numberOfActors= Integer.parseInt(st.nextToken().trim());
						
				// read all of the actors in
				actorList = readActors(inputFromFile, numberOfActors);

				// create the video recording
				// select a random price between 9.99 and 24.99
				basePrice = 9 + (int) (Math.random() * 16);
				price = basePrice + .99;
				
				Duration duration = new Duration(0, numberOfMinutes, 0);
				myRecording = new VideoRecording(producer, actorList, rating, yearReleased, title, 
												 price, category, imageName, duration);

				// check to see if we have information on this category
				if (dataTable.containsKey(category)) {
				
					// get the list of recordings for this category
					videoArrayList = (ArrayList) dataTable.get(category); 					
				}
				else {
				
					// this is a new category.  simply add the category
					// to our dataTable
					videoArrayList = new ArrayList();
					dataTable.put(category, videoArrayList);				
				}
				
				// add the recording
				videoArrayList.add(myRecording);
				
				// move ahead and consume the line separator
				line = inputFromFile.readLine();
			}

			inputFromFile.close();
			log("File loaded successfully!");
			log("READY!\n");
		}
		catch (FileNotFoundException exc) {
			log("Could not find the file \"" + FILE_NAME + "\".");
			log("Make sure it is in the current directory.");
			log("========>>> PLEASE CONTACT THE INSTRUCTOR.\n\n\n");
			log(exc);
			
			throw(exc);
		}
		catch (IOException exc) {
			log("IO error occurred while reading file: " + FILE_NAME);
			log("========>>> PLEASE CONTACT THE INSTRUCTOR.\n\n\n");
			log(exc);

			throw(exc);
		}
		catch (Exception exc) {
			System.out.println(debug);
			exc.printStackTrace();
		}
	
	}
	
	/**
	 *  Helper method for reading a given number of actors from a Reader.
	 *
	 *  @param inputFromFile the reader for the file we are reading
	 *  @param numberOfStrings the number of actors to read
	 *
	 *  @return an array of Actorss
	 */
	protected String[] readActors(BufferedReader inputFromFile, int numberOfActors) 
		throws IOException
	{
		String[] actorList = new String[numberOfActors];
		
		StringTokenizer st;
		String actorLine;
		String actorName;
		
		for (int i=0; i < numberOfActors; i++)
		{
			actorLine = inputFromFile.readLine();

			st = new StringTokenizer(actorLine, ",");

			actorName = st.nextToken().trim();

			actorList[i] = actorName;
		}	
		
		return actorList;
	}
	
	
	
	/**
	 *  Saves the data to a storage device.  <p>
	 *
	 */
	public void save() throws IOException {	
	
	}

	protected void log(Object msg) {
	
		// NO MESSAGES FOR NOW
		// System.out.println("VideoDataAccessor: " + msg);
	}
}