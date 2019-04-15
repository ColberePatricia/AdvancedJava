/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjavaproject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 *  This class is the main frame for the application.
 *  It creates the ApplicationPanel adds it to
 *  the tabbed pane.  See the diagram below for basic layout.
 *
 *  <pre>
 *      
 *        _____________________________________
 *       |       ______              ______    |
 *       |  A = |matrix|        b = |vector|   |
 *       |                                     |
 *       |   LU pivot   Inverse   Clear        |
 *       |              _______                |
 *       |             |Results|               |
 *       |           Error display             |
 *       |   ________________                  |
 *       |  |Result to load|v|   Load   Save   |
 *       |_____________________________________|
 * 
 *  </pre>
 *
 *
 *  @author, Patricia
 *
 */

public class MainFrame extends JFrame {
    
	/**
	 *  The application panel
	 */
	protected ApplicationPanel applicationPanel;
	
	/**
	 *  This constructor creates the ApplicationPalnel.
	 */
	public MainFrame() {
	
		//
		//  LAYOUT FRAME
		//
		setTitle("Welcome to the Application!");
		
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		
                try {
                    applicationPanel = new ApplicationPanel(this);
                } catch (IOException exc) {
                    System.out.println("IOException caught in MainFrame(): "+exc);
                }
		

		container.add(BorderLayout.CENTER, applicationPanel);

		//
		// GIVEN:  Menu Bar is created
		//
		JMenuBar myMenuBar = new JMenuBar();
		
		//
		//  CREATE A "FILE" MENU W/ "EXIT" MENU ITEM
		//
		JMenu fileMenu = new JMenu("File");
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
         * @param theMenuBar menu bar to control the look and feel of the application
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
	 *  simply exits the program.
	 */	
	class ExitActionListener implements ActionListener {
	
                /**
                 * 
                 * @param event  the event of exiting
                 */
		public void actionPerformed(ActionEvent event) {
		
			exit();
		}
	}
	
	/**
	 *  This class handles window closing event.  The event handler
	 *  simply exits the program.
	 */
	class WindowCloser extends WindowAdapter {

		/**
		 *  let's call our exit() method defined above  
                 * 
                 *  @param e the event of closing the window
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
	
                /**
                 * 
                 * @param event the event of changing the look and feel
                 */
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
	 
                /**
                 * 
                 * @param event the event on clicking on about
                 */
	 	public void actionPerformed(ActionEvent event) {
			
			String msg = "With this application you can do a LU pivot or find an inverse!\nYou can also save your results and load previous results!";
			JOptionPane.showMessageDialog(MainFrame.this, msg);
		}
	 }
}
