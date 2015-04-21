package game;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class StandardBot extends Mob {

	StandardBot(World world, BodyDef bd, FixtureDef fd, PolygonShape shape,
			float gridScale) {
		super(world, bd, fd, shape, gridScale);
		// TODO Auto-generated constructor stub
	}
	
}
