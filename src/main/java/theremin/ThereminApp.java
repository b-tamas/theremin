package theremin;

public class ThereminApp {
	Tracks tracks;
	
	public ThereminApp() {
		System.out.println("New ThereminApp");
		
		
		tracks = new Tracks(4);
		
		///////////////////////////
		//rythm
		
		ClipTrackSet rythmClipTrackSet = new ClipTrackSet();

		Clip rythmClip1=new Clip();
		rythmClip1.soundfile="KickSnr01.wav";
		rythmClipTrackSet.addClip(rythmClip1);
				
		Clip rythmClip2=new Clip();
		rythmClip2.soundfile="KickSnr05.wav";
		rythmClipTrackSet.addClip(rythmClip2);
		
		
		/////////////////////////
		//bass
		
		ClipTrackSet bassClipTrackSet = new ClipTrackSet();

		Clip bassClip1=new Clip();
		bassClip1.soundfile="BowFidl01-E.wav";
		bassClipTrackSet.addClip(bassClip1);
		
		Clip bassClip2=new Clip();
		bassClip2.soundfile="BowFidl09-A.wav";
		bassClipTrackSet.addClip(bassClip2);
		
		
		/////////////
		//foll
		
		ClipTrackSet follClipTrackSet = new ClipTrackSet();

		Clip follClip1=new Clip();
		follClip1.soundfile="ChugginA.wav";
		follClipTrackSet.addClip(follClip1);
		
		Clip follClip2=new Clip();
		follClip2.soundfile="ChugginE.wav";
		follClipTrackSet.addClip(follClip2);
		
		
		
		
		/////////////
		//melody
		
		ClipTrackSet melodyClipTrackSet = new ClipTrackSet();

		Clip melodyClip1=new Clip();
		melodyClip1.soundfile="ChugginA.wav";
		melodyClipTrackSet.addClip(melodyClip1);
		
		Clip melodyClip2=new Clip();
		melodyClip2.soundfile="ChugginE.wav";
		melodyClipTrackSet.addClip(melodyClip2);
		
		
		
		
		tracks.track[1].clipTrackSet=rythmClipTrackSet;
		tracks.track[2].clipTrackSet=bassClipTrackSet;
		tracks.track[3].clipTrackSet=follClipTrackSet;
		tracks.track[4].clipTrackSet=melodyClipTrackSet;
		
		
		
		
		
	}
	
		
	
	
	
	
	
	public static void main(String[] args) {
		ThereminApp ta = new ThereminApp();

	}

}
