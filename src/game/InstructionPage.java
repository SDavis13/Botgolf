package game;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * This page represents the InstructionPage page to be used from MainPage.
 * 
 * @author     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public class InstructionPage extends View{
	static InstructionPage page = new InstructionPage(Consts.INSTRUCTION);
	JLabel jlInstructions = new JLabel();
    ImageIcon instr;
    JButton jbBack = new JButton("Back");
    

    /**
     * This is the instructionPage constructor.
     * 
     * @param frame		Object type of Main passed
     */
    protected InstructionPage(String name){
        super(name);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        active = false;
        try{
            instr = new ImageIcon(ImageIO.read(new File(Consts.IMG_INSTR)));
            jlInstructions.setSize(instr.getIconWidth(), instr.getIconHeight());
            jlInstructions.setIcon(instr);
        }catch(IOException e){
            jlInstructions.setText("Could not load instructions image.");
            e.printStackTrace();
        }
        
        jbBack.addActionListener(this);
        jbBack.setMargin(Consts.BUTTON_MARGIN);
        add(jlInstructions);
        add(jbBack);
    }
    public static InstructionPage getInstance(){
        return page;
    }
    
    /**
     * Action listener
     * 
     * @param arg0		Action listener event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbBack){
            frame.switchView(Consts.MAIN, null);
        }


    }

}
