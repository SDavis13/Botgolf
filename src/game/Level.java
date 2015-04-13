package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import org.jbox2d.dynamics.*;

public class Level {
    /**
     * Velocity value accuracy.
     * Higher = more accuracy, less performance
     */
    final static int VELOCITY_ITERATIONS = 6;
    /**
     * Position value accuracy.
     * Higher = more accuracy, less performance
     */
    final static int POSITION_ITERATIONS = 3;
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
    boolean moveMobs(){
        //TODO move the mobs
        return true;
    }
    void step(){
        world.step(Consts.TIMESTEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }
    void render(Graphics2D g){
        Rectangle bounds = g.getClipBounds();
        if(hole.pixShape.intersects(bounds)){
            hole.render(g);
        }
        for(Wall wall : walls){
            
        }
    }
    public Ball getBall(){
        return ball;
    }
}
