/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjavaproject;
import java.util.*;
import java.text.*;

/**
 * Class to create and handle the use of a recording, containing a result
 * with a name and a content
 * 
 * @author Patricia
 */
public class Recording implements Comparable {
    
	//
	//  DATA MEMBERS
	//
	
	/**
	 *  The recording name 
	 */
	protected String name;
	
        /**
	 *  The recording content 
	 */
	protected String content;
        
        
	//
	//  CONSTRUCTORS
	//
	
	/** 
	 *  Basic default constructor  
	 */
	public Recording() {
		// default constructor
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                name = dateFormat.format(date);
	}
        
        /**
         * 
         * Constructs a recording w/ given parameter values
         * @param theContent contains the content of the recording
         */
	public Recording(String theContent) {
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                name = dateFormat.format(date);
		content = theContent;
	}
        
        /**
         * 
         * Constructs a recording w/ given parameter values
         * @param theName contains the name of the recording
         * @param theContent contains the content of the recording
         */
	public Recording(String theName, String theContent) {
	
                name = theName;
		content = theContent;
	}

	//
	//  GETTER / SETTER METHODS
	//
	
	/**
	 *  @return the recording name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 *  @param theName the recording name
	 */
	public void setName(String theName) {
		name = theName;
	}


	/**
	 *  @return the content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 *  @param theContent the recording price
	 */
	public void setContent(String theContent) {
		content = theContent;
	}

	/** 
	 *  Allow use to sort the recordings by name
         *  
         *  @param object the recording compared to the target name
         *  @return the boolean comparing the names
	 */
	public int compareTo(Object object) {
	
		Recording recording = (Recording) object;
		String targetName = recording.getName();
		
		return name.compareTo(targetName);
	}
        
        
	/**
	 *  @return the content as a string
	 */
	public String toString() {
		return content;
	}
	
}
