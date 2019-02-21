/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lambdaexpressionexercise;

/**
 *
 * @author Patricia
 */

/**
* This class defines several public static member variables of
* type ArrayProcessor that process arrays in various ways. It
* also defines a function that can create ArrayProcessors for
* counting occurrences of values in an array. (Note that this
* program depends on interface ArrayProcessor.)
*/

public class LambdaExpressionExercise {
    
    /**
    * This function returns an ArrayProcessor that counts
    * the number of times a certain value occurs in an array
    * of doubles. The parameter specifies the value that is
    * to be counted.
    */
    /*public static ArrayProcessor counter( double value ) {
    return array -> {
    ….
    };
    }*/

    /**
    * An ArrayProcessor that computes and returns the maximum
    * value of an array. (The array must have length at least 1.)
    */
    public static final ArrayProcessor maxer = array -> {
        double max = array[0];
        int i;
        for (i=1;i<array.length;i++){
            if (max < array[i])
                max = array[i];
        }
        return max;
    };

    /**
    * An ArrayProcessor that computes and returns the minimum
    * value of an array. (The array must have length at least 1.)
    */
    public static final ArrayProcessor miner = array -> {
        double min = array[0];
        int i;
        for (i=1;i<array.length;i++){
            if (min > array[i])
                min = array[i];
        }
        return min;
    };

     /**
    * An ArrayProcessor that computes and returns the sum of the
    * values in an array. (The array must have length at least 1.)
    */
    public static final ArrayProcessor sumer = array -> {
        double sum = 0;
        int i;
        for (i=0;i<array.length;i++){
            sum += array[i];
        }
        return sum;
    };


    /**
    * An ArrayProcessor that computes and returns the average of the
    * values in an array. (The array must have length at least 1.)
    */
    public static final ArrayProcessor averager = array -> {return(sumer.apply(array)/array.length);} ;

    public static ArrayProcessor counter( double value ) { 
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[] firstList = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        double[] secondList = { 17.0, 3.14, 17.0, -3.4, 17.0, 42.0, 29.2, 3.14 };

        System.out.println("Sum of first list (should be 55): " + sumer.apply(firstList) );
        System.out.println("Average of first list (should be 5.5): " + averager.apply(firstList) );
        System.out.println("Minimum of second list (should be -3.4): " + miner.apply(secondList) );
        System.out.println("Maximum of second list (should be 42.0): " + maxer.apply(secondList) );

        System.out.println();
/*
        System.out.println("Count of 17.0 in second list (should be 3): " + counter(17.0).apply(secondList) );
        System.out.println("Count of 20.0 in second list (should be 0): " + counter(20.0).apply(secondList) );
        System.out.println("Count of 5.0 in first list (should be 1): " + counter(5.0).apply(firstList) );
*/
    }
    
}
