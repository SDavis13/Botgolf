package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

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
        fixture.setUserData(this);
    	
        int[] xAry = new int[shape.m_count];
        int[] yAry = new int[shape.m_count];
        
        for(int i = 0; i < xAry.length; i++){
            xAry[i] = (int)(Utils.toPixX(shape.m_vertices[i].x + bd.position.x) + .5f);
            yAry[i] = (int)(Utils.toPixY(shape.m_vertices[i].y + bd.position.y) + .5f);
        }
        pixShape = new Polygon(xAry, yAry, shape.m_count);
        pixX = Utils.toPixX(body.getPosition().x);
        pixY = Utils.toPixY(body.getPosition().y);
    }

    @Override
    public void hit(Entity otherEntity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics2D g) 
    {    	    	
        g.setColor(Color.ORANGE);
        g.fillPolygon(pixShape);      
    }

    @Override
    public void pixUpdate() {
        float newPixX = Utils.toPixX(body.getPosition().x);
        float newPixY = Utils.toPixY(body.getPosition().y);
        pixShape.translate((int)(newPixX - pixX), (int)(newPixY - pixY));
        pixX = newPixX;
        pixY = newPixY;
    }

}
