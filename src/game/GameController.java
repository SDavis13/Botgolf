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

/**
 * GameController manages gameplay by keeping track of keyboard and
 * mouse input as well as recognizing and driving game state changes.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-28
 * @since       2015-04-24
 * @implements	ContactListener
 */
public class GameController implements ContactListener{
    /**
     * The name of the thread executing physics and game logic, for debugging purposes.
     */
    static final String THREAD_NAME = "PhysicsLoop";
    /**
     * Delay in milliseconds after the game has been won or lost before the game goes to the menu.
     */
    static final long WINDELAY = 5000;
    /**
     * The GamePage
     */
    GamePage view;
    /**
     * The current Level
     */
    Level curLevel;
    /**
     * The Ball
     */
    Ball ball;
    /**
     * The Hole
     */
    Hole hole;
    /**
     * The java.util.Timer which drives the game logic loop.
     */
    Timer tickRunner;
    /**
     * The current state of the game.
     */
    GameState state;
    /**
     * The GameState saved when the game is paused.
     */
    GameState tempState;
    /**
     * The factory used to load the level.
     * This is currently hardcoded to one level (From the CreateLevel class) and will need to be changed when support for non-hardcoded levels is added.
     */
    LevelFactory curFactory = new CreateLevel();

    /**
     * Constructor for GameController.
     * 
     * @param page The GamePage
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
     * The LoadLevel method loads the level from a levelFactory given the level specification.
     * 
     * @param levelSpec A GameSpec
     */
    public void loadLevel(GameSpec levelSpec){
        if(levelSpec.newGame)
            curLevel = curFactory.createLevel(levelSpec);
        ball = curLevel.getBall();
        hole = curLevel.getHole();
        view.setLevel(curLevel);
        curLevel.world.setContactListener(this);
        SoundRepository.setMusic(curLevel.music);
    }
    
    /**
     * StartGame method used to start the game.
     */
    public void startGame(){
    	curLevel.unPause();
        state = tempState;
        tickRunner = new Timer(THREAD_NAME);
        tickRunner.scheduleAtFixedRate(new PhysicsLoop(), 25, Consts.TIMERTICK);
        SoundRepository.startMusic();
    }
   
    /**
     * PauseGame used to pause the current game.
     */
    public void pauseGame(){
        tempState = state;
        tickRunner.cancel();
        curLevel.pause();
        state = GameState.PAUSED;
        SoundRepository.pauseMusic();
    }
    
    /**
     * ExitGame used to change game state to inactive and then exit.
     */
    public void exitGame(){
    	tempState = GameState.READY;
    	state = GameState.INACTIVE;
    	curLevel.pause();
    	tickRunner.cancel();
    	SoundRepository.resetMusic();
    	view.exit();
    }
    
    /**
     * EndGame method used when game has ended.
     * Waits WINDELAY milliseconds before calling exitGame().
     */
    public void endGame(){
    	new Timer().schedule(
    		new TimerTask(){
    			@Override
    			public void run(){
    			    exitGame();
    			}
    		},
    		WINDELAY
    	);
    }
    
    /**
     * PhysicsLoop class created extends TimerTask
     * 
     * @extends TimerTask
     */
    private class PhysicsLoop extends TimerTask{
        boolean launched = false;
        boolean ranWin = false;

