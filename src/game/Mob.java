package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * This is the mob class that defines the robots as far as rendering and physics.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     1.0
 * @since       2015-04-21
 */
public class Mob extends Entity{
    public final static int DEFAULT_HEALTH = 2;
    final int imgXOffset;
    final int imgYOffset;
    PolygonShape shape;
    Polygon pixShape;
    Rectangle rectangle;
    Image mobGraphic;
    int health = DEFAULT_HEALTH;
    int numOfSpacesMobCanMove;

    Mob(World world, BodyDef bd, FixtureDef fd, PolygonShape shape, float gridScale){

        try {
            mobGraphic = ImageIO.read(new File(Consts.IMG_GENROBO)).getScaledInstance(80, 98, Image.SCALE_SMOOTH);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

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
        rectangle = pixShape.getBounds();
        pixX = Utils.toPixX(body.getPosition().x);
        pixY = Utils.toPixY(body.getPosition().y);

        imgXOffset = mobGraphic.getWidth(null)/2;
        imgYOffset = mobGraphic.getHeight(null)-rectangle.height/2;
    }

    /**
     * setHealth method is used for setting health on robot.
     * 
     * @param 	health	this is the health defined for robot
     */
    public void setHealth(int health){
        this.health = health;
    }

    /**
     * hit method used to decrement health when hit by ball object.
     * 
     * @param	otherEntity		object defined as another entity
     */
    @Override
    public void hit(Entity otherEntity) {
        health--;
        if (health <= 0)
        {
            remove = true;
        }

    }

    /**
     * render method is used to draw the image of the mob.
     * 
     * @param	g	this is an object of Graphics2D
     */
    @Override
    public void render(Graphics2D g) {
        g.drawImage(mobGraphic, (int)(pixX+.5f) - imgXOffset, (int)(pixY+.5f) - imgYOffset, null);
        g.draw(pixShape);
    }

    /**
     * pixUpdate method used to update pixel location of mob.
     */
    @Override
    public void pixUpdate() {
        float newPixX = Utils.toPixX(body.getPosition().x);
        float newPixY = Utils.toPixY(body.getPosition().y);
        pixShape.translate((int)(newPixX - pixX), (int)(newPixY - pixY));
        rectangle = pixShape.getBounds();
        pixX = newPixX;
        pixY = newPixY;
    }


}
