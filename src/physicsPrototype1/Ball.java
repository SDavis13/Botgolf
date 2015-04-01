package physicsPrototype1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.event.MouseInputAdapter;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;


public class Ball {
    float x,y;
    int mouseX,mouseY;
    boolean grabbed;
    static final int IMPULSE_SCALE = 10;
    
    Ellipse2D.Float pixCircle;
    CircleShape shape;
    Body body;
    World world;
    
    BallLaunch ballLauncher = new BallLaunch();
    
    public Ball(World world){
        this.world = world;
        grabbed = false;
        x = 0;
        y = 0;
        mouseX = 0;
        mouseY = 0;
        //body definition
        BodyDef bd = new BodyDef();
        bd.position.set(Main.toPosX(300), Main.toPosY(300));
        bd.type = BodyType.DYNAMIC;
        bd.linearDamping = 1.5f;
        
        //define shape of the body.
        shape = new CircleShape();
        shape.m_radius = .75f;
        
        //define fixture of the body.
        FixtureDef fd = new FixtureDef();
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

    void render(Graphics2D g){
        g.setColor(Color.BLACK);
        x = Main.toPixelPosX(body.getPosition().x);
        y = Main.toPixelPosY(body.getPosition().y);
        float temp = (Main.toPixelWidth(shape.m_radius));
        pixCircle.x = x - temp;
        pixCircle.y = y - temp;
        g.fill(pixCircle);
        if(grabbed){
            int temp2 = (int)(temp + 0.5f);
            g.drawLine((int)(x + .5f), (int)(y + .5f), mouseX, mouseY);
            g.drawOval((mouseX - temp2/2), (mouseY - temp2/2), temp2, temp2);
        }
    }
    
    class BallLaunch extends MouseInputAdapter{
        public void mouseDragged(MouseEvent e){
            mouseX = e.getX();
            mouseY = e.getY();
            if(pixCircle.contains(mouseX,mouseY) && e.getButton() == 0){
                grabbed = true;
            }
        }
        public void mouseReleased(MouseEvent e){
            mouseX = e.getX();
            mouseY = e.getY();
            if(grabbed && e.getButton() == 1){
                grabbed = false;
                float xDif = body.getPosition().x - Main.toPosX(mouseX);
                float yDif = body.getPosition().y - Main.toPosY(mouseY);
                Vec2 temp = 
                        new Vec2( xDif * IMPULSE_SCALE,
                                yDif * IMPULSE_SCALE );
                body.applyLinearImpulse(temp,body.getPosition());
            }
        }
    }
}
