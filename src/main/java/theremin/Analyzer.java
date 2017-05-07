package theremin;

import java.util.ArrayList;

public class Analyzer {
	Boolean TrackActionPossible;
	Boolean SetActionPossible;
	TrackActionType tat = null;
	SetActionType sat = null;
	Integer priority;
		
	
	ArrayList<Integer> generateSlowMovementData(ArrayList<Integer> data ) {
		
		//ArrayList<Integer> data = new ArrayList<Integer>();
			for (int i=0; i<10000; i++) {
				
				//System.out.println(i);
				
				//reading every 100ms, assumed
				//100bpm = 0.6 sec/beat
				//6 data=1 cycle = 2pi radian  (6.28)
				
				data.add(Math.round(  (long) (Math.sin((float)i*6.28)*255))) ;
				//System.out.println(data.get(i));
								
						
						//180 bpm => 0.333 sec /beat
						//nyquist
						//0.1666 sec/beat
				
			}
		
		return data;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
