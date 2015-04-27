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
 * This class represents the standard robot settings along with
 * extending the Mob class.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 * @extends		Mob
 */
public class StandardBot extends Mob {		

    /**
     * Constructor for Standard robot.
     * 
     * @param world			Object of world passed
     * @param bd			Object of Body Definition passed
     * @param fd			Object of Fixture Definition passed
     * @param shape			Object of Polygon Shape passed
     * @param gridScale		Float of grid scale is passed
     */
	StandardBot(World world, BodyDef bd, FixtureDef fd, PolygonShape shape, Grid grid)
    {
        super(world, bd, fd, shape, grid);
        mobGraphic = new Image[3];
        try {
            mobGraphic[0] = ImageIO.read(
                    new File(Consts.IMG_STANDROBO0)).getScaledInstance(80, 98, Image.SCALE_SMOOTH);
            mobGraphic[1] = ImageIO.read(
                    new File(Consts.IMG_STANDROBO1)).getScaledInstance(80, 98, Image.SCALE_SMOOTH);
            mobGraphic[2] = ImageIO.read(
                    new File(Consts.IMG_STANDROBO2)).getScaledInstance(80, 98, Image.SCALE_SMOOTH);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }	
}
