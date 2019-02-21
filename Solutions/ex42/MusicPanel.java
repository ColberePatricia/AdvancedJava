import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *  This panel is responsible for displaying music recordings.  It contains
 *  a combo box for selecting the category.  Once a category is selected then
 *  a list of music recordings are displayed in a list box.  The user can then 
 *  highlight a recording and get the details.
 *
 *  @author Development Team
 *
 */
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
	 *  Button labeled "Go"
	 */
	protected JButton goButton;

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
	 *  Array list used to hold music recordings
	 */
	 protected ArrayList musicArrayList;
	 
	
	/**
	 *  A reference to the data accessor for music recordings
	 */
	 protected MusicDataAccessor myDataAccessor;
	 
	 
	/**
	 *  Creates the GUI components and arranges them
	 *  in the container.
	 */
	public MusicPanel(MainFrame theParentFrame) {
	
		parentFrame = theParentFrame;

		//
		//  TO DO:  Intialize your data accessor here.
		//
		myDataAccessor = new MusicDataAccessor();
		
		
		selectionLabel = new JLabel("Select a Music Category");
		
		// create and populate the combo box
		categoryComboBox = new JComboBox();
		ArrayList categoryArrayList = myDataAccessor.getCategories();
		
		String aCategory;
		Iterator iterator = categoryArrayList.iterator();
		
		while (iterator.hasNext()) {
			aCategory = (String) iterator.next();
			categoryComboBox.addItem(aCategory);
		}
		
		// creating go button
		goButton = new JButton("Go!");
		
		// create the top panel
		topPanel = new JPanel();
		
		// create the list box and associated scroll panes
		musicListBox = new JList();
		musicListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		musicScrollPane = new JScrollPane(musicListBox);
		
		detailsButton = new JButton("Details...");
		addButton = new JButton("Add...");
		clearButton = new JButton("Clear");
		exitButton = new JButton("Exit");
		
		bottomPanel = new JPanel();
		
		// setting the layout
		this.setLayout(new BorderLayout());
		
		// set layout and add components for top panel
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));    // this is the default layout for panels, 
																// but let's set it again to enhance our 
																// learning experience ;-)
												
		topPanel.add(selectionLabel);
		topPanel.add(categoryComboBox);
		topPanel.add(goButton);
		
		this.add(BorderLayout.NORTH, topPanel);
		
		this.add(BorderLayout.CENTER, musicScrollPane);
		
		// use the default flow layout for the bottom panel
		bottomPanel.add(detailsButton);
		bottomPanel.add(addButton);
		bottomPanel.add(clearButton);
		bottomPanel.add(exitButton);
		
		this.add(BorderLayout.SOUTH, bottomPanel);
		
		
		//
		//  REGISTER LISTENERS
		//
		goButton.addActionListener(new GoActionListener());
		detailsButton.addActionListener(new DetailsActionListener());
		exitButton.addActionListener(new ExitActionListener());
	}

	/**
	 *  When the go button is pressed, we will:
	 *
	 *  1.  Find out the selected category from combo box
	 *  2.  Get a list of recordings for the selected category from the data accessor
	 *  3.  Populate the list box w/ the recording artist and titles
	 */
	class GoActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {
		
			// get the selected item from the category combo box
			String category = (String) categoryComboBox.getSelectedItem();
		
			// get a list of recordings from the data accessor
			musicArrayList = myDataAccessor.getRecordings(category);
		
			// convert ArrayList to an array of objects
			Object[] theData = musicArrayList.toArray();
			
			musicListBox.setListData(theData);
		}
	}
	
	
	/**
	 *  When the details button is pressed, we will
	 *
	 *  1.  Get the selected item index from the list box
	 *  2.  Get the desired music recording from the music array list
	 *  3.  Create a details dialog for the music recording
	 *  4.  Show the details dialog
	 */
	class DetailsActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {
		
			//  1.  Get the selected item index from the list box
			int index = musicListBox.getSelectedIndex();
			
			//  2.  Get the desired music recording from the music array list
			MusicRecording myMusicRecording = (MusicRecording) musicArrayList.get(index);
			
			//  3.  Create a details dialog for the music recording
			MusicDetailsDialog myDetailsDialog = new MusicDetailsDialog(parentFrame, myMusicRecording);
			
			//  4.  Show the details dialog
			myDetailsDialog.setVisible(true);
		}
	}


	/**
	 *  When called, let's call the exit routine of the parent frame
	 */
	class ExitActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {

			parentFrame.exit();
		}
	}

}