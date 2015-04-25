package game;

import java.awt.Graphics2D;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

/**
 * This is the abstract class for Entity.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public abstract class Entity {
    Body body;
    Fixture fixture;
    float pixX,pixY;
    World world;
    boolean remove = false;

    /**
     * Abstract hit method.
     *
     * @param otherEntity	Object type of entity passed
     */
    public abstract void hit(Entity otherEntity);
    
    /**
     * Abstract render method.
     *
     * @param g		Object type of Graphics2D passed
     */
    public abstract void render(Graphics2D g);
    
    /**
     * Abstract pixUpdate method.
     */
    public abstract void pixUpdate();

    /**
     * Gets the jbox2d position of the Entity.
     * 
     * @return a Vec2 position.
     */
    public Vec2 getPosition(){
        return body.getPosition();
    }
}
