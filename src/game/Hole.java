package game;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class Hole extends Entity{
    public CircleShape shape;
    public Ellipse2D.Float pixShape;
    public boolean win = false;
    
    BufferedImage holeImage;
    float pixRad;
    
    Hole(World world, BodyDef bd, FixtureDef fd){

    	try {
            holeImage = ImageIO.read(new File(Consts.IMG_HOLE));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        this.world = world;
        pixX = 0;
        pixY = 0;
        
        pixRad = holeImage.getWidth(null) / 2f;
        shape = new CircleShape();
        shape.m_radius = Utils.toPhysLength(pixRad);
             
        fd.shape = shape;
        
        body = world.createBody(bd);
        body.createFixture(fd).setUserData(this);
        
        pixX = Utils.toPixX(body.getPosition().x);
        pixY = Utils.toPixY(body.getPosition().y);
        pixShape = new Ellipse2D.Float((pixX - pixRad), (pixY - pixRad), pixRad*2, pixRad*2);
    }

    @Override
    public void hit(Entity otherEntity) {
        if(otherEntity instanceof Ball){
            SoundRepository.playSound(Consts.SND_SCORE);
            win = true;            
        }
    }

    @Override
    public void render(Graphics2D g) 
    {
        g.drawImage(holeImage, (int)pixShape.x, (int)pixShape.y, null);
    }

    @Override
    public void pixUpdate() {
    	pixX = Utils.toPixX(body.getPosition().x);
        pixY = Utils.toPixY(body.getPosition().y);
        pixRad = (Utils.toPixLength(shape.m_radius));
        pixShape.x = pixX - pixRad;
        pixShape.y = pixY - pixRad;
    }

}
