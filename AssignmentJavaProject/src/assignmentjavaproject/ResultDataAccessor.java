/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjavaproject;

import java.util.*;
import java.io.*;
/**
 *
 * @author Patricia
 */
public class ResultDataAccessor extends DataAccessor {
    
	//////////////////////////////////////////////////////
	//
	//  DATA FILE FORMAT:
	//
	//  The data file has the following format:
	//
	//    Text corresponding to the result
	//    ----------------------------
	//
	//
	//  Here is a sample file with 2 results:
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
	//  Note that each result is ended w/ a line separator.  The contents
	//  of the separator is ignored during the read.  The reader simply consumes
	//  an extra line feed after reading a result.
	//



	/**
	 *  The name of the database file to read/write.
	 */
	protected static final String FILE_NAME = "result.db";


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
	public ResultDataAccessor() throws IOException
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
	
		StringTokenizer st = null;

                ArrayList resultArrayList = null;
                
		Recording myRecording;
		String line = "";

		String resultName;
                int numberOfLines;
		String resultContent;
		
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
				resultName = st.nextToken().trim();
                                numberOfLines = Integer.parseInt(st.nextToken().trim());
						
				// read all of the tracks in
				resultContent = readResultContent(inputFromFile, numberOfLines);

				// create the music recording
				myRecording = new Recording(resultName, numberOfLines, resultContent);
				
                                
				// check to see if we have information on this category
				if (dataTable.containsKey(resultName)) {
				
					// get the list of recordings for this category
					resultArrayList = (ArrayList) dataTable.get(resultName); 					
				}
				else {
				
					// this is a new category.  simply add the category
					// to our dataTable
					resultArrayList = new ArrayList();
					dataTable.put(resultName, resultArrayList);				
				}
				
				// add the recording
				resultArrayList.add(myRecording);
				
                                
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
	protected String readResultContent(BufferedReader inputFromFile, int numberOfLines) 
		throws IOException
	{
		String resultContent = "";
		
		
		for (int i=0; i < numberOfLines; i++)
		{
			resultContent += inputFromFile.readLine()+"\n";

		}	
		
		return resultContent;
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
	
	
	// IMPORTANT:  REMOVE THE "BEGIN" COMMENT TAG ON THE LINE BELOW
	
	
		try {
		
			log("Doing save...");

			//  GIVEN:  temps
			Recording tempRecording = null;


			//  TO DO: create a FileWriter for the file "music.db" and set append to true			
			Boolean append = true;
                        FileWriter myFileWriter = new FileWriter("result.db", append);
			
			
			
			//  TO DO: create a PrintWriter based on the file writer and set autoflush to true			
			//		   name the PrintWriter "outputToFile".			
			boolean autoflush = true;
                        PrintWriter outputToFile = new PrintWriter(myFileWriter, autoflush);
			
			
			
			// GIVEN:  
			//
			// This while loop will only write out information for the new recordings
			// that were added to the recentRecordingList.  The recentRecordingList
			// is populated by the addRecording(...) method.
			//
			Iterator iterator = recentRecordingList.iterator();		
			while (iterator.hasNext()) {

				// GIVEN:  Get's the next recording	and its track list		
				tempRecording = (Recording) iterator.next();

				// TO DO:  Print output to the file using the file format
				//         Artist, Title, Category, ImageName, NmberOfTracks 
				//
				//   To help get you started, the code for printing the artist name is already given.
				//   
				//
				outputToFile.print(tempRecording.getName() + ", ");
				outputToFile.print(tempRecording.getNumberOfLines()+ "\n");
				outputToFile.print(tempRecording.getResultContent()+ "\n");
				
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
	
	// IMPORTANT:  REMOVE THE "END" COMMENT TAG ON THE LINE BELOW
	
	
	}
        
}
