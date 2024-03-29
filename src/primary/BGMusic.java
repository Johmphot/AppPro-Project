package primary;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class BGMusic
{
	static Clip clip;
	public static FloatControl BGgainControl;
	
	public void music()
	{
		try 
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/BackgroundMusic.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(clip.LOOP_CONTINUOUSLY);
		} 
		catch (UnsupportedAudioFileException e) {} 
		catch (IOException e) {} 
		catch (LineUnavailableException e) {}
	}
	public void decrease()
	{
	    BGgainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    BGgainControl.setValue(-5.0f);
	}
	public void increase()
	{
	    BGgainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    BGgainControl.setValue(5.0f);
	}
}
