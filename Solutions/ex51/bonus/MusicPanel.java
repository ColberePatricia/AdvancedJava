import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import java.io.IOException;

public class MusicPanel extends JPanel {
	
	/**
	 *  The label to hold the text "Select a Music Category"
	 */
	protected JLabel selectionLabel;
	
	/**
	 *  The combo box to hold a list of music categories
	 */
	protected JComboBox categoryComboBox;
	

	/**
	 *  A panel to contain components.
	 */
	protected JPanel topPanel;

	/**
	 * List Box for displaying the music recordings
	 */
	protected JList musicListBox;
	
	/**
	 *  Supporting scroll pane for the musicListBox
	 */
	protected JScrollPane musicScrollPane;
	
	/**
	 *  Button labeled "Details"
	 */
	protected JButton detailsButton;

	/**
	 *  Button labeled "Add"
	 */
	protected JButton addButton;


	/**
	 *  Button labeled "Save"
	 */
	JButton saveButton = new JButton("Save");


	/**
	 *  Button labeled "Clear"
	 */
	protected JButton clearButton;

	/**
	 *  Button labeled "Exit"
	 */
	protected JButton exitButton;

		
	/**
	 *  A panel to contain components.
	 */
	protected JPanel bottomPanel;
		
	/**
	 *  A reference to the parent frame
	 */
	protected MainFrame parentFrame;

	/**
	 *  A holder for the list of music recordings
	 */
	protected ArrayList musicArrayList;
	
	/**
	 *  A reference music data accessor
	 */
	protected MusicDataAccessor myDataAccessor;

	/**
	 *  Keep track of changes made
	 */
	boolean changesMade = false;
	
	/**
	 *  Creates the GUI components and arranges them
	 *  in the container.
	 */
	public MusicPanel(MainFrame theParentFrame) {
	
		parentFrame = theParentFrame;

		try {
			myDataAccessor = new MusicDataAccessor();
		}
		catch (IOException e) {

			JOptionPane.showMessageDialog(parentFrame, "Error while loading data accessor! " + e.getMessage());		
		}
		
		selectionLabel = new JLabel("Select a Music Category");

		// populate category combo box		
		categoryComboBox = new JComboBox();
		categoryComboBox.addItem("-------");
		
		ArrayList categoryArrayList = myDataAccessor.getCategories();
		
		Iterator iterator = categoryArrayList.iterator();
		String aCategory;
		
		while (iterator.hasNext()) {
			
			aCategory = (String) iterator.next();
			categoryComboBox.addItem(aCategory);
		}
				
		topPanel = new JPanel();
		
		musicListBox = new JList();
		musicListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		musicScrollPane = new JScrollPane(musicListBox);
		
		// create the buttons for the bottom panel
		detailsButton = new JButton("Details...");
		addButton = new JButton("Add...");
		clearButton = new JButton("Clear");
		exitButton = new JButton("Exit");
		
		bottomPanel = new JPanel();
		
		// set the layout for "this" MusicPanel
		this.setLayout(new BorderLayout());
		
		// set the layout for the topPanel and add components
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(selectionLabel);
		topPanel.add(categoryComboBox);
		
		// add the top panel to the northern region of "this" MusicPanel
		this.add(BorderLayout.NORTH, topPanel);
		
		// add the musicScrollPane to the center of "this" MusicPanel
		this.add(BorderLayout.CENTER, musicScrollPane);
		
		// set the layout for the bottomPanel and add components
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(detailsButton);
		bottomPanel.add(addButton);
		bottomPanel.add(saveButton);
		bottomPanel.add(clearButton);
		bottomPanel.add(exitButton);
		
		// add the bottomPanel to the southern region of "this" MusicPanel
		this.add(BorderLayout.SOUTH, bottomPanel);
	
		//
		//  REGISTER LISTENERS
		//
		detailsButton.addActionListener(new DetailsActionListener());
		addButton.addActionListener(new EntryActionListener());
		saveButton.addActionListener(new SaveActionListener());
		
		// bonus work
		clearButton.addActionListener(new ClearActionListener());
		exitButton.addActionListener(new ExitActionListener());
		categoryComboBox.addItemListener(new GoItemListener());
		musicListBox.addListSelectionListener(new MusicListSelectionListener());
	
		// more bonus work - state management
		detailsButton.setEnabled(false);
		clearButton.setEnabled(false);
	}

	
	/**
	 *  Populate the list box w/ the categories
	 *
	 *  <pre>
	 *  
	 *    1.  If the selected category is not equal to "----" then
	 *        1a.  Get the music array list from data accessor
	 *    2.  Else create an empty music array list
	 *
	 *    3.  If the list is not empty then enable "Clear" button
	 *
	 *  </pre>
	 */
	protected void populateListBox() {

		String category = (String) categoryComboBox.getSelectedItem();

		if (! category.startsWith("---")) {
			musicArrayList = myDataAccessor.getRecordings(category);
		}
		else {
			musicArrayList = new ArrayList(); 
		}

		Object[] theData = musicArrayList.toArray();
		musicListBox.setListData(theData);	
				
		// bonus work
		// clear button is enabled if we have some data
		if (musicArrayList.size() > 0) {
			clearButton.setEnabled(true);
		}
		else {
			clearButton.setEnabled(false);
		}
	}
	
