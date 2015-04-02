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
    float pxOffset, pyOffset;
    
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
            if(state == GameState.READY)
                if(ball.contains(e.getX(),e.getY()) && e.getButton() == 0){
                    state = GameState.GRAB;
                }
        }
        public void mouseReleased(MouseEvent e){
            if(state == GameState.GRAB && e.getButton() == 1){
                state = GameState.LAUNCH;
                ball.launch(Utils.toPhysX(e.getX(),pxOffset), Utils.toPhysY(e.getY(),pyOffset));
            }
        }
    }
        
}
