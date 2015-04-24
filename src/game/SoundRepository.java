package game;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//import org.jbox2d.callbacks.ContactListener;
//import org.jbox2d.dynamics.contacts.Contact;

public class SoundRepository {
    HashMap<String,Clip> soundBank = new HashMap<String,Clip>();
    static SoundRepository repo = new SoundRepository();

    private SoundRepository(){
        loadSound();
    }

    public static SoundRepository getInstance(){
        return repo;
    }

    private void loadSound(){
        File file;
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
        file = new File(Consts.SNDFILE_SCORE);
        try {
            if(file != null)
                clip.open(AudioSystem.getAudioInputStream(file));
        } catch (LineUnavailableException e) {
            // Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
        soundBank.put(Consts.SND_SCORE, clip);
    }

    public static void playSound(String soundName) {
        Clip clip = repo.soundBank.get(soundName);
        if(clip != null){
            if(clip.getFramePosition() != 0) clip.setFramePosition(0);
            clip.start();
        }
    }


}