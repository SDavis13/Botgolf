package game;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import physicsPrototype1.Main;

public class Ball extends Entity{
    CircleShape shape;
    Ellipse2D.Float pixShape;
    static final float IMPULSE_SCALE = 10;

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
    
    public void launch(float impulseX, float impulseY){
        float xDif = body.getPosition().x - impulseX;
        float yDif = body.getPosition().y - impulseY;
        Vec2 temp = 
                new Vec2( xDif * IMPULSE_SCALE,
                        yDif * IMPULSE_SCALE );
        body.applyLinearImpulse(temp,body.getPosition());
    }
    
    public boolean contains(int pixX, int pixY){
        return pixShape.contains(pixX, pixY);
    }
    
}
