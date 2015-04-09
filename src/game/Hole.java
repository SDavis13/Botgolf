package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import physicsPrototype1.Main;

public class Hole extends Entity{
    public CircleShape shape;
    public Ellipse2D.Float pixShape;
    
    Image holeImage = Toolkit.getDefaultToolkit().getImage(Consts.IMG_HOLE);
    
    Hole(World world, BodyDef bd, FixtureDef fd, CircleShape shape, Vec2 position){
        this.world = world;
        
        int pixRad = holeImage.getWidth(null) / 2;
        //define shape of the body.
        shape = new CircleShape();
        shape.m_radius = Utils.toPhysLength(pixRad);
        
        //create the body and add fixture to it
        body = world.createBody(bd);
        body.createFixture(fd).setUserData(this);
        
        pixX = Main.toPixelPosX(body.getPosition().x);
        pixY = Main.toPixelPosY(body.getPosition().y);
        pixShape = new Ellipse2D.Float((pixX - pixRad), (pixY - pixRad), pixRad*2, pixRad*2);
    }

    @Override
    public void hit(Entity otherEntity) {
        if(otherEntity instanceof Ball){
            SoundRepository.playSound(Consts.SND_SCORE);
            //TODO Need to figure out how to signal the game controller
        }
    }

    @Override
    public void render(Graphics g1) {
        // TODO Auto-generated method stub
        
    }

}
