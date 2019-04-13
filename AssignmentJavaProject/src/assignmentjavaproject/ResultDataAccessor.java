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
		String resultContent;
		try
		{
			log("Loading File: " + FILE_NAME + "...");
			BufferedReader inputFromFile = new BufferedReader(new FileReader(FILE_NAME));
                        inputFromFile.readLine();
			while ( (line = inputFromFile.readLine()) != null )
			{		
				// create a tokenizer for a comma delimited line
				st = new StringTokenizer(line, ",");
		
				//  Parse the info line to read the title
				//
				resultName = st.nextToken().trim();
                               // log("\nres name: "+ resultName);
						
				// read the content
				resultContent = readResultContent(inputFromFile);
                                //st = new StringTokenizer(line, RECORD_SEPARATOR);
                                //resultContent = st.nextToken().trim();
                                log("\nres content: "+resultContent);

				// create the music recording
				myRecording = new Recording(resultName, resultContent);
				//log("\nresult name: "+resultName+" \nresult content: "+resultContent+"\n");
                                
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
	protected String readResultContent(BufferedReader inputFromFile) 
		throws IOException
	{
		String resultContent = "";
		String line = "";
                line = inputFromFile.readLine();
                while(!line.startsWith("---") && line!=null){
                    resultContent += line+"\n";
                    line = inputFromFile.readLine();
                }
		
		return resultContent;
	}
	
	
	
	/**
	 *  Saves the data to a storage device.  <p>
	 *
	 *
	 *
	 *
	 *  @exception IOException thrown if error occurs during IO
	 */
	public void save() throws IOException {
	
		
	
		try {
		
			log("Doing save...");

			//  GIVEN:  temps
			Recording tempRecording = null;


			//  TO DO: create a FileWriter for the file "result.db" and set append to true			
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
				outputToFile.print("\n"+tempRecording.getName() + ", \n");
				outputToFile.print(tempRecording.getContent()+ "\n");
				
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
        
        /**
	 *  Returns a sorted list of the categories for the recordings.
	 */
	public ArrayList getResultsList() {
	
		Set resultSet = dataTable.keySet();
		
		log("Getting the results...");

		ArrayList results = new ArrayList(resultSet);

		// sort the list first
		Collections.sort(results);
		
		log("getResults complete!\n");
		
		return results;		
	}
        
        /**
	 *  Adds a new recording to the data table in memory.  <p>
	 *
	 *  Note:  This method does not save the data to persistent storage (ie disk file).
	 *
	 *  @param theRecording the recording to add
	 *
	 *  @see save()
	 */
	public void addRecording(Recording theRecording) {

		// get the category for this recording
		String name = theRecording.getName();
		
		log("Adding recording to table memory:  " + theRecording.getName()+"\n");
		
		// get the list of recordings for this category
		ArrayList resultArrayList;
		if (dataTable.containsKey(name)) {
				
			// get the list of recordings for this category
			resultArrayList = (ArrayList) dataTable.get(name); 					
		}
		else {
				
			// this is a new category.  simply add the category
			// to our dataTable
			resultArrayList = new ArrayList();
			dataTable.put(name, resultArrayList);				
		
                }
		// add this recording the list of recordings
		resultArrayList.add(theRecording);
		
		// also, add it to our recentRecordingList
		recentRecordingList.add(theRecording);
		
		log("addRecording() complete!\n");
	}
        
}
