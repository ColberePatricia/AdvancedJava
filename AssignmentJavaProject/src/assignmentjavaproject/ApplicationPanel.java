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
 * Class to create the application panel in the main frame
 * 
 * @author Patricia
 */
public class ApplicationPanel extends JPanel {
        
        /**
         *  Labels for the matrix and vector inputs
         */
        protected JLabel matrixLabel;
        protected JLabel vectorLabel;
	
	/**
	 *  Five panels to contain components.
	 */
	protected JPanel topPanel;
	protected JPanel middleTopPanel;
	protected JPanel middleBottomPanel;
	protected JPanel bottomPanel;
	protected JPanel bottomAndMiddleBottomPanel;

        /**
         *  Four scrollable panels
         */
	protected JScrollPane matrixScrollPane;
        protected JScrollPane vectorScrollPane;
	protected JScrollPane resultScrollPane;
	protected JScrollPane displayErrorScrollPane;
	
	/**
	 *  Button labeled respectively
         *  "LU pivot"
         *  "Inverse"
         *  "Clear"
         *  "Load"
         *  "Save"
	 */
	protected JButton LUpivotButton;
	protected JButton inverseButton;
	protected JButton clearButton;
	protected JButton loadButton;
	protected JButton saveButton;
        
        /**
	 *  A holder for the list of recordings
	 */
	protected ArrayList resultArrayList;
		
        /**
	 *  The combo box to hold a list of results
	 */
	protected JComboBox resultComboBox;
		
	/**
	 *  A reference to the parent frame
	 */
	protected MainFrame parentFrame;

        /**
         * Four text areas for inputing or outputing text
         */
        protected JTextArea matrixInput;
        protected JTextArea vectorInput;
        protected JTextArea resultOutput;
        protected JTextArea displayError;
        
	
	/**
	 *  A reference result data accessor
	 */
	protected ResultDataAccessor myDataAccessor;

	
	/**
	 *  Creates the GUI components and arranges them
	 *  in the container.
         * 
         *  @param theParentFrame frame containing this application panel
         *  @return the application panel
	 */
	public ApplicationPanel(MainFrame theParentFrame) throws IOException{
	
		parentFrame = theParentFrame;

		myDataAccessor = new ResultDataAccessor();
		
		matrixLabel = new JLabel("A = ");
		vectorLabel = new JLabel("      b = ");
		
               	
                // populate result combo box		
		resultComboBox = new JComboBox();
		resultComboBox.addItem("-------");
                
                resultArrayList = myDataAccessor.getResultsList();
		
		Iterator iterator = resultArrayList.iterator();
		String aResult;
		
		while (iterator.hasNext()) {
			
			aResult = (String) iterator.next();
			resultComboBox.addItem(aResult);
		}
                
		topPanel = new JPanel();
		bottomPanel = new JPanel();
                middleTopPanel = new JPanel();
                middleBottomPanel = new JPanel();
                bottomAndMiddleBottomPanel = new JPanel();
                resultArrayList = new ArrayList();
                
                matrixInput = new JTextArea(17,30);
                vectorInput = new JTextArea(17,30);
                resultOutput = new JTextArea(20,10);
                displayError = new JTextArea(3,10);
                
                
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
		
		
		// set the layout for "this" Panel
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
                bottomPanel.add(resultComboBox);
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
		resultComboBox.addItemListener(new GoItemListener());
                
	
		// state management
		clearButton.setEnabled(false);
                saveButton.setEnabled(false);
	}
        

	
	/**
	 *  Populate the output with the content of the result recording
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
	protected void populateOutput() {

		String result = (String) resultComboBox.getSelectedItem();

		if (! result.startsWith("---")) {
			resultArrayList = myDataAccessor.getRecordings(result);
		}
		else {
			resultArrayList = new ArrayList(); 
		}

		Object[] theData = resultArrayList.toArray();
		resultOutput.setText(""+theData[0]);	
				
		// clear button is enabled if we have some data
		if (resultArrayList.size() > 0) {
			clearButton.setEnabled(true);
		}
	}
        
	/**
	 *  When called, let's call the exit routine of the parent frame
	 */
	class ExitActionListener implements ActionListener {
	
