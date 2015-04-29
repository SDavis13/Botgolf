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
 * This class represents a robot which explodes when it dies.
 * 
 * @authors Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version 2015-04-28
 * @since 2015-04-24
 * @extends Mob
 */
public class ExplosionBot extends Mob {

    static final float BLAST_POWER = 1000;

    /**
     * {@inheritDoc}
     */
    ExplosionBot(World world, BodyDef bd, FixtureDef fd, PolygonShape shape, Grid grid)
    {
        super(world, bd, fd, shape, grid);
        ticksTillRemoval = 20;
        mobGraphic = new Image[4];
        try {
            mobGraphic[0] = ImageIO.read(
                    new File(Consts.IMG_MADROBO0)).getScaledInstance(80, 98, Image.SCALE_SMOOTH);
            mobGraphic[1] = ImageIO.read(
                    new File(Consts.IMG_MADROBO1)).getScaledInstance(80, 98, Image.SCALE_SMOOTH);
            mobGraphic[2] = ImageIO.read(
                    new File(Consts.IMG_MADROBO2)).getScaledInstance(80, 98, Image.SCALE_SMOOTH);
            mobGraphic[3] = ImageIO.read(
                    new File(Consts.IMG_MADROBO3)).getScaledInstance(80, 98, Image.SCALE_SMOOTH);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * this is the hit method that removes health and robot when 
     * health is down to zero.
     * 
     * @param otherEntity    Object type of Entity passed
     */
    @Override
    public void hit(Entity otherEntity) {
        health--;

        if (health > 0) {
            if(otherEntity instanceof Ball){
                SoundRepository.playSound(Consts.SOUNDS[Consts.SNDIDX_ROBOTHIT]);
            }
        }

        if (health <= 0) {
            if(otherEntity instanceof Ball) {
                SoundRepository.playSound(Consts.SOUNDS[Consts.SNDIDX_ROBOTBOOM]);

                Utils.applyBlastImpulse(otherEntity.body, body.getPosition(), 
                        otherEntity.body.getPosition(), BLAST_POWER);
            }
            dead = true;
        }
    }
}
