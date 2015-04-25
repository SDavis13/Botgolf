package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.event.MouseInputAdapter;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

import com.sun.javafx.event.EventQueue;

/**
 * GameController represents the game play.  This keeps track of keyboard and
 * mouse input as well as recognizes game state changes.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     1.0
 * @since       2015-04-21
 * @implements	ContactListener
 */
public class GameController implements ContactListener{
    static final String THREAD_NAME = "PhysicsLoop";
    GamePage view;
    Level curLevel;
    Ball ball;
    Hole hole;
    Timer tickRunner;
    GameState state;
    GameState tempState;
    LevelFactory curFactory = new CreateLevel(); //TODO This is hardcoded and will need to be changed.

    /**
     * Constructor for GameController.
     * 
     * @param page	Object type of GamePage passed
     */
    public GameController(GamePage page){
        state = GameState.INACTIVE;
        tempState = GameState.READY;
        view = page;
        MouseInput mouseInput = new MouseInput();
        KeyboardInput keyInput = new KeyboardInput();
        view.addMouseListener(mouseInput);
        view.addMouseMotionListener(mouseInput);
        view.addKeyListener(keyInput);
    }
    
    /**
     * LoadLevel method creates the specs and levels.
     * 
     * @param levelSpec		Object type of GameSpec passed
     */
    public void loadLevel(GameSpec levelSpec){
        if(levelSpec.newGame)
            curLevel = curFactory.createLevel(levelSpec);
        ball = curLevel.getBall();
        hole = curLevel.getHole();
        view.setLevel(curLevel);
        curLevel.world.setContactListener(this);
    }
    
    /**
     * StartGame method used to start the game in unpaused and with time.
     */
    public void startGame(){
    	curLevel.unPause();
        state = tempState;
        tickRunner = new Timer(THREAD_NAME);
        tickRunner.scheduleAtFixedRate(new PhysicsLoop(), 25, Consts.TIMERTICK);
    }
   
    /**
     * PauseGame used to pause the game state of the current game.
     */
    public void pauseGame(){
        tempState = state;
        tickRunner.cancel();
        curLevel.pause();
        state = GameState.PAUSED;
    }
    
    /**
     * ExitGame used to change game state and then exit.
     */
    public void exitGame(){
    	tempState = GameState.READY;
    	state = GameState.INACTIVE;
    	curLevel.pause();
    	tickRunner.cancel();
    	view.exit();
    }
    
    /**
     * WinGame method used when game is won.
     */
    public void endGame(){
    	new Timer().schedule(
    		new TimerTask(){
    			@Override
    			public void run(){
		    		exitGame();
    			}
    		},
    		5000
    	);
    }
    
    /**
     * 
     * @author ctswanson
     *
     */
    private class PhysicsLoop extends TimerTask{
        boolean launched = false;
        boolean ranWin = false;

        @Override
        public void run(){
            curLevel.step();
            if (hole.win)
            {
            	state = GameState.WIN;
            }
            if (ball.shotCount == 0)
            {
            	state = GameState.LOSE;
            }
            switch(state){
                case LAUNCH:
                    if(!ball.postLaunch()){
                        launched = true;
                    }else{
                        launched = false;
                        state = GameState.MOBTURN;
                    }
                    break;
                case MOBTURN:
                    if(!curLevel.moveMobs()) state = GameState.READY;
                    break;
                case WIN:
                    if(!ranWin){
                    	ranWin = true;
                    	endGame();
                    }
                    break;
                case LOSE:
                	if(!ranWin){
                		endGame();
                	}
                	break;
            }
        }
    }

    
    private class KeyboardInput extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int code = e.getKeyCode();
            if(code == Consts.pauseKey || code == Consts.pauseMenuKey){
                
                if(state == GameState.PAUSED){
                	startGame();                	
                }
                else
                {
                    pauseGame();
                }
            }            
            /*else if(code == Consts.pauseMenuKey){
                pauseGame();
                view.pause(Consts.PAUSE);
            }*/

        }
    }

    private class MouseInput extends MouseInputAdapter{
        float xOrigin, yOrigin, oldXOffset, oldYOffset;
        @Override
        public void mouseDragged(MouseEvent e){
            int mouseX = e.getX();
            int mouseY = e.getY();
            ball.setMouseLoc(mouseX,mouseY);
            if(state == GameState.READY){
                if(ball.contains(mouseX,mouseY) && e.getButton() == 0){
                    state = GameState.GRAB;
                    ball.setGrabbed();
                }else{
                    if(xOrigin == 0 && yOrigin == 0){
                        xOrigin = e.getX();
                        yOrigin = e.getY();
                        oldXOffset = Consts.pxOffset;
                        oldYOffset = Consts.pyOffset;
                    }
                    Consts.pxOffset = oldXOffset + (xOrigin - mouseX);
                    Consts.pyOffset = oldYOffset + (yOrigin - mouseY);
                }
            }
        }
        @Override
        public void mouseReleased(MouseEvent e){
            xOrigin = 0;
            yOrigin = 0;
            if(state == GameState.GRAB && e.getButton() == 1){
                ball.launch(Utils.toPhysX(e.getX()), Utils.toPhysY(e.getY()));
                state = GameState.LAUNCH;
            }
        }
        public void mousePressed(MouseEvent e){
        	view.requestFocusInWindow();
        }
    }

    @Override
    public void beginContact(Contact contact) {
        Entity entity1 = (Entity)contact.getFixtureA().getUserData();
        Entity entity2 = (Entity)contact.getFixtureB().getUserData();
        entity1.hit(entity2);
        entity2.hit(entity1);
    }
    @Override
    public void endContact(Contact contact) {
        // TODO Auto-generated method stub

    }
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // TODO Auto-generated method stub

    }
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // TODO Auto-generated method stub

    }

}