        /**
         * Run method calls Level.step(), checks if the player has won or lost, then goes through GameStates and acts accordingly.
         */
        @Override
        public void run(){
            curLevel.step();
            if (hole.win)
            {
            	state = GameState.WIN;
            }else if (ball.shotCount == 0)
            {
            	state = GameState.LOSE;
            }
            switch(state){
                case LAUNCH:
                    if(!ball.postLaunch()){
                        launched = true;
                    }else{
                        launched = false;
                        curLevel.getGrid().addObstruction(ball.getPosition(), Obstruction.KILL);
                        state = GameState.MOBTURN;
                    }
                    break;
                case MOBTURN:
                    if(!curLevel.moveMobs()) state = GameState.READY;
                    break;
                case WIN:
                    SoundRepository.resetMusic();
                    if(!ranWin){
                        ranWin = true;
                        endGame();
                    }
                    break;
                case LOSE:
                	if(!ranWin){
                	    ranWin = true;
                		endGame();
                	}
                	break;
            }
        }
    }

    
    /**
     * KeyboardInput class created extends the KeyAdapter to detect keyboard input.
     * 
     * @extends KeyAdapter
     */
    private class KeyboardInput extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int code = e.getKeyCode();
            if(code == Consts.pauseKey || code == Consts.pauseMenuKey){
                
                if(state == GameState.PAUSED){
                	SoundRepository.playSound(Consts.SOUNDS[Consts.SNDIDX_PAUSE]);
                	startGame();                	
                }
                else
                {
                    pauseGame();
                }
            }else if(code == 0){
                
            }else{
            	SoundRepository.playSound(Consts.SOUNDS[Consts.SNDIDX_BOING]);
            }          
            /*else if(code == Consts.pauseMenuKey){
                pauseGame();
                view.pause(Consts.PAUSE);
            }*/

        }
    }

    /**
     * MouseInput class extends MouseInputAdapter to detect mouse input.
     * 
     * @extends MouseInputAdapter
     */
    private class MouseInput extends MouseInputAdapter{
        float xOrigin, yOrigin, oldXOffset, oldYOffset;
        
        /**
         * Used to receive a mouse dragged event for grabbing the ball or moving the screen offset around.
         * 
         * @param e MouseEvent
         */
        @Override
        public void mouseDragged(MouseEvent e){
            int mouseX = e.getX();
            int mouseY = e.getY();
            ball.setMouseLoc(mouseX,mouseY);
            if(state == GameState.READY){
                if(ball.containsDoubleSize(mouseX,mouseY) && e.getButton() == 0){
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
        
        /**
         * Used to receive a mouse released event to launch the ball if the ball is grabbed.
         * 
         * @param e MouseEvent
         */
        @Override
        public void mouseReleased(MouseEvent e){
            xOrigin = 0;
            yOrigin = 0;
            if(state == GameState.GRAB && e.getButton() == 1){
                curLevel.getGrid().removeObstruction(ball.getPosition(), Obstruction.KILL);
                ball.launch(Utils.toPhysX(e.getX()), Utils.toPhysY(e.getY()));
                state = GameState.LAUNCH;
            }
        }
        
        /**
         * Used to request focus in window.
         * Focus is necessary for the KeyAdapter to receive input.
         * @param e MouseEvent
         */
        public void mousePressed(MouseEvent e){
        	view.requestFocusInWindow();
        }
    }

    /**
     * Used to receive input on contact between two JBox2D Fixtures, and by extension the Entities that hold them.
     * Calls .hit() on the Entities involved.
     * @see org.jbox2d.callbacks.ContactListener
     * @param contact
     */
    @Override
    public void beginContact(Contact contact) {
        Entity entity1 = (Entity)contact.getFixtureA().getUserData();
        Entity entity2 = (Entity)contact.getFixtureB().getUserData();
        entity1.hit(entity2);
        entity2.hit(entity1);
    }
    
    /**
     * Required by the ContactListener interface, but unused in this instance.
     * Would be used if we had something that needed to occur when two entities stopped being in contact with each other.
     * @param contact
     */
    @Override
    public void endContact(Contact contact) {
        // TODO Auto-generated method stub
    }
   
    /**
     * Required by the ContactListener interface, but unused in this instance.
     * 
     * @param contact
     * @param oldManifold
     */
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // TODO Auto-generated method stub
    }
    
    /**
     * Required by the ContactListener interface, but unused in this instance.
     * 
     * @param contact
     * @param impulse
     */
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // TODO Auto-generated method stub
    }

}
