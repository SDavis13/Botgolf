package game;

import java.awt.Graphics;
import java.awt.Graphics2D;

import org.jbox2d.dynamics.*;

public abstract class Entity {
    Body body;
    Fixture fixture;
    float pixX,pixY;
    World world;
    boolean remove = false;

    public abstract void hit(Entity otherEntity);
    public abstract void render(Graphics2D g);
    public abstract void pixUpdate();

}