                /**
                 * 
                 * @param event the event to exit the application
                 */
		public void actionPerformed(ActionEvent event) {

			parentFrame.exit();
		}
	}
	
        
	/**
	 *  When the "LU pivot" button is pressed, we will: give the appropriate result
	 *
	 */
        class LUpivotActionListener implements ActionListener {
	
                /**
                 * 
                 * @param event click of the button "LU pivot"
                 */
		public void actionPerformed(ActionEvent event) {
                    // We calculate the LU pivot and we add our result to the output
                    resultOutput.setText("LU Decomposition with scaled partial pivoting\nOriginal matrix\n");
                    // We write the original matrix to the output
                    String originalMatrixString=matrixInput.getText();
                    Matrix originalMatrix=Matrix.getMatrixFromString(originalMatrixString);
                    // We check that the format of the matrix is correct, else we show an error message
                    if (!originalMatrix.Equals(new Matrix(999))){
                        resultOutput.append(Matrix.MatrixToString(originalMatrix)+"\n\nOriginal vector\n");
                        String originalVectorString=vectorInput.getText();
                        double[] originalVector = Matrix.getVectorFromString(originalVectorString);
                        
                        // We check that the vector is not empty
                        if (!originalVector.equals(new double[999])){
                            // We write the original vector to the output
                            resultOutput.append(Matrix.VectorToString(originalVector)+"\n\nLower matrix\n");

                            // We check that the vector and the matrix have compatible formats, else we show an error message
                            if(originalMatrix.areFormatsCompatible(originalVector)){
                                // We get the matrix P
                                Matrix p = originalMatrix.reorder();
                                Matrix PA = p.MultiplyMatrix(originalMatrix);
                                Matrix lowerMatrix = PA.getLowerMatrix();
                                Matrix upperMatrix = PA.getUpperMatrix();
                                // We check that the pivots were found and so that the lower and upper matrices were calculated
                                if (!lowerMatrix.Equals(new Matrix(999)) && !upperMatrix.Equals(new Matrix(999))){
                                    // We write the lower matrix to the output
                                    resultOutput.append(Matrix.MatrixToString(lowerMatrix)+"Upper matrix\n");

                                    // We write the upper matrix to the output
                                    resultOutput.append(Matrix.MatrixToString(upperMatrix)+"Solution\n");

                                    double[] PB = p.MultiplyVector(originalVector);
                                    double[] solution = originalMatrix.getLUsolution(lowerMatrix, upperMatrix, PB);
                                    
                                    resultOutput.append(Matrix.VectorToString(solution)+"\nDeterminant = ");

                                    double determinant = originalMatrix.getDeterminant();
                                    resultOutput.append(determinant + "\n");
                                    displayError.setText("");


                                    clearButton.setEnabled(true);
                                    saveButton.setEnabled(true);
                                } else {
                                    resultOutput.setText("");
                                    displayError.setText("Zero pivot found for the lower and upper matrices!\n");
                                    clearButton.setEnabled(true);
                                    saveButton.setEnabled(false);
                                }
                                
                            } else {
                                resultOutput.setText("");
                                displayError.setText("Input a matrix with a format compatible to the vector!\n");
                                clearButton.setEnabled(true);
                                saveButton.setEnabled(false);
                            }
                        } else {
                            resultOutput.setText("");
                            displayError.setText("Input a vector!\n");
                            clearButton.setEnabled(true);
                            saveButton.setEnabled(false);
                        }

                        
                        
                    } else{
                        resultOutput.setText("");
                        displayError.setText("Input a matrix with the right format!\nSeparate each number by a single space and begin each line with a number.\nInput a consistant and same number of rows and of columns for the matrix.\n");
                        clearButton.setEnabled(true);
                        saveButton.setEnabled(false);
                    }
                    
		}
	}
        
        /**
	 *  When the "Inverse" button is pressed, we will: give the appropriate result
	 *
	 */
        class InverseActionListener implements ActionListener {
	
