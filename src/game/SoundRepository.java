package game;

import java.util.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

//import org.jbox2d.callbacks.ContactListener;
//import org.jbox2d.dynamics.contacts.Contact;

public class SoundRepository {


	 /*    private class SoundPlayer implements ContactListener{

        @Override
        public void beginContact(Contact contact) {
            Object object1 = contact.getFixtureA().getUserData();
            Object object2 = contact.getFixtureB().getUserData();
            
            if(object1 instanceof Clip){
                Clip clip = (Clip)(object1);
                if(clip.getFramePosition() != 0) clip.setFramePosition(0);
                (clip).start();
                clip.close();
            }
            if(object2 instanceof Clip){
                Clip clip = (Clip)(object2);
                if(clip.getFramePosition() != 0) clip.setFramePosition(0);
                (clip).start();
                clip.close();
            }
        }
        */
	
	//create hashtable
	//Hashtable<String, Object> soundBank = new Hashtable<String,Object>();

	static HashMap<String,Clip> soundBank = new HashMap<String,Clip>();
	//String key;
	//Object File;
	
	
	
	//soundBank.put("zap2", new Clip();
	
/*
	public SoundRepository(String fileName){
		try{
			File file = new File(fileName);
			if(file.exists()){
				AudioInputStream sound = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(sound);
			}
			else{
				throw new RuntimeException("Sound: file not found: " +fileName);
			}
		}
		catch(UnsupportedAudioFileException e){
			e.printStackTrace();
			throw new RuntimeException("Sound: Malformed URL: " +e);
		} catch (LineUnavailableException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
	public void play(){
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop(){
		clip.stop();
	}
*/
	public static void playSound(String file) {
		Clip clip = null;
		AudioInputStream audio = null;
		if(soundBank.containsKey(file)){
			clip = soundBank.get(file);
			clip.setFramePosition(0);
		}
		else{
			audio = AudioSystem.getAudioInputStream(clip.getFormat(), audio);//getClass().getResource("/resources/game/sounds/" + file + ".wav"));
			clip = soundBank.put("zap2", clip);//AudioSystem.getClip();
			try {
				clip.open(audio);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			soundBank.put(file, clip);
		}
		clip.start();
	}


}