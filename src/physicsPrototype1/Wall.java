package physicsPrototype1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
    Color color;
    Clip clip;
    
    public Wall(World world, float posX, float posY, float halfWidth, float halfHeight){
        id = num;
        num++;
        this.world = world;
        shape = new PolygonShape();
        shape.setAsBox(halfWidth,halfHeight);
        
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
        File file;
        switch(id){
            case 1: color = Color.RED;
                    file = new File("resources/physicsPrototype1/bop.wav");
                    break;
            case 2: color = Color.YELLOW;
                    file = new File("resources/physicsPrototype1/hit.wav");
                    break;
            case 3: color = Color.GREEN;
                    file = new File("resources/physicsPrototype1/ping.wav");
                    break;
            case 4: color = Color.BLUE;
                    file = new File("resources/physicsPrototype1/whipp.wav");
                    break;
            default:
                    color = new Color(200,245,255);
                    file = null;
        }
        try {
            if(file != null)
                    clip.open(AudioSystem.getAudioInputStream(file));
        } catch (LineUnavailableException e) {
            // Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
             
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1.0f;
        fd.friction = 0.1f;
        if(id > 4){
            fd.friction = 10f;
        }
     
        BodyDef bd = new BodyDef();
        bd.position.set(posX, posY);
        bd.type = BodyType.STATIC;
             
        body = world.createBody(bd);
        body.createFixture(fd).setUserData(clip);
        
        int[] xAry = new int[shape.m_count];
        int[] yAry = new int[shape.m_count];
        
        for(int i = 0; i < xAry.length; i++){
            xAry[i] = (int)(Main.toPixelPosX(shape.m_vertices[i].x + posX) + .5f);
            yAry[i] = (int)(Main.toPixelPosY(shape.m_vertices[i].y + posY) + .5f);
        }
        pixShape = new Polygon(xAry, yAry, shape.m_count);
        
    }
    
    void render(Graphics2D g){
        g.setColor(color);
        g.fill(pixShape);
    }
}
