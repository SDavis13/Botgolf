package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainPage extends View{
    static BufferedImage logo;
    JPanel jpTitle;
    JButton jbCampaign;
    JButton jbQuickplay;
    JButton jbOptions;
    JButton jbInstructions;
    JButton jbHighscore;
    JButton jbAbout;
    JButton jbExit;
    
    public MainPage(String name) {
        super(name);
        try{
            logo = ImageIO.read(new File(Consts.IMGLOC + "logo.png"));
        }catch(IOException e){
            System.out.println("Could not load main logo image.");
            e.printStackTrace();
        }
        //jpTitle.getGraphics().drawImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
