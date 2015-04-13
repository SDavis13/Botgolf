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
                                IMG_GENROBO = IMGLOC + "BotGeneric.png";
                                
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
                                GAME        = "Game";
    
                                //Sound names
    public static final String  SND_SCORE   = SNDLOC + "score.wav";
                                
    
    public static final int SCALE = 2000;
    
    
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
