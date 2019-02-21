/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjavaproject;
import java.util.*;

/**
 *
 * @author Patricia
 */
public class Recording implements Comparable {
    
	//
	//  DATA MEMBERS
	//
	
	/**
	 *  The recording title 
	 */
	protected String name;

	/**
	 *  The recording category
	 */
	protected int numberOfLines;
	
	/**
	 *  The recording image name
	 */
	protected String resultContent;
	
	//
	//  CONSTRUCTORS
	//
	
	/** 
	 *  Basic default constructor  
	 */
	public Recording() {
		// default constructor
	}

	
	/**
	 *  Constructs a recording w/ given parameter values
	 */
	public Recording(String theName, int theNumberOfLines, String theResultContent) {
	
		name = theName;
		numberOfLines = theNumberOfLines;
		resultContent = theResultContent;
	}

	//
	//  GETTER / SETTER METHODS
	//
	
	/**
	 *  Returns the recording title
	 */
	public String getName() {
		return name;
	}
	
	/**
	 *  Sets the recording title
	 */
	public void setName(String theName) {
		name = theName;
	}


	/**
	 *  Returns the recording price
	 */
	public int getNumberOfLines() {
		return numberOfLines;
	}
	
	/**
	 *  Sets the recording price
	 */
	public void setNumberOfLines(int theNumberOfLines) {
		numberOfLines = theNumberOfLines;
	}


	/**
	 *  Returns the recording category
	 */
	public String getResultContent() {
		return resultContent;
	}
	
	/**
	 *  Sets the recording category
	 */
	public void setCategory(String theResultContent) {
		resultContent = theResultContent;
	}

	/** 
	 *  Allow use to sort the recordings by name
	 */
	public int compareTo(Object object) {
	
		Recording recording = (Recording) object;
		String targetName = recording.getName();
		
		return name.compareTo(targetName);
	}
        
        
	/**
	 *  Returns the artist name and title of recording
	 */
	public String toString() {
		//return name + ", " + numberOfLines + "\n" + resultContent;
                return name;
	}
	
}
