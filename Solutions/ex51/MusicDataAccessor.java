import java.util.*;
import java.io.*;

/**
 *  This class implements a trivial database for music recordings.  
 *  Methods are available to get a list of the music categories and a 
 *  list of recordings.   <p>
 *
 *  Usage Example:
 *  <pre>
 *
 *    // create and load the data accessor
 *    DataAccessor myDataAccessor = new MusicDataAccessor();  
 *
 *    // get a list of available categories;
 *    ArrayList cats = myDataAccessor.getCategories();
 *    ... 
 *
 *    // get a list of jazz recordings
 *    ArrayList jazzRecordingList = myDataAccessor.getRecordings("Jazz");
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
 *  The current implementation of this class has the following categories:
 *
 *  @author Development Team
 */
public class MusicDataAccessor extends DataAccessor {

	//////////////////////////////////////////////////////
	//
	//  DATA FILE FORMAT:
	//
	//  The data file has the following format:
	//
	//    artist name, recording title, category, image name, number of tracks
	//    Track title #1, total seconds
	//    Track title #2, total seconds
	//    ----------------------------
	//
	//
	//  Here is a sample file with 2 recordings:
	//
	//    Will Smith, Big Willie Style, Hip-Hop, willsmith_bigwilliestyle.gif, 3
	//    Intro, 184
	//    Y'All Know, 328
	//    Gettin' Jiggy Wit It, 215
	//    ----------------------------
	//    U2, The Best Of, Rock, u2_bestof.gif, 2
	//    Pride (In The Name Of Love), 327
	//    New Year's Day, 216
	//    ----------------------------
	//
	//
	//  Note that each recording is ended w/ a line separator.  The contents
	//  of the separator is ignored during the read.  The reader simply consumes
	//  an extra line feed after reading a recording.
	//


	/**
	 *  The name of the database file to read/write.
	 */
	protected static final String FILE_NAME = "music.db";


	/**
	 *  Marker used to separate the music recordings in the data file.
	 */
	protected static final String RECORD_SEPARATOR = "----------";

	
	/**
	 *  Constructs the data accessor and calls the load() method
	 *  to load data.
	 *
	 *  @exception IOException thrown if error occurs during IO	 
	 */
	public MusicDataAccessor() throws IOException
	{
	
		// now we can load the data into the table
		load();
	}
	
