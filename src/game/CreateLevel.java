package game;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * This is where the level is created. The walls, hole, and robots are 
 * defined in this. This class specifies a hardcoded level.
 * 
 * @author Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version 2015-04-28
 * @since 2015-04-24
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
    public static final int WINPOINTS = 3000;
    public static final int SHOTPOINTS = 500;

    private static Grid grid;


    /**
     * CreateLevel method creates a level to the specs defined.
     * 
     * @param specs Object type of game specs passed
     */
    @Override
    public Level createLevel(GameSpec specs){

        World world = new World(new Vec2(0.0f, 0.0f));

        ArrayList<Wall> wallList = new ArrayList<Wall>();
        ArrayList<Mob> mobList = new ArrayList<Mob>();

        Hole hole = createHole(world);
        Ball ball = createBall(world);
        ball.setNumShots(4);

        grid = new Grid(GRID_SIZE, GRID_SIZE/2, GRID_SIZE/2);

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

        for(int i = -1; i <= 9; i++){
            grid.addObstruction(i, 25, Obstruction.STATIC);
            grid.addObstruction(i, 17, Obstruction.STATIC);
        }
        for(int j = 18; j <= 24; j++){
            grid.addObstruction(-1, j, Obstruction.STATIC);
            grid.addObstruction(9, j, Obstruction.STATIC);
        }
        grid.addObstruction(0, 21, Obstruction.STATIC);
        grid.addObstruction(1, 21, Obstruction.STATIC);
        grid.addObstruction(2, 21, Obstruction.STATIC);
        grid.addObstruction(4, 23, Obstruction.STATIC);
        grid.addObstruction(5, 23, Obstruction.STATIC);
        grid.addObstruction(4, 22, Obstruction.STATIC);
        grid.addObstruction(5, 22, Obstruction.STATIC);
        grid.addObstruction(6, 22, Obstruction.STATIC);
        grid.addObstruction(5, 21, Obstruction.STATIC);
        grid.addObstruction(6, 21, Obstruction.STATIC);

        grid.addObstruction(hole.getPosition(), Obstruction.STATIC);
        for(Mob mob : mobList){
            grid.addObstruction(mob.getPosition(), Obstruction.DYNAMIC);
        }
        grid.addObstruction(ball.getPosition(), Obstruction.KILL);

        Level theLevel = new Level(world, specs, wallList, mobList, ball, hole, grid, Consts.SOUNDS[Consts.MUSIDX_TANDEMGRAVITIES]);
        theLevel.pointsForWin = WINPOINTS;
        theLevel.pointsPerShot = SHOTPOINTS;
        return theLevel;
    }

    /**
     * Creates a Ball object.
     * 
     * @param theWorld the World in which to create the Ball.
     * @return The Ball.
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
     * Creates a Hole object.
     * 
     * @param theWorld The World to put the Hole in.
     * @return A Hole.
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
     * Creates a Wall object.
     * 
     * @param theWorld The World to put the Wall in.
     * @param posX The center X coordinate of the Wall.
     * @param posY The center Y coordinate of the Wall.
     * @param halfWidth Half the width of the Wall.
     * @param halfHeight Half the height of the wall.
     * @param rotation Angle of the Wall in radians.
     * @return a Wall with the given specification.
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
     * Creates a Mob object.
     * 
     * @param theWorld the World to put the Mob in.
     * @param posX The center X coordinate of the Mob.
     * @param posY The center Y coordinate of the Mob.
     * @param halfWidth Half the width of the Mob.
     * @param halfHeight Half the height of the Mob.
     * @return a generic Mob with the given specification
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

        return new Mob(theWorld, bd, fd, shape, grid);
    }

    /**
     * Creates a standard robot object.
     * 
     * @param theWorld the World to put the Mob in.
     * @param posX The center X coordinate of the robot.
     * @param posY The center Y coordinate of the robot.
     * @param halfWidth Half the width of the robot.
     * @param halfHeight Half the height of the robot.
     * @return a StandardBot with the given specification
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

        return new StandardBot(theWorld, bd, fd, shape, grid);
    }

    /**
     * Creates an explosive robot object.
     * 
     * @param theWorld the World to put the robot in.
     * @param posX The center X coordinate of the robot.
     * @param posY The center Y coordinate of the robot.
     * @param halfWidth Half the width of the robot.
     * @param halfHeight Half the height of the robot.
     * @return an ExplosionBot with the given specification
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

        return new ExplosionBot(theWorld, bd, fd, shape, grid);
    }
}
