/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjavaproject;
import java.util.*;
import java.text.*;

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
	 *  Constructs a recording w/ given parameter values
	 */
	public Recording(String theContent) {
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                name = dateFormat.format(date);
		content = theContent;
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
	 *  Returns the content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 *  Sets the recording price
	 */
	public void setContent(String theContent) {
		content = theContent;
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
	 *  Returns the content
	 */
	public String toString() {
		return content;
	}
	
}
