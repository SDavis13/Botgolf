package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

public class Mob extends Entity{
    public final static int DEFAULT_HEALTH = 2;
    final int imgXOffset;
    final int imgYOffset;
    PolygonShape shape;
	Polygon pixShape;
	Rectangle rectangle;
	Image mobGraphic;
    int health = DEFAULT_HEALTH;
    int numOfSpacesMobCanMove;
    
    Mob(World world, BodyDef bd, FixtureDef fd, PolygonShape shape, float gridScale){
        
        try {
            mobGraphic = ImageIO.read(new File(Consts.IMG_GENROBO)).getScaledInstance(80, 98, Image.SCALE_SMOOTH);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        this.world = world;
        this.shape = shape;

        fd.shape = shape;
        
        body = world.createBody(bd);
        fixture = body.createFixture(fd);
        fixture.setUserData(this);
        
        int[] xAry = new int[shape.m_count];
        int[] yAry = new int[shape.m_count];
        
        for(int i = 0; i < xAry.length; i++){
            xAry[i] = (int)(Utils.toPixX(shape.m_vertices[i].x + bd.position.x) + .5f);
            yAry[i] = (int)(Utils.toPixY(shape.m_vertices[i].y + bd.position.y) + .5f);
        }
        pixShape = new Polygon(xAry, yAry, shape.m_count);
        rectangle = pixShape.getBounds();
        pixX = Utils.toPixX(body.getPosition().x);
        pixY = Utils.toPixY(body.getPosition().y);
        
        imgXOffset = mobGraphic.getWidth(null)/2;
        imgYOffset = mobGraphic.getHeight(null)-rectangle.height/2;
    }
    
    public void setHealth(int health){
        this.health = health;
    }

    @Override
    public void hit(Entity otherEntity) {
        health--;
        if (health <= 0)
        {
        	remove = true;
        }
        
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(mobGraphic, (int)(pixX+.5f) - imgXOffset, (int)(pixY+.5f) - imgYOffset, null);
        g.draw(pixShape);
    }

    @Override
    public void pixUpdate() {
        float newPixX = Utils.toPixX(body.getPosition().x);
        float newPixY = Utils.toPixY(body.getPosition().y);
        pixShape.translate((int)(newPixX - pixX), (int)(newPixY - pixY));
        rectangle = pixShape.getBounds();
        pixX = newPixX;
        pixY = newPixY;
    }
    
    
}
