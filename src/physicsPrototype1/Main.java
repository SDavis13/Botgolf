package physicsPrototype1;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class Main extends JFrame{
    private static final long serialVersionUID = 1L;
    protected static World world = new World(new Vec2(0.0f, 0.0f));
    protected Ball ball = new Ball(world);
    JPanel gamePanel = new GamePanel();
    
    //Screen width and height
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    
    public static void main(String[] args){
        
    }
    
    public Main(){
        getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        pack();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Botgolf Prototype");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.add(gamePanel);
        this.setVisible(true);
        
        gamePanel.addMouseListener(ball.ballLauncher);
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
            ball.render(g);
            
            g.dispose();
        }
    }
    
    //Convert a JBox2D x coordinate to a Swing pixel x coordinate
    public static float toPixelPosX(float posX) {
        float x = WIDTH*posX / 100.0f;
        return x;
    }
     
    //Convert a Swing pixel x coordinate to a JBox2D x coordinate
    public static float toPosX(float posX) {
        float x = (posX*100.0f*1.0f)/WIDTH;
        return x;
    }
     
    //Convert a JBox2D y coordinate to a Swing pixel y coordinate
    public static float toPixelPosY(float posY) {
        float y = HEIGHT - (1.0f*HEIGHT) * posY / 100.0f;
        return y;
    }
     
    //Convert a Swing pixel y coordinate to a JBox2D y coordinate
    public static float toPosY(float posY) {
        float y = 100.0f - ((posY * 100*1.0f) /HEIGHT) ;
        return y;
    }
     
    //Convert a JBox2D width to pixel width
    public static float toPixelWidth(float width) {
        return WIDTH*width / 100.0f;
    }
     
    //Convert a JBox2D height to pixel height
    public static float toPixelHeight(float height) {
        return HEIGHT*height/100.0f;
    }
}