package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

public class GamePage extends View{
    static GamePage page = new GamePage(Consts.GAME);
    static final String THREAD_NAME = "RenderLoop";
    Level curLevel;
    GameSpec spec;
    GameController control;
    RenderLoop loop;
    Timer tickRunner;

    public static GamePage getInstance(){
        return page;
    }
    
    protected GamePage(String name) {
        super(name);
        control = new GameController(this);
        setDoubleBuffered(true);
    }

    protected void paintComponent(Graphics g1){
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D)g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        curLevel.render(g);
        
        g.dispose();
    }
    
    public void startRender(){
        tickRunner = new Timer(THREAD_NAME);
        tickRunner.scheduleAtFixedRate(new RenderLoop(), 25, Consts.TIMERTICK);
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
        startRender();
    }
    
    public void actionPerformed(ActionEvent e){
        
    }
    
    public void setLevel(Level level){
        curLevel = level;
    }
    
    private class RenderLoop extends TimerTask{
        public void run(){
            repaint();
        }
    }

}
