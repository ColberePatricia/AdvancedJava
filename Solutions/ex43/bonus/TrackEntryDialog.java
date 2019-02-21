import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.*;

/**
 *  This class displays a dialog for adding a new track. <p>
 *
 *  Usage Example:
 *
 *   <pre>
 *     // create and show the dialog
 *     //
 *     TrackEntryDialog myEntryDialog = new TrackEntryDialog(myParentFrame);
 *     myEntryDialog.setVisible(true);
 *
 *
 *     // if the user pressed "Ok" then retrieve the track from the dialog
 *     //
 *     Track theTrack;
 *
 *     if (myEntryDialog.isOkButtonPressed()) {
 *         theTrack = myEntryDialog.getTrack();
 *     }
 *
 *   </pre>
 *
 *   @author Development Team
 */
public class TrackEntryDialog extends JDialog {

	
	/**
	 *  A holder for the parent frame that is passed in during construction
	 */
	protected Frame parentFrame;
		
	public JTextField trackTitleTextField;
	public JTextField titleTextField;
	public JTextField minutesTextField;
	public JTextField secondsTextField;

	protected boolean okButtonPressed = false;
	protected JButton okButton;  	
	protected JButton cancelButton;
	
	
	/**
	 *  Constructs a modal dialog 
	 *
	 *  @param theParentFrame the parent frame for this dialog
	 */
	public TrackEntryDialog(Frame theParentFrame) {

		this(theParentFrame, "Add A New Track");	
	}
	
	
	/**
	 *  Constructs a modal dialog.  This version allows you to customize the title
	 *
	 *  @param theParentFrame the parent frame for this dialog
	 *  @paran theTitle the title of the dialog
	 */
	public TrackEntryDialog(Frame theParentFrame, String theTitle) {
	
		super(theParentFrame, theTitle, true);		// creates a modal dialog

		parentFrame = theParentFrame;

		buildGui();
	}
	
	/**
	 *  This method covers the details of creating and arranging the dialog components.
	 */
	private void buildGui() {
	
		Container container = this.getContentPane();
		
		container.setLayout(new BorderLayout());

		//
		//  track info panel
		//
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new EmptyBorder(10, 10, 0, 10));

		infoPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//  create and arrange the label, "Track Title: ..."
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5, 0, 5, 5);
		JLabel trackTitleLabel = new JLabel("Track Title:  ");
		trackTitleLabel.setForeground(Color.black);
		infoPanel.add(trackTitleLabel, c);

		// create and arrange the text field for "Track Title"
		c.gridx = 3;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1.0;
		c.insets = new Insets(2, 0, 5, 5);
		trackTitleTextField = new JTextField(15);
		infoPanel.add(trackTitleTextField, c);
				
		//  create and arrange the label, "Duration: ..."
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weightx = 0.0;
		c.insets = new Insets(2, 0, 15, 5);
		JLabel titleLabel = new JLabel("Duration -  ");
		titleLabel.setForeground(Color.black);
		infoPanel.add(titleLabel, c);

		//  create and arrange the label, "Minutes: ..."
		c.gridx = 3;
		c.anchor = GridBagConstraints.EAST;
		JLabel minutesLabel = new JLabel("Minutes:  ");
		minutesLabel.setForeground(Color.black);
		infoPanel.add(minutesLabel, c);
		
		c.gridx = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.WEST;
		minutesTextField = new JTextField(3);
		infoPanel.add(minutesTextField, c);
		
		c.anchor = GridBagConstraints.EAST;
		JLabel secondsLabel = new JLabel("Seconds:  ");
		secondsLabel.setForeground(Color.black);
		infoPanel.add(secondsLabel, c);
		
		c.anchor = GridBagConstraints.WEST;
		secondsTextField = new JTextField(3);
		infoPanel.add(secondsTextField, c);
		
		container.add(BorderLayout.NORTH, infoPanel);
		
		//
		//  Create and add the "OK" button
		//
		JPanel bottomPanel = new JPanel();
		okButton = new JButton("OK");
		bottomPanel.add(okButton);
		
		cancelButton = new JButton("Cancel");
		bottomPanel.add(cancelButton);
		
		container.add(BorderLayout.SOUTH, bottomPanel);
		
		ActionListener buttonListener = new OkCancelActionListener();
		okButton.addActionListener(buttonListener);
		cancelButton.addActionListener(buttonListener);
				
		this.pack();
		
		// locate this window based off of the parent frame
		Point parentLocation = parentFrame.getLocation();
		this.setLocation(parentLocation.x + 100, parentLocation.y + 100);
	}
	
	/**
	 *  Returns true if the "Ok" button was pressed.
	 */
	public boolean isOkButtonPressed() {
		
		return okButtonPressed;
	}
	
	/**
	 *  Returns a Track object for the information entered into the dialog.
	 */
	public Track getTrack() {
	
		String title = trackTitleTextField.getText();
		String minutesString = minutesTextField.getText();
		String secondsString = secondsTextField.getText();
		
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		
		// get the minutes
		try {
			minutes = Integer.parseInt(minutesString);
		}
		catch (Exception e) {
			// ignore for now and leave value as 0
		}

		// get the seconds
		try {
			seconds = Integer.parseInt(secondsString);
		}
		catch (Exception e) {
			// ignore for now and leave value as 0
		}

		Duration trackDuration = new Duration(hours, minutes, seconds);
		Track theTrack = new Track(title, trackDuration);
		
		return theTrack;
	}
	
	
	//
	//  INNER CLASS
	//
	
	/**
	 *  Sets a flag if the "Ok" button was pressed.  Closes the dialog.
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
			
			// finally, let's close the dialog
			setVisible(false);
		}
	}
}