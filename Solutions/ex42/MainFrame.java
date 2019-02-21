import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *  This class is the main frame for the RainForest application.
 *  It creates the MusicPanel and VideoPanel and adds them to
 *  the tabbed pane.  See the diagram below for basic layout.
 *
 *  <pre>
 *        _______  _______ 
 *       / Music \/ Video \____________________
 *       |                                     |
 *       |                                     |
 *       |                                     |
 *       |                                     |
 *       |_____________________________________|
 * 
 *  </pre>
 *
 *
 *  @author Development Team
 *
 */
public class MainFrame extends JFrame {

	/**
	 *  The tabbed pane to hold the music and 
	 *  video panels
	 */
	protected JTabbedPane tabbedPane;
	
	/**
	 *  The music panel
	 */
	protected MusicPanel musicPanel;
	
	/**
	 *  The video panel
	 */
	protected VideoPanel videoPanel;
	
	/**
	 *  This constructor creates the MusicPanel and VideoPanel.  These
	 *  panels are added to a tabbed pane.
	 */
	public MainFrame() {
	
		//
		//  LAYOUT FRAME
		//
		setTitle("Welcome to the RainForest!  v5.2");
		
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		
		tabbedPane = new JTabbedPane();

		musicPanel = new MusicPanel(this);
		tabbedPane.addTab("Music", musicPanel);

		videoPanel = new VideoPanel(this);
		tabbedPane.addTab("Video", videoPanel);
		
		container.add(BorderLayout.CENTER, tabbedPane);

		setSize(500, 400);
		setLocation(100, 100);
		
		//
		//  ADD LISTENERS
		//
		this.addWindowListener(new WindowCloser());
		
	}

	/**
	 *  Hides the window and disposes of its resources.  Finally, we exit.
	 */
	public void exit() {

		setVisible(false);
		dispose();
		System.exit(0);
	
	}

	//
	//  INNER CLASS
	//
	
	/**
	 *  This class handles window closing event.  The event handler
	 *  simply exists the program.
	 */
	class WindowCloser extends WindowAdapter {

		/**
		 *  let's call our exit() method defined above  
		 */
		public void windowClosing(WindowEvent e) {

			exit();
		}
	}
}