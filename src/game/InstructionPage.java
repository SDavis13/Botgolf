package game;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class InstructionPage extends View{
    JTextPane jtInstructions;
    JButton jbBack;
    
    protected InstructionPage(Main frame){
    	//copied from MainPage
    	try{
            logo = new ImageIcon(ImageIO.read(new File(Consts.IMG_LOGO)));
            jlTitle.setSize(logo.getIconWidth(), logo.getIconHeight());
            jlTitle.setIcon(logo);
        }catch(IOException e){
            jlTitle.setText("BotGolf");;
            System.out.println("Could not load main logo image.");
            e.printStackTrace();
        }
    	/*
    	super(Consts.INSTRUCTION);
        this.frame = frame;
        active = false;*/
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
    	
        
    }

}
