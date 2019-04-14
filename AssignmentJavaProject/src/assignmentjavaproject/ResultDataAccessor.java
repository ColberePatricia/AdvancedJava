/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjavaproject;

import java.util.*;
import java.io.*;
/**
 * Class to access the results saved in the database and to add new ones
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
	//  
	//  2019/04/14 00:11:51,
	//  Matrix Inversion
	//  Original matrix
	//      11.0000000   22.0000000
	//      56.0000000   37.0000000
	//  
	//  
	//  
	//  Lower matrix
	//      1.0000000   0.0000000
	//      0.1964286   1.0000000
	//  
	//  Upper matrix
	//      56.0000000   37.0000000
	//      0.0000000   14.7321429
	//  
	//  Inverse matrix
	//      -0.0448485   0.0266667
	//      0.0678788   -0.0133333
	//  
	//  
	//  Determinant = -825.0
	//  
	//  ----------
	//  
	//  2019/04/14 00:12:45,
	//  LU Decomposition with scaled partial pivoting
	//  Original matrix
	//      11.0000000   22.0000000   5.0000000
	//      56.0000000   37.0000000   35.0000000
	//      41.0000000   2.0000000   11.0000000
	//  
	//  
	//  
	//  Original vector
	//      12.0000000   50.0000000   2.0000000
	//  
	//  
	//  Lower matrix
	//      1.0000000   0.0000000   0.0000000
	//      0.7321429   1.0000000   0.0000000
	//      0.1964286   -0.5871886   1.0000000
	//  
	//  Upper matrix
	//      56.0000000   37.0000000   35.0000000
	//      0.0000000   -25.0892857   -14.6250000
	//      0.0000000   0.0000000   -10.4626335
	//  
	//  Solution
	//      -0.4344218   0.3685714   1.7340136
	//  
	//  Determinant = 14700.0
	//  
	//  ----------

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
	
		// now we can load the data into the result output
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
		
				//  Parse the info line to read the name
				resultName = st.nextToken().trim();
						
				// read the content
				resultContent = readResultContent(inputFromFile);

				// create the music recording
				myRecording = new Recording(resultName, resultContent);
                                
				// check to see if we have information on this name
				if (dataTable.containsKey(resultName)) {
				
					// get the list of recordings for this name
					resultArrayList = (ArrayList) dataTable.get(resultName); 					
				}
				else {
				
					// this is a new name.  simply add the name
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
	 *  Helper method for reading a content from a Reader.
	 *
	 *  @param inputFromFile the reader for the file we are reading
	 *
	 *  @return a content
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

			//  temps
			Recording tempRecording = null;


			//  create a FileWriter for the file "result.db" and set append to true			
			Boolean append = true;
                        FileWriter myFileWriter = new FileWriter("result.db", append);
			
			
			
			//  create a PrintWriter based on the file writer and set autoflush to true	
			boolean autoflush = true;
                        PrintWriter outputToFile = new PrintWriter(myFileWriter, autoflush);
			
			
			
			//
			// This while loop will only write out information for the new recordings
			// that were added to the recentRecordingList.  The recentRecordingList
			// is populated by the addRecording(...) method.
			//
			Iterator iterator = recentRecordingList.iterator();		
			while (iterator.hasNext()) {

				// Gets the next recording and its content		
				tempRecording = (Recording) iterator.next();

				//   Print output to the file using the file format
                                //
				//         name, 
                                //         content
				//   
				//
				outputToFile.print("\n"+tempRecording.getName() + ", \n");
				outputToFile.print(tempRecording.getContent()+ "\n");
				
				// GIVEN:  Print the record separator
				outputToFile.println(RECORD_SEPARATOR);
			}
			
			// close the output file
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
        
        /**
	 *  @return a sorted list of the categories for the recordings.
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

		// get the name for this recording
		String name = theRecording.getName();
		
		log("Adding recording to table memory:  " + theRecording.getName()+"\n");
		
		// get the list of recordings for this name
		ArrayList resultArrayList;
		if (dataTable.containsKey(name)) {
				
			// get the list of recordings for this name
			resultArrayList = (ArrayList) dataTable.get(name); 					
		}
		else {
				
			// this is a new name, simply add the name
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
