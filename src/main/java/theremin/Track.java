package theremin;
import java.time.LocalDateTime;


public class Track extends Object {
	LocalDateTime NextControlTime = LocalDateTime.now();
	LocalDateTime TransientPeriodEnds = LocalDateTime.now();
	
	
	
	ClipTrackSet clipTrackSet = new ClipTrackSet();
	
	
	
	Analyzer[] Analyzers = new Analyzer[10];
	
//	ControlStream: TODO later
//	TrackMoodStatus	: TODO later
    
    
    
    
	public Track() {
//		super();
	}
	
			
}


