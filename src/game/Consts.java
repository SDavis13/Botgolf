package game;

import java.awt.event.KeyEvent;

public class Consts {
    private Consts(){}
    
    //Constants
                                //File locations
    public static final String  RESLOC = "resources/game/",
                                IMGLOC = RESLOC + "images/",
                                SNDLOC = RESLOC + "sound/",
                                LVLLOC = RESLOC + "levels/";
                                
                                //Image names/locations
    public static final String  IMG_LOGO    = IMGLOC + "Logo.png",
                                IMG_BALL    = IMGLOC + "Ball.png",
                                IMG_HOLE    = IMGLOC + "Hole.png",
                                IMG_GENROBO = IMGLOC + "BotGeneric.png",
                                IMG_STANDROBO = IMGLOC + "BotStandard.png",
                                IMG_MADROBO = IMGLOC + "BotMad.png";
                                
                                //Page names
    public static final String  MAIN        = "Main",
                                QUICKPLAY   = "Quickplay",
                                INSTRUCTION = "Instruction",
                                OPTIONS     = "Options",
                                CAMPAIGN    = "Campaign",
                                GAMESELECT  = "GameSelect",
                                ABOUT       = "About",
                                PAUSE       = "Pause",
                                HIGHSCORE   = "HighScore",
                                GAME        = "Game",
    							GAMEMENUPAGE= "GameMenuPage";
    
                                //Sound names
    public static final String[] SOUNDS = {"Score", "Wall1", "Wall2", "RobotBoom", "RobotMove"};
    
    public static final int SNDIDX_SCORE = 0,
    						SNDIDX_WALL1 = 1,
    						SNDIDX_WALL2 = 2,
    						SNDIDX_ROBOTBOOM = 3,
    						SNDIDX_ROBOTMOVE = 4;
    

                                //Sound locations
    public static final String[] SOUNDFILES = {SNDLOC+"score.wav", SNDLOC+"wall1.wav",
    	SNDLOC+"wall2.wav", SNDLOC+"robotboom.wav", SNDLOC+"robotmove.wav"};
                                
    
    public static final int SCALE = 2000;
    public static final float TIMESTEP = 1.f/60.f;
    public static final long TIMERTICK = (long)(1000 * TIMESTEP + 0.5f);
    
    
    //Variable globals
    
                      //Keyboard shortcuts
    public static int pauseMenuKey = KeyEvent.VK_ESCAPE, //goes to pause menu
                      pauseKey = KeyEvent.VK_PAUSE; //simply pauses the game
                      //TODO Make a way to save and load key preferences to/from a file
    
                        //Physics globals
    public static float rollingFriction = 1.5f,
                        pxOffset        = 0,
                        pyOffset        = 0;
}
