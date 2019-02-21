import java.util.*;

public class Driver {

	public static void main(String args[]) {
	
		Duration tempDuration = null;
		Track[] myTrackList = new Track[3];

		//
		//  1.  Create a music recording for MILES DAVIS														
		//
		tempDuration = new Duration(314);
		myTrackList[0] = new Track("Move", tempDuration);
	
		tempDuration = new Duration(222);
		myTrackList[1] = new Track("Jeru", tempDuration);
	
		tempDuration = new Duration(351);
		myTrackList[2] = new Track("Moon Dreams", tempDuration);
		
		MusicRecording miles = new MusicRecording("Miles Davis", myTrackList, 
													"Birth of the Cool", 15.99, "Jazz", "");
		
		//
		//  2.  Create a music recording for KENNY G
		//		
		myTrackList = new Track[3];		
		tempDuration = new Duration(240);
		myTrackList[0] = new Track("The Joy Of Life", tempDuration);
	
		tempDuration = new Duration(241);
		myTrackList[1] = new Track("Forever In Love", tempDuration);
	
		tempDuration = new Duration(228);
		myTrackList[2] = new Track("In The Rain", tempDuration);
		
		MusicRecording kenny = new MusicRecording("Kenny G", myTrackList, 
													"Breathless", 15.99, "Jazz", "");		
		
		//
		//  TO DO:  START ADDING YOUR CODE HERE
		//
                ArrayList musicArrayList = new ArrayList();
                //musicArrayList.add(miles);
                //musicArrayList.add(kenny);
                MusicDataAccessor myDataAccessor = new MusicDataAccessor();
                
                /*String musicCategory = "Jazz";
                if (args.length>0)
                    musicCategory = args[0];
                musicArrayList = myDataAccessor.getRecordings(musicCategory);*/
                //musicArrayList = myDataAccessor.getRecordings(""+myDataAccessor.getCategories().get(0));
                System.out.println(myDataAccessor.getCategories());
                
                MusicRecording tempRec;
                
                System.out.println("Size of the array list: "+musicArrayList.size());
                
                /*
                Iterator myIterator = musicArrayList.iterator();
                while(myIterator.hasNext()){
                    tempRec = (MusicRecording) myIterator.next();
                    System.out.println(tempRec);
                    System.out.println("TOTAL DURATION: "+ tempRec.getDuration()+"\n");
                }
                */
                
                Iterator myIterator;
                Iterator myIteratorCategories = myDataAccessor.getCategories().iterator();
                int index=0;
                while(myIteratorCategories.hasNext()){
                    myIterator = myDataAccessor.getRecordings(""+myDataAccessor.getCategories().get(index)).iterator();
                    while(myIterator.hasNext()){
                        tempRec = (MusicRecording) myIterator.next();
                        System.out.println(tempRec);
                        System.out.println("TOTAL DURATION: "+ tempRec.getDuration()+"\n");
                    }
                }
                
                
	}
}