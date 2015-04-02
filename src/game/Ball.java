package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import physicsPrototype1.Main;

import java.awt.Toolkit;


public class Ball extends Entity{
    /**
     * Value between 0 and 1. Higher = bouncier
     */
    public static final float BOUNCE = 0.5f;
    /**
     * Value between 0 and 1. Lower = slipperier
     */
    public static final float FRICTION = 0.1f;
    /**
     * Mass / Area
     */
    public static final float DENSITY = 0.15f; //TODO Should probably make this a function of Consts.SCALE
    
    int mouseX,mouseY;
    boolean grabbed;

    CircleShape shape;
    Ellipse2D.Float pixCircle;
    static final float IMPULSE_SCALE = 10;
    Image ballImage = Toolkit.getDefaultToolkit().getImage(Consts.IMGLOC + "Ball.png");

    public Ball(World world, BodyDef bd, FixtureDef fd, CircleShape shape, Vec2 position){
        
    	this.world = world;
        grabbed = false;
        pixX = 0;
        pixY = 0;
        mouseX = 0;
        mouseY = 0;
        //body definition
        bd.position.set(position);
        bd.type = BodyType.DYNAMIC;
        bd.linearDamping = Consts.rollingFriction;
        
        int pixRad = ballImage.getWidth(null) / 2;
        //define shape of the body.
        shape = new CircleShape();
        shape.m_radius = Utils.toPhysLength(pixRad);
        
        //define fixture of the body.        
        fd.shape = shape;
        fd.density = DENSITY;
        fd.friction = FRICTION;
        fd.restitution = BOUNCE;
        
        //create the body and add fixture to it
        body = world.createBody(bd);
        body.createFixture(fd)/*.setUserData(?)*/;
        
        pixX = Main.toPixelPosX(body.getPosition().x);
        pixY = Main.toPixelPosY(body.getPosition().y);
        pixCircle = new Ellipse2D.Float((pixX - pixRad), (pixY - pixRad), pixRad*2, pixRad*2);
    }
    
    public void setGrabbed(){
        grabbed = true;
    }

    @Override
    public void hit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics g1) {
            	
        pixX = Main.toPixelPosX(body.getPosition().x);
        pixY = Main.toPixelPosY(body.getPosition().y);
        float temp = (Main.toPixelWidth(shape.m_radius));
        pixCircle.x = pixX - temp;
        pixCircle.y = pixY - temp;
        g1.drawImage(ballImage, (int)pixCircle.x, (int)pixCircle.y, null);
        if(grabbed){
            int temp2 = (int)(temp + 0.5f);
            g1.drawLine((int)(pixX + .5f), (int)(pixY + .5f), mouseX, mouseY);
            g1.drawOval((mouseX - temp2/2), (mouseY - temp2/2), temp2, temp2);
        }
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
    
    public boolean contains(int pixX, int pixY){
        return pixCircle.contains(pixX, pixY);
    }
    
}
