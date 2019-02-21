import java.awt.*;
import javax.swing.*;

/**
 *  This class is the main panel for the RainForest application.
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
 *  @author, App Development Team
 *
 */
public class MainPanel extends JPanel {

	public MainPanel() {
  
		// Container container = this.getContentPane();
		// container.setLayout(new BorderLayout());

		this.setLayout(new BorderLayout());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		MusicPanel musicPanel = new MusicPanel();
		tabbedPane.addTab("Music", musicPanel);

		VideoPanel videoPanel = new VideoPanel();
		tabbedPane.addTab("Video", videoPanel);
		
		this.add(BorderLayout.CENTER, tabbedPane);
  
	}
}