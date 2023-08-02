import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Map implements ActionListener{
	JFrame mainPage = new JFrame();
	JButton[][] btns;
	WarShip[] warShips;
	JLabel statusLabel = new JLabel();
	HumanPlayer human = new HumanPlayer();
	CompPlayer comp  = new CompPlayer();
	
	
	Map(int mapSize,String Title) {
		mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPage.setSize(400,400);
		mainPage.setLayout(new FlowLayout());
		mainPage.setTitle(Title);
		generateButtons(mapSize);
		generateWarships(mapSize);
		mainPage.add(statusLabel);
		displayMap(Title);
		mainPage.setVisible(true);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj instanceof JButton ) {
			JButton btn = (JButton)obj;
			human.Fire(btn, warShips, btns, statusLabel);
			
		}
		
	}
	public void generateButtons(int size) {
		JButton[][] tempBtns = new JButton[size][size];
		for(int row=0;row<size;row++) {
			JPanel panel = new JPanel();
			for(int col=0;col<size;col++) {
				JButton btn = new JButton(row+"_"+col);
				btn.addActionListener(this);
				tempBtns[row][col] = btn;
				panel.add(btn);
				
			}
			mainPage.add(panel);
		}
		this.btns=tempBtns;
	}
	public void generateWarships(int size) {
		int qtdOfShips = size/2;
		WarShip[] tempWarShips = new WarShip[qtdOfShips];
		
		for(int i =0; i<qtdOfShips/3;i++) {
			Carrier carr = new Carrier(size);
			tempWarShips[i]=carr;
		}
		for(int i =qtdOfShips/3; i<qtdOfShips*2/3;i++) {
			Submarine sub = new Submarine(size);
			tempWarShips[i]=sub;
		}
		for(int i =qtdOfShips*2/3; i<qtdOfShips;i++) {
			Cruiser cruis = new Cruiser(size);
			tempWarShips[i]=cruis;
		}
		this.warShips=tempWarShips;
	}
	public void displayMap(String Title) {
		for(int i =0;i<this.warShips.length;i++) {
			int x= this.warShips[i].x;
			int[] y = this.warShips[i].y;
			System.out.println(Title);
			
			for(int j=0;j<y.length;j++) {
				System.out.println("Painting Warship");
				System.out.println("Warship " + i+": X Position " + x);
				System.out.println("Warship " + i+": Y Position " + y[j]);
				btns[y[j]][x].setBackground(Color.green);
			}
		}
		
	}
}
