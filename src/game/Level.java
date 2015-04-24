package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.jbox2d.dynamics.World;

/**
 * This class represents the level specifications including walls, hole
 * and robots.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     1.0
 * @since       2015-04-21
 */
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
    final static String WINSTRING = "You win!";
    final static String LOSESTRING = "WUT ARE U DOIN? U LOSE!";
    int id;
    String name;
    Ball ball;
    ArrayList<Mob> mobs;
    ArrayList<Mob> mDelete;
    IterFlag mobIterFlag = new IterFlag();
    ArrayList<Wall> walls;
    ArrayList<Wall> wDelete;
    Hole hole;
    World world;    

    Level(World world, GameSpec specs, ArrayList<Wall> wallList, ArrayList<Mob> mobList, Ball theBall, Hole theHole){
        this.world = world;
        name = specs.levelName;
        id = specs.levelNum;
        walls = wallList;
        mobs = mobList;
        ball = theBall;
        hole = theHole;
        mDelete = new ArrayList<Mob>();
        wDelete = new ArrayList<Wall>();

        mobIterFlag.access = true;
    }

    boolean moveMobs(){
        //TODO move the mobs
        return false;
    }

    void step(){
        world.step(Consts.TIMESTEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        ball.pixUpdate();
        hole.pixUpdate();
        for(Mob mob : mobs){
            mob.pixUpdate();
            if(mob.remove){
                mDelete.add(mob);
            }
        }
        synchronized(mobIterFlag){
            if(mobIterFlag.access){
                for(Mob mob : mDelete){
                    world.destroyBody(mob.body);
                    mobs.remove(mob);
                }
                mDelete.clear();
            }
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

        synchronized(mobIterFlag){
            mobIterFlag.access = false;
        }
        for(Mob mob : mobs){
            if(mob.pixShape.intersects(bounds)){
                mob.render(g);
            }
        }
        mobIterFlag.access = true;

        if(hole.pixShape.intersects(bounds)){
            hole.render(g);
        }
        ball.render(g);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
        g.setColor(Color.CYAN);
        g.drawString("Hits: " + ball.shotCount, 10, 40);

        if(hole.win){             
            g.setColor(Color.CYAN);
            g.drawString(WINSTRING, 400, 100);                                   
        }
        
        if(ball.shotCount == 0 && hole.win == false)
        {        	
            g.setColor(Color.CYAN);
            g.drawString(LOSESTRING, 50, 200);   
            
        }
    }

    /**
     * getBall method used to return the Ball object.
     * 
     * @return		returns a Ball object
     */
    public Ball getBall(){
        return ball;
    }

    /**
     * getHole method used to return the Hole object.
     * 
     * @return		returns a Hole object
     */
    public Hole getHole()
    {
        return hole;
    }

    	class IterFlag{
    		boolean access;
    	}
}
