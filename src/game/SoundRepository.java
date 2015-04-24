package game;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//import org.jbox2d.callbacks.ContactListener;
//import org.jbox2d.dynamics.contacts.Contact;

/**
 * This class represents the sound repository for the game.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-25
 * @since       2015-04-25
 */
public class SoundRepository {
    HashMap<String,Clip> soundBank = new HashMap<String,Clip>();
    static SoundRepository repo = new SoundRepository();

    /**
     * Constructor for SoundRepository
     */
    private SoundRepository(){
        loadSound();
    }

    /**
     * This is used to create a singleton instance.
     * 
     * @return		Returns the repository object instance
     */
    public static SoundRepository getInstance(){
        return repo;
    }

    /**
     * LoadSound method is used to load the sounds to play.
     */
    private void loadSound(){
    	for(int i = 0; i < Consts.SOUNDS.length; i++){
	        File file;
	        Clip clip = null;
	        try {
	            clip = AudioSystem.getClip();
	        } catch (LineUnavailableException e1) {
	            e1.printStackTrace();
	        }
	        file = new File(Consts.SOUNDFILES[i]);
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
	        soundBank.put(Consts.SOUNDS[i], clip);
    	}
    }

    /**
     * PlaySound method is used to play particular sounds.
     * 
     * @param soundName		String of sound name passed
     */
    public static void playSound(String soundName) {
        Clip clip = repo.soundBank.get(soundName);
        if(clip != null){
            if(clip.getFramePosition() != 0) clip.setFramePosition(0);
            clip.start();
        }
    }

    /**
     * Finalize method is used to finalize the clip in collection.
     */
    @Override
    protected void finalize(){
        Collection<Clip> sounds = soundBank.values();
        for(Clip clip : sounds){
            clip.close();
        }
        sounds.clear();
    }
}