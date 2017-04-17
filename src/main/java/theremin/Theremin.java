package theremin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;


   
// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class Theremin extends JFrame {
   
   // Constructor
   public Theremin() throws URISyntaxException {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("Alternative Theremin");
      this.setSize(300, 200);
      this.setVisible(true);
   
      try {
    	  
    	  
    	  
    	  URL url = Class.class.getResource("/BowFidl01-E.wav");
    	  AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
    	  
         Clip clipFid1 = AudioSystem.getClip();
         clipFid1.open(audioIn);
         clipFid1.start();
         
         
         
         
         
    	  URL kick1Url = Class.class.getResource("/KickSnr01.wav");
    	  AudioInputStream kick1 = AudioSystem.getAudioInputStream(kick1Url);
   	  
        Clip clipDrum1 = AudioSystem.getClip();
        clipDrum1.open(kick1);
        clipDrum1.start();
         

         
         
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }
   
   public static void main(String[] args) {
      try {
		new Theremin();
	} catch (URISyntaxException e) {
		e.printStackTrace();
	}
   }
}