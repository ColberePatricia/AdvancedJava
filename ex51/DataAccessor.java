import java.util.*;
import java.io.*;

/**
 *  This abstract class defines a trivial recordings database.  Methods are available to
 *  get a list of the music categories and a list of recordings.   <p>
 *
 *  Subclasses must implement the methods to load and save the data.  This design 
 *  allows subclasses to implement a simple solution of reading/writing to a flat text
 *  file or an advanced solution of reading/writing information to a RDBMS or Socket. <p>
 *
 *
 *  @author App Development Team
 */
public abstract class DataAccessor {

	/**
	 *  A HashMap/hashtable of the recordings.  
	 *
	 *  The key is the "category".
	 *
	 *  The data stored for each key is an ArrayList which is
	 *  the collection of recordings.
	 *
	 */
	protected HashMap dataTable;
		
	/**
	 *  This hold a list of the recent recordings that have been added 
	 *  using the addRecording() method.
	 *
	 */
	 protected ArrayList recentRecordingList;
	 
	 
	 /**
	  *  Default constructor.  Initializes the dataTable and recentRecordList
	  */
	 public DataAccessor() {
	 	dataTable = new HashMap();
		recentRecordingList = new ArrayList();
	 }
	 
	 
	/**
	 *  Returns a sorted list of the categories for the recordings.
	 */
	public ArrayList getCategories() {
	
		Set categorySet = dataTable.keySet();
		
		log("Getting the categories...");

		ArrayList categories = new ArrayList(categorySet);

		// sort the list first
		Collections.sort(categories);
		
		log("getCategories complete!\n");
		
		return categories;		
	}


	/**
	 *  Returns a list of recordings that match a given category
	 *
	 *  @param category the category for requested recordings.
	 */
	public ArrayList getRecordings(String category) {
	
		ArrayList recordingList = null;
		
		log("Getting a list of recordings for: " + category);
		
		// get a list of all recordings for this category
		recordingList = (ArrayList) dataTable.get(category);
		
		// sort the list first
		Collections.sort(recordingList);
		
		log("getRecordings() complete!\n");
		
		return recordingList;
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
		/*String name = theRecording.getName();
		
		log("Adding recording to table memory:  " + theRecording.getContent());
		
		// get the list of recordings for this category
		ArrayList recordingList = (ArrayList) dataTable.get(category);
		
		// add this recording the list of recordings
		recordingList.add(theRecording);
		
		// also, add it to our recentRecordingList
		recentRecordingList.add(theRecording);
		
		log("addRecording() complete!\n");*/
	}

	/**
	 *  Loads the data from a storage device
	 */
	public abstract void load() throws IOException;
	
	
	/**
	 *  Saves the data to a storage device
	 */
	public abstract void save() throws IOException;

	
	/**
	 *  Helper method for logging message to the console.
	 */
	protected void log(Object msg) {
	 	System.out.println("Data Accessor:  " + msg);
	}
}