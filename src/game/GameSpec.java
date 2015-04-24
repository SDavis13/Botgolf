package game;

/**
 * GameSpec represents specs for a new game that is created.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public class GameSpec {
    boolean newGame;
    String campaignName;
    String levelName;
    int levelNum;

    /**
     * First constructor for GameSpec.
     * 
     * @param levelName		String of level name passed
     */
    GameSpec(String levelName){
        newGame = true;
        campaignName = null;
        this.levelName = levelName;
        levelNum = 0;
    }

    /**
     * Second constructor for GameSpec with different signature.
     * 
     * @param newGame		Boolean of new game passed
     * @param campaignName	String of campaign name passed
     * @param levelName		String of level name passed
     * @param levelNum		Integer of level number passed
     */
    GameSpec(boolean newGame, String campaignName, String levelName, int levelNum){
        this.newGame = newGame;
        this.campaignName = campaignName;
        this.levelName = levelName;
        this.levelNum = levelNum;
    }
}
