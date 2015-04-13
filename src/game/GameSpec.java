package game;

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
