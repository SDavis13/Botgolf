package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * GamePage represents the GamePage view for the jbox2d physics rendering
 * as well as the Jframe rendering over the top.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     1.0
 * @since       2015-04-21
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
     * @return 	page	returns an instance of GamePage
     */
    public static GamePage getInstance(){
        return page;
    }

    /**
     * GamePage is created with protected view.
     * Creates a new a new GameController object
     * 
     * @param 	name	name of the GamePage for simplicity.		
     */
    protected GamePage(String name) {
        super(name);
        control = new GameController(this);
        setDoubleBuffered(true);
    }

    /**
     * Method for rendering the graphic portion of components on GamePage.
     * 
     * @param	g1	component name of Graphics
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
     */
    public void pause(){
        tickRunner.cancel();
        frame.switchView(Consts.PAUSE, curLevel.name);
        //TODO give curLevel a getName() method
        //TODO Make all instance vars private, except for the obvious and GameSpec's stuff
    }

    /**
     * Activate method used to bring in the GameSpec.
     * 
     * @param	message		Object passed to compare with GameSpec object
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
     * Action listener
     * 
     * @param	e	action listener event
     */
    @Override
    public void actionPerformed(ActionEvent e){

    }

    /**
     * This sets the level for the GamePage.
     * 
     * @param 	level	This is the level that is passed to GamePage
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
