package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.event.MouseInputAdapter;

public class GamePage extends View{
    static GamePage page = new GamePage(Consts.GAME, new GameController());
    Level level;
    GameSpec spec;
    GameController control;
    RenderLoop loop;
    ScheduledExecutorService tickRunner;

    public static GamePage getInstance(){
        return page;
    }
    
    protected GamePage(String name, GameController control) {
        super(name);
        this.control = control;
        
    }

    protected void paintComponent(Graphics g1){
        
    }
    
    public void pause(){
        frame.switchView(Consts.PAUSE, level.name);
            //TODO give level a getName() method
            //TODO Make all instance vars private, except for the obvious and GameSpec's stuff
    }
    
    public void activate(Object message){
        assert (message instanceof GameSpec);
        spec = (GameSpec) message;
    }
    
    public void actionPerformed(ActionEvent e){
        
    }
    
    private class RenderLoop implements Runnable{
        public void run(){
            
        }
    }

}
