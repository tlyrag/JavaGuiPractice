import java.awt.*;
import java.lang.ProcessHandle.Info;

import javax.swing.*;

public class GameInforFrame {
	JFrame gameInfo = new JFrame();
	JLabel[] InfoLabelArr;
	
	
	GameInforFrame(Map map) {
		gameInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameInfo.setSize(300,300);
		gameInfo.setLayout(new FlowLayout());
		setLabels(map);
		
		
		
		gameInfo.setVisible(true);
	}
	public void setLabels(Map map) {
		JLabel[] InfoLabelArr = new JLabel[map.map.length];
		for(int i =0;i<InfoLabelArr.length;i++) {
			JLabel InfoLabel = new JLabel(map.displayNodeList(map.map[i]));
			gameInfo.add(InfoLabel);
		}
		this.InfoLabelArr = InfoLabelArr;
	}
	

}
