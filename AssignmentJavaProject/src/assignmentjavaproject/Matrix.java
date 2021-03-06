/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjavaproject;
import java.lang.Math.*;
import java.text.DecimalFormat;
/**
 * Class to handle the matrices: create them and modify them
 * 
 * @author Patricia
 */
public class Matrix {
    /**
     * The number of rows and columns of the matrix
     */
    public int numRows, numCols;
    /**
     * The array of arrays representing the matrix with its values and indices
     * matrix[i][j] is the value at the row i and column j
     */
    public double [][] matrix;
    
    /**
     * Constructor of Matrix with the given parameters
     * 
     * @param myRows the number of rows of the matrix
     * @param myCols  the number of columns of the matrix
     */
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
    
    /**
     * Constructor of Matrix with the given parameters
     * 
     * @param mySize the size of the matrix
     */
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
    
    /**
     * Constructor of Matrix with the given parameters
     * 
     * @param myMatrix the matrix as a matrix object
     */
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
    
    /**
     * Constructor of Matrix with the given parameters
     * 
     * @param myMatrix the matrix as an array of arrays object
     */
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
    
    /**
     * Multiplies the current matrix with the one in parameter
     * 
     * @param myMatrix multiplied matrix with the current one
     * @return the matrix result of the product
     */
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
    
    /**
     * Multiplies the current matrix with the given vector
     * 
     * @param myVector vector multiplied with the current matrix
     * @return the vector resulting of the product
     */
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
    
    /**
     * Transpose the current matrix
     * 
     * @return the transposed matrix
     */
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
    
    /**
     * Checks the equality of the current matrix with the one given in parameter
     * 
     * @param myMatrix with which the current one is compared
     * @return true if the matrices are equal, false if not
     */
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
    
    /**
     * Checks if the current matrix has a compatible format for a product 
     * with the given vector given in parameter
     * 
     * @param myVector vector to compare the format
     * @return true if the formats are compatible, false if not
     */
    public boolean areFormatsCompatible(double[] myVector){
        // If the matrix and vector sizes do not match
        if (numCols != myVector.length){
            System.out.println("The formats of the matrix and vector do not correspond");
            return false;
        }
        
        return true;
    }
    
    /**
     * Print the given vector
     * 
     * @param vector vector to be printed
     */
    public static void PrintVector(double[]vector){
            for (int i=0;i<vector.length;i++){
                System.out.print(vector[i]+"   ");
            }
            System.out.println();
    }
      
    /**
     *¨Print the given matrix
     * 
     * @param myMatrix matrix to be printed
     */
    public static void PrintMatrix(Matrix myMatrix){
            for (int i=0;i<myMatrix.numRows;i++){
                PrintVector(myMatrix.matrix[i]);
            }
            System.out.println();
    }
    
    /**
     * Gives a string from a vector
     * 
     * @param vector vector to be converted to string
     * @return the string corresponding to the given vector
     */
    public static String VectorToString(double [] vector){
        // We want a precision of 7 decimals
        DecimalFormat dc = new DecimalFormat("0.0000000");
        String result="    ";
        for (int i=0;i<vector.length;i++){
                result+=dc.format(vector[i])+"   ";
        }
        result+="\n";
        return result;
    }
    
    /**
     * Gives a string from a matrix
     * 
     * @param myMatrix matrix to be converted to string
     * @return the string corresponding to the given matrix
     */
    public static String MatrixToString(Matrix myMatrix){
        String result = "";
        for (int i=0;i<myMatrix.numRows;i++){
                result += VectorToString(myMatrix.matrix[i]);
        }
        result+="\n";
        return result;
    }
    
    /**
     * Get the vector corresponding to a given string
     * 
     * @param vectorString string corresponding to a vector
     * @return array of doubles corresponding to the string given as parameter
     */
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
    
    /**
     * Get the matrix corresponding to a given string
     * 
     * @param matrixString string corresponding to a matrix
     * @return matrix object corresponding to the string given as parameter
     */
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
        
