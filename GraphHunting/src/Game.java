import java.awt.*;

import javax.swing.*;

import org.w3c.dom.Attr;

public class Game extends JFrame{
	JButton[] JButtonArr;
	JFrame gameInfo = new JFrame();
	Players[] players;
	Map map;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game newGame = new Game(500);
	}
	Game(int mapSize) {
		Map newMap = new Map(mapSize);
		this.map=newMap;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setLayout(new FlowLayout());
		GameInforFrame info =new  GameInforFrame(newMap);
		setButtons(newMap);
		int turnCount =0;
		
		generatePlayers(newMap);
		setVisible(true);
		while(turnCount<100 && checkGameOver()){
			turn();
			turnCount++;
		}

	
	}
	public void setButtons(Map map) {
		JButton[] JButtonArr = new JButton[map.map.length];
		for(int i =0;i<JButtonArr.length;i++) {
			JButton ButtonMap = new JButton(i+"");
			JButtonArr[i]=ButtonMap;
			add(ButtonMap);
		}
		this.JButtonArr = JButtonArr;
	}
	public void generatePlayers(Map map) {
		Players[] players = new Players[map.map.length/10];
		int playerAmount = players.length;
		for(int i =0;i<playerAmount/3;i++) {
			int randomNext = (int) (Math.random()* (map.map.length-1));
			players[i] = new Agent(randomNext);
			JButtonArr[randomNext].setBackground(Color.red);
			JButtonArr[randomNext].setForeground(Color.white);
			JButtonArr[randomNext].setText(randomNext+"_"+"Agent Here");
		}
		for(int i =playerAmount/3;i<playerAmount;i++) {
			int randomNext = (int) (Math.random()*map.map.length);
			players[i] = new Thief(randomNext);
			JButtonArr[randomNext].setBackground(Color.blue);
			JButtonArr[randomNext].setForeground(Color.white);
			JButtonArr[randomNext].setText(randomNext+"_"+"Thief Here");
		}
		this.players=players;
	}
	public void move(Players player) {
		
			int currPosition = player.Position;
			
			JButtonArr[currPosition].setText(currPosition+"");
			JButtonArr[currPosition].setBackground(Color.gray);
			JButtonArr[currPosition].setForeground(Color.black);
			Node[] nextPositions =map.map[currPosition].next;
			
			int randomNextPosition = (int) (Math.random() * nextPositions.length);
			player.Position = nextPositions[randomNextPosition].index;
			
			if(player.getClass().getTypeName().equals("Thief") && player.status) {
				JButtonArr[player.Position].setBackground(Color.blue);
				JButtonArr[player.Position].setForeground(Color.white);
				JButtonArr[player.Position].setText(player.Position+"_"+"Thief Here");
			} else if (player.getClass().getTypeName().equals("Agent") && player.status){
				JButtonArr[player.Position].setBackground(Color.red);
				JButtonArr[player.Position].setForeground(Color.white);
				JButtonArr[player.Position].setText(player.Position+"_"+"Agent Here");
			}
	}
	public void atack(Players player) {
		if(player.getClass().getTypeName().equals("Agent")) {
			for(int i =0; i<players.length;i++) {
				if(players[i].Position==player.Position && players[i].getClass().getTypeName().equals("Thief")) {
					players[i].status=false;
					player.score++;
					System.out.println("One Thief is dead");
				}
			}
		} 
		
		int currPosition = player.Position;

	}
	public void turn() {
		for(int i =0; i<players.length;i++) {
			if(players[i].status) {
				move(players[i]);
				atack(players[i]);
			}

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("System Crashed");
			}
		}
	}
	public Boolean checkGameOver() {
		for(int i =0; i<players.length;i++) {
			if(players[i].status && players[i].getClass().getTypeName().equals("Thief")) {
				return true;
			}
		}
		return false;
	}

}
