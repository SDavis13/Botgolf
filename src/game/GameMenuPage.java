package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * Class of GameMenuPage used to return back to main menu or exit game after
 * the game has been played.
 * 
 * @author     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public class GameMenuPage extends View {
	static GameMenuPage page = new GameMenuPage(Consts.GAMEMENUPAGE);
	JPanel pausePanel = new JPanel();
    JButton restartButton = new JButton("Restart");
    JButton exitMainButton = new JButton("Exit To Main Menu");
    JLabel label = new JLabel();

	/**
	 * Constructor for GameMenuPage.
	 * 
	 * @param name		String type of name passed
	 */
    protected GameMenuPage(String name) {
        super(name);
        Insets insetDefinition = new Insets(15,20,15,20);
        
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
        label.setForeground(Color.BLACK);
        
        restartButton.setMargin(insetDefinition);
        exitMainButton.setMargin(insetDefinition);

        restartButton.addActionListener(this);
        exitMainButton.addActionListener(this);
        add(label);

        
        GroupLayout pausePanelLayout = new GroupLayout(pausePanel);
        pausePanel.setLayout(pausePanelLayout);
        pausePanelLayout.setHorizontalGroup(
            pausePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, pausePanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(restartButton, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(exitMainButton, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        pausePanelLayout.setVerticalGroup(
            pausePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, pausePanelLayout.createSequentialGroup()
                .addContainerGap(496, Short.MAX_VALUE)
                .addGroup(pausePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(restartButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitMainButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        
        add(pausePanel);
    }	
	
	/**
	 * GetInstance returns a singleton instance of this page.
	 * 
	 * @return	Returns a page instance of this view
	 */
    public static GameMenuPage getInstance(){
        return page;
    }

	/**
	 * ActionPerformed method to listen for mouse clicks for buttons on page.
	 */
    @Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == restartButton){
			frame.switchView(Consts.GAME, new GameSpec("Test"));
        }
        if(e.getSource() == exitMainButton){
        	frame.switchView(Consts.MAIN, null);
        }
	}
    
    public void activate(Object message){
    	super.activate(message);
    	
    	if (message instanceof Integer)
    	{
    		label.setText("Score: " + (Integer)message);
    	}
    }
}
