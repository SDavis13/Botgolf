package game;

import java.awt.event.ActionListener;

import javax.swing.*;

public abstract class View extends JPanel implements ActionListener{
    String name;
    Main frame;
    boolean active;
    
    public View(String name){
        this.name = name;
        frame = Main.getInstance();
    }
    
    public void activate(Object message){
        active = true;
    }
    
}
