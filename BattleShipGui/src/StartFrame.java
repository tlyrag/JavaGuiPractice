import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class StartFrame implements ActionListener{
	JFrame mainPage = new JFrame("BattleShip");
	JLabel mainLabel = new JLabel("Enter map size:");
	JTextField enterMapSize = new JTextField(12);
	JButton StartGame = new JButton("Start Game");
	
	StartFrame() {
		mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPage.setSize(400,400);
		mainPage.setLayout(new FlowLayout());
		StartGame.addActionListener(this);
		
		mainPage.add(mainLabel);
		mainPage.add(enterMapSize);
		mainPage.add(StartGame);
		mainPage.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String mapSize = enterMapSize.getText();
		Map CompMap = new Map(Integer.parseInt(mapSize),"Computer Map");
		Map HumanMap = new Map(Integer.parseInt(mapSize),"Commander Map");
	}
}
