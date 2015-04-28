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
 * @version     2015-04-24
 * @since       2015-04-24
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
    final static String LOSESTRING = "You lose.";
    final static String PAUSESTRING = "Paused";
    final static Color TEXTCOLOR = new Color(0x659e0a);
    int id;
    String name;
    String music;
    Ball ball;
    ArrayList<Mob> mobs;
    ArrayList<Mob> mDelete;
    IterFlag mobIterFlag = new IterFlag();
    ArrayList<Wall> walls;
    ArrayList<Wall> wDelete;
    Hole hole;
    World world;
    Grid grid;
    boolean pause;
    int pointsForWin;
    int pointsPerShot;
    public int scoreCount = 0;  // added by CTS
    public boolean winTalliedToScore = false;  // added by CTS
    
    /**
     * Constructor for Level
     * 
     * @param world		Object type of World passed
     * @param specs		Object type of GameSpec passed
     * @param wallList	Object type of ArrayList passed
     * @param mobList	Object type of ArrayList passed
     * @param theBall	Object type of Ball passed
     * @param theHole	Object type of Hole passed
     */
    Level(World world, GameSpec specs, ArrayList<Wall> wallList, ArrayList<Mob> mobList, Ball theBall, Hole theHole, Grid theGrid, String musicName){
        this.world = world;
        name = specs.levelName;
        id = specs.levelNum;
        walls = wallList;
        mobs = mobList;
        ball = theBall;
        hole = theHole;
        grid = theGrid;
        pause = false;
        mDelete = new ArrayList<Mob>();
        wDelete = new ArrayList<Wall>();
        music = musicName;

        mobIterFlag.access = true;
    }

    /**
     * Tells all the mobs to move. As of the current version, it will only return true because the mobs move all at once.
     * 
     * @return true if mobs are done moving, false if they are in the middle.
     */
    boolean moveMobs(){
        for(Mob mob : mobs){
            mob.move();
        }
        return false;
    }

    /**
     * Step method to update pixel location of world, ball, hole.
     */
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
                    // added by CTS
                    scoreCount = scoreCount + (mob.getOrigHealthAmount() * 1000); 
                }
                mDelete.clear();
            }
        }
        for(Wall wall : walls){
            wall.pixUpdate();
        }
        //added by CTS
        if (hole.win) {
            if(!winTalliedToScore){
                winTalliedToScore = true;
                scoreCount += pointsForWin;
                tallyShots();
            }
        }
    }

    /**
     * Render method to render the wall, mob, ball and hole in world.
     * 
     * @param g		Object type of Graphics2D passed.
     */
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
        
        g.setColor(new Color(0x80000030, true));
        g.fillRect(0, 0, g.getClipBounds().width, 48);
        
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
        g.setColor(TEXTCOLOR);
        g.drawString("Shots Left: " + ball.shotCount, 10, 40);
        g.drawString("Score: " + scoreCount, 400, 40);  //added by CTS

        if(pause)
        {
        	g.drawString(PAUSESTRING, 340, 250);
        }
        if(hole.win){             
            g.drawString(WINSTRING, 300, 350);
        }
        
        if(ball.shotCount == 0 && hole.win == false)
        {        	
            g.drawString(LOSESTRING, 300, 350);   
            
        }
    }
    
    
    /**
     * Pause method to indicate pause is true.
     */
    public void pause()
    {
    	pause = true;
    }
    
    /**
     * Unpause method to indicate pause is false.
     */
    public void unPause()
    {
    	pause = false;
    }

    /**
     * getBall method used to return the Ball object.
     * 
     * @return		Returns a Ball object
     */
    public Ball getBall(){
        return ball;
    }

    /**
     * getHole method used to return the Hole object.
     * 
     * @return		Returns a Hole object
     */
    public Hole getHole()
    {
        return hole;
    }
    
    public void tallyShots(){
       for(; ball.shotCount > 0; ball.shotCount--){
            scoreCount += pointsPerShot;
        }
    }
    
    /**
     * GetGrid method used to return a Grid object.
     * 
     * @return		Returns a Grid object
     */
    public Grid getGrid(){
        return grid;
    }

	/**
	 * Class type of IterFlag
	 */
    class IterFlag{
		boolean access;
	}
}
