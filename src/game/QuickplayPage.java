package game;

import java.awt.event.ActionEvent;

import javax.swing.*;

public class QuickplayPage extends View{
    static QuickplayPage page = new QuickplayPage(Consts.QUICKPLAY);
    JScrollPane jsLevelList = new JScrollPane();
    JButton[] jbLevels;
    JButton jbBack = new JButton("Back");
    
    public static QuickplayPage getInstance(){
        return page;
    }
    
    protected QuickplayPage(String name) {
        super(name);
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        
    }

}
