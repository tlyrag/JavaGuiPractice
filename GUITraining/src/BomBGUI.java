import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BomBGUI extends JFrame implements ActionListener{
	JButton[] buttons;
	int[] bombCoordinate = new int[2];
	JLabel playerTurn = new JLabel("Player 1 Turn");
	Player player1 = new Player("Player 1");
	Player player2 = new Player("Player 2");
	
	BomBGUI (int mapSize) {
		super("FIND THE BOMB");
		generateMap(mapSize);
		randomCoord(mapSize);
		add(playerTurn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(400,400);
		setVisible(true);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		JButton b = (JButton)obj;
		String[] position = b.getText().split("_");
		if(playerTurn.getText().equals("Player 1 Turn")) {
			b.setText(checkBomb(position,player1));
			b.setBackground(Color.blue);
			b.setForeground(Color.white);
			playerTurn.setText("Player 2 Turn");
		} else {
			b.setText(checkBomb(position,player2));
			b.setBackground(Color.red);
			b.setForeground(Color.white);
			playerTurn.setText("Player 1 Turn");
		}
		
		//JOptionPane.showMessageDialog(null, b.getText());
		
	}
	public String  checkBomb(String[] choice,Player player) {
		if(choice[0].equals(Integer.toString(bombCoordinate[0])) && choice[1].equals(Integer.toString(bombCoordinate[1]))) {
			return "BOOM";
		}
		
		player1.points++;
		return "No Bomb Here";
		
	}
	
	
	public void randomCoord(int mapSize) {
		int x = (int)(Math.random()*mapSize);
		int y = (int)(Math.random()*mapSize);
		this.bombCoordinate[0]=x;
		this.bombCoordinate[1]=y;
	}
	
	
	public void generateMap(int size) {
		
		for(int row =0;row<size;row++) {
			JPanel[] panels = new JPanel[size];
			
			panels[row] = new JPanel();
			panels[row].setLayout(new FlowLayout());
			for(int col =0;col<size;col++) {
				JButton[] buttons = new JButton[size];
				buttons[col] = new JButton(row+"_"+ col);
				buttons[col].addActionListener(this);
				panels[row].add(buttons[col]);
				
			}	
			add(panels[row]);
		}
		
	}




}
