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
	
	float x,y;
    int mouseX,mouseY;
    boolean grabbed;    
	
    CircleShape shape;
    Ellipse2D.Float pixCircle;
    static final float IMPULSE_SCALE = 10;
    Image ballImage = Toolkit.getDefaultToolkit().getImage("resources/game/images/");

    public Ball(World world, BodyDef bd, FixtureDef fd, CircleShape shape){
        
    	this.world = world;
        grabbed = false;
        x = 0;
        y = 0;
        mouseX = 0;
        mouseY = 0;
        //body definition        
        bd.position.set(Main.toPosX(300), Main.toPosY(300));
        bd.type = BodyType.DYNAMIC;
        bd.linearDamping = 1.5f;
        
        //define shape of the body.
        shape = new CircleShape();
        shape.m_radius = .75f;
        
        //define fixture of the body.        
        fd.shape = shape;
        fd.density = 0.15f; //This defines the heaviness of the body with respect to its area.
        fd.friction = 0.1f; //This defines how bodies slide when they come in contact with each other. 
        //Friction value can be set between 0 and 1. Lower value means more slippery bodies.
        fd.restitution = 0.5f; //This define how bouncy is the body.
        //Restitution values can be set between 0 and 1. Here higher value means more bouncy body.
        
        //create the body and add fixture to it
        body = world.createBody(bd);
        body.createFixture(fd).setUserData((int)0);
        
        x = Main.toPixelPosX(body.getPosition().x);
        y = Main.toPixelPosY(body.getPosition().y);
        float temp = Main.toPixelWidth(shape.m_radius);
        pixCircle = new Ellipse2D.Float((x - temp), (y - temp), temp*2, temp*2);
    	
    }

    @Override
    public void hit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics g1) {
            	
        x = Main.toPixelPosX(body.getPosition().x);
        y = Main.toPixelPosY(body.getPosition().y);
        float temp = (Main.toPixelWidth(shape.m_radius));
        pixCircle.x = x - temp;
        pixCircle.y = y - temp;
        g1.drawImage(ballImage, (int)pixCircle.x, (int)pixCircle.y, null);
        if(grabbed){
            int temp2 = (int)(temp + 0.5f);
            g1.drawLine((int)(x + .5f), (int)(y + .5f), mouseX, mouseY);
            g1.drawOval((mouseX - temp2/2), (mouseY - temp2/2), temp2, temp2);
        }
        
    }
    
    public void launch(float impulseX, float impulseY){
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
