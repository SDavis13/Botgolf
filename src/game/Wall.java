package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

public class Wall extends Entity {
	
	PolygonShape shape;
	Polygon pixShape;

    Wall(World world, BodyDef bd, FixtureDef fd, PolygonShape shape) 
    {
    	this.world = world;
    	this.shape = shape;
    	
    	fd.shape = shape;
    	
    	body = world.createBody(bd);
        fixture = body.createFixture(fd);
    	
    }

    @Override
    public void hit(Entity otherEntity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics g1) 
    {    	    	
        g1.setColor(Color.ORANGE);
        g1.fillPolygon(pixShape);      
    }

    @Override
    public void pixUpdate() {
        // Auto-generated method stub
        // Doesn't need code until (if) moving walls exist.
        
    }

}
