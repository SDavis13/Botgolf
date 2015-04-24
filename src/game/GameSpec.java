package game;

/**
 * GameSpec represents specs for a new game that is created.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     1.0
 * @since       2015-04-21
 */
public class GameSpec {
    boolean newGame;
    String campaignName;
    String levelName;
    int levelNum;

    GameSpec(String levelName){
        newGame = true;
        campaignName = null;
        this.levelName = levelName;
        levelNum = 0;
    }

    GameSpec(boolean newGame, String campaignName, String levelName, int levelNum){
        this.newGame = newGame;
        this.campaignName = campaignName;
        this.levelName = levelName;
        this.levelNum = levelNum;
    }
}
