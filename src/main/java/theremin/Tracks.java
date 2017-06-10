package theremin;

public class Tracks extends Object {

	public Track[] track = new Track[5];
	// public ControlStream[] controlStream = new ControlStream[5];

	public Tracks(int i) {
		super();
		this.track[1] = new Track();
		this.track[2] = new Track();
		this.track[3] = new Track();
		this.track[4] = new Track();

		// this.controlStream[1] = new ControlStream();
		// this.controlStream[2] = new ControlStream();
		// this.controlStream[3] = new ControlStream();
		// this.controlStream[4] = new ControlStream();

	}

}
