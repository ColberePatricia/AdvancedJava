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
		setTitle("Welcome to the RainForest!  v5.3");
		
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		
		tabbedPane = new JTabbedPane();

		musicPanel = new MusicPanel(this);
		tabbedPane.addTab("Music", musicPanel);

		videoPanel = new VideoPanel(this);
		tabbedPane.addTab("Video", videoPanel);
		
		container.add(BorderLayout.CENTER, tabbedPane);

		//
		// GIVEN:  Menu Bar is created
		//
		JMenuBar myMenuBar = new JMenuBar();
		
		//
		//  CREATE A "FILE" MENU W/ "EXIT" MENU ITEM
		//
		JMenu fileMenu = new JMenu("File");

		//
		// BONUS WORK:  Nested menus
		//
		JMenu openMenu = new JMenu("Open");
			JMenuItem localMenuItem = new JMenuItem("Local Drive...");
			openMenu.add(localMenuItem);

			JMenuItem networkMenuItem = new JMenuItem("Network...");
			openMenu.add(networkMenuItem);

			JMenuItem webMenuItem = new JMenuItem("Web Location...");
			openMenu.add(webMenuItem);
		fileMenu.add(openMenu);

		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		
		// add the file menu to the menu bar
		myMenuBar.add(fileMenu);
		
		// register a listener for the "exit" menu item.
		exitMenuItem.addActionListener(new ExitActionListener());
		
				
		//  GIVEN: Builds a menu based on the installed look and feels.  More info on
		//  look and feels in the "setupLookAndFeelMenu" method listed below.
		setupLookAndFeelMenu(myMenuBar);
		
		
		//
		//  CREATE A "HELP" MENU W/ "ABOUT" MENU ITEM
		//
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutMenuItem = new JMenuItem("About");
		helpMenu.add(aboutMenuItem);

		// add help menu to menu bar
		myMenuBar.add(helpMenu);
		
		// register a listener for the "about" menu item
		aboutMenuItem.addActionListener(new AboutActionListener());
		
		this.setJMenuBar(myMenuBar);
		
		setSize(500, 400);
		setLocation(100, 100);
		
		//
		//  ADD LISTENERS
		//
		this.addWindowListener(new WindowCloser());
	
		
		//
		//  BONUS WORK:  Setting mnemonics and accelerators
		//
		fileMenu.setMnemonic('f');
		exitMenuItem.setMnemonic('x');
		helpMenu.setMnemonic('h');
		aboutMenuItem.setMnemonic('a');
		
		// set-up CTRL-X for exit
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		// set-up CTRL-S for save
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		
		// set-up CTRL-A for about
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
	}

	/**
	 *  GIVEN:  Look And Feel
	 *
	 *  Builds a menu based on the installed look and feels.  This method loops
	 *  through each installed look and feel and creates a menu item w/ the name
	 *  of the look and feel.  We also keep track of the class name by setting the
	 *  action command for the menu item.  This allows the associated listener to
	 *  determine the class name to load for the look and feel.  <p>  
	 *
	 *  More info on look and feels see the "LookAndFeelListener" at the bottom of the file.
	 *
	 */
	protected void setupLookAndFeelMenu(JMenuBar theMenuBar) {

		UIManager.LookAndFeelInfo[] lookAndFeelInfo = UIManager.getInstalledLookAndFeels();
		JMenu lookAndFeelMenu = new JMenu("Options");
		JMenuItem anItem = null;
		LookAndFeelListener myListener = new LookAndFeelListener();
		
		try {
			for (int i=0; i < lookAndFeelInfo.length; i++) {
				anItem = new JMenuItem(lookAndFeelInfo[i].getName() + " Look and Feel");
				anItem.setActionCommand(lookAndFeelInfo[i].getClassName());
				anItem.addActionListener(myListener);

				lookAndFeelMenu.add(anItem);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		theMenuBar.add(lookAndFeelMenu);
	}

	
	/**
	 *  Helper method that hides the window and disposes of its resources.  Finally, we exit.
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
	 *  This class handles action events.  The event handler
	 *  simply exists the program.
	 */	
	class ExitActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {
		
			exit();
		}
	}
	
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

	/**
	 *  Listener class to set the look and feel at run time.
	 *
	 *  Changing the look and feel is so simple :-)  Just call the
	 *  method:
	 *
	 *  <code>
	 *     UIManager.setLookAndFeel(<class name of look and feel>);
	 *  </code>
	 *
	 *
	 *  The available look and feels are:
	 *
	 * <pre>
	 *
	 *    NAME             CLASS NAME
	 *    --------         -----------
	 *    Metal            javax.swing.plaf.metal.MetalLookAndFeel
	 *    Windows          com.sun.java.swing.plaf.windows.WindowsLookAndFeel
	 *    Motif            com.sun.java.swing.plaf.motif.MotifLookAndFeel
	 *
	 *  </pre>
	 *
	 *  There is also an additional Mac look and feel that you can download
	 *  from Sun's web site.
	 *
	 */
	class LookAndFeelListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {

			// get the class name to load
			String className = event.getActionCommand();		

			try {
				// set the look and feel
				UIManager.setLookAndFeel(className);
				
				// update our component tree w/ new look and feel
				SwingUtilities.updateComponentTreeUI(MainFrame.this);				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 *  Listener for the "About" menu item
	 */
	 class AboutActionListener implements ActionListener {
	 
	 	public void actionPerformed(ActionEvent event) {
			
			String msg = "RainForest v6:01 am \n The Ultimate Shopping Experience!";
			JOptionPane.showMessageDialog(MainFrame.this, msg);
		}
	 }
}