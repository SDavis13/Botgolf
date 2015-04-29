package game;

import java.awt.Graphics2D;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

/**
 * This is the abstract class for Entity.
 * 
 * @authors Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version 2015-04-28
 * @since 2015-04-24
 */
public abstract class Entity {
    Body body;
    Fixture fixture;
    float pixX,pixY;
    World world;
    boolean remove = false;

    /**
     * Abstract hit method.
     * Called by a JBox2D ContactListener when another entity comes in contact with this one.
     *
     * @param otherEntity the other Entity which hits this one.
     */
    public abstract void hit(Entity otherEntity);

    /**
     * Abstract render method.
     * Renders the Entity to a Graphics2D object.
     *
     * @param g the Graphics2D context in which to render the Entity.
     */
    public abstract void render(Graphics2D g);

    /**
     * Abstract pixUpdate method.
     * Updates the pixel based objects used by the Entity for rendering.
     */
    public abstract void pixUpdate();

    /**
     * Gets the JBox2D position of the Entity from the Body associated with it.
     * 
     * @return a Vec2 position.
     */
    public Vec2 getPosition(){
        return body.getPosition();
    }
}