                /**
                 * 
                 * @param event the "Inverse" button has been clicked
                 */
		public void actionPerformed(ActionEvent event) {
                    // We calculate the inverse and we add our result to the output
                    resultOutput.setText("Matrix Inversion\nOriginal matrix\n");
                    // We write the original matrix to the output
                    String originalMatrixString=matrixInput.getText();
                    Matrix originalMatrix=Matrix.getMatrixFromString(originalMatrixString);
                    // We check that the format of the matrix is correct, else we show an error message
                    if (!originalMatrix.Equals(new Matrix(999))){
                        resultOutput.append(Matrix.MatrixToString(originalMatrix)+"\n\nLower matrix\n");
                    
                        // We get the matrix P
                        Matrix p = originalMatrix.reorder();
                        Matrix PA = p.MultiplyMatrix(originalMatrix);
                        Matrix lowerMatrix = PA.getLowerMatrix();
                        Matrix upperMatrix = PA.getUpperMatrix();
                        // We check that the pivots were found and so that the lower and upper matrices were calculated
                        if (!lowerMatrix.Equals(new Matrix(999)) && !upperMatrix.Equals(new Matrix(999))){
                            // We write the lower matrix to the output
                            resultOutput.append(Matrix.MatrixToString(lowerMatrix)+"Upper matrix\n");

                            // We write the upper matrix to the output
                            resultOutput.append(Matrix.MatrixToString(upperMatrix)+"Inverse matrix\n");

                            Matrix inverseMatrix = originalMatrix.getInverseMatrix(lowerMatrix, upperMatrix, p);
                            resultOutput.append(Matrix.MatrixToString(inverseMatrix)+"\nDeterminant = ");

                            double determinant = originalMatrix.getDeterminant();
                            resultOutput.append(determinant + "\n");
                            displayError.setText("");

                            clearButton.setEnabled(true);
                            saveButton.setEnabled(true);
                        } else{
                            resultOutput.setText("");
                            displayError.setText("Zero pivot found for the lower and upper matrices!\nThe inverse does not exist!\n");
                            clearButton.setEnabled(true);
                            saveButton.setEnabled(false);
                        }
                    } else {
                        resultOutput.setText("");
                        displayError.setText("Input a matrix with the right format!\nSeparate each number by a single space and begin each line with a number.\nInput a consistant and same number of rows and of columns for the matrix.\n");
                        clearButton.setEnabled(true);
                        saveButton.setEnabled(false);
                    }
                    
                    
		}
	}
        
        /**
	 *  When the "Clear" button is pressed, we will: clear the output
	 *
	 */
        class ClearActionListener implements ActionListener {
	
            /**
             * 
             * @param event the "Clear" button has been clicked
             */
		public void actionPerformed(ActionEvent event) {

			// clear the text in the result output
			resultOutput.setText("");
                        clearButton.setEnabled(false);
                        saveButton.setEnabled(false);
                        
		}
	}
        
        /**
	 *  When the "Load" button is pressed, we will: output the loaded result
	 *
	 */
        class LoadActionListener implements ActionListener {
	
                /**
                 * 
                 * @param event the "Load" button has been clicked
                 */
		public void actionPerformed(ActionEvent event) {
                    try{
                        myDataAccessor.load();
                    } catch (IOException exc) {
                        System.out.println("IOException caught in save(): "+exc);
                    }
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

                /**
                 * 
                 * @param event the "Save" button has been clicked
                 */
		public void actionPerformed(ActionEvent event) {
                    try{
                        ArrayList resultArrayList = myDataAccessor.getResultsList();
                        
                        String content = resultOutput.getText();
			Recording theRecording = new Recording(content);
			myDataAccessor.addRecording(theRecording);
			resultComboBox.setSelectedItem(theRecording.getName());
                        
                        myDataAccessor.save();
                        // If the result has been correctly saved, there is no need for now to use the save button again
                        saveButton.setEnabled(false);
                    } catch (IOException exc) {
                        System.out.println("IOException caught in save(): "+exc);
                    }
                    
		}
	}

	/**
	 *  When a name is selected, we will:
	 *
	 *  <pre>
	 *
	 *  1.  Find out the selected name from combo box
	 *  2.  Get the result corresponding to the selected name
	 *
	 *  </pre>
	 */	
	class GoItemListener implements ItemListener {
	
                /**
                 * 
                 * @param event the name has been selected in the combobox
                 */
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				populateOutput();
			}
		}
	}
}
