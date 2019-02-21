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
	 *  Creates the GUI components and arranges them
	 *  in the container.
	 */
	public MusicPanel(MainFrame theParentFrame) {
	
		parentFrame = theParentFrame;

		//
		//  TO DO:  Start adding your code here.
		//
		selectionLabel = new JLabel("Select a Music Category");
		
		// create and populate the combo box
		categoryComboBox = new JComboBox();
		categoryComboBox.addItem("Classical");
		categoryComboBox.addItem("Country");
		categoryComboBox.addItem("Hip-Hop");
		categoryComboBox.addItem("Jazz");
		categoryComboBox.addItem("Rock");
		
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
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));     											
		topPanel.add(selectionLabel);
		topPanel.add(categoryComboBox);
		topPanel.add(goButton);
		
		this.add(topPanel, BorderLayout.NORTH);
		
		this.add(musicScrollPane, BorderLayout.CENTER);
		
		// use the default flow layout for the bottom panel
		bottomPanel.add(detailsButton);
		bottomPanel.add(addButton);
		bottomPanel.add(clearButton);
		bottomPanel.add(exitButton);
		
		this.add(bottomPanel, BorderLayout.SOUTH);


		//
		//  BONUS WORK
		//
		selectionLabel.setForeground(Color.black);
		
		goButton.setForeground(Color.yellow);
		detailsButton.setForeground(Color.yellow);
		addButton.setForeground(Color.yellow);
		clearButton.setForeground(Color.yellow);
		exitButton.setForeground(Color.yellow);

		goButton.setToolTipText("Retrieves a list of recordings");
		detailsButton.setToolTipText("Displays details for a recording");
		addButton.setToolTipText("Add a new recording");
		clearButton.setToolTipText("Clears the music panel");
		exitButton.setToolTipText("Exits the program");
		
	}
	
}