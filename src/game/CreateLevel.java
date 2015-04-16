package game;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

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
    public static final float MOB_SIZE = 4f;
    
    public static final Vec2 BALL_CENTER = new Vec2(4,76);
    public static final Vec2 HOLE_POSITION = new Vec2(4,96);
    
    
	public Level createLevel(GameSpec specs){
					
		World world = new World(new Vec2(0.0f, 0.0f));
        
        ArrayList<Wall> wallList = new ArrayList<Wall>();
        ArrayList<Mob> mobList = new ArrayList<Mob>();
        
        Hole hole = createHole(world);
        Ball ball = createBall(world);
        
        Mob mob;
        mob = createMob(world, 20,76,MOB_SIZE,MOB_SIZE);
        mob.setHealth(1);
        mobList.add(mob);
        mob = createMob(world, 8,88,MOB_SIZE,MOB_SIZE);
        mob.setHealth(3);
        mobList.add(mob);
        mob = createMob(world, 32,92,MOB_SIZE,MOB_SIZE);
        mob.setHealth(2);
        mobList.add(mob);
        
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
	
	private Ball createBall(World theWorld){
	    Ball ball;
        FixtureDef ballFix = new FixtureDef();
        ballFix.density = BALL_DENSITY;
        ballFix.friction = BALL_FRICTION;
        ballFix.restitution = BALL_BOUNCE;
        BodyDef ballBody = new BodyDef(); 
        ballBody.position = BALL_CENTER;
        ballBody.linearDamping = Consts.rollingFriction;
        
        ball = new Ball(theWorld,ballBody,ballFix);
        return ball;
	}
	
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
}
