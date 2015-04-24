package game;

/**
 * This is the interface for the Level factory.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public interface LevelFactory {
    public Level createLevel(GameSpec specs);
}
