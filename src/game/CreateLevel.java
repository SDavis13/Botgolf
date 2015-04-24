package game;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * This is where the level is created.  The walls, hole, and robots are 
 * defined in this.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     1.0
 * @since       2015-04-21
 * @implements 	LevelFactory
 */
public class CreateLevel implements LevelFactory {
    /**
     * Value between 0 and 1. Higher = bouncier
     */
    public static final float BALL_BOUNCE = 0.5f;
    /**
     * Value between 0 and 1. Lower = slipperier
     */
    public static final float BALL_FRICTION = 0.1f;
    /**
     * Mass / Area
     */
    public static final float BALL_DENSITY = 0.15f; //TODO Should probably make this a function of Consts.SCALE

    public static final float WALL_FRICTION = 0.1f;
    public static final float MOB_FRICTION = 0.1f;
    public static final float GRID_SIZE = 4f;
    public static final float HALF_MOB_SIZE = 2f;

    public static final Vec2 BALL_CENTER = new Vec2(4,76);
    public static final Vec2 HOLE_POSITION = new Vec2(4,96);


    /**
     * createLevel method creates a level to the specs defined.
     * 
     * @param	specs	specs that are passed to level in order to create
     */
    @Override
    public Level createLevel(GameSpec specs){

        World world = new World(new Vec2(0.0f, 0.0f));

        ArrayList<Wall> wallList = new ArrayList<Wall>();
        ArrayList<Mob> mobList = new ArrayList<Mob>();

        Hole hole = createHole(world);
        Ball ball = createBall(world);
        ball.setNumHits(4);

        StandardBot stanMob;
        ExplosionBot expMob;
        expMob = createExpMob(world, 20,76,HALF_MOB_SIZE,HALF_MOB_SIZE);
        expMob.setHealth(1);
        mobList.add(expMob);
        stanMob = createStanMob(world, 8,88,HALF_MOB_SIZE,HALF_MOB_SIZE);
        stanMob.setHealth(2);
        mobList.add(stanMob);
        expMob = createExpMob(world, 32,92,HALF_MOB_SIZE,HALF_MOB_SIZE);
        expMob.setHealth(3);
        mobList.add(expMob);

        //Exterior Walls
        wallList.add(createWall(world, 1,84,1,14,0));//West
        wallList.add(createWall(world, 20,99,20,1,0));//North
        wallList.add(createWall(world, 39,84,1,14,0));//East
        wallList.add(createWall(world, 20,69,20,1,0));//South
        //Interior Walls
        wallList.add(createWall(world, 8,84,6,2,0));//Horizontal
        wallList.add(createWall(world, 24,88,6,2,(float)Math.toRadians(-45)));//Diagonal

        Level theLevel = new Level(world, specs, wallList, mobList, ball, hole);
        return theLevel;
    }

    /**
     * createBall method used to define variables of the Ball.
     * 
     * @param 		theWorld	world that ball is created in
     * @return					the ball object created
     */
    private Ball createBall(World theWorld){
        Ball ball;
        FixtureDef ballFix = new FixtureDef();
        ballFix.density = BALL_DENSITY;
        ballFix.friction = BALL_FRICTION;
        ballFix.restitution = BALL_BOUNCE;
        BodyDef ballBody = new BodyDef();
        ballBody.type = BodyType.DYNAMIC;
        ballBody.position = BALL_CENTER;
        ballBody.linearDamping = Consts.rollingFriction;

        ball = new Ball(theWorld,ballBody,ballFix);
        return ball;
    }

    /**
     * createHole method used to create the physics for the Hole.
     * 
     * @param 	theWorld	world where hole is created in
     * @return				the Hole object that is created
     */
    private Hole createHole(World theWorld)
    {
        FixtureDef fd = new FixtureDef();
        fd.density = 0;
        fd.friction = 0;
        fd.isSensor = true;

        BodyDef bd = new BodyDef();
        bd.position = HOLE_POSITION;
        bd.type = BodyType.STATIC;

        return new Hole(theWorld, bd, fd);
    }

    /**
     * createWall method for creating a wall in the world.
     * 
     * @param theWorld		the world where wall is created
     * @param posX			x coordinate of where wall starts
     * @param posY			y coordinate of where wall starts
     * @param halfWidth		half the width of wall
     * @param halfHeight	half the height of the wall
     * @param rotation		rotation of the position for wall
     * @return				returns a wall object
     */
    private Wall createWall(World theWorld, float posX, float posY, float halfWidth, float halfHeight, float rotation){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(halfWidth, halfHeight, new Vec2(0,0), rotation);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.friction = WALL_FRICTION;

        BodyDef bd = new BodyDef();
        bd.position.set(posX, posY);
        bd.type = BodyType.STATIC;

        return new Wall(theWorld, bd, fd, shape);
    }

    /**
     * createMob is the method used to create the robot in the world.
     * 
     * @param theWorld		the world where the robot is created
     * @param posX			x coordinate of the robot position
     * @param posY			y coordinate of the robot position
     * @param halfWidth		half the width of the mob
     * @param halfHeight	half the height of the mob
     * @return				returns a Mob object to the world
     */
    private Mob createMob(World theWorld, float posX, float posY, float halfWidth, float halfHeight){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(halfWidth, halfHeight);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.friction = MOB_FRICTION;

        BodyDef bd = new BodyDef();
        bd.position.set(posX, posY);
        bd.type = BodyType.STATIC;

        return new Mob(theWorld, bd, fd, shape, GRID_SIZE);
    }

    /**
     * createStanMob is creating the position of standard robot in world.
     * 
     * @param theWorld		the world where standard robot is defined
     * @param posX			x coordinate of the standard robot
     * @param posY			y coordinate of the standard robot
     * @param halfWidth		half width of the standard robot
     * @param halfHeight	half height of the standard robot
     * @return				returns a standard robot object
     */
    private StandardBot createStanMob(World theWorld, float posX, float posY, float halfWidth, float halfHeight){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(halfWidth, halfHeight);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.friction = MOB_FRICTION;

        BodyDef bd = new BodyDef();
        bd.position.set(posX, posY);
        bd.type = BodyType.STATIC;

        return new StandardBot(theWorld, bd, fd, shape, GRID_SIZE);
    }

    /**
     * createExpMob is creating the position of explosion robot in world.
     * 
     * @param theWorld		the world where explosion robot is defined
     * @param posX			x coordinate of the explosion robot
     * @param posY			y coordinate of the explosion robot
     * @param halfWidth		half width of the explosion robot
     * @param halfHeight	half height of the explosion robot
     * @return				returns a explosion robot object
     */
    private ExplosionBot createExpMob(World theWorld, float posX, float posY, float halfWidth, float halfHeight){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(halfWidth, halfHeight);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.friction = MOB_FRICTION;

        BodyDef bd = new BodyDef();
        bd.position.set(posX, posY);
        bd.type = BodyType.STATIC;

        return new ExplosionBot(theWorld, bd, fd, shape, GRID_SIZE);
    }
}