	//
	//  INNER CLASSES
	//
	
	/**
	 *  <pre>
	 *  
	 *  When the go button is pressed, we will:
	 *
	 *  1.  Find out the selected category from combo box
	 *  2.  Get a list of CDs for the selected category from the data accessor
	 *  3.  Populate the list box w/ the cd artist and titles
	 *
	 *  </pre>
	 */
	class GoActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {

			// the real work occurs in the populateListBox() method.
			populateListBox();
		}
	}
	
	/**
	 *  When the details button is pressed, we will
	 *
	 *  <pre>
	 *  
	 *  1.  Get the selected item index from the list box
	 *  2.  Get the desired compact disc from the cd vector
	 *  3.  Create a details dialog for the compact disc
	 *  4.  Show the details dialog
	 *
	 *  </pre>
	 */
	class DetailsActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {

			int index = musicListBox.getSelectedIndex();
			
			MusicRecording myMusicRecording = (MusicRecording) musicArrayList.get(index);
			
			MusicDetailsDialog myDetailsDialog = new MusicDetailsDialog(parentFrame, myMusicRecording);
			
			myDetailsDialog.setVisible(true);
		}	
	}


	//
	//  BONUS WORK FOLLOWS
	//
	
	/**
	 *  When called, let's call the exit routine of the parent frame
	 */
	class ExitActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {

			if (changesMade) {
			
				int response = JOptionPane.showConfirmDialog(
									MusicPanel.this, "Would you like to save changes?");
			
				switch (response) {
				
					case JOptionPane.YES_OPTION:
						saveTheData();
						parentFrame.exit();
						break;
						
					case JOptionPane.NO_OPTION:
						parentFrame.exit();
						break;
					
					case JOptionPane.CANCEL_OPTION:
					case JOptionPane.CLOSED_OPTION:
						// they chose cancel, don't exit the program
						break;
				}
			}
			else {
				// no changes made
				parentFrame.exit();
			}
			
		}
	}
	
	/**
	 *  When the "Clear" button is pressed, we will:
	 *
	 *  <pre>
	 *  
	 *  1.  Clear the musicListBox
	 *  2.  Set the first category item as selected
	 *
	 *  </pre>
	 */
	class ClearActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {

			// create an empty array
			Object[] noData = new Object[1];

			// this will clear out the list			
			musicListBox.setListData(noData);

			// set the first category item as selected
			categoryComboBox.setSelectedIndex(0);
		}
	}

	/**
	 *  When a category is selected, we will:
	 *
	 *  <pre>
	 *
	 *  1.  Find out the selected category from combo box
	 *  2.  Get a list of CDs for the selected category from the data accessor
	 *  3.  Populate the list box w/ the cd artist and titles
	 *
	 *  </pre>
	 */	
	class GoItemListener implements ItemListener {
	
		public void itemStateChanged(ItemEvent event) {
			
			if (event.getStateChange() == ItemEvent.SELECTED) {
				populateListBox();
			}
		}
	}
	
	/**
	 *  State management bonus. 
	 *
	 *  The "Details" button is only enabled if an item is selected. <p>
	 *
	 *  The ListSelectionListener interface is defined in the java.swing.event package
	 */
	class MusicListSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent event) {
					
			if (musicListBox.isSelectionEmpty()) {
				detailsButton.setEnabled(false);
			}
			else {
				detailsButton.setEnabled(true);
			}
		}
	}
	
	/**
	 *  When the "Add..." button is pressed, we will:
	 *
	 *  <pre>
	 *
	 *  1.  Create an instance of the MusicEntryDialog.
	 *  2.  Show this dialog
	 *  3.  When the dialog returns, check if it's "Ok" button was pressed.
	 *      3a.  If "Ok" button was pressed
	 *           -  Get the music recording from the dialog
	 *           -  Add this music recording to the data accessor
	 *
	 *  </pre>
	 */
	class EntryActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
		
			ArrayList categoryArrayList = myDataAccessor.getCategories();

			//
			// TO DO:  Do your work here.
			//
			MusicEntryDialog myDialog = new MusicEntryDialog(parentFrame, categoryArrayList);
			
			myDialog.setVisible(true);
			
			if (myDialog.isOkButtonPressed()) {
				
				MusicRecording theRecording = myDialog.getMusicRecording();
				
				myDataAccessor.addRecording(theRecording);
				
				categoryComboBox.setSelectedItem(theRecording.getCategory());
			
				changesMade = true;
			}
			
		}	
	}


	/**
	 *  When the "Save" button is pressed, we will:
	 *
	 *  <pre>
	 *
	 *  1.  Call the save() method of our data accessor.
	 *  2.  If any problems occur during the save then we'll report
	 *      them to the user.
	 *
	 *  </pre>
	 */
	class SaveActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			saveTheData();
		}
	}

	protected void saveTheData() {
	
		try {
			myDataAccessor.save();

			JOptionPane.showMessageDialog(parentFrame, "Recording saved successfully!");
			changesMade = false;

		}
		catch (IOException e) {

			JOptionPane.showMessageDialog(parentFrame, "Error while saving! " + e.getMessage());

			e.printStackTrace();
		}	
	}
}