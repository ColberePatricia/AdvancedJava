import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.*;

/**
 *  This class displays the details of a music recording.
 *  It shows the artist's name, recording title and price.
 *  Also, the dialog shows an image of the recording along
 *  with its track list. <p>
 *
 *  Usage Example:
 *
 *   <pre>
 *
 *     MusicDetailsDialog myDetailsDialog = new MusicDetailsDialog(myParentContainer, myRecording);
 *     myDetailsDialog.setVisible(true);
 *
 *   </pre>
 *
 *   @author, App Development Team
 */
public class MusicDetailsDialog extends JDialog {

	/**
	 *  A holder for the music recording that is passed in during construction
	 */
	protected MusicRecording myRecording;
	
	/**
	 *  A holder for the parent frame that is passed in during construction
	 */
	protected Container parentContainer;
	
	
	/**
	 *  Constructs a modal dialog for a given music recording.  This version
	 *  uses the artist name and recording title for the dialog title.
	 *
	 *  @param theParentContainer the parent frame for this dialog
	 *  @param theMusicRecording the music recording to display
	 */
	public MusicDetailsDialog(Container theParentContainer, MusicRecording theMusicRecording) {

		this(theParentContainer, "Music Details for " + theMusicRecording.toString(), theMusicRecording);	
	}
	
	
	/**
	 *  Constructs a modal dialog for a given music recording.  This version allows you to customize the title
	 *
	 *  @param theParentContainer the parent frame for this dialog
	 *  @paran theTitle the title of the dialog
	 *  @param theMusicRecording the music recording to display
	 */
	public MusicDetailsDialog(Container theParentContainer, String theTitle, MusicRecording theMusicRecording) {
	
		super(new JFrame(), theTitle, true);		// creates a modal dialog

		myRecording = theMusicRecording;
		parentContainer = theParentContainer;

		buildGui();
	}
	
	/**
	 *  This method covers the details of creating and arranging the dialog components.
	 */
	private void buildGui() {
	
		Container container = this.getContentPane();
		
		container.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		
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
		c.insets = new Insets(10, 0, 2, 10);
		JLabel artistLabel = new JLabel("Artist:  " + myRecording.getArtist());
		artistLabel.setForeground(Color.black);
		infoPanel.add(artistLabel, c);
		
		//  create and arrange the label, "Title: ..."
		c.gridy = GridBagConstraints.RELATIVE;
		c.insets = new Insets(2, 0, 10, 10);
		JLabel titleLabel = new JLabel("Title:  " + myRecording.getTitle());
		titleLabel.setForeground(Color.black);
		infoPanel.add(titleLabel, c);

		//  create and arrange the label, "Category ..."
		JLabel categoryLabel = new JLabel("Category:  " + myRecording.getCategory());
		c.insets = new Insets(2, 0, 2, 0);
		categoryLabel.setForeground(Color.black);
		infoPanel.add(categoryLabel, c);

		//  create and arrange the label, "Duration..."
		Duration theDuration = myRecording.getDuration();
		int runningTime = theDuration.getTotalSeconds() / 60;
		JLabel durationLabel = new JLabel("Duration:  " + runningTime + " mins.");
		durationLabel.setForeground(Color.black);
		infoPanel.add(durationLabel, c);
		
		//  create and arrange the label, "Price: $..."
		JLabel priceLabel = new JLabel("Price: $" + myRecording.getPrice() + " USD");
		c.insets = new Insets(10, 0, 2, 0);
		priceLabel.setForeground(Color.black);
		infoPanel.add(priceLabel, c);
		
		//  create an arrange the recording icon
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 5;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1.0;
		c.weighty = 1.0;		
		c.insets = new Insets(5, 5, 20, 0);
		String imageName = myRecording.getImageName();
		ImageIcon recordingIcon = null;
		JLabel recordingLabel = null;

		// attempt to load the image		
		try
		{
			if (imageName.trim().length() == 0) {
				recordingLabel = new JLabel("  Image not available  ");
			}
			else {			
				recordingIcon = new ImageIcon(getClass().getResource("images/music/" + imageName));
				recordingLabel = new JLabel(recordingIcon);	
			}
		}
		catch (Exception exc)
		{
			// okay...couldn't load.  Just give a text message.
			recordingLabel = new JLabel("  Image not available  ");
		}
		
		recordingLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		recordingLabel.setToolTipText(myRecording.getArtist());
		
		infoPanel.add(recordingLabel, c);
		
		container.add(BorderLayout.NORTH, infoPanel);
		
		//
		//  Populate the track list box
		//
		Track[] tracksArray = myRecording.getTrackList();
		
		JList tracksListBox= new JList(tracksArray);
		JScrollPane tracksScrollPane = new JScrollPane(tracksListBox);
		
		TitledBorder listBorder = BorderFactory.createTitledBorder("List of Tracks");
		listBorder.setTitleColor(Color.black);
		
		tracksScrollPane.setBorder(listBorder);
		
		container.add(BorderLayout.CENTER, tracksScrollPane);
		
		//
		//  Create and add the "OK" button
		//
		JPanel bottomPanel = new JPanel();
		JButton okButton  = new JButton("OK");
		bottomPanel.add(okButton);
		container.add(BorderLayout.SOUTH, bottomPanel);
		
		okButton.addActionListener(new OkButtonActionListener());
		
		this.pack();
		
		// locate this window based off of the parent frame
		Point parentLocation = parentContainer.getLocation();
		this.setLocation(parentLocation.x + 250, parentLocation.y + 150);
	}
	
	//
	//  INNER CLASS
	//
	
	/**
	 *  Closes the dialog when ok button is pressed
	 */
	class OkButtonActionListener implements ActionListener {
		
		/**
		 *  Simply closes this dialog.
		 */
		public void actionPerformed(ActionEvent event)
		{
			setVisible(false);
		}
	}
}