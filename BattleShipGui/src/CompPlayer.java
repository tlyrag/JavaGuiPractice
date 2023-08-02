import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

public class CompPlayer implements Play{
	String name = "Comp";
	int score =0;
	String achievements;
	@Override
	public void CalDistance() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Fire(JButton btn, WarShip[] warShips,JButton[][] btns ,JLabel statusLabel) {
		// TODO Auto-generated method stub
		int y = Integer.parseInt(btn.getText().split("_")[0]);
		int x = Integer.parseInt(btn.getText().split("_")[1]);
		System.out.println("X Position Selected: "+x+" y Position Selected: "+y);
		
		for(int i =0;i<warShips.length;i++) {
			int xWar= warShips[i].x;
			int[] yWar = warShips[i].y;
			System.out.println("Checking Warship: " +i);
			System.out.println("Warship X Coordinate: " +xWar);
			if(x==xWar) {
				for(int j=0; j<yWar.length;j++) {
					System.out.println("Warship Y Coordinate: " +yWar[j]);
					System.out.println("Comparing y:"+y+" With yWar: "+yWar[j]);
					System.out.println(y==yWar[j]);
					if(y==yWar[j]) {
						btns[y][x].setBackground(Color.red);
						btns[y][x].setText("Hit");
						warShips[i].shield--;
						if(warShips[i].shield==0) {
							warShips[i].status=false;
							statusLabel.setText(warShips[i].getClass().getName() + "Was Destroyed");
						}
						return;
					}
				}
			
			} else {
				btns[y][x].setBackground(Color.blue);
				btns[y][x].setText("Miss");;
			}
		}
	}

}
