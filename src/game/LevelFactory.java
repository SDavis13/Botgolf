package game;

/**
 * This is the interface for the Level factory.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     1.0
 * @since       2015-04-21
 */
public interface LevelFactory {
    public Level createLevel(GameSpec specs);
}
