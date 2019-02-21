/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patricia
 */
public class Track {
    protected String title;
    protected Duration duration;
    
    
    public String getTitle(){
        return title;
    }
    public Duration getDuration(){
        return duration;
    }
    
    public void setTitle(String newTitle){
        title=newTitle;
    }
    public void setDuration(Duration newDuration){
        duration=newDuration;
    }
    
    public void printTitleAndDuration(){
        System.out.println("Title = "+title);
        System.out.println("Duration = "+duration.toString());
    }
}
