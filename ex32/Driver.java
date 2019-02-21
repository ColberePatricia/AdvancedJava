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
        Track[] trackList = new Track[3];
        trackList[0] = new Track();
        trackList[1] = new Track();
        trackList[2] = new Track();
        
        trackList[0].setTitle("Watching The Wheels");
        trackList[0].setDuration(new Duration(0, 3, 48));
        trackList[1].setTitle("Beautiful Boy");
        trackList[1].setDuration(new Duration(0, 3, 30));
        trackList[2].setTitle("Starting Over");
        trackList[2].setDuration(new Duration(0, 4, 15));
        
        
        MusicRecording musicRec = new MusicRecording("John Lennon", trackList, "Double Fantasy", 0, "", "");
        
        System.out.println(musicRec);
    }
}
