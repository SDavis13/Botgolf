package physicsPrototype1;

import java.awt.Graphics2D;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.*;


public class Ball {
    int x,y;
    int mouseX,mouseY;
    
    CircleShape shape;
    Body body;
    World world;
    
    public Ball(World world){
        this.world = world;
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
        shape.m_radius = 0.5f;
        
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
        body.createFixture(fd);
    }
    
    void render(Graphics2D g){
        x = (int)Main.toPixelPosX(body.getPosition().x);
        y = (int)Main.toPixelPosY(body.getPosition().y);
        int temp = (int)(Main.toPixelWidth(shape.m_radius + .5f));
        g.drawOval((x - temp), (y - temp), temp*2, temp*2);
    }
}
