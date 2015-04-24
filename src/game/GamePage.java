package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * GamePage represents the GamePage view from the MainPage.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public class GamePage extends View{
    static GamePage page = new GamePage(Consts.GAME);
    static final String THREAD_NAME = "RenderLoop";
    Level curLevel;
    GameSpec spec;
    GameController control;
    RenderLoop loop;
    Timer tickRunner;

    /**
     * Create a GamePage instance.
     * 
     * @return page		Returns an instance of GamePage
     */
    public static GamePage getInstance(){
        return page;
    }

    /**
     * GamePage constructor.
     * 
     * @param name	String of name passed		
     */
    protected GamePage(String name) {
        super(name);
        control = new GameController(this);
        setDoubleBuffered(true);
        setFocusable(true);
    }

    /**
     * Method for rendering the graphic portion of components on GamePage.
     * 
     * @param g1	Object of Graphics passed
     */
    @Override
    protected void paintComponent(Graphics g1){
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D)g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        curLevel.render(g);

        g.dispose();
    }

    /**
     * This creates the tickRunner timer for swing side of things.
     */
    public void startRender(){
        tickRunner = new Timer(THREAD_NAME);
        tickRunner.scheduleAtFixedRate(new RenderLoop(), 25, Consts.TIMERTICK);
    }

    /**
     * This is pause detection for when game play is paused.
     * 
     * @param viewName	String of the view name passed
     */
    public void pause(String viewName){
    	control.pauseGame();
        tickRunner.cancel();
        frame.switchView(/*Consts.PAUSE*/viewName, curLevel.name);
        //TODO give curLevel a getName() method
        //TODO Make all instance vars private, except for the obvious and GameSpec's stuff
    }
    
    /**
     * Lose method for when the player loses.
     */
    public void lose(){
    	//TODO ERRTHING
    	frame.switchView(Consts.GAMEMENUPAGE, curLevel.name);
    }

    /**
     * Activate method used to bring in the GameSpec.
     * 
     * @param message	Object of message passed to compare
     */
    @Override
    public void activate(Object message){
        if(message instanceof GameSpec){
            spec = (GameSpec) message;
            control.loadLevel(spec);
        }
        control.startGame();
        startRender();
    }

    /**
     * Action listener.
     * 
     * @param e		Action listener event
     */
    @Override
    public void actionPerformed(ActionEvent e){

    }

    /**
     * This sets the level for the GamePage.
     * 
     * @param level		Object type of Level passed
     */
    public void setLevel(Level level){
        curLevel = level;
    }

    /**
     * This is the RenderLoop that runs in order to continually repaints
     * the components on the GamePage view.
     */
    private class RenderLoop extends TimerTask{
        @Override
        public void run(){
            repaint();
        }
    }

}
