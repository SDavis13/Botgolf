package game;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.SynthLookAndFeel;

/**
 * This is the main class where everything starts.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 * @extends		JFrame
 */
public class Main extends JFrame{
    static Main theMain = new Main();
    Hashtable<String,View> views = new Hashtable<String,View>();
    View curView;

    /**
     * Screen width and height of main window that opens
     */
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;           

    public static void main(String[] args){
        Main frame = theMain;
        frame.createPages();
        frame.switchView("Main", null);
    }

    /**
     * Created a singleton instance of Main.
     *
     * @return 	Returns an instance of Main
     */
    public static Main getInstance(){
        return theMain;
    }

    /**
     * Main begins with the look and feel of theme being loaded.  Gets the content
     * pane and sets dimension of box.  Brings up the initial page where our buttons are.
     */
    private Main(){
        SynthLookAndFeel theme = new SynthLookAndFeel();
        String projectDirectory = System.getProperty("user.dir");
        System.out.println(System.getProperty("user.dir"));
        try {
            theme.load(new URL("file:///" + projectDirectory + "/resources/game/script/gui.xml"));
            UIManager.setLookAndFeel(theme);
        } catch (ParseException | IOException | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Botgolf");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Adds the views or pages to a hash table called 'views'.
     * 
     * @param view	Object of View passed
     */
    public void addView(View view){
        views.put(view.getName(), view);
    }

    /**
     * This switches between the views.
     * 
     * @param name		String type name passed
     * @param message  	Object type of message passed
     */
    public void switchView(String name, Object message){
        View view = views.get(name);
        if(view != null){
            if(curView != null){
                curView.deactivate();
                remove(curView);
            }
            add(view);
            curView = view;
            view.activate(message);
            revalidate();
            repaint();
        }
    }

    /**
     * Repaints the current view page selected.
     */
    @Override
    public void repaint(){
        super.repaint();
        curView.repaint();
    }


    /**
     * This creates pages to use between switching views.  
     * These are singleton views.
     */
    private void createPages(){
        addView(MainPage.getInstance());
        addView(GamePage.getInstance());
        addView(GameMenuPage.getInstance());
        addView(InstructionPage.getInstance());
    }
}
