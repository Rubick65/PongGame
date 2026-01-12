package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Objects;

public class Sound {


    private final Clip[] soundEffects = new Clip[10];

    public Sound() {
        loadSound(0, "/sound/pong_hit.wav");
    }

    private void loadSound(int index, String path) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(
                    Objects.requireNonNull(getClass().getResource(path)));

            soundEffects[index] = AudioSystem.getClip();
            soundEffects[index].open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(int index) {
        Clip clip = soundEffects[index];
        if (clip.isRunning())
            clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }
}
