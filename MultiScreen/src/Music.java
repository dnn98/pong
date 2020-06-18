import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;


import java.io.File;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.AudioClip;

public class Music{
	private MediaPlayer mediaPlayer;
	public Music() {
		com.sun.javafx.application.PlatformImpl.startup(()->{});
		String bip = "vampire_killer.mp3";
		Media hit = new Media(Paths.get(bip).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
		mediaPlayer.play();
		

	}
	
}