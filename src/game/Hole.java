package game;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

public class Hole extends Entity{
    public CircleShape shape;
    public Ellipse2D.Float pixShape;
    
    Hole(World world, BodyDef bd, FixtureDef fd, CircleShape shape){
        
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
