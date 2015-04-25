package game;

import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class GameMenuPage extends View {
	static GameMenuPage page = new GameMenuPage(Consts.GAMEMENUPAGE);
	JPanel pausePanel = new JPanel();
    JButton restartButton = new JButton("Restart");
    JButton exitMainButton = new JButton("Exit To Main Menu");

	protected GameMenuPage(String name) {
        super(name);
        
        Insets insetDefinition = new Insets(15,20,15,20);
                
        restartButton.setMargin(insetDefinition);
        exitMainButton.setMargin(insetDefinition);

        pausePanel = new javax.swing.JPanel();
        restartButton = new javax.swing.JButton();
        exitMainButton = new javax.swing.JButton();

        restartButton.setText("Restart");
        restartButton.addActionListener(this);
        exitMainButton.setText("Exit To Main Menu");
        exitMainButton.addActionListener(this);

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
	
	public static GameMenuPage getInstance(){
        return page;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == restartButton){
			frame.switchView(Consts.GAME, new GameSpec("Test"));
        }
        if(e.getSource() == exitMainButton){
        	frame.switchView(Consts.MAIN, null);
        }
	}
}
