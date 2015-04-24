package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class Ball extends Entity and 
 * creates a ball used in World
 * and sets its behavior.
 * 
 * @author Josh
 * @version 1.0
 * @since 4/23/14
 * @extends Entity class
 */
public class Ball extends Entity{
    
    int mouseX,mouseY;
    boolean grabbed;
    float pixRad;
    int shotCount = 3;
    
    CircleShape shape;
    Ellipse2D.Float pixCircle;
    static final float IMPULSE_SCALE = 10; //TODO Should make this also a function of Consts.SCALE
    BufferedImage ballImage;
/**
 * Constructor Ball creates a Ball.
 * 
 * @param world sets physics to this world
 * @param bd
 * @param fd
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
     * 
     *
     */
    public void setGrabbed(){
        grabbed = true;
    }
    
    public void setMouseLoc(int mouseX, int mouseY){
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    @Override
    public void hit(Entity otherEntity) {
        // TODO Auto-generated method stub
        
    }

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
    
    @Override
    public void pixUpdate() {
        pixX = Utils.toPixX(body.getPosition().x);
        pixY = Utils.toPixY(body.getPosition().y);
        pixRad = (Utils.toPixLength(shape.m_radius));
        pixCircle.x = pixX - pixRad;
        pixCircle.y = pixY - pixRad;
    }
    
    public void launch(float impulseX, float impulseY){
        grabbed = false;
        float xDif = body.getPosition().x - impulseX;
        float yDif = body.getPosition().y - impulseY;
        Vec2 temp = 
                new Vec2( xDif * IMPULSE_SCALE,
                        yDif * IMPULSE_SCALE );
        body.applyLinearImpulse(temp,body.getPosition());
    }
    
    public boolean postLaunch(){
    	Vec2 vec = body.getLinearVelocity();
    	if(Math.round(vec.x) == 0 && Math.round(vec.y)==0){
    		shotCount--;
    		return true;
    	}
    	return false;
    }
    public boolean contains(int pixX, int pixY){
        return pixCircle.contains(pixX, pixY);
    }
}
