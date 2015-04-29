package game;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;

/**
 * QuickPlayPage represents the quick play button from Main page.
 * 
 * @author     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public class QuickplayPage extends View{
    static QuickplayPage page = new QuickplayPage(Consts.QUICKPLAY);
    JScrollPane jsLevelList = new JScrollPane();
    JButton[] jbLevels;
    JButton jbBack = new JButton("Back");
    
    /**
     * Create a GamePage instance.
     * 
     * @return page		Returns an instance of GamePage
     */
    public static QuickplayPage getInstance(){
        return page;
    }
    
    /**
     * Constructor for QuickplayPage.
     * 
     * @param name		String name is passed
     */
    protected QuickplayPage(String name) {
        super(name);
        
    }

    /**
     * Action listener
     * 
     * @param arg0		Action listener event
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        
    }

}
