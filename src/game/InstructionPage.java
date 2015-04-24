package game;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class InstructionPage extends View{
    JLabel jlInstructions;
    ImageIcon logo;
    JButton jbBack;

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

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub


    }

}
