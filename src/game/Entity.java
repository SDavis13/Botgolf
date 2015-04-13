package game;

import java.awt.Graphics;

import org.jbox2d.dynamics.*;

public abstract class Entity {
    Body body;
    Fixture fixture;
    float pixX,pixY;
    World world;

    public abstract void hit(Entity otherEntity);
    public abstract void render(Graphics g1);
    public abstract void pixUpdate();

}
