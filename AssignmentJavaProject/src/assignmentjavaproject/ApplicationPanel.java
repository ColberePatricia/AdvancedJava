/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjavaproject;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author Patricia
 */
public class ApplicationPanel extends JPanel {
        
        protected JLabel matrixLabel;
        protected JLabel vectorLabel;
	
	/**
	 *  A panel to contain components.
	 */
	protected JPanel topPanel;
	/**
	 *  A panel to contain components.
	 */
	protected JPanel middleTopPanel;
	/**
	 *  A panel to contain components.
	 */
	protected JPanel middleBottomPanel;
	/**
	 *  A panel to contain components.
	 */
	protected JPanel bottomPanel;
        
	protected JPanel bottomAndMiddleBottomPanel;

	protected JScrollPane matrixScrollPane;
        protected JScrollPane vectorScrollPane;
	protected JScrollPane resultScrollPane;
	protected JScrollPane displayErrorScrollPane;
	
	/**
	 *  Button labeled "Add"
	 */
	protected JButton LUpivotButton;
	protected JButton inverseButton;
	protected JButton clearButton;
	protected JButton loadButton;
	protected JButton saveButton;
		
		
	/**
	 *  A reference to the parent frame
	 */
	protected MainFrame parentFrame;

        
        protected JTextArea matrixInput;
        protected JTextArea vectorInput;
        protected JTextArea resultOutput;
        protected JTextArea displayError;
        
	
	/**
	 *  A reference music data accessor
	 */
	protected ResultDataAccessor myDataAccessor;

	
	/**
	 *  Creates the GUI components and arranges them
	 *  in the container.
	 */
	public ApplicationPanel(MainFrame theParentFrame) throws IOException{
	
		parentFrame = theParentFrame;

		myDataAccessor = new ResultDataAccessor();
		
		matrixLabel = new JLabel("A = ");
		vectorLabel = new JLabel("      b = ");
		
                // TODO
                // IN THE LOAD PAGE
                /*
		ArrayList resultNameArrayList = myDataAccessor.getResultName();
		
		Iterator iterator = resultNameArrayList.iterator();
		String aCategory;
		
		while (iterator.hasNext()) {
			
			aCategory = (String) iterator.next();
			categoryComboBox.addItem(aCategory);
		}
			*/	
		topPanel = new JPanel();
		bottomPanel = new JPanel();
                middleTopPanel = new JPanel();
                middleBottomPanel = new JPanel();
                bottomAndMiddleBottomPanel = new JPanel();
                
                matrixInput = new JTextArea(17,30);
                vectorInput = new JTextArea(17,30);
                resultOutput = new JTextArea(20,10);
                displayError = new JTextArea(1,10);
                
                
                displayError.setEditable(false);
                displayError.setBackground(Color.LIGHT_GRAY);
				
		matrixScrollPane = new JScrollPane(matrixInput);
		vectorScrollPane = new JScrollPane(vectorInput);
		resultScrollPane = new JScrollPane(resultOutput);
		displayErrorScrollPane = new JScrollPane(displayError);
                
                matrixScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                matrixScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                vectorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                vectorScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                resultScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                resultScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                displayErrorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                displayErrorScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
                
		// create the buttons for the bottom panel
		LUpivotButton = new JButton("LU pivot");
                inverseButton = new JButton("Inverse");
                clearButton = new JButton("Clear");
                loadButton = new JButton("Load");
                saveButton = new JButton("Save");
		
		
		// set the layout for "this" MusicPanel
		this.setLayout(new BorderLayout());
		
		// set the layout for the topPanel and add components
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(matrixLabel);
		topPanel.add(matrixScrollPane);
		topPanel.add(vectorLabel);
		topPanel.add(vectorScrollPane);
		
		// add the top panel to the northern region of "this" MusicPanel
		this.add(BorderLayout.NORTH, topPanel);
		//this.add(BorderLayout.CENTER, matrixScrollPane);
		//this.add(BorderLayout.SOUTH, vectorScrollPane);
		
		// set the layout for the bottomPanel and add components
		middleTopPanel.setLayout(new FlowLayout());
		middleTopPanel.add(LUpivotButton);
		middleTopPanel.add(inverseButton);
		middleTopPanel.add(clearButton);
                
		// add the bottomPanel to the southern region of "this" MusicPanel
		this.add(BorderLayout.CENTER, middleTopPanel);
                
                
                
                bottomPanel.setLayout(new FlowLayout());
                bottomPanel.add(loadButton);
                bottomPanel.add(saveButton);
                
                bottomAndMiddleBottomPanel.setLayout(new BoxLayout(bottomAndMiddleBottomPanel, BoxLayout.Y_AXIS));
                bottomAndMiddleBottomPanel.add(resultScrollPane);
                bottomAndMiddleBottomPanel.add(displayErrorScrollPane);
                bottomAndMiddleBottomPanel.add(bottomPanel);
                
		// add the bottomPanel to the southern region of "this" MusicPanel
		this.add(BorderLayout.SOUTH, bottomAndMiddleBottomPanel);
                
                                
	
		//
		//  REGISTER LISTENERS
		//
		LUpivotButton.addActionListener(new LUpivotActionListener());
		inverseButton.addActionListener(new InverseActionListener());
		clearButton.addActionListener(new ClearActionListener());
		loadButton.addActionListener(new LoadActionListener());
		saveButton.addActionListener(new SaveActionListener());
                
                //matrixInput.getDocument().addDocumentListener(new MatrixInputDocumentListener(matrixInput));
	
		// state management
		clearButton.setEnabled(false);
                saveButton.setEnabled(false);
	}

	
	/**
	 *  Populate the list box w/ the categories
	 *
	 *  <pre>
	 *  
	 *    1.  If the selected category is not equal to "----" then
	 *        1a.  Get the music array list from data accessor
	 *    2.  Else create an empty music array list
	 *
	 *    3.  If the list is not empty then enable "Clear" button
	 *
	 *  </pre>
	 */
	protected void populateListBox() {
/*
		String category = (String) categoryComboBox.getSelectedItem();

		if (! category.startsWith("---")) {
			musicArrayList = myDataAccessor.getRecordings(category);
		}
		else {
			musicArrayList = new ArrayList(); 
		}

		Object[] theData = musicArrayList.toArray();
		musicListBox.setListData(theData);	
				
		// bonus work
		// clear button is enabled if we have some data
		if (musicArrayList.size() > 0) {
			clearButton.setEnabled(true);
		}
		else {
			clearButton.setEnabled(false);
		}*/
	}
        
