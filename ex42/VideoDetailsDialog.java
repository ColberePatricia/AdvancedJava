import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.*;

/**
 *  This class displays the details of a video recording.
 *  It shows the producer's name, recording title and price.
 *  Also, the dialog shows an image of the recording along
 *  with a list of actors. <p>
 *
 *  Usage Example:
 *
 *   <pre>
 *
 *     VideoDetailsDialog myDetailsDialog = new VideoDetailsDialog(myParentFrame, myRecording);
 *     myDetailsDialog.setVisible(true);
 *
 *   </pre>
 *
 *   @author, App Development Team
 */
public class VideoDetailsDialog extends JDialog {

	/**
	 *  A holder for the video recording that is passed in during construction
	 */
	protected VideoRecording myRecording;
	
	/**
	 *  A holder for the parent frame that is passed in during construction
	 */
	protected Frame parentFrame;
	
	
	/**
	 *  Constructs a modal dialog for a given video recording.  This version
	 *  uses the artist name and recording title for the dialog title.
	 *
	 *  @param theParentFrame the parent frame for this dialog
	 *  @param theVideoRecording the video recording to display
	 */
	public VideoDetailsDialog(Frame theParentFrame, VideoRecording theVideoRecording) {

		this(theParentFrame, "Video Details for " + theVideoRecording.toString(), theVideoRecording);	
	}
	
	
	/**
	 *  Constructs a modal dialog for a given video recording.  This version allows you to customize the title
	 *
	 *  @param theParentFrame the parent frame for this dialog
	 *  @paran theTitle the title of the dialog
	 *  @param theVideoRecording the video recording to display
	 */
	public VideoDetailsDialog(Frame theParentFrame, String theTitle, VideoRecording theVideoRecording) {
	
		super(theParentFrame, theTitle, true);		// creates a modal dialog

		myRecording = theVideoRecording;
		parentFrame = theParentFrame;

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
		
		//  create and arrange the label, "Director: ..."
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 0, 2, 15);
		JLabel titleLabel = new JLabel("Title:  " + myRecording.getTitle());
		titleLabel.setForeground(Color.black);
		infoPanel.add(titleLabel, c);
		
		//  create and arrange the label, "Title: ..."
		c.gridy = GridBagConstraints.RELATIVE;
		c.insets = new Insets(2, 0, 15, 5);
		JLabel artistLabel = new JLabel("Director:  " + myRecording.getProducer());
		artistLabel.setForeground(Color.black);
		infoPanel.add(artistLabel, c);
		

		// category
		JLabel categoryLabel = new JLabel("Category:  " + myRecording.getCategory());
		categoryLabel.setForeground(Color.black);
		c.insets = new Insets(2, 0, 2, 5);
		infoPanel.add(categoryLabel, c);

		// rating
		JLabel ratingLabel = new JLabel("Rating:  " + myRecording.getRating());
		ratingLabel.setForeground(Color.black);
		infoPanel.add(ratingLabel, c);
		
		// duration
		Duration theDuration = myRecording.getDuration();
		int runningTime = theDuration.getTotalSeconds() / 60;
		JLabel durationLabel = new JLabel("Duration:  " + runningTime + " mins.");
		durationLabel.setForeground(Color.black);
		infoPanel.add(durationLabel, c);
		
		// releaesed
		JLabel releasedLabel = new JLabel("Released:  " + myRecording.getYearReleased());
		releasedLabel.setForeground(Color.black);
		c.insets = new Insets(2, 0, 15, 5);
		infoPanel.add(releasedLabel, c);

		//  create and arrange the label, "Price: $..."
		JLabel priceLabel = new JLabel("Price:  $" + myRecording.getPrice() + " USD");
		priceLabel.setForeground(Color.black);
		c.insets = new Insets(2, 0, 15, 0);
		infoPanel.add(priceLabel, c);
		
		//  create an arrange the recording icon
		c.gridx = 5;
		c.gridy = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 7;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1.0;
		c.weighty = 1.0;		
		c.insets = new Insets(5, 5, 5, 0);
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
				recordingIcon = new ImageIcon(getClass().getResource("images/video/" + imageName));
				recordingLabel = new JLabel(recordingIcon);	
			}
		}
		catch (Exception exc)
		{
			// okay...couldn't load.  Just give a text message.
			recordingLabel = new JLabel("  Image not available  ");
		}
		
		recordingLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		infoPanel.add(recordingLabel, c);
		
		container.add(BorderLayout.NORTH, infoPanel);
		
		//
		//  Populate the track list box
		//
		String[] actorsArray = myRecording.getActors();
		
		JList actorsListBox= new JList(actorsArray);
		JScrollPane actorsScrollPane = new JScrollPane(actorsListBox);
		
		TitledBorder listBorder = BorderFactory.createTitledBorder("Starring:");
		listBorder.setTitleColor(Color.black);
		
		actorsScrollPane.setBorder(listBorder);
		
		container.add(BorderLayout.CENTER, actorsScrollPane);
		
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
		Point parentLocation = parentFrame.getLocation();
		this.setLocation(parentLocation.x + 50, parentLocation.y + 50);
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