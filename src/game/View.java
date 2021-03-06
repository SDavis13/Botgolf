package game;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * This class represents the abstract class of View.  This also
 * extends the Jpanel and implements ActionListener.
 * 
 * @author     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public abstract class View extends JPanel implements ActionListener{
    Main frame;
    boolean active;

    /**
     * Constructor for View.
     * 
     * @param name	String name has been passed
     */
    protected View(String name){
        super();
        setName(name);
        frame = Main.getInstance();
        active = false;
    }

    /**
     * Activate method for the view.
     * 
     * @param message	Object message is passed
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
