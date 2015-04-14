package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

public class Mob extends Entity{
    PolygonShape shape;	
	Rectangle2D.Float pixShape;	
	Image genericMob = Toolkit.getDefaultToolkit().getImage(Consts.IMG_GENROBO);
    int health;
    int numOfSpacesMobCanMove;    
    
    Mob(World world, BodyDef bd, FixtureDef fd, PolygonShape shape, float gridScale){
    	this.world = world;        
    	shape = new PolygonShape();
    	shape.setAsBox(5f, 5f);    
    	
    	fd.shape = shape;    	
    	fd.density = 1.0f;
    	fd.friction = 0.1f;
    	
    	bd.position.set(1f, 1f);
    	bd.type = BodyType.STATIC;
    }

    @Override
    public void hit(Entity otherEntity) {
        health--;
        if (health == 0)
        {
        	remove = true;
        }
        
    }

    @Override
    public void render(Graphics g1) {
        g1.drawImage(genericMob, (int)pixShape.x, (int)pixShape.y, null);
    }

    @Override
    public void pixUpdate() {
        pixX = Utils.toPixX(body.getPosition().x);
        pixY = Utils.toPixY(body.getPosition().y);
        float temp = (Utils.toPixLength(shape.m_radius));        
        pixShape.x = pixX - temp;
        pixShape.y = pixY - temp;
    }
    
    
}
