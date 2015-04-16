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

	HashMap<String,Clip> soundBank = new HashMap<String,Clip>();
	//String key;
	//Object File;
	
	
	public Clip clip;
	//public soundBank.put("zap2", clip);
	

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

	public static void playSound(String sndScore) {
		// TODO Auto-generated method stub
		
	}


}