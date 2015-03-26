package game;

import java.awt.Graphics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

public class Wall extends Entity {

     PolygonShape shape;
    Wall(World world, BodyDef bd, FixtureDef fd, PolygonShape shape) {
        
    }

    @Override
    public void hit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics g1) {
        // TODO Auto-generated method stub
        
    }

}