        /*
	class MatrixInputDocumentListener implements DocumentListener {
            protected JTextArea jtext;
            public MatrixInputDocumentListener(JTextArea myJtext) {
                super();
                jtext = myJtext;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("REMOVING: "+jtext.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("INSERTING: "+jtext.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
            }
        }
	*/
        
	/**
	 *  When called, let's call the exit routine of the parent frame
	 */
	class ExitActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {

			parentFrame.exit();
		}
	}
	
        
	/**
	 *  When the "Clear" button is pressed, we will:
	 *
	 *  <pre>
	 *  
	 *  1.  Clear the musicListBox
	 *  2.  Set the first category item as selected
	 *
	 *  </pre>
	 */
        class LUpivotActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {
                    // We calculate the LU pivot and we add our result to the output
                    resultOutput.setText("LU Decomposition with scaled partial pivoting\nOriginal matrix\n");
                    // We write the original matrix to the output
                    String originalMatrix="";
                    
                    resultOutput.append(originalMatrix+"\n\nOriginal vector");
                    
                    // We write the original
                    clearButton.setEnabled(true);
                    saveButton.setEnabled(true);
		}
	}
        class InverseActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {
                    // We calculate the inverse and we add our result to the output
                    resultOutput.setText("Inverse");
                    clearButton.setEnabled(true);
                    saveButton.setEnabled(true);
		}
	}
        class ClearActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {

			// clear the text in the result output
			resultOutput.setText("");
                        clearButton.setEnabled(false);
                        saveButton.setEnabled(false);
                        
		}
	}
        class LoadActionListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {
		}
	}
	/**
	 *  When the "Save" button is pressed, we will:
	 *
	 *  <pre>
	 *
	 *  1.  Call the save() method of our data accessor.
	 *  2.  If any problems occur during the save then we'll report
	 *      them to the user.
	 *
	 *  </pre>
	 */
	class SaveActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
                    try{
                        myDataAccessor.save();
                        // If the result has been correctly saved, there is no need for now to use the save button again
                        saveButton.setEnabled(false);
                    } catch (IOException exc) {
                        System.out.println("IOException caught in save(): "+exc);
		}
                    
		}
	}

	/**
	 *  When a category is selected, we will:
	 *
	 *  <pre>
	 *
	 *  1.  Find out the selected category from combo box
	 *  2.  Get a list of CDs for the selected category from the data accessor
	 *  3.  Populate the list box w/ the cd artist and titles
	 *
	 *  </pre>
	 */	
	class GoItemListener implements ItemListener {
	
		public void itemStateChanged(ItemEvent event) {
			
			if (event.getStateChange() == ItemEvent.SELECTED) {
				populateListBox();
			}
		}
	}
	
	
	
	/**
	 *  When the "Add..." button is pressed, we will:
	 *
	 *  <pre>
	 *
	 *  1.  Create an instance of the MusicEntryDialog.
	 *  2.  Show this dialog
	 *  3.  When the dialog returns, check if it's "Ok" button was pressed.
	 *      3a.  If "Ok" button was pressed
	 *           -  Get the music recording from the dialog
	 *           -  Add this music recording to the data accessor
	 *
	 *  </pre>
	 */
	class EntryActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
		
			/*ArrayList categoryArrayList = myDataAccessor.getCategories();

			//
			// TO DO:  Do your work here.
			//
			MusicEntryDialog myDialog = new MusicEntryDialog(parentFrame, categoryArrayList);
			
			myDialog.setVisible(true);
			
			if (myDialog.isOkButtonPressed()) {
				
				MusicRecording theRecording = myDialog.getMusicRecording();
				
				myDataAccessor.addRecording(theRecording);
				
				categoryComboBox.setSelectedItem(theRecording.getCategory());
			}*/
			
		}	
	}


	

}
