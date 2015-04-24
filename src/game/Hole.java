package game;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * This represents the specifications of the Hole for the game.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 * @extends 	Entity
 */
public class Hole extends Entity{
    public CircleShape shape;
    public Ellipse2D.Float pixShape;
    public boolean win = false;

    BufferedImage holeImage;
    float pixRad;

    /**
     * Constructor for Hole
     * 
     * @param world		Object type of World passed
     * @param bd		Object type of Body Definition passed
     * @param fd		Object type of Fixture Definition passed
     */
    Hole(World world, BodyDef bd, FixtureDef fd){

        try {
            holeImage = ImageIO.read(new File(Consts.IMG_HOLE));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        this.world = world;
        pixX = 0;
        pixY = 0;

        pixRad = holeImage.getWidth(null) / 2f;
        shape = new CircleShape();
        shape.m_radius = Utils.toPhysLength(pixRad);

        fd.shape = shape;

        body = world.createBody(bd);
        body.createFixture(fd).setUserData(this);

        pixX = Utils.toPixX(body.getPosition().x);
        pixY = Utils.toPixY(body.getPosition().y);
        pixShape = new Ellipse2D.Float((pixX - pixRad), (pixY - pixRad), pixRad*2, pixRad*2);
    }

    /**
     * Hit method created to play sound if ball entity hits hole.
     * 
     * @param otherEntity	Object type of Entity passed
     */
    @Override
    public void hit(Entity otherEntity) {
        if(otherEntity instanceof Ball){
            SoundRepository.playSound(Consts.SOUNDS[Consts.SNDIDX_SCORE]);
            win = true;            
        }
    }

    /**
     * Render method used to draw the hole graphic to location.
     * 
     * @param g		Object type of Graphics2D passed
     */
    @Override
    public void render(Graphics2D g) 
    {
        g.drawImage(holeImage, (int)pixShape.x, (int)pixShape.y, null);
    }

    /**
     * PixUpdate method used to define where the graphic of hole is drawn.
     */
    @Override
    public void pixUpdate() {
        pixX = Utils.toPixX(body.getPosition().x);
        pixY = Utils.toPixY(body.getPosition().y);
        pixRad = (Utils.toPixLength(shape.m_radius));
        pixShape.x = pixX - pixRad;
        pixShape.y = pixY - pixRad;
    }

}
