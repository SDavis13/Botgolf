package game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class StandardBot extends Mob {		

    StandardBot(World world, BodyDef bd, FixtureDef fd, PolygonShape shape, float gridScale)
    {
        super(world, bd, fd, shape, gridScale);

        try {
            mobGraphic = ImageIO.read(new File(Consts.IMG_STANDROBO)).getScaledInstance(80, 98, Image.SCALE_SMOOTH);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }	
}