	/**
	 *  Loads the data from a storage device.
	 *
	 *  @exception IOException thrown if error occurs during IO
	 */
	public void load() throws IOException {
	
		ArrayList musicArrayList = null;
		StringTokenizer st = null;

		MusicRecording myRecording;
		String line = "";

		String artist, title;
		String category, imageName;
		int numberOfTracks;
		int basePrice;
		double price;

		Track[] trackList;
		
		try
		{
			log("Loading File: " + FILE_NAME + "...");
			BufferedReader inputFromFile = new BufferedReader(new FileReader(FILE_NAME));
			
			while ( (line = inputFromFile.readLine()) != null )
			{			
				// create a tokenizer for a comma delimited line
				st = new StringTokenizer(line, ",");
		
				//  Parse the info line to read following items
				//  -  the artist, title, category, imageName, number of tracks
				//
				artist = st.nextToken().trim();
				title = st.nextToken().trim();
				category = st.nextToken().trim();
				imageName = st.nextToken().trim();
				numberOfTracks = Integer.parseInt(st.nextToken().trim());
						
				// read all of the tracks in
				trackList = readTracks(inputFromFile, numberOfTracks);

				// select a random price between 9.99 and 15.99
				basePrice = 9 + (int) (Math.random() * 7);
				price = basePrice + .99;

				// create the music recording
				myRecording = new MusicRecording(artist, trackList, title, 
												 price, category, imageName);

				// check to see if we have information on this category
				if (dataTable.containsKey(category)) {
				
					// get the list of recordings for this category
					musicArrayList = (ArrayList) dataTable.get(category); 					
				}
				else {
				
					// this is a new category.  simply add the category
					// to our dataTable
					musicArrayList = new ArrayList();
					dataTable.put(category, musicArrayList);				
				}
				
				// add the recording
				musicArrayList.add(myRecording);
				
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
			
			// throw it again so the caller is also informed of our problem
			throw exc;
		}
		catch (IOException exc) {
			log("IO error occurred while reading file: " + FILE_NAME);
			log("========>>> PLEASE CONTACT THE INSTRUCTOR.\n\n\n");
			log(exc);

			// throw it again so the caller is also informed of our problem
			throw exc;
		}
	
	}
	
	/**
	 *  Helper method for reading a given number of tracks from a Reader.
	 *
	 *  @param inputFromFile the reader for the file we are reading
	 *  @param numberOfTracks the number of tracks to read
	 *
	 *  @return an array of Tracks
     *
	 *  @exception IOException thrown if error occurs during IO
	 */
	protected Track[] readTracks(BufferedReader inputFromFile, int numberOfTracks) 
		throws IOException
	{
		Track[] trackList = new Track[numberOfTracks];
		
		StringTokenizer st;
		String trackLine;
		String trackName;
		Duration trackDuration;
		int totalSeconds;
		
		for (int i=0; i < numberOfTracks; i++)
		{
			trackLine = inputFromFile.readLine();

			st = new StringTokenizer(trackLine, ",");

			trackName = st.nextToken().trim();
			totalSeconds = Integer.parseInt(st.nextToken().trim()); 
			trackDuration = new Duration(totalSeconds);

			trackList[i] = new Track(trackName, trackDuration);
		}	
		
		return trackList;
	}
	
	
	
	/**
	 *  Saves the data to a storage device.  <p>
	 *
	 *  <b><i> NOTE: This method is left an exercise for the student.  </i><b><br>
	 *
	 *
	 *  @exception IOException thrown if error occurs during IO
	 */
	public void save() throws IOException {
		
		try {
		
			log("Doing save...");

			//  GIVEN:  temps
			MusicRecording tempRecording = null;
			Track[] tempTrackList = null;
			Track tempTrack = null;
			Duration tempDuration = null;


			//  TO DO: create a file writer for the file "music.db" and set append to true			
			boolean append = true;
			FileWriter myFileWriter = new FileWriter("music.db", append);
			
			//  TO DO: create a print writer based on fileWriter and set autoflush to true			
			//		   name the print writer "outputToFile".			
			boolean autoFlush = true;
			PrintWriter outputToFile = new PrintWriter(myFileWriter, autoFlush);
			
			// GIVEN:  
			//
			// This while loop will only write out information for the new recordings
			// that were added to the recentRecordingList.  The recentRecordingList
			// is populated by the addRecording(...) method.
			//
			Iterator iterator = recentRecordingList.iterator();		
			while (iterator.hasNext()) {

				// GIVEN:  Get's the next recording	and its track list		
				tempRecording = (MusicRecording) iterator.next();
				tempTrackList = tempRecording.getTrackList();

				// TO DO:  Print output to the file using the file format
				// artist name, recording title, category, image name, number of tracks 
				outputToFile.print(tempRecording.getArtist() + ", ");
				outputToFile.print(tempRecording.getTitle() + ", ");				
				outputToFile.print(tempRecording.getCategory() + ", ");
				outputToFile.print(tempRecording.getImageName() + ", ");
				outputToFile.println(tempTrackList.length);
				
				// GIVEN:  Dump out the tracks using the following format:  track title, total seconds
				for (int i=0; i < tempTrackList.length; i++) {
				
					tempTrack = tempTrackList[i];
					tempDuration = tempTrack.getDuration();
					
					outputToFile.print(tempTrack.getTitle() + ", ");
					outputToFile.println(tempDuration.getTotalSeconds());
				}
				
				// GIVEN:  Print the record separator
				outputToFile.println(RECORD_SEPARATOR);
			}
			
			// TO DO: close the output file
			outputToFile.close();
			
			log("Save completed!");
			
			// clear out the recent list
			recentRecordingList.clear();
			
		}
		catch (IOException exc) {
		
			log("Error occurred during the save.");
			log(exc);
			
			// throw it again so the caller is also informed of our problem
			throw exc;
		}
		
	}

}