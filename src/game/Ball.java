package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * Class Ball extends Entity and 
 * creates a ball used in World
 * and sets its behavior.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 * @extends 	Entity class
 */
public class Ball extends Entity{

    int mouseX,mouseY;
    boolean grabbed;
    float pixRad;
    int shotCount = 3;

    

    CircleShape shape;
    Ellipse2D.Float pixCircle;
    Ellipse2D.Float grabCircle;
    static final float IMPULSE_SCALE = Consts.SCALE/400;
    BufferedImage ballImage;
    
    /**
     * Constructor Ball creates a Ball.
     * 
     * @param world		Object type of World passed
     * @param bd		Object type of body definition passed
     * @param fd		Object type of fixture definition passed
     */
    public Ball(World world, BodyDef bd, FixtureDef fd){

        try {
            ballImage = ImageIO.read(new File(Consts.IMG_BALL));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        this.world = world;
        grabbed = false;
        pixX = 0;
        pixY = 0;
        mouseX = 0;
        mouseY = 0;

        pixRad = ballImage.getWidth(null) / 2f;
        shape = new CircleShape();
        shape.m_radius = Utils.toPhysLength(pixRad);

        fd.shape = shape;

        body = world.createBody(bd);
        body.createFixture(fd).setUserData(this);

        pixX = Utils.toPixX(body.getPosition().x);
        pixY = Utils.toPixY(body.getPosition().y);
        pixCircle = new Ellipse2D.Float((pixX - pixRad), (pixY - pixRad), pixRad*2, pixRad*2);
        grabCircle = new Ellipse2D.Float(pixCircle.x - pixRad, pixCircle.y - pixRad, pixCircle.width*2, pixCircle.height*2);
    }
    /**
     * SetGrabbed method sets grabbed to true
     */
    public void setGrabbed(){
        grabbed = true;
    }

    /**
     * SetMouseLoc method sets mouse to a x,y coordinate on the world.
     * 
     * @param	mouseX	Set x value of mouse location.
     * @param	mouseY	Set y value of mouse location.
     */
    public void setMouseLoc(int mouseX, int mouseY){
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }
    
    /**
     * SetNumHits method keeps track of number of hits.
     * 
     * @param hits	Integer type of number of hits
     */
    public void setNumHits(int hits)
    {
    	shotCount = hits;
    }

    /**
     * Hit method to identify when another entity is hit.
     * 
     * @param otherEntity	Object type of entity passed
     */
    @Override
    public void hit(Entity otherEntity) {
        // TODO Auto-generated method stub

    }

    /**
     * Render method to draw the ball image. 
     * 
     * @param g		Object type of Graphics2D passed
     */
    @Override
    public void render(Graphics2D g) {
        g.drawImage(ballImage, (int)pixCircle.x, (int)pixCircle.y, null);
        g.setColor(Color.BLACK);
        g.draw(pixCircle);
        if(grabbed){
            g.setColor(Color.LIGHT_GRAY);
            int temp2 = (int)(pixRad + 0.5f);
            g.drawLine((int)(pixX + .5f), (int)(pixY + .5f), mouseX, mouseY);
            g.drawOval((mouseX - temp2/2), (mouseY - temp2/2), temp2, temp2);
        }
    }

    /**
     * PixUpdate method to update the location of the object.
     */
    @Override
    public void pixUpdate() {
        pixX = Utils.toPixX(body.getPosition().x);
        pixY = Utils.toPixY(body.getPosition().y);
        pixRad = (Utils.toPixLength(shape.m_radius));
        pixCircle.x = pixX - pixRad;
        pixCircle.y = pixY - pixRad;
        grabCircle.x = pixCircle.x - pixRad;
        grabCircle.y = pixCircle.y - pixRad;
    }

    /**
     * Launch method to launch the ball.
     * Plays sound after mouse is released.
     * 
     * @param impulseX	Float type passed for x-coordinate
     * @param impulseY	Float type passed for y-coordinate
     */
    public void launch(float impulseX, float impulseY){
        grabbed = false;
        float xDif = body.getPosition().x - impulseX;
        float yDif = body.getPosition().y - impulseY;
        Vec2 temp = 
                new Vec2( xDif * IMPULSE_SCALE,
                        yDif * IMPULSE_SCALE );
        body.applyLinearImpulse(temp,body.getPosition());
        SoundRepository.playSound(Consts.SOUNDS[Consts.SNDIDX_LAUNCH]);
    }

    /**
     * Postlaunch method to update the location of the object.
     */
    public boolean postLaunch(){
        Vec2 vec = body.getLinearVelocity();
        if(Math.round(vec.x) == 0 && Math.round(vec.y)==0){
            shotCount--;
            return true;
        }
        return false;
    }
    
    /**
     * Contains method to see if x and y coordinates are contained in pixCircle.
     * 
     * @param pixX	Integer type of pixel x-coordinate passed
     * @param pixY	Integer type of pixel y-coordinate passed
     */
    public boolean contains(int pixX, int pixY){
        return pixCircle.contains(pixX, pixY);
    }
    
    public boolean containsDoubleSize(int pixX, int pixY){
        return grabCircle.contains(pixX, pixY);
    }
}
