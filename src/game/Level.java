package game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import org.jbox2d.dynamics.*;

public class Level {
    int id;
    String name;
    Ball ball;
    ArrayList<Mob> mobs;
    ArrayList<Wall> walls;
    Hole hole;
    SoundRepository sounds;
    World world;
    
    Level(World world){
        this.world = world;
    }
    void moveMobs(){
        
    }
    void step(){
        
    }
    void render(Graphics2D g){
        
    }
}