package physicsPrototype1;

import java.awt.Graphics2D;
import java.awt.Polygon;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

public class Wall {
    int x,y;
    PolygonShape shape;
    Body body;
    World world;
    Polygon pixShape;
    static int num = 1;
    int id;
    
    public Wall(World world, float posX, float posY, float width, float height){
        id = num;
        num++;
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
        body.createFixture(fd).setUserData(id);
        
        int[] xAry = new int[shape.m_count];
        int[] yAry = new int[shape.m_count];
        
        for(int i = 0; i < xAry.length; i++){
            xAry[i] = (int)(Main.toPixelPosX(shape.m_vertices[i].x) + .5f);
            yAry[i] = (int)(Main.toPixelPosY(shape.m_vertices[i].y) + .5f);
        }
        pixShape = new Polygon(xAry, yAry, shape.m_count);
    }
    
    void render(Graphics2D g){
        g.fill(pixShape);
    }
}
