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
 * This is the ball which is launched around in the game.
 * 
 * @authors Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version 2015-04-28
 * @since 2015-04-24
 * @extends Entity
 */
public class Ball extends Entity{
    /**
     * The scale of the launch speed.
     */
    static final float IMPULSE_SCALE = Consts.SCALE/400;
    /**
     * The coordinates of the mouse. These are used to render the "rubber band" thing that appears when the ball is grabbed.
     */
    int mouseX,mouseY;
    /**
     * Whether or not the ball is being grabbed.
     */
    boolean grabbed;
    /**
     * The radius of the ball in pixels.
     */
    float pixRad;
    /**
     * The number of shots left.
     */
    int shotCount = 3;
    /**
     * The JBox2D shape of the Ball.
     */
    CircleShape shape;
    /**
     * The javax.awt shape of the Ball.
     */
    Ellipse2D.Float pixCircle;
    /**
     * An expanded circle for grabbing the Ball. Should have double the radius.
     */
    Ellipse2D.Float grabCircle;
    /**
     * The image of the Ball.
     */
    BufferedImage ballImage;

    /**
     * Constructor.
     * Note that attaching a JBox2D Shape to the fixture passed is unnecessary and any such shape will be replaced.
     * This constructor produces a new Shape using the interior radius of the Ball's image, defined in Consts.
     * 
     * @param world the World that the Ball exists in.
     * @param bd The JBox2D Body definition. This includes the position of the Ball.
     * @param fd The fixture that defines the physical characteristics of the Ball.
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
     * Sets grabbed to true.
     */
    public void setGrabbed(){
        grabbed = true;
    }

    /**
     * Setter for the mouse location.
     * 
     * @param mouseX the X value of mouse location.
     * @param mouseY the Y value of mouse location.
     */
    public void setMouseLoc(int mouseX, int mouseY){
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    /**
     * Setter for shotCount.
     * 
     * @param shots the number of shots.
     */
    public void setNumShots(int shots)
    {
        shotCount = shots;
    }

    /**
     * Required as an Entity, but not currently used.
     * 
     * @param otherEntity the Entity that has made contact with the Ball.
     */
    @Override
    public void hit(Entity otherEntity) {
        // TODO Auto-generated method stub
    }

    /**
     * Render method to draw the ball image. 
     * 
     * @param g the Graphics2D context in which to draw the Ball.
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
     * Updates the pixel location of the object.
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
     * Launches the Ball by taking the difference between the impulse coordinates and the current Ball coordinates and turning it into a velocity vector, scaled by IMPULSE_SCALE.
     * Plays the launch sound and sets grabbed to false.
     * 
     * @param impulseX the JBox2D X coordinate of the foreign end of the launch vector.
     * @param impulseY the JBox2D Y coordinate of the foreign end of the launch vector.
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
     * Finds out whether the Ball has stopped yet or not. Accurate to within .5 of 0 meters per second.
     * (Reminder: JBox2D values are in meters instead of pixels.)
     * @return the boolean value of whether the ball is still moving.
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
     * Finds out if a pixel coordinate is contained in pixCircle.
     * 
     * @param pixX the X value of a pixel coordinate
     * @param pixY the Y value of a pixel coordinate
     */
    public boolean contains(int pixX, int pixY){
        return pixCircle.contains(pixX, pixY);
    }

    /**
     * Finds out if a pixel coordinate is contained in grabCircle.
     * 
     * @param pixX the X value of a pixel coordinate
     * @param pixY the Y value of a pixel coordinate
     */
    public boolean containsDoubleSize(int pixX, int pixY){
        return grabCircle.contains(pixX, pixY);
    }
}
