package game;

import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.event.MouseInputAdapter;

import org.jbox2d.common.Vec2;

public class GameController {
    final static long TICKTIME = 1000/60;
    GamePage view;
    Level curLevel;
    Ball ball;
    Timer tickRunner;
    GameState state;
    GameState tempState;
    float pxOffset, pyOffset;
    
    public GameController(){
        state = GameState.INACTIVE;
        tempState = GameState.READY;
    }
    public void loadLevel(String levelName){
        
    }
    public void startGame(){
        state = tempState;
        tickRunner = new Timer();
        tickRunner.schedule(new PhysicsLoop(), 0, TICKTIME);
    }
    public void pauseGame(){
        tempState = state;
        tickRunner.cancel();
        state = GameState.PAUSED;
    }
    private class PhysicsLoop extends TimerTask{
        public void run(){
            curLevel.step();
        }
    }
    
    private class KeyboardInput extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int code = e.getKeyCode();
            if(code == Consts.pauseKey){
                pauseGame();
            }else if(code == Consts.pauseMenuKey){
                pauseGame();
                view.pause();
            }
            
        }
    }
    
    private class MouseInput extends MouseInputAdapter{
        @Override
        public void mouseDragged(MouseEvent e){
            if(state == GameState.READY)
                if(ball.contains(e.getX(),e.getY()) && e.getButton() == 0){
                    state = GameState.GRAB;
                    ball.setGrabbed();
                }
        }
        @Override
        public void mouseReleased(MouseEvent e){
            if(state == GameState.GRAB && e.getButton() == 1){
                state = GameState.LAUNCH;
                ball.launch(Utils.toPhysX(e.getX(),pxOffset), Utils.toPhysY(e.getY(),pyOffset));
            }
        }
    }
        
}
