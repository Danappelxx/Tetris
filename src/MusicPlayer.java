import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * Created by dan on 5/29/16.
 */

public class MusicPlayer {
    private MediaPlayer player;

    public MusicPlayer() {
        new JFXPanel(); // initialize javafx
        Media media = new Media(new File("tetris.mp3").toURI().toASCIIString());
        player = new MediaPlayer(media);
        player.setAutoPlay(true);
        player.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void start() {
        player.play();
    }

    public void stop() {
        player.stop();
    }
}
