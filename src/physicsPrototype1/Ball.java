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
    int x,y;
    int mouseX,mouseY;
    boolean grabbed;
    
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
        bd.position.set(50, 50);
        bd.type = BodyType.DYNAMIC;
        
        //define shape of the body.
        shape = new CircleShape();
        shape.m_radius = 1f;
        
        //define fixture of the body.
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.5f; //This defines the heaviness of the body with respect to its area.
        fd.friction = 0.3f; //This defines how bodies slide when they come in contact with each other. 
        //Friction value can be set between 0 and 1. Lower value means more slippery bodies.
        fd.restitution = 0.5f; //This define how bouncy is the body.
        //Restitution values can be set between 0 and 1. Here higher value means more bouncy body.
        
        //create the body and add fixture to it
        body = world.createBody(bd);
        body.createFixture(fd).setUserData((int)0);
        
        x = (int)Main.toPixelPosX(body.getPosition().x);
        y = (int)Main.toPixelPosY(body.getPosition().y);
        float temp = Main.toPixelWidth(shape.m_radius);
        pixCircle = new Ellipse2D.Float((x - temp), (y - temp), temp*2, temp*2);
    }
    
    void render(Graphics2D g){
        g.setColor(Color.BLACK);
        x = (int)(Main.toPixelPosX(body.getPosition().x) + .5f);
        y = (int)(Main.toPixelPosY(body.getPosition().y) + .5f);
        float temp = (Main.toPixelWidth(shape.m_radius));
        pixCircle.x = x - temp;
        pixCircle.y = y - temp;
        g.fill(pixCircle);
        if(grabbed){
            int temp2 = (int)(temp + 0.5f);
            g.drawLine(x, y, mouseX, mouseY);
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
                Vec2 temp = 
                        new Vec2(Main.toPosX(x - mouseX),Main.toPosY(y - mouseY));
                body.applyForceToCenter(temp);
            }
        }
    }
}