        // We check that the matrix has the same number of rows as columns
        if (numRowsM!=numColsM){
            System.out.println("The matrix has a different number of rows and columns!");
            return myMatrix;
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
    
    /**
     * Gives the lower matrix from the LU pivot
     * 
     * @return the lower matrix
     */
    public Matrix getLowerMatrix(){
        Matrix lowerMatrix = this.lu_fact()[0];
        return lowerMatrix;
    }
    
    /**
     * Gives the upper matrix from the LU pivot
     * 
     * @return the upper matrix
     */
    public Matrix getUpperMatrix(){
        Matrix upperMatrix = this.lu_fact()[1];
        return upperMatrix;
    }
    
    /**
     * Get the LU pivot solution from the given parameters
     * 
     * @param lower lower matrix from the LU pivot
     * @param upper upper matrix from the LU pivot
     * @param vector vector to which the matrix is multiplied
     * @return the solution to the LU pivot
     */
    public double[] getLUsolution(Matrix lower, Matrix upper, double[] vector){
        double []solution = new double[this.numRows];
        double []temp = vector.clone();
        
        // forward substitution for L y = b
        for (int i=1;i<this.numRows;i++){
            for (int j=0;j<i;j++)
                temp[i] -= lower.matrix[i][j] * temp[j];
        }
        
        // back substitution for U x = y
        for (int i=this.numRows-1;i>=0;i--){
            for (int j=i+1;j<this.numRows;j++)
                temp[i] -= upper.matrix[i][j] * temp[j];
            temp[i] /= upper.matrix[i][i];
        }
        
        // Get the solution and return it;
        for (int i=0;i<this.numRows;i++)
            solution[i] = temp[i];
        
        return solution;
    }
    
    /**
     * Get the inverse of the matrix corresponding to the given parameters
     * 
     * @param lower lower matrix given by the LU pivot
     * @param upper upper matrix given by the LU pivot
     * @param p P matrix corresponding to reordering the original matrix A
     * @return the inverse of the original matrix A
     */
    public Matrix getInverseMatrix(Matrix lower, Matrix upper, Matrix p){
        int n = this.numRows;
        Matrix inverse = new Matrix(n);
        double [] temp = new double[n];
        double []e=new double[n];
        Matrix inverseLower = new Matrix(n);
        Matrix inverseUpper = new Matrix(n);
        
        for (int k=0;k<n;k++){
            // We create ek
            for (int idx = 0; idx<n;idx++)
                e[idx]=0;
            e[k]=1;
            
            // forward substitution for L y = e.
            // temp begins with a copy of e.
            temp=e.clone();
            for (int i = 1; i < n; i++)
                for (int j = 0; j < i; j++)
                    temp[i] -= lower.matrix[i][j]*temp[j];
            
            // We get the k column of the inverse of the lower matrix
            for (int idx=0;idx<n;idx++){
                inverseLower.matrix[idx][k]=temp[idx];
            }
            
            // back substitution for U y = e.
            // temp begins with a copy of e.
            temp=e.clone();
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) 
                    temp[i] -= upper.matrix[i][j]*temp[j];
                temp[i] /= upper.matrix[i][i];
            }
            
            // We get the k column of the inverse of the upper matrix
            for (int idx=0;idx<n;idx++){
                inverseUpper.matrix[idx][k]=temp[idx];
            }
        }
        //System.out.println("inverse l\n"+Matrix.MatrixToString(inverseLower)+"\ninverse u\n"+Matrix.MatrixToString(inverseUpper));
        inverse = inverseUpper.MultiplyMatrix(inverseLower.MultiplyMatrix(p));
        
