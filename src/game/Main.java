package game;

import java.awt.Dimension;
import java.util.*;

import javax.swing.*;

public class Main extends JFrame{
    static Main theMain = new Main();
    Hashtable<String,View> views;
    View curView;
    
    //Screen width and height
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    
    public static void main(String[] args){
        Main frame = theMain;
    }
    
    public static Main getInstance(){
        return theMain;
    }
    
    private Main(){
        getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        pack();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Botgolf");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setVisible(true);
    }
    
    public void addView(View view){
        views.put(view.name, view);
    }
    
    public void switchView(String name, Object message){
        View view = views.get(name);
        remove(curView);
        add(view);
        curView = view;
        view.activate(message);
    }
}
