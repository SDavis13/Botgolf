package physicsPrototype1;

import javax.swing.JFrame;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class Main extends JFrame{
    private static final long serialVersionUID = 1L;
    protected static World world = new World(new Vec2(0.0f, 0.0f));
    protected Ball ball = new Ball(world);
    
    public static void main(String[] args){
        
    }
    
    public Main(){
        
    }
    
    //This method creates a wall.
    public static void addWall(float posX, float posY, float width, float height){
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(width,height);
             
        FixtureDef fd = new FixtureDef();
        fd.shape = ps;
        fd.density = 1.0f;
        fd.friction = 0.3f;   
     
        BodyDef bd = new BodyDef();
        bd.position.set(posX, posY);
             
        world.createBody(bd).createFixture(fd);
    }
    
    //Convert a JBox2D x coordinate to a Swing pixel x coordinate
    public static float toPixelPosX(float posX) {
        float x = WIDTH*posX / 100.0f;
        return x;
    }
     
    //Convert a Swing pixel x coordinate to a JBox2D x coordinate
    public static float toPosX(float posX) {
        float x = (posX*100.0f*1.0f)/WIDTH;
        return x;
    }
     
    //Convert a JBox2D y coordinate to a Swing pixel y coordinate
    public static float toPixelPosY(float posY) {
        float y = HEIGHT - (1.0f*HEIGHT) * posY / 100.0f;
        return y;
    }
     
    //Convert a Swing pixel y coordinate to a JBox2D y coordinate
    public static float toPosY(float posY) {
        float y = 100.0f - ((posY * 100*1.0f) /HEIGHT) ;
        return y;
    }
     
    //Convert a JBox2D width to pixel width
    public static float toPixelWidth(float width) {
        return WIDTH*width / 100.0f;
    }
     
    //Convert a JBox2D height to pixel height
    public static float toPixelHeight(float height) {
        return HEIGHT*height/100.0f;
    }
}