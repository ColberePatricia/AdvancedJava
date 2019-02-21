import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.*;

/**
 *  This class displays a dialog for entering a new recording. <p>
 *
 *  Usage Example:
 *
 *   <pre>
 *
 *     // create and show the dialog
 *     //
 *     MusicEntryDialog myEntryDialog = new MusicEntryDialog(myParentFrame, categoryArrayList);
 *     myEntryDialog.setVisible(true);
 *
 *
 *     // if the user pressed "Ok" then retrieve the music recording from the dialog
 *     //
 *     MusicRecording theRecording;
 *
 *     if (myEntryDialog.isOkButtonPressed()) {
 *         theRecording = myEntryDialog.getMusicRecording();
 *     }
 *
 *   </pre>
 *
 *   @author, App Development Team
 */
public class MusicEntryDialog extends JDialog {

	
	/**
	 *  A holder for the parent frame that is passed in during construction
	 */
	protected Frame parentFrame;

	/**
	 *  A holder the list of categories
	 */
	protected ArrayList categoryArrayList;
	
	/** 
	 *  Text field for the artist's name
	 */
	protected JTextField artistTextField;

	/** 
	 *  Text field for the music recording's title
	 */
	protected JTextField titleTextField;
	
	/** 
	 *  Combo box to display the categories
	 */
	protected JComboBox categoryComboBox;
	
	/** 
	 *  Boolean flag for the status of "Ok" button being pressed
	 */
	protected boolean okButtonPressed = false;
	
	/**
	 *  The "Ok" button
	 */
	protected JButton okButton;  	

	/**
	 *  The "Cancel" button
	 */
	protected JButton cancelButton;
	
	/**
	 *  A list model for the TrackList component (view).
	 */
	 protected DefaultListModel trackListModel;
	 
	 
	/**
	 *  Constructs a modal dialog for adding a new music recording.  The
	 *  list of categories are used to populate the combo box.
	 *
	 *  @param theParentFrame the parent frame for this dialog
	 *  @param theCategories a list of music categories
	 */
	public MusicEntryDialog(Frame theParentFrame, ArrayList theCategories) {

		this(theParentFrame, "Add A New Recording", theCategories);	
	}
	
	
	/**
	 *  Constructs a modal dialog for adding a new music recording.  This version allows you to customize the title
	 *
	 *  @param theParentFrame the parent frame for this dialog
	 *  @paran theTitle the title of the dialog
	 *  @param theCategories a list of music categories
	 */
	public MusicEntryDialog(Frame theParentFrame, String theTitle, ArrayList theCategories) {
	
		super(theParentFrame, theTitle, true);		// creates a modal dialog

		parentFrame = theParentFrame;
		categoryArrayList = theCategories;

		buildGui();
	}
	
	/**
	 *  This method covers the details of creating and arranging the dialog components.
	 */
	private void buildGui() {
	
		Container container = this.getContentPane();
		
		container.setLayout(new BorderLayout());
				
		//
		//  artist info panel
		//
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new EmptyBorder(10, 10, 0, 10));

		infoPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//  create and arrange the label, "Artist: ..."
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5, 0, 2, 5);
		JLabel artistLabel = new JLabel("Artist:  ");
		artistLabel.setForeground(Color.black);
		infoPanel.add(artistLabel, c);
				
		//  create and arrange the label, "Title: ..."
		c.gridy = 2;
		c.insets = new Insets(2, 0, 2, 5);
		JLabel titleLabel = new JLabel("Title:  ");
		titleLabel.setForeground(Color.black);
		infoPanel.add(titleLabel, c);

		//  create and arrange the label, "Category: ..."
		c.gridy = 3;
		c.insets = new Insets(2, 0, 10, 5);
		JLabel categoryLabel = new JLabel("Category:  ");
		categoryLabel.setForeground(Color.black);
		infoPanel.add(categoryLabel, c);

		
		// create and arrange the text field for "Artist"
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 1.0;		
		c.insets = new Insets(2, 0, 0, 5);
		artistTextField = new JTextField(15);
		infoPanel.add(artistTextField, c);

		// create and arrange the text field for "Title"
		c.gridy = 2;
		c.insets = new Insets(2, 0, 10, 5);
		titleTextField = new JTextField(15);
		infoPanel.add(titleTextField, c);

		// create and arrange the combo box for "Category
		categoryComboBox = new JComboBox();
		Iterator iterator = categoryArrayList.iterator();
		String aCategory;
		
		while (iterator.hasNext()) {
			
			aCategory = (String) iterator.next();
			categoryComboBox.addItem(aCategory);
		}
		c.gridy = 3;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		infoPanel.add(categoryComboBox, c);

		
		// create and arrange the add button
		c.gridy = 4;
		JButton addTrackButton = new JButton("Add Track...");
		addTrackButton.setToolTipText("Click to add a new track to the recording");
		infoPanel.add(addTrackButton, c);
		
		container.add(BorderLayout.NORTH, infoPanel);
		
		//
		//  Setup the track list box
		//
		trackListModel = new DefaultListModel();
		
		JList tracksListBox= new JList(trackListModel);
		JScrollPane tracksScrollPane = new JScrollPane(tracksListBox);
		
		TitledBorder listBorder = BorderFactory.createTitledBorder("List of Tracks");
		listBorder.setTitleColor(Color.black);
		
		tracksScrollPane.setBorder(listBorder);
		
		container.add(BorderLayout.CENTER, tracksScrollPane);
		
		//
		//  Create and add the "OK" button
		//
		JPanel bottomPanel = new JPanel();
		okButton = new JButton("OK");
		bottomPanel.add(okButton);
		
		cancelButton = new JButton("Cancel");
		bottomPanel.add(cancelButton);
		
		container.add(BorderLayout.SOUTH, bottomPanel);
		
		// register listeners
		addTrackButton.addActionListener(new TrackActionListener());
		
		ActionListener buttonListener = new OkCancelActionListener();
		okButton.addActionListener(buttonListener);
		cancelButton.addActionListener(buttonListener);
		
		// this.setSize(300, 380);
		
		this.pack();
		
		// locate this window based off of the parent frame
		Point parentLocation = parentFrame.getLocation();
		this.setLocation(parentLocation.x + 50, parentLocation.y + 50);
	}

	
	/**
	 *  Returns true if the "Ok" button was pressed.
	 */
	public boolean isOkButtonPressed() {
		
		return okButtonPressed;	
	}
	
	/**
	 *  Returns a MusicRecording object for the information entered into the dialog. <p>
	 *
	 *  This is accomplished by reading the data from the GUI fields and constructing
	 *  a MusicRecording object.  This object is then returned to the caller.
	 */
	public MusicRecording getMusicRecording() {
	
		// get the artist, title and category
		String artist = artistTextField.getText();
		String title = titleTextField.getText();
		String category = (String) categoryComboBox.getSelectedItem();

		// just for fun, let's compute a random price for the recording
		// select a random price between 9.99 and 15.99
		int basePrice = 9 + (int) (Math.random() * 7);
		double price = basePrice + .99;
		
		// build up the track list array based on the list model
		int size = trackListModel.getSize();
		Track[] trackList = new Track[size];
		
		for (int i=0; i < size; i++) {
			trackList[i] = (Track) trackListModel.getElementAt(i);
		}
		
		// now let's construct and return the recording
		MusicRecording theRecording = new MusicRecording(artist, trackList, title,  
														 price, category, "blank.gif");
				
		return theRecording;
	}
	
	
	//
	//  INNER CLASS
	//
	
	/**
	 *  Closes the dialog when ok button is pressed
	 */
	class OkCancelActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			
			if (source == okButton) {
				okButtonPressed = true;
			}
			else {
				okButtonPressed = false;
			}
			
			setVisible(false);
		}
	}


	/**
	 *  Shows the "TrackEntry" dialog box.  After the user enters information
	 *  in the track dialog, we check to see if the okay button was pressed.  If 
	 *  so, then we get the track from the dialog and add it to list model.  As a result
	 *  the trackList component is updated.  An example of Model-View-Controller (MVC).
	 */
	class TrackActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event)
		{
			TrackEntryDialog myTrackDialog = new TrackEntryDialog(parentFrame);
			
			myTrackDialog.setVisible(true);
			
			if (myTrackDialog.isOkButtonPressed()) {
				Track theTrack = myTrackDialog.getTrack();
				trackListModel.addElement(theTrack);
			}
		}
	}
}