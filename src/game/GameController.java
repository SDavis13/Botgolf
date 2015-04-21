package game;

import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.event.MouseInputAdapter;

import org.jbox2d.common.Vec2;

public class GameController {
    GamePage view;
    Level curLevel;
    Ball ball;
    Timer tickRunner;
    GameState state;
    GameState tempState;
    LevelFactory curFactory = new CreateLevel(); //TODO This is hardcoded and will need to be changed.
    
    public GameController(GamePage page){
        state = GameState.INACTIVE;
        tempState = GameState.READY;
        view = page;
        MouseInput mouseInput = new MouseInput();
        view.addMouseListener(mouseInput);
        view.addMouseMotionListener(mouseInput);
        view.addKeyListener(new KeyboardInput());
    }
    public void loadLevel(GameSpec levelSpec){
        if(levelSpec.newGame)
                curLevel = curFactory.createLevel(levelSpec);
        ball = curLevel.getBall();
        view.setLevel(curLevel);
    }
    public void startGame(){
        state = tempState;
        tickRunner = new Timer();
        tickRunner.scheduleAtFixedRate(new PhysicsLoop(), 25, Consts.TIMERTICK);
    }
    public void pauseGame(){
        tempState = state;
        tickRunner.cancel();
        state = GameState.PAUSED;
    }
    private class PhysicsLoop extends TimerTask{
        boolean launched = false;
        
        public void run(){
            curLevel.step();
            switch(state){
                case LAUNCH:
                    if(ball.body.isAwake()){
                        launched = true;
                    }else{
                        launched = false;
                        state = GameState.MOBTURN;
                    }
                    break;
                case MOBTURN:
                    curLevel.moveMobs();
                    break;
            }
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
        float xOrigin, yOrigin, oldXOffset, oldYOffset;
        @Override
        public void mouseDragged(MouseEvent e){
            if(state == GameState.READY){
                if(ball.contains(e.getX(),e.getY()) && e.getButton() == 0){
                    state = GameState.GRAB;
                    ball.setGrabbed();
                }else{
                    if(xOrigin == 0 && yOrigin == 0){
                        xOrigin = e.getX();
                        yOrigin = e.getY();
                        oldXOffset = Consts.pxOffset;
                        oldYOffset = Consts.pyOffset;
                    }
                    Consts.pxOffset = oldXOffset + (xOrigin - e.getX());
                    Consts.pyOffset = oldYOffset + (yOrigin - e.getY());
                }
            }
        }
        @Override
        public void mouseReleased(MouseEvent e){
            xOrigin = 0;
            yOrigin = 0;
            if(state == GameState.GRAB && e.getButton() == 1){
                state = GameState.LAUNCH;
                ball.launch(Utils.toPhysX(e.getX()), Utils.toPhysY(e.getY()));
            }
        }
    }
        
}
