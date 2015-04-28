package game;

import java.awt.Insets;
import java.awt.event.KeyEvent;

/**
 * This is where all constants and variable globals are saved for organizing.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public class Consts {
    private Consts(){}
    
    // Constants
    
    /**
     * File locations
     */
    public static final String  RESLOC = "resources/game/",
                                IMGLOC = RESLOC + "images/",
                                SNDLOC = RESLOC + "sound/",
                                LVLLOC = RESLOC + "levels/";
                                
    /**
     * Image names/locations
     */
    public static final String  IMG_LOGO    = IMGLOC + "Logo.png",
                                IMG_BALL    = IMGLOC + "Ball.png",
                                IMG_HOLE    = IMGLOC + "Hole.png",
                                IMG_GENROBO = IMGLOC + "BotGeneric.png",
                                IMG_STANDROBO2 = IMGLOC + "NormalBot2.png",
                                IMG_STANDROBO1 = IMGLOC + "NormalBot1.png",
                                IMG_STANDROBO0 = IMGLOC + "NormalBot0.png",
                                IMG_MADROBO3 = IMGLOC + "Explodibot3.png",
                                IMG_MADROBO2 = IMGLOC + "Explodibot2.png",
                                IMG_MADROBO1 = IMGLOC + "Explodibot1.png",
                                IMG_MADROBO0 = IMGLOC + "Explodibot0.png",
                                IMG_INSTR = IMGLOC + "instructionsRobot.png";

    /**
     * Page Names
     */
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
    
    /**
     * Sound names for hashtable retrieval
     */
    public static final String[] SOUNDS =  {"Score", 
    										"Wall1", 
    										//"Wall2", 
    										"RobotBoom", 
    										"RobotMove", 
    										"Launch",
    										"Pause", 
    										"Boing",
    										"RobotHit",
    										"TandemGravities"};
    
    /**
     * Indexes for sound names in the array.
     */
    public static final int SNDIDX_SCORE = 0,
    						SNDIDX_WALL1 = 1,
    						//SNDIDX_WALL2 = 2,
    						SNDIDX_ROBOTBOOM = 2,
    						SNDIDX_ROBOTMOVE = 3,
    						SNDIDX_LAUNCH = 4,
    						SNDIDX_PAUSE = 5,
    						SNDIDX_BOING = 6,
    						SNDIDX_ROBOTHIT = 7,
                            MUSIDX_TANDEMGRAVITIES = 8;
    
    /**
     * Sound file locations
     */
    public static final String[] SOUNDFILES =  {SNDLOC+"score.wav", 
    											SNDLOC+"wall1.wav",
    											//SNDLOC+"wall2.wav", 
    											SNDLOC+"robotboom.wav", 
    											SNDLOC+"robotmove.wav", 
    											SNDLOC+"launch.wav",
    											SNDLOC+"zap2.wav", 
    											SNDLOC+"boing.wav",
    											SNDLOC+"robothit.wav",
    											SNDLOC+"tandemgravities.wav"};
                                
    /**
     * Scale of the graphical representation of the JBox2D world for conversion.
     * The JBox2D world is 100x100 meters, and the corresponding area is SCALExSCALE pixels.
     */
    public static final int SCALE = 2000;
    /**
     * Time in seconds between logic ticks.
     */
    public static final float TIMESTEP = 1.f/60.f;
    /**
     * Time in milliseconds between render ticks.
     */
    public static final long TIMERTICK = (long)(1000 * TIMESTEP + 0.5f);
    /**
     * A Swing object used to make sure the button graphics don't interfere with the text.
     */
    public static final Insets BUTTON_MARGIN = new Insets(10,20,15,20);

    
    //Variable globals
    
    /**
     * Keyboard Shortcuts. Global variables rather than constants.
     */
    public static int pauseMenuKey = KeyEvent.VK_ESCAPE, //goes to pause menu
                      pauseKey = KeyEvent.VK_PAUSE; //simply pauses the game
                      //TODO Make a way to save and load key preferences to/from a file
    
    /**
     * Physics Globals
     */
    public static float rollingFriction = 1.5f,
                        pxOffset        = 0,
                        pyOffset        = 0;
}
