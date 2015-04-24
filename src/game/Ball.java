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
 * @version     1.0
 * @since       2015-04-21
 * @extends 	Entity class
 */
public class Ball extends Entity{

    int mouseX,mouseY;
    boolean grabbed;
    float pixRad;
    int shotCount = 3;

    CircleShape shape;
    Ellipse2D.Float pixCircle;
    static final float IMPULSE_SCALE = Consts.SCALE/400;
    BufferedImage ballImage;
    
    /**
     * Constructor Ball creates a Ball.
     * 
     * @param 	world 	sets physics to this world
     * @param 	bd		BodyDef parameter
     * @param 	fd		FixtureDef parameter
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
    }
    /**
     * setGrabbed method sets grabbed to true
     */
    public void setGrabbed(){
        grabbed = true;
    }

    /**
     * setMouseLoc method sets mouse to a x,y coordinate on the playfield.
     * 
     * @param	mouseX	Set x value of mouse location.
     * @param	mouseY	Set y value of mouse location.
     */
    public void setMouseLoc(int mouseX, int mouseY){
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    /**
     * hit method to identify when another entity is hit.
     * 
     * @param	otherEntity		Another entity like wall, or robot
     */
    @Override
    public void hit(Entity otherEntity) {
        // TODO Auto-generated method stub

    }

    /**
     * render method to draw the ball image. 
     * 
     * @param	g		A Graphics2D object that is passed.
     */
    @Override
    public void render(Graphics2D g) {
        g.drawImage(ballImage, (int)pixCircle.x, (int)pixCircle.y, null);
        g.setColor(Color.BLACK);
        g.draw(pixCircle);
        if(grabbed){
            int temp2 = (int)(pixRad + 0.5f);
            g.drawLine((int)(pixX + .5f), (int)(pixY + .5f), mouseX, mouseY);
            g.drawOval((mouseX - temp2/2), (mouseY - temp2/2), temp2, temp2);
        }
    }

    /**
     * pixUpdate method to update the location of the object.
     */
    @Override
    public void pixUpdate() {
        pixX = Utils.toPixX(body.getPosition().x);
        pixY = Utils.toPixY(body.getPosition().y);
        pixRad = (Utils.toPixLength(shape.m_radius));
        pixCircle.x = pixX - pixRad;
        pixCircle.y = pixY - pixRad;
    }

    /**
     * launch method to launch the ball.
     * 
     * @param	impulseX	x coordinate for launching ball.
     * @param	impulseY	y coordinate for launching ball.
     */
    public void launch(float impulseX, float impulseY){
        grabbed = false;
        float xDif = body.getPosition().x - impulseX;
        float yDif = body.getPosition().y - impulseY;
        Vec2 temp = 
                new Vec2( xDif * IMPULSE_SCALE,
                        yDif * IMPULSE_SCALE );
        body.applyLinearImpulse(temp,body.getPosition());
    }

    /**
     * postlaunch method to update the location of the object.
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
     * contains method to see if x and y coordinates are contained in pixCircle.
     * 
     * @param	pixX	x coordinate of x coordinate of .
     * @param	pixY	y coordinate for launching ball.
     */
    public boolean contains(int pixX, int pixY){
        return pixCircle.contains(pixX, pixY);
    }
}
