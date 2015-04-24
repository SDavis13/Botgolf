package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * This class represents specifications of Wall.  This also
 * extends the Entity class and those specifications. 
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 * @extends		Entity
 */
public class Wall extends Entity {

    PolygonShape shape;
    Polygon pixShape;

    /**
     * Constructor for Wall.
     * 
     * @param world		Passed world object
     * @param bd		Passed body definition object
     * @param fd		Passed fixture definition object
     * @param shape		Passed polygon shape object
     */
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

    /**
     * Hit method created when a hit is detected by another entity.
     * 
     * @param otherEntity	Object type of entity passed
     */
    @Override
    public void hit(Entity otherEntity) {
        // TODO Auto-generated method stub

    }

    /**
     * Render method used to draw the wall.
     * 
     * @param g		Object type of Graphics2D passed
     */
    @Override
    public void render(Graphics2D g) 
    {    	    	
        g.setColor(Color.ORANGE);
        g.fillPolygon(pixShape);      
    }

    /**
     * PixUpdate method used to define pixel location of wall.
     */
    @Override
    public void pixUpdate() {
        float newPixX = Utils.toPixX(body.getPosition().x);
        float newPixY = Utils.toPixY(body.getPosition().y);
        pixShape.translate((int)(newPixX - pixX), (int)(newPixY - pixY));
        pixX = newPixX;
        pixY = newPixY;
    }

}
