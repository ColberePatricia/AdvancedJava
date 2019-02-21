/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patricia
 */
public class MusicRecording extends Recording {
    protected String artist;
    protected Track[] trackList;
    
    public MusicRecording(String theArtist, Track[] theTrackList, String theTitle, double thePrice, String theCategory, String theImageName, Duration theDuration){
        super(theTitle, thePrice, theCategory, theImageName);
        artist=theArtist;
        trackList=theTrackList;
    }
    
    public MusicRecording(String theArtist, Track[] theTrackList, String theTitle, double thePrice, String theCategory, String theImageName){
        this(theArtist, theTrackList, theTitle, thePrice, theCategory, theImageName, new Duration());
    }
    

    
    public Duration getDuration(){
        Duration totalDuration = new Duration(0);
        
        for (int i=0;i<trackList.length;i++){
            totalDuration = totalDuration.add(trackList[i].duration);
        }
        
        return totalDuration;
    }
    public String getArtist(){
        return artist;
    }
    public Track[] getTrackList(){
        return trackList;
    }
    
    public String toString(){
        String res = artist+", "+title+"\n";
        
        for (int i=0;i<trackList.length;i++){
            res+=trackList[i].toString()+"\n";
        }
        
        res+="Total Duration: "+getDuration()+"\n";
        
        return res;
    }
    
    
}
