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
        Track myTrack = new Track();
        myTrack.setTitle("Watching The Wheels");
        myTrack.setDuration(new Duration(0, 3, 48));
        
        if (args.length>2){
            Track secondTrack = new Track();
            secondTrack.setTitle(args[0]);
            secondTrack.setDuration(new Duration(0, Integer.parseInt(args[1]), Integer.parseInt(args[2])));
            secondTrack.printTitleAndDuration();

        }
        
        myTrack.printTitleAndDuration();
    }
}
