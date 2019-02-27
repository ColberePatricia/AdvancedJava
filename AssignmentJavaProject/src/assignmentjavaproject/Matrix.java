/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjavaproject;

/**
 *
 * @author Patricia
 */
public class Matrix {
    public int numRows, numCols;
    public double [][] matrix;
    
    Matrix(int myRows, int myCols){
        numRows = myRows;
        numCols = myCols;
        matrix = new double[numRows][numCols];
        
        // We give our matrix defaulr values of zero
        for (int i = 0;i<numRows;i++){
            for (int j=0;j<numCols;j++)
                matrix[i][j]=0.0;
        }
    }
    
    Matrix(int mySize){
        numRows = mySize;
        numCols = mySize;
        matrix = new double[numRows][numCols];
        
        // We give our matrix defaulr values of zero
        for (int i = 0;i<numRows;i++){
            for (int j=0;j<numCols;j++)
                matrix[i][j]=0.0;
        }
    }
    
    Matrix(Matrix myMatrix){
        numRows = myMatrix.numRows;
        numCols = myMatrix.numCols;
        matrix = new double[numRows][numCols];
        
        // We give our matrix defaulr values of zero
        for (int i = 0;i<numRows;i++){
            for (int j=0;j<numCols;j++)
                matrix[i][j]=myMatrix.matrix[i][j];
        }
    }
    
    Matrix(double [][] myMatrix){
        numRows = myMatrix.length;
        numCols = myMatrix[0].length;
        matrix = new double[numRows][numCols];
        
        // We give our matrix defaulr values of zero
        for (int i = 0;i<numRows;i++){
            for (int j=0;j<numCols;j++)
                matrix[i][j]=myMatrix[i][j];
        }
    }
    
    public Matrix MultiplyMatrix(Matrix myMatrix){
        Matrix result;
        // If the matrix sizes do not match
        if (numCols != myMatrix.numRows) {
            // Show an error message
            // TO DO
            System.out.println("The formats of the matrices do not correspond");
        }
        // We create the result matrix
        result=new Matrix(numRows, myMatrix.numCols);
        
        // We do the multiplication
        for (int i=0;i<numRows;i++){
            for (int j=0;j<myMatrix.numCols;j++){
                for (int k=0;k<numCols;k++){
                    result.matrix[i][j] += matrix[i][k] * myMatrix.matrix[k][j];
                }
            }
        }
        
        return result;
    }
    
    public double[] MultiplyVector(double[] myVector){
        double[] result;
        // If the matrix and vector sizes do not match
        if (numCols != myVector.length){
            // Show an error message
            // TO DO
            System.out.println("The formats of the matrix and vector do not correspond");
            return new double[999];
        }
        
        result = new double[numRows];
        
        // We do the multiplication
        for (int i=0;i<numRows;i++){
            for (int j=0;j<numCols;j++){
                result[i] += matrix[i][j] * myVector[j];
            }
        }
        
        return result;
    }
    
    public Matrix Transpose(){
        Matrix result = new Matrix(numCols, numRows);
        
        // We do the transpose of the matrix
        for (int i=0;i<numCols;i++){
            for (int j=0;j<numRows;j++){
                result.matrix[i][j]=matrix[j][i];
            }
        }
        
        return result;
    }
    
    public boolean Equals(Matrix myMatrix){
        // The matrices should have the same number of rows and columns
        if (numRows != myMatrix.numRows || numCols != myMatrix.numCols)
            return false;
        
        // The matrices should have the same values
        for (int i=0;i<numRows;i++){
            for (int j=0;j<numCols;j++){
                if (matrix[i][j]!=myMatrix.matrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    
    // Returns true if the matrix and vector have compatible formats
    public boolean areFormatsCompatible(double[] myVector){
        // If the matrix and vector sizes do not match
        if (numCols != myVector.length){
            System.out.println("The formats of the matrix and vector do not correspond");
            return false;
        }
        
        return true;
    }
    
    public static void PrintVector(double[]vector){
            for (int i=0;i<vector.length;i++){
                System.out.print(vector[i]+"   ");
            }
            System.out.println();
    }
        
    public static void PrintMatrix(Matrix myMatrix){
            for (int i=0;i<myMatrix.numRows;i++){
                PrintVector(myMatrix.matrix[i]);
            }
            System.out.println();
    }
    
    public static String VectorToString(double [] vector){
        String result="";
        for (int i=0;i<vector.length;i++){
                result+=vector[i]+"   ";
        }
        result+="\n";
        return result;
    }
    
    public static String MatrixToString(Matrix myMatrix){
        String result = "";
        for (int i=0;i<myMatrix.numRows;i++){
                result += VectorToString(myMatrix.matrix[i]);
        }
        result+="\n";
        return result;
    }
    
    // We get an array of doubles from a string input in the GUI
    public static double[] getVectorFromString(String vectorString){
        if (vectorString.length()==0){
            return new double[999];
        }
        String [] resultString = vectorString.split(" ");
        double [] result = new double[resultString.length];
        for (int i=0;i<resultString.length;i++)
            result[i]=Double.parseDouble(resultString[i]);
        
        return result;
    }
    
    // We get a matrix object from a string input in the GUI
    public static Matrix getMatrixFromString(String matrixString){
        Matrix myMatrix = new Matrix(999);
        if (matrixString.length()==0){
            return myMatrix;
        }
        String[] lines = matrixString.split("\r\n|\r|\n");
        // We get the number of rows and columns of the matrix
        int numRowsM = lines.length;
        int numColsM = lines[0].split(" ").length;
        
        // We check that the matrix is in a correct format, each rows should have
        // the same number of columns, if the format is incorrect we return
        // a matrix of size (999,999) full of zeroes and print an error
        for (int i=0; i<numRowsM;i++){
            if (numColsM != lines[i].split(" ").length){
                System.out.println("The matrix has an incorrect format!");
                return myMatrix;
            }
        }
        
        // We put the right size to the matrix
        myMatrix = new Matrix(numRowsM, numColsM);
        // This will contain the values of the current line in the loop
        String []arrayOfCurrentLine;
        for (int i=0;i<numRowsM;i++){
            arrayOfCurrentLine = lines[i].split(" ");
            for (int j=0;j<numColsM;j++){
                myMatrix.matrix[i][j] = Double.parseDouble(arrayOfCurrentLine[j]);
            }
        }
        
        return myMatrix;
    }
    
    public Matrix getUpperMatrix(){
        Matrix upperMatrix;
        
        
        return upperMatrix;
    }
    
    public Matrix getLowerMatrix(){
        Matrix lowerMatrix;
        
        
        return lowerMatrix;
    }
    
    public double[] getLUsolution(double[] vector){
        double []solution;
        
        
        return solution;
    }
    
    public Matrix getInverseMatrix(){
        Matrix inverse;
        
        
        return inverse;
    }
    
    public double getDeterminant(){
        double determinant;
        
        
        return determinant;
    }
}
