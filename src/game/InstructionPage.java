package game;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextPane;

public class InstructionPage extends View{
    JTextPane jtInstructions;
    JButton jbBack;
    
    protected InstructionPage(Main frame){
        super(Consts.INSTRUCTION);
        this.frame = frame;
        active = false;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        
    }

}
