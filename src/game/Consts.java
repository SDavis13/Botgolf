package game;

import java.awt.event.KeyEvent;

public class Consts {
    private Consts(){}
                                //File locations
    public static final String  RESLOC = "resources/game/",
                                IMGLOC = RESLOC + "images/",
                                SNDLOC = RESLOC + "sound/",
                                LVLLOC = RESLOC + "levels/",
                                
                                //Page names
                                MAIN        = "Main",
                                QUICKPLAY   = "Quickplay",
                                INSTRUCTION = "Instruction",
                                OPTIONS     = "Options",
                                CAMPAIGN    = "Campaign",
                                GAMESELECT  = "GameSelect",
                                ABOUT       = "About",
                                PAUSE       = "Pause",
                                HIGHSCORE   = "HighScore",
                                GAME        = "Game";
    
    public static final int SCALE = 2000;
    
    public static int pauseMenuKey = KeyEvent.VK_ESCAPE, //goes to pause menu
                      pauseKey = KeyEvent.VK_PAUSE; //simply pauses the game
                      //TODO Make a way to save and load key preferences to/from a file
}
