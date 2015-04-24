package game;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

public abstract class View extends JPanel implements ActionListener{
    String name;
    Main frame;
    boolean active;

    protected View(String name){
        this.name = name;
        frame = Main.getInstance();
        active = false;
    }

    public void activate(Object message){
        active = true;
    }

    public void deactivate(){
        active = false;
    }

}
