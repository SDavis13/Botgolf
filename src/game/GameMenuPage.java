package game;

import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class GameMenuPage extends View {
	static GameMenuPage page = new GameMenuPage(Consts.GAMEMENUPAGE);

	protected GameMenuPage(String name) {
        super(name);
        
        Insets insetDefinition = new Insets(15,20,15,20);
        
        JPanel pausePanel = new JPanel();
        JButton restartButton = new JButton("Restart");
        restartButton.setMargin(insetDefinition);
        JButton exitMainButton = new JButton("Exit To Main Menu");
        exitMainButton.setMargin(insetDefinition);

        pausePanel = new javax.swing.JPanel();
        restartButton = new javax.swing.JButton();
        exitMainButton = new javax.swing.JButton();

        restartButton.setText("Restart");
        exitMainButton.setText("Exit To Main Menu");

        javax.swing.GroupLayout pausePanelLayout = new javax.swing.GroupLayout(pausePanel);
        pausePanel.setLayout(pausePanelLayout);
        pausePanelLayout.setHorizontalGroup(
            pausePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pausePanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(restartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(exitMainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        pausePanelLayout.setVerticalGroup(
            pausePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pausePanelLayout.createSequentialGroup()
                .addContainerGap(496, Short.MAX_VALUE)
                .addGroup(pausePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(restartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitMainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        
        add(pausePanel);
    }	
	
	public static GameMenuPage getInstance(){
        return page;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
