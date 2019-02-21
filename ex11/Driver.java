/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patricia
 */
import java.lang.*;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Welcome to the RainForest!");
        System.out.println("Please browse our music categories: ");
        System.out.println(); // blank line
        
        if(args.length>0){
            for (int i=0;i<args.length;i++){
                if (args[i].length()>=4)
                    System.out.println(i + ": " + args[i].toUpperCase().substring(0, 3) + ", length: "+ args[i].length());
                else
                    System.out.println(i + ": " + args[i].toUpperCase() + ", length: "+ args[i].length());
            }
        } else {
            System.out.println("Command usage: 'Java Driver <arguments>' with arguments Classical or Jazz for instance");
        }
    }

}
