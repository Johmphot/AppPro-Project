package primary;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class SEMusic
{
	static Clip clip;
	public static FloatControl SEgainControl;
	
	public void play()
	{
		try 
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/button.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} 
		catch (UnsupportedAudioFileException e) {} 
		catch (IOException e) {} 
		catch (LineUnavailableException e) {}
	}
	public void decrease()
	{
	    SEgainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    SEgainControl.setValue(-5.0f);
	}
	public void increase()
	{
	    SEgainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    SEgainControl.setValue(5.0f);
	}
}
