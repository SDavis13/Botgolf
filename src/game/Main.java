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

public class Main extends JFrame{
    static Main theMain = new Main();
    Hashtable<String,View> views = new Hashtable<String,View>();
    View curView;       
    
    //Screen width and height
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;           
    
    public static void main(String[] args){
        Main frame = theMain;
        frame.createPages();
        frame.switchView("Main", null);
    }
    
    public static Main getInstance(){
        return theMain;
    }
    
    private Main(){
    	SynthLookAndFeel theme = new SynthLookAndFeel();
    	try {
			theme.load(new URL("file:///Botgolf/resources/game/script/gui.xml")); //NEEDS FIXING
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
    
    public void addView(View view){
        views.put(view.name, view);
    }
    
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
    
    private void createPages(){
        addView(MainPage.getInstance());
    }
}
