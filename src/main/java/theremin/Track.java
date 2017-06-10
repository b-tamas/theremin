package theremin;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Track extends Object {
	LocalDateTime NextControlTime = LocalDateTime.now();
	LocalDateTime TransientPeriodEnds = LocalDateTime.now();

	// public ControlStream[] controlStream = new ControlStream[5];
	ArrayList<ControlStream> controlStream = new ArrayList<ControlStream>();

	ClipTrackSet clipTrackSet = new ClipTrackSet();

	// Analyzer[] Analyzers = new Analyzer[10];
	ArrayList<Analyzer> analyzers = new ArrayList<Analyzer>();

	ControlStream=new ControlStream;ControlStream[2]=new ControlStream;ControlStream[3]=new ControlStream();ControlStream[4]=new ControlStream();

	// ControlStream: TODO later
	// TrackMoodStatus : TODO later

	public Track() {
		// super();
	}
}