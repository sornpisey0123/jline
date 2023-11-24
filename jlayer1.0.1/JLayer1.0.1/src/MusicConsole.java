import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.AdvancedPlayerEvent;
import javazoom.jl.player.advanced.AdvancedPlayerListener;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackEventCode;
import javazoom.jl.player.advanced.PlaybackEventSource;

import java.io.FileInputStream;
import java.io.IOException;

public class MusicConsole {

    public static void main(String[] args) {
        try {
            // Provide the path to your MP3 file
            String mp3FilePath = "path/to/your/file.mp3";

            // Create an input stream from the file
            FileInputStream fileInputStream = new FileInputStream(mp3FilePath);

            // Create a Bitstream from the input stream
            Bitstream bitstream = new Bitstream(fileInputStream);

            // Get the audio format from the Bitstream
            int audioFormat = bitstream.readFrame().synchronizedFrames(0).sfbwidth[0];

            // Create an AdvancedPlayer with the input stream and audio format
            AdvancedPlayer player = new AdvancedPlayer(fileInputStream, audioFormat, new PlaybackListener());

            // Start playing the MP3 file
            player.play();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }

    // Custom listener to handle playback events
    static class PlaybackListener implements AdvancedPlayerListener {

        @Override
        public void playbackFinished(PlaybackEvent evt) {
            if (evt.getType() == PlaybackEvent.Type.STOPPED) {
                System.out.println("Playback stopped");
            }
        }

        @Override
        public void playbackStarted(PlaybackEvent evt) {
            if (evt.getType() == PlaybackEvent.Type.STARTED) {
                System.out.println("Playback started");
            }
        }

        @Override
        public void playbackFinished(AdvancedPlayerEvent evt) {
            if (evt.getFrame() >= 0) {
                System.out.println("Playback finished");
            }
        }

        @Override
        public void playbackStarted(AdvancedPlayerEvent evt) {
            if (evt.getFrame() >= 0) {
                System.out.println("Playback started");
            }
        }

        @Override
        public void playbackFinished(PlaybackEvent evt, PlaybackEventSource evtSrc) {
            if (evt.getType() == PlaybackEvent.Type.STOPPED) {
                System.out.println("Playback stopped");
            }
        }
    }
}
