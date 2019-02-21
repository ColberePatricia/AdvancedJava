/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patricia
 */
public class Driver {
    public static void main(String[] args) {
        Duration firstDuration = new Duration(1, 15, 45);
        System.out.println(firstDuration);
        System.out.println("Hours: "+firstDuration.getHours());
        System.out.println("Minutes: "+firstDuration.getMinutes());
        System.out.println("Seconds: "+firstDuration.getSeconds());
        System.out.println("Total Seconds: "+firstDuration.getTotalSeconds());
        
        Duration secondDuration = new Duration(2700);
        System.out.println(secondDuration.toString());
        
        Duration totalDuration = firstDuration.add(secondDuration);
        System.out.println("sum = "+totalDuration);
        
        if (args.length>0){
            Duration thirdDuration = new Duration(Integer.parseInt(args[0]));
        }
    }
}
