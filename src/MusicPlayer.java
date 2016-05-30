import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Created by dan on 5/29/16.
 */

public class MusicPlayer {
    private MediaPlayer player;

    public MusicPlayer() {
        new JFXPanel(); // initialize javafx

        Media media = media();
        if (media == null) return;

        player = new MediaPlayer(media);
        player.setAutoPlay(true);
        player.setCycleCount(MediaPlayer.INDEFINITE);
    }

    private Media media() {
        try {
            return new Media(getClass().getResource("/tetris.mp3").toURI().toASCIIString());
        } catch (Exception e) {
            return null;
        }
    }

    public void start() {
        player.play();
    }

    public void stop() {
        player.stop();
    }
}