        return inverse;
    }
    
    /**
     * Get a subarray from an array of arrays
     * 
     * @param myMatrix array of arrays from which we get the subarray
     * @param N size of myMatrix
     * @param j1 size of the subarray
     * @return the subarray
     */
    public static double[][] generateSubArray (double myMatrix[][], int N, int j1){
            double[][] m;
            m = new double[N-1][];
            for (int k=0; k<(N-1); k++)
                    m[k] = new double[N-1];

            for (int i=1; i<N; i++){
                  int j2=0;
                  for (int j=0; j<N; j++){
                      if(j == j1)
                            continue;
                      m[i-1][j2] = myMatrix[i][j];
                      j2++;
                  }
              }
            return m;
    }
    
    /**
     * Recursive method to calculate the determinant of the current matrix
     * 
     * @return the determinant
     */
    public double getDeterminant(){
        int N = this.numRows;
        double determinant = 0;
        double m[][];
        
        // Trivial 1x1 matrix
        if (N == 1)
            determinant = this.matrix[0][0];
        
        // Trivial 2x2 matrix
        else if (N == 2)
            determinant = this.matrix[0][0]*this.matrix[1][1] - this.matrix[1][0]*this.matrix[0][1];
        
        // NxN matrix
        else{
            determinant=0;
            for (int j1=0; j1<N; j1++){
                 m = generateSubArray (this.matrix, N, j1);
                 Matrix newMatrix = new Matrix(m);
                 determinant += Math.pow(-1.0, 1.0+j1+1.0) * this.matrix[0][j1] * newMatrix.getDeterminant();
            }
        }
        
        return determinant;
    }
    
    
    /**
     * Do the LU factorisation for the current matrix
     * @return an array containing two matrices: the lower and upper matrices
     */
    public Matrix[] lu_fact(){
        // Our solution will contain first the lower matrix and second the upper matrix
        Matrix []solution = new Matrix[2];
        solution[0] = solution[1] = new Matrix(999);
        int n = this.numRows;
        Matrix lower = new Matrix(n);
        Matrix upper = new Matrix(n);
        Matrix temp = new Matrix(this);
        double mult;
        
        // LU decomposition without pivoting
        for (int k=0;k<n-1;k++){
            for (int i=k+1;i<n;i++){
                // Zero pivot found
                if (Math.abs(temp.matrix[k][k]) < 1.e-07){
                    return solution;
                }
                mult = temp.matrix[i][k]/temp.matrix[k][k];
                temp.matrix[i][k] = mult; // entries of L are saved in temp
                for (int j=k+1;j<n;j++){
                    temp.matrix[i][j] -= mult*temp.matrix[k][j]; // entries of U are saved in temp
                }
            }
        }
        
        // Create the lower and upper matrices from the temp matrix
        // Lower matrix
        for (int i=0;i<n;i++)
            lower.matrix[i][i] = 1.0;
        for (int i=1;i<n;i++){
            for (int j=0;j<i;j++)
                lower.matrix[i][j] = temp.matrix[i][j];
        }
        
        // Upper matrix
        for (int i=0;i<n;i++){
            for (int j=i;j<n;j++)
                upper.matrix[i][j] = temp.matrix[i][j];
        }
        
        // Put the lower and upper matrices in our solution and return it
        solution[0] = lower;
        solution[1] = upper;
        
        return solution;
    }
    
    /**
    * Computes the permutation matrix P such that the matrix PA can be
    * factorised into LU and the system PA = Pb can be solved by forward and 
    * backward substitution
    * 
    * @return the matrix permutation P 
    * 
    */
    public Matrix reorder(){
        // If no pivot is found, we return an empty matrix of size 999
        Matrix p = new Matrix(999);
        int n = this.numRows;
        // Note: pivoting information is stored in temperary vector pvt
        int pvtk, pvti;
        double aet, tmp, mult;
        int [] pvt = new int[n];
        Matrix temp = new Matrix(this);
        
        for (int k=0;k<n; k++)
            pvt[k]=k;
        
        double []scale = new double[n];
        // find scale vector
        for (int k=0; k<n; k++){
            scale[k]=0;
            for (int j=0;j<n;j++){
                if (Math.abs(scale[k]) < Math.abs(temp.matrix[k][j]))
                    scale[k] = Math.abs(temp.matrix[k][j]);
            }
        }
        
        int pc;
        // main elimination loop
        for (int k=0;k<n-1;k++){
            pc = k;
            aet = Math.abs(temp.matrix[pvt[k]][k]/scale[k]);
            for (int i=k+1;i<n;i++){
                tmp = Math.abs(temp.matrix[pvt[i]][k]/scale[pvt[i]]);
                if (tmp > aet){
                    aet=tmp;
                    pc=i;
                }
            }
            if (Math.abs(aet)<1.e-07){
                System.out.println("Zero pivot found!");
                return p;
            }
            
            if (pc!=k){                             // swap pvt[k] and pvt[pc]
                int ii=pvt[k];
                pvt[k]=pvt[pc];
                pvt[pc]=ii;
            }
            
            // now eliminate the column entries logically below mx[pvt[k]][k]
            pvtk = pvt[k];                          // pivot row
		for (int i = k + 1; i < n; i++) {
                    pvti = pvt[i];
			if (temp.matrix[pvti][k] != 0) {
				mult = temp.matrix[pvti][k]/temp.matrix[pvtk][k]; 
				temp.matrix[pvti][k] = mult;
				for (int j = k + 1; j < n; j++)
                                    temp.matrix[pvti][j] -= mult*temp.matrix[pvtk][j];
			}
                }
        }
        
        p = new Matrix(n);
        // We fill p before returning it
        for (int i=0; i<n; i++)
            p.matrix[i][pvt[i]]=1.0;
        
        return p;
    }
}
