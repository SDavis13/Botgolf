package physicsPrototype1;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

public class Wall {
    int x,y;
    PolygonShape shape;
    Body body;
    World world;
    
    public Wall(World world, float posX, float posY, float width, float height){
        this.world = world;
        shape = new PolygonShape();
        shape.setAsBox(width,height);
             
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1.0f;
        fd.friction = 0.3f;   
     
        BodyDef bd = new BodyDef();
        bd.position.set(posX, posY);
        bd.type = BodyType.STATIC;
             
        body = world.createBody(bd);
        body.createFixture(fd);
    }
}
