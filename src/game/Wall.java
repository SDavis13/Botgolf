package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

public class Wall extends Entity {
	
	PolygonShape shape;
	Polygon pixShape;      
    Color color;
     
    Wall(World world, BodyDef bd, FixtureDef fd, PolygonShape shape) 
    {
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics g1) 
    {    	    	
        g1.setColor(color.ORANGE);
        g1.fillPolygon(pixShape);        
    }

    @Override
    public void pixUpdate() {
        // TODO Auto-generated method stub
        
    }

}
