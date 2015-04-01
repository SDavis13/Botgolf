package game;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

public class Ball extends Entity{
    CircleShape shape;
    Ellipse2D.Float pixShape;

    public Ball(World world, BodyDef bd, FixtureDef fd, CircleShape shape){
        
    }

    @Override
    public void hit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics g1) {
        // TODO Auto-generated method stub
        
    }
    
    public void launch(Vec2 velocity){
        
    }
    
    public boolean contains(int pixX, int pixY){
        return pixShape.contains(pixX, pixY);
    }
    
}
