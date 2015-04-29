package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.jbox2d.dynamics.World;

/**
 * This class represents a level in the game. It is a set of entities and the variables and methods necessary to work with them in the context of a level.
 * A level must have, at minimum, a ball, and a hole to shoot the ball into.
 * 
 * @authors Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version 2015-04-28
 * @since 2015-04-24
 */
public class Level {
    /**
     * Accuracy of JBox2D Velocity iterations.
     * Higher = more accuracy, less performance
     */
    final static int VELOCITY_ITERATIONS = 6;
    /**
     * Accuracy of JBox2D Position iterations.
     * Higher = more accuracy, less performance
     */
    final static int POSITION_ITERATIONS = 3;
    final static String WINSTRING = "You win!";
    final static String LOSESTRING = "You lose.";
    final static String PAUSESTRING = "Paused";
    final static Color TEXTCOLOR = new Color(0x659e0a);
    /**
     * For future use. Will be used if the level is in a campaign.
     */
    int id;
    /**
     * Name of the level. Not currently used.
     */
    String name;
    /**
     * The name of the music for this level.
     */
    String music;
    /**
     * The Ball.
     */
    Ball ball;
    /**
     * The list of Mobs in this level.
     */
    ArrayList<Mob> mobs;
    /**
     * The list of Mobs to remove from this level.
     */
    ArrayList<Mob> mDelete;
    /**
     * The IterFlag used to synchronize Mob removal.
     */
    IterFlag mobIterFlag;
    /**
     * The list of Walls in this level.
     */
    ArrayList<Wall> walls;
    /**
     * The list of Walls to remove from this level. Not currently used.
     */
    ArrayList<Wall> wDelete;
    /**
     * The Hole.
     */
    Hole hole;
    /**
     * The JBox2D World.
     */
    World world;
    /**
     * The Grid.
     */
    Grid grid;
    /**
     * Whether or not the game is paused.
     */
    boolean pause;
    /**
     * Number of points awarded when the player wins by sending the Ball into the Hole.
     */
    int pointsForWin;
    /**
     * Number of points awarded per shot left when the player wins.
     */
    int pointsPerShot;
    /**
     * The player's score.
     */
    public int scoreCount = 0;  // added by CTS
    /**
     * Whether the points for winning have already been tallied to the score.
     * Used to prevent tallying a win multiple times.
     */
    public boolean winTalliedToScore = false;  // added by CTS

    /**
     * Constructor for Level
     * 
     * @param world A JBox2D world.
     * @param specs The level specification.
     * @param wallList A list of Walls in the level
     * @param mobList A list of Mobs in the level
     * @param theBall The Ball
     * @param theHole The Hole
     * @param theGrid A grid for Mob navigation
     * @param musicName The name of the background music for this level
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

        mobIterFlag = new IterFlag(true);
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
     * Step method to update which tells the JBox2D World to step, then updates Entities.
     * 
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
     * Render method to render Entities as well as HUD items(e.g. score, win).
     * 
     * @param g A Graphics2D context to render to.
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
     * Pause method to indicate to the level that the game is paused.
     */
    public void pause()
    {
        pause = true;
    }

    /**
     * Unpause method to indicate to the level that the game is no longer paused.
     */
    public void unPause()
    {
        pause = false;
    }

    /**
     * Method to get the Ball object of this level.
     * 
     * @return the Ball
     */
    public Ball getBall(){
        return ball;
    }

    /**
     * Method to get the Hole object of this level.
     * 
     * @return the Hole
     */
    public Hole getHole()
    {
        return hole;
    }

    /**
     * Converts all remaining shots to score points per pointsPerShot.
     */
    public void tallyShots(){
        for(; ball.shotCount > 0; ball.shotCount--){
            scoreCount += pointsPerShot;
        }
    }

    /**
     * Method to get the Grid object of this level.
     * 
     * @return the Grid
     */
    public Grid getGrid(){
        return grid;
    }

    /**
     * The class Iterflag is a simple wrapper for a boolean, for the purpose of synchronization.
     */
    class IterFlag{
        boolean access;

        /**
         * Constructor.
         * @param access Whether or not something should be accessed at this time.
         */
        IterFlag(boolean access){
            this.access = access;
        }
    }
}
