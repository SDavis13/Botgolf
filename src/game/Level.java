package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import org.jbox2d.dynamics.*;

public class Level {
    int id;
    String name;
    Ball ball;
    ArrayList<Mob> mobs;
    ArrayList<Wall> walls;
    Hole hole;
    World world;
    
    Level(World world){
        this.world = world;
    }
    void moveMobs(){
        
    }
    void step(){
        
    }
    void render(Graphics2D g){
        Rectangle bounds = g.getClipBounds();
        if(hole.pixShape.intersects(bounds)){
            hole.render(g);
        }
        for(Wall wall : walls){
            
        }
    }
}
