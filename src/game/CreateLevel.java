package game;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

public class CreateLevel implements LevelFactory {
	public Level createLevel(GameSpec specs){
					
		World world = new World(new Vec2(0.0f, 0.0f));		
				
		ArrayList<Wall> wallList = new ArrayList<Wall>();
		ArrayList<Mob> mobs;
				
		PolygonShape polyShape = new PolygonShape();		
		
		BodyDef body = new BodyDef();
		body.type = BodyType.STATIC;
		body.position.set(2f, 2f);
		
		FixtureDef fixture = new FixtureDef();
		fixture.shape = polyShape;
		fixture.density = 1.0f;
		fixture.friction = 0.1f;				
		
		Wall wall1 = new Wall(world, body, fixture, polyShape);
		wallList.add(wall1);
		body.position.set(4f, 4f);
		Wall wall2 = new Wall(world, body, fixture, polyShape);
		wallList.add(wall2);
		body.position.set(6f, 6f);		
		Wall wall3 = new Wall(world, body, fixture, polyShape);
		wallList.add(wall3);
		body.position.set(8f, 8f);		
		Wall wall4 = new Wall(world, body, fixture, polyShape);
		wallList.add(wall4);
		
		Level theLevel = new Level(world, specs.levelName, specs.levelNum, wallList);
		return theLevel;
	}
}
