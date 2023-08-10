import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LayOutDemo extends JFrame{
	
	JButton btn1 = new JButton("A");
	JButton btn2 = new JButton("b");
	JButton btn3 = new JButton("c");
	JButton btn4 = new JButton("d");
	JButton btn5 = new JButton("e");
	
	public static void main(String[] args) {
		LayOutDemo frame = new  LayOutDemo();
	}
	
	public LayOutDemo() {
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		btn1.setSize(10,10);
		add(btn1,BorderLayout.NORTH);
		add(btn2,BorderLayout.SOUTH);
		add(btn3,BorderLayout.LINE_START);
		add(btn4,BorderLayout.LINE_END);
		add(btn5,BorderLayout.CENTER);
		setVisible(true);
	}
	
}
