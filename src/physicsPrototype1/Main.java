package physicsPrototype1;

import java.awt.*;
import java.util.*;

import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;
//import org.jbox2d.dynamics.joints.FrictionJointDef;

public class Main extends JFrame{
    private static final long serialVersionUID = 1L;
    protected static World world = new World(new Vec2(0.0f, 0.0f));
    protected Ball ball = new Ball(world);
    protected Wall[] walls = new Wall[4];
    //Wall fricWall;
    JPanel gamePanel = new GamePanel();
    Loop loop = new Loop();
    float timeStep = 1.0f / 60.f;
    int velocityIterations = 6;
    int positionIterations = 3;
    Timer timer;
    
    //Screen width and height
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    
    public static void main(String[] args){
        Main theMain = new Main();
        System.out.print("Starting: " + theMain.timeStep + " seconds per tick.");
    }
    
    public Main(){
        getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        pack();
        
        float alength = 13.5f;
        float awidth = 1.5f;
        walls[0] = new Wall(world, 3f, Main.toPosY(300), awidth, alength);//red
        walls[1] = new Wall(world, Main.toPosX(300), Main.toPosY(0) - 3f, alength, awidth);//yellow
        walls[2] = new Wall(world, Main.toPosX(600) - 3f, Main.toPosY(300), awidth, alength);//green
        walls[3] = new Wall(world, Main.toPosX(300), Main.toPosY(600) + 3f, alength, awidth);//blue
        
        /*
        fricWall = new Wall(world, 50f, 50f, 47f, 47f);
        FrictionJointDef uniFric = new FrictionJointDef();
        uniFric.bodyA = ball.body;
        uniFric.bodyB = fricWall.body;
        uniFric.maxForce = 25f;
        uniFric.maxTorque = 0f;
        
        world.createJoint(uniFric);
        */
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Botgolf Prototype");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.add(gamePanel);
        this.setVisible(true);
        
        gamePanel.addMouseListener(ball.ballLauncher);
        gamePanel.addMouseMotionListener(ball.ballLauncher);
        
        world.setContactListener(new SoundPlayer());
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new Loop(), 
                100, 25);//milliseconds
    }
    
    public void step(){
        world.step(timeStep, velocityIterations, positionIterations);
    }
    
    private class GamePanel extends JPanel {

        private static final long serialVersionUID = 1L;
        
        public GamePanel(){
            setDoubleBuffered(true);
        }
        
        public void paintComponent(Graphics g1) {
            super.paintComponent(g1);
            Graphics2D g = (Graphics2D) g1;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            //fricWall.render(g);
            for(int i = 0; i < walls.length; i++){
                walls[i].render(g);
            }
            ball.render(g);
            
            g.dispose();
        }
    }
    
    private class Loop extends TimerTask {
        @Override
        public void run() {
            gamePanel.repaint();
            step();
        }
    }
    
    private class SoundPlayer implements ContactListener{

        @Override
        public void beginContact(Contact contact) {
            Object object1 = contact.getFixtureA().getUserData();
            Object object2 = contact.getFixtureB().getUserData();
            
            if(object1 instanceof Clip){
                Clip clip = (Clip)(object1);
                if(clip.getFramePosition() != 0) clip.setFramePosition(0);
                (clip).start();
                clip.close();
            }
            if(object2 instanceof Clip){
                Clip clip = (Clip)(object2);
                if(clip.getFramePosition() != 0) clip.setFramePosition(0);
                (clip).start();
                clip.close();
            }
        }

        @Override
        public void endContact(Contact contact) {
            // Auto-generated method stub
            
        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {
            // Auto-generated method stub
            
        }

        @Override
        public void preSolve(Contact contact, Manifold manifold) {
            // Auto-generated method stub
            
        }
    }
    
    static int envSize = 2000;
    //Convert a JBox2D x coordinate to a Swing pixel x coordinate
    public static float toPixelPosX(float posX) {
        float x = envSize*posX / 100.0f;
        return x;
    }

    //Convert a Swing pixel x coordinate to a JBox2D x coordinate
    public static float toPosX(float posX) {
        float x = (posX*100.0f*1.0f)/envSize;
        return x;
    }

    //Convert a JBox2D y coordinate to a Swing pixel y coordinate
    public static float toPixelPosY(float posY) {
        float y = envSize - (1.0f*envSize) * posY / 100.0f;
        return y;
    }

    //Convert a Swing pixel y coordinate to a JBox2D y coordinate
    public static float toPosY(float posY) {
        float y = 100.0f - ((posY * 100*1.0f) /envSize) ;
        return y;
    }

    //Convert a JBox2D width to pixel width
    public static float toPixelWidth(float width) {
        return envSize*width / 100.0f;
    }

    //Convert a JBox2D height to pixel height
    public static float toPixelHeight(float height) {
        return envSize*height/100.0f;
    }
}