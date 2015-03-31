package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainPage extends View{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    static MainPage page = new MainPage(Consts.MAIN);
    static ImageIcon logo;
    JLabel jlTitle = new JLabel(); //Title logo
    JPanel jpShirt = new JPanel(); //Holds buttons
        JPanel jpLeft = new JPanel();
        JPanel jpRight = new JPanel();
    static final int NUM_ROWS = 3;
    static final int NUM_COLS = 2;
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
    	
    	Insets insetDefinition = new Insets(15,20,15,20);
    	
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        try{
            logo = new ImageIcon(ImageIO.read(new File(Consts.IMGLOC + "logo.png")));
            jlTitle.setSize(logo.getIconWidth(), logo.getIconHeight());
            jlTitle.setIcon(logo);
        }catch(IOException e){
            jlTitle.setText("BotGolf");;
            System.out.println("Could not load main logo image.");
            e.printStackTrace();
        }
                
        jbCampaign.setMargin(insetDefinition);
        jbQuickplay.setMargin(insetDefinition);
        jbOptions.setMargin(insetDefinition);
        jbInstructions.setMargin(insetDefinition);
        jbHighscore.setMargin(insetDefinition);
        jbAbout.setMargin(insetDefinition);
        jbExit.setMargin(insetDefinition);
        
        jlTitle.setAlignmentX(CENTER_ALIGNMENT);
        //jlTitle.setBorder(BorderFactory.createLineBorder(Color.black));
        //jpShirt.setLayout(new BoxLayout(jpShirt, BoxLayout.X_AXIS));
            jpLeft.setLayout(new BoxLayout(jpLeft, BoxLayout.Y_AXIS));
            jpRight.setLayout(new BoxLayout(jpRight, BoxLayout.Y_AXIS));
        jbCampaign.addActionListener(this);
        jbCampaign.setAlignmentX(RIGHT_ALIGNMENT);
        jbQuickplay.addActionListener(this);
        jbQuickplay.setAlignmentX(RIGHT_ALIGNMENT);
        jbOptions.addActionListener(this);
        jbOptions.setAlignmentX(RIGHT_ALIGNMENT);
        jbInstructions.addActionListener(this);
        jbInstructions.setAlignmentX(LEFT_ALIGNMENT);
        jbHighscore.addActionListener(this);
        jbHighscore.setAlignmentX(LEFT_ALIGNMENT);
        jbAbout.addActionListener(this);
        jbAbout.setAlignmentX(LEFT_ALIGNMENT);
        jbExit.addActionListener(this);
        jbExit.setAlignmentX(CENTER_ALIGNMENT);
        
        add(jlTitle);
        add(jpShirt);
            jpShirt.add(jpLeft);
            jpShirt.add(jpRight);
        add(jbExit);
        jpLeft.add(jbCampaign);//column1
        jpRight.add(jbHighscore);//column2
        jpLeft.add(jbQuickplay);//column1
        jpRight.add(jbInstructions);//column2
        jpLeft.add(jbOptions);//column1
        jpRight.add(jbAbout);//column2
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
