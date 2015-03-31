package game;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.event.MouseInputAdapter;

import org.jbox2d.common.Vec2;

import physicsPrototype1.Main;

public class GameController {
    GamePage view;
    Level curLevel;
    Ball ball;
    ScheduledExecutorService tickRunner;
    GameState state;
    
    public GameController(){
        
    }
    public void loadLevel(String levelName){
        
    }
    public void startGame(){
        
    }
    public void pauseGame(){
        
    }
    private class PhysicsLoop implements Runnable{
        public void run(){
            curLevel.step();
        }
    }
    
    private class KeyboardInput extends KeyAdapter{
        
    }
    
    private class MouseInput extends MouseInputAdapter{
        public void mouseDragged(MouseEvent e){
            /*mouseX = e.getX();
            mouseY = e.getY();
            if(pixCircle.contains(mouseX,mouseY) && e.getButton() == 0){
                grabbed = true;
            }*/
        }
        public void mouseReleased(MouseEvent e){
            /*mouseX = e.getX();
            mouseY = e.getY();
            if(grabbed && e.getButton() == 1){
                grabbed = false;
                float xDif = body.getPosition().x - Main.toPosX(mouseX);
                float yDif = body.getPosition().y - Main.toPosY(mouseY);
                Vec2 temp = 
                        new Vec2( xDif * IMPULSE_SCALE,
                                yDif * IMPULSE_SCALE );
                body.applyLinearImpulse(temp,body.getPosition());
            }*/
        }
    }
        
}
