package game;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * This page represents the InstructionPage page to be used from MainPage.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     1.0
 * @since       2015-04-21
 */
public class InstructionPage extends View{
    JLabel jlInstructions;
    ImageIcon logo;
    JButton jbBack;

    /**
     * This is the instructionPage constructor.
     * 
     * @param 	frame	passed a object of Main
     */
    protected InstructionPage(Main frame){
        super(Consts.INSTRUCTION);
        this.frame = frame;
        active = false;
        try{
            logo = new ImageIcon(ImageIO.read(new File(Consts.IMG_LOGO)));
            jlInstructions.setSize(logo.getIconWidth(), logo.getIconHeight());
            jlInstructions.setIcon(logo);
        }catch(IOException e){
            jlInstructions.setText("BotGolf");;
            System.out.println("Could not load main logo image.");
            e.printStackTrace();
        }
    }

    /**
     * Action listener
     * 
     * @param	arg0	action listener event
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub


    }

}
