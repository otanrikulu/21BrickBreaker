package game;

import java.io.*;
import javax.sound.sampled.*;


//java sucks @music
//Java doesn't play mp3 files

public class Music extends Thread //want to run on a different thread
{
	File file;
	AudioInputStream audioStream;
	Clip clip;

	@Override
	public void run()
	{
		try
		{
			file = new File("src/images/RR2.wav");
			audioStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch (UnsupportedAudioFileException | IOException |  LineUnavailableException e)
		{
			e.printStackTrace();
		}

	}
}
