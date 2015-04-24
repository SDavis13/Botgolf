package game;

import java.awt.event.ActionEvent;
import javax.swing.*;

public class GameMenuPage extends View {
	static GameMenuPage page = new GameMenuPage(Consts.GAMEMENUPAGE);

	protected GameMenuPage(String name) {
        super(name);
        
        JPanel pausePanel = new JPanel();
        JButton restartButton = new JButton("Restart");
        JButton exitMainButton = new JButton("Exit To Main Menu");
        
//        javax.swing.GroupLayout pausePanelLayout = new javax.swing.GroupLayout(pausePanel);
//        pausePanel.setLayout(pausePanelLayout);
//        pausePanelLayout.setHorizontalGroup(
//            pausePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pausePanelLayout.createSequentialGroup()
//                .addGap(45, 45, 45)
//                .addComponent(restartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(30, 30, 30)
//                .addComponent(exitMainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(45, Short.MAX_VALUE))
//        );
//        pausePanelLayout.setVerticalGroup(
//            pausePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pausePanelLayout.createSequentialGroup()
//                .addContainerGap(196, Short.MAX_VALUE)
//                .addGroup(pausePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                    .addComponent(restartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(exitMainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addGap(54, 54, 54))
//        );
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getRootPane());
//        getRootPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(200, 200, 200)
//                .addComponent(pausePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(200, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                .addContainerGap(200, Short.MAX_VALUE)
//                .addComponent(pausePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(100, 100, 100))
//        );
        pausePanel.add(restartButton);
        pausePanel.add(exitMainButton);
    }	
	
	public static GameMenuPage getInstance(){
        return page;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
