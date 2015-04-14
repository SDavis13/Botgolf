package game;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class CreateLevel implements LevelFactory {
	public Level createLevel(GameSpec specs){
		World world = new World(new Vec2(0.0f, 0.0f));
		Level theLevel = new Level(world, specs.levelName, specs.levelNum);		
		return theLevel;
	}
}
