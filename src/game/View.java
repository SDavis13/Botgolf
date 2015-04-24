package game;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * This class represents the abstract class of View.  This also
 * extends the Jpanel and implements ActionListener.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     1.0
 * @since       2015-04-21
 * @extends		JPanel
 * @implements	ActionListener
 */
public abstract class View extends JPanel implements ActionListener{
    String name;
    Main frame;
    boolean active;

    /**
     * Constructor for View.
     * 
     * @param name	passed a name for for view
     */
    protected View(String name){
        this.name = name;
        frame = Main.getInstance();
        active = false;
    }

    /**
     * Activate method for the view.
     * 
     * @param message	this object is passed
     */
    public void activate(Object message){
        active = true;
    }

    /**
     * Deactivate method for the view.
     */
    public void deactivate(){
        active = false;
    }

}
