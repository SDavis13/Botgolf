package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainPage extends View{
    static MainPage page = new MainPage(Consts.MAIN);
    static ImageIcon logo;
    JLabel jlTitle = new JLabel();
    JButton jbCampaign = new JButton("Campaign");
    JButton jbQuickplay = new JButton("Quickplay");
    JButton jbOptions = new JButton("Options");
    JButton jbInstructions = new JButton("How to Play");
    JButton jbHighscore = new JButton("High Scores");
    JButton jbAbout = new JButton("About");
    JButton jbExit = new JButton("Exit");
    
    public static MainPage getInstance(){
        return page;
    }
    
    protected MainPage(String name) {
        super(name);
        try{
            logo = new ImageIcon(ImageIO.read(new File(Consts.IMGLOC + "logo.png")));
            jlTitle.setSize(logo.getIconWidth(), logo.getIconHeight());
            jlTitle.setIcon(logo);
        }catch(IOException e){
            System.out.println("Could not load main logo image.");
            e.printStackTrace();
        }
        jbCampaign.addActionListener(this);
        jbQuickplay.addActionListener(this);
        jbOptions.addActionListener(this);
        jbInstructions.addActionListener(this);
        jbHighscore.addActionListener(this);
        jbAbout.addActionListener(this);
        jbExit.addActionListener(this);
        
        
        
        add(jlTitle);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbQuickplay){
            frame.switchView(Consts.QUICKPLAY, null);
        }
        if(e.getSource() == jbCampaign){
            frame.switchView(Consts.CAMPAIGN, null);
        }
        if(e.getSource() == jbOptions){
            frame.switchView(Consts.OPTIONS, null);
        }
        if(e.getSource() == jbInstructions){
            frame.switchView(Consts.INSTRUCTION, null);
        }
        if(e.getSource() == jbHighscore){
            frame.switchView(Consts.HIGHSCORE, null);
        }
        if(e.getSource() == jbAbout){
            frame.switchView(Consts.ABOUT, null);
        }
        if(e.getSource() == jbExit){
            System.exit(0);
        }
        
    }
    
}
