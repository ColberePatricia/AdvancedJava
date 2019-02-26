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
}
