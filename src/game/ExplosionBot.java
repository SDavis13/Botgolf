package game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * This class represents the explosion robot settings along with
 * extending the Mob class.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     1.0
 * @since       2015-04-21
 * @extends		Mob
 */
public class ExplosionBot extends Mob {		

    static final float BLAST_POWER = 1000;

    ExplosionBot(World world, BodyDef bd, FixtureDef fd, PolygonShape shape, float gridScale)
    {
        super(world, bd, fd, shape, gridScale);

        try {
            mobGraphic = ImageIO.read(
                    new File(Consts.IMG_MADROBO)).getScaledInstance(80, 98, Image.SCALE_SMOOTH);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * this is the hit method that removes health and robot when 
     * health is down to zero.
     */
    @Override
    public void hit(Entity otherEntity) {
        health--;
        if (health <= 0)
        {
            if(otherEntity instanceof Ball)
            {
                Utils.applyBlastImpulse(otherEntity.body, body.getPosition(), 
                        otherEntity.body.getPosition(), BLAST_POWER);
            }
            remove = true;
        }

    }
}
