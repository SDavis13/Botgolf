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
        ball.pixUpdate();
        hole.pixUpdate();
        for(Mob mob : mobs){
            if(mob.remove) world.destroyBody(mob.body);
            mob.pixUpdate();
        }
        for(Wall wall : walls){
            wall.pixUpdate();
        }
    }
    void render(Graphics2D g){
        Rectangle bounds = g.getClipBounds();
        for(Wall wall : walls){
            if(wall.pixShape.intersects(bounds)){
                wall.render(g);
            }
        }
        for(Mob mob : mobs){
            if(mob.pixShape.intersects(bounds)){
                mob.render(g);
            }
        }
        if(hole.pixShape.intersects(bounds)){
            hole.render(g);
        }
        ball.render(g);
    }
    public Ball getBall(){
        return ball;
    }
}
