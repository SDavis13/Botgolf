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
            if(state == GameState.READY)
                if(ball.contains(e.getX(),e.getY()) && e.getButton() == 0){
                    state = GameState.GRAB;
                }
        }
        public void mouseReleased(MouseEvent e){
            if(state == GameState.GRAB && e.getButton() == 1){
                /*state = GameState.LAUNCH;
                float xDif = body.getPosition().x - Main.toPosX(mouseX);
                float yDif = body.getPosition().y - Main.toPosY(mouseY);
                Vec2 temp = 
                        new Vec2( xDif * IMPULSE_SCALE,
                                yDif * IMPULSE_SCALE );
                body.applyLinearImpulse(temp,body.getPosition());*/
            }
        }
    }
    
    //Convert a JBox2D x coordinate to a Swing pixel x coordinate
    public static float toPixelPosX(float posX) {
        float x = Main.WIDTH*posX / 100.0f;
        return x;
    }

    //Convert a Swing pixel x coordinate to a JBox2D x coordinate
    public static float toPosX(float pixX) {
        float x = (pixX*100.0f*1.0f)/Main.WIDTH;
        return x;
    }

    //Convert a JBox2D y coordinate to a Swing pixel y coordinate
    public static float toPixelPosY(float posY) {
        float y = Main.HEIGHT - (1.0f*Main.HEIGHT) * posY / 100.0f;
        return y;
    }

    //Convert a Swing pixel y coordinate to a JBox2D y coordinate
    public static float toPosY(float pixY) {
        float y = 100.0f - ((pixY * 100*1.0f) /Main.HEIGHT) ;
        return y;
    }

    //Convert a JBox2D width to pixel width
    public static float toPixelWidth(float width) {
        return Main.WIDTH*width / 100.0f;
    }

    //Convert a JBox2D height to pixel height
    public static float toPixelHeight(float height) {
        return Main.HEIGHT*height/100.0f;
    }
        
}
