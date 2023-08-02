import java.awt.*;

import javax.swing.*;
public class GUITests {
	static JFrame myFrame = new JFrame();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JButton btn = new JButton();
		myFrame.add(btn);
		btn.setSize(new Dimension(100,100));
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
		myFrame.setSize(1000,1000);
		myFrame.setVisible(true); 
	}

}
