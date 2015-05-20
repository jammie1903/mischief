package com.rachmie.timetravel;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioController {
    private AudioController() {
    }

    private static AudioController instance = new AudioController();

    public static AudioController getInstance() {
        return instance;
    }

    private Map<String, Clip> clips = new HashMap<>();

    public void playSound(String name) {
        try {
            Clip clip = getSound(name);
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private Clip getSound(String name) {
        if (clips.containsKey(name)) {
            return clips.get(name);
        }
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream("/sounds/" + name + ".wav"));
            clip.open(inputStream);
            clips.put(name, clip);
            return clip;

        } catch (Exception ex) {
        }
        return null;
    }
}
