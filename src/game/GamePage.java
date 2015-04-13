package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

public class GamePage extends View{
    static GamePage page = new GamePage(Consts.GAME, new GameController());
    Level curLevel;
    GameSpec spec;
    GameController control;
    RenderLoop loop;
    Timer tickRunner;

    public static GamePage getInstance(){
        return page;
    }
    
    protected GamePage(String name, GameController control) {
        super(name);
        this.control = control;
        
    }

    protected void paintComponent(Graphics g1){
        super.paintComponent(g1);
        curLevel.render((Graphics2D)g1);
    }
    
    public void startRender(){
        tickRunner = new Timer();
        tickRunner.schedule(new RenderLoop(), 0, Consts.TIMERTICK);
    }
    
    public void pause(){
        tickRunner.cancel();
        frame.switchView(Consts.PAUSE, curLevel.name);
            //TODO give curLevel a getName() method
            //TODO Make all instance vars private, except for the obvious and GameSpec's stuff
    }
    
    public void activate(Object message){
        if(message instanceof GameSpec){
            spec = (GameSpec) message;
            control.loadLevel(spec);
        }
        control.startGame();
    }
    
    public void actionPerformed(ActionEvent e){
        
    }
    
    private class RenderLoop extends TimerTask{
        public void run(){
            repaint();
        }
    }

}
