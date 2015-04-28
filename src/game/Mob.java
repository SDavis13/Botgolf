package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * This is the mob class that defines the robots as far as rendering and physics.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public class Mob extends Entity{
    public final static int DEFAULT_HEALTH = 2;
    static final float BLAST_POWER = 1000;
    final int imgXOffset;
    final int imgYOffset;
    PolygonShape shape;
    Polygon pixShape;
    Rectangle rectangle;
    Image[] mobGraphic;
    Grid grid;
    int health = DEFAULT_HEALTH;
    int origHealth = 0;  // added by CTS
    int numOfSpacesMobCanMove;
    int ticksTillRemoval = 60;
    boolean dead = false;


    /**
     * Constructor for Mob object.
     * 
     * @param world			Object of World passed
     * @param bd			Object of body definition passed
     * @param fd			Object of fixture definition passed
     * @param shape			Object of Polygon shape passed
     * @param gridScale		Float gridscale passed
     */
    Mob(World world, BodyDef bd, FixtureDef fd, PolygonShape shape, Grid grid){
        mobGraphic = new Image[1];
        try {
            mobGraphic[0] = ImageIO.read(new File(Consts.IMG_GENROBO)).getScaledInstance(80, 98, Image.SCALE_SMOOTH);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        this.world = world;
        this.shape = shape;
        this.grid = grid;
        
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

        imgXOffset = mobGraphic[0].getWidth(null)/2;
        imgYOffset = mobGraphic[0].getHeight(null)-rectangle.height/2;
    }

    /**
     * SetHealth method is used for setting health on robot.
     * 
     * @param health	Integer health passed
     */
    public void setHealth(int health){
    	this.health = health;
    	origHealth = health; //added by CTS
    }

    /**
     * Hit method used to decrement health when hit by ball object.
     * and play hit sounds for robot.
     * 
     * @param otherEntity	Object type of Entity passed
     */
    @Override
    public void hit(Entity otherEntity) {
        health--;
        
        if (health >= 0) {
        	if(otherEntity instanceof Ball){
        		SoundRepository.playSound(Consts.SOUNDS[Consts.SNDIDX_ROBOTHIT]);
        	}
        }
        
        if (health <= 0) {
        	dead = true;
        }
    }

    public boolean move(){
        Obstruction[] obs = grid.vnNeighborhood(body.getPosition());
        int idxMoveTo = -1;
        ArrayList<Integer> idxs = new ArrayList<Integer>();
        for(int i = 0; i < obs.length; i++){
            if(obs[i] != null){
                if(obs[i].freeSpace()){
                    if(obs[i].hasItem(Obstruction.KILL)){
                        idxMoveTo = i;
                        break;
                    }else idxs.add(i);
                }
            }else idxs.add(i);
        }
        Vec2[] destinations = grid.vnNeighborLocs(body.getPosition());
        SoundRepository.playSound(Consts.SOUNDS[Consts.SNDIDX_ROBOTMOVE]);
        if(idxMoveTo != -1){
            moveTo(destinations[idxMoveTo]);
            return true;
        }
        if(!idxs.isEmpty()){
            Collections.shuffle(idxs);
            moveTo(destinations[idxs.get(0)]);
            return true;
        }else{
            return true;
        }
    }
    
    protected void moveTo(Vec2 position){
        grid.removeObstruction(body.getPosition(), Obstruction.DYNAMIC);
        grid.addObstruction(position, Obstruction.DYNAMIC);
        body.setTransform(position, 0);
    }
   
    // added by CTS
    public int getOrigHealthAmount(){
    	return origHealth;
    }
    
    /**
     * Render method is used to draw the image of the mob.
     * 
     * @param g		Object type of Graphics2D passed
     */
    @Override
    public void render(Graphics2D g) {
        Image image;
        if(health >= mobGraphic.length){
            image = mobGraphic[mobGraphic.length - 1];
        }else if(health < 0){
            image = mobGraphic[0];
        }else image = mobGraphic[health];
        g.drawImage(image, (int)(pixX+.5f) - imgXOffset, (int)(pixY+.5f) - imgYOffset, null);
        //g.draw(pixShape);
    }

    /**
     * PixUpdate method used to update pixel location of mob.
     */
    @Override
    public void pixUpdate() {
        float newPixX = Utils.toPixX(body.getPosition().x);
        float newPixY = Utils.toPixY(body.getPosition().y);
        pixShape.translate((int)(newPixX - pixX), (int)(newPixY - pixY));
        rectangle = pixShape.getBounds();
        pixX = newPixX;
        pixY = newPixY;
        if(dead){
            ticksTillRemoval--;
            if(ticksTillRemoval < 0){
                remove = true;
            }
        }
    }


}
