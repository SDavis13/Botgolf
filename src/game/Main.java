package game;

import java.awt.Dimension;
import java.net.URL;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.synth.SynthLookAndFeel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @authors     Spencer Davis
 * @version     1.0
 * @since       2015-03-31
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
     * Getting the instance of theMain.
     *
     * @return theMain
     */
    public static Main getInstance(){
        return theMain;
    }
    
    /**
     * Creating.
     * <p>
     *
     * @param  variable Description text text text.
     * @return Description text text text.
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
     * Creates the views.
     * <p>
     * Creates the views or pages to view.
     *
     */
    public void addView(View view){
        views.put(view.name, view);
    }
    
    /**
     * Switches between the views.
     * <p>
     * This switches between the views.
     *
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
     * 
     */
    public void repaint(){
        super.repaint();
        curView.repaint();
    }
    
    
    /**
     * Creates the pages to use.
     * <p>
     * This creates pages to use between switching views.
     *
     */
    private void createPages(){
        addView(MainPage.getInstance());
        addView(GamePage.getInstance());
    }
}
