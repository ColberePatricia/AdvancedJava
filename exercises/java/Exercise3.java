/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patricia
 */
public class Exercise3 {
    public static void main(String[] args){
        int []numbers = new int[10];
        int sum = 0;
        
        for (int i=0; i<5;i++)
            numbers[i]=i+2;
        for (int i=0; i<numbers.length;i++)
            sum+=numbers[i];
        
        System.out.println("The sum is: "+sum);
    }
}
