import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class GUI {
	JFrame StudGrade = new JFrame("Student Grades");
	JLabel Quiz1Label = new JLabel("Quiz 1 Grade");
	JLabel Quiz2Label = new JLabel("Quiz 2 Grade");
	JLabel Assignment = new JLabel("Assignment Grade");
	JLabel Midterm = new JLabel("Midterm Grade");
	JLabel Final = new JLabel("Final Grade");
	JPanel LabelPanel = new JPanel();
	
	GUI () {
		this.LabelPanel.setLayout(new BoxLayout(LabelPanel, BoxLayout.Y_AXIS));
		this.LabelPanel.add(Quiz1Label);
		this.LabelPanel.add(Quiz2Label);
		this.LabelPanel.add(Assignment);
		this.LabelPanel.add(Midterm);
		this.LabelPanel.add(Final);
		this.StudGrade.add(LabelPanel,BorderLayout.LINE_START);
		
		
		
		initiateFrame();
	}
	public  void initiateFrame() {
		this.StudGrade.setSize(400,400);
		this.StudGrade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.StudGrade.setVisible(true);
		this.StudGrade.setLayout(new BorderLayout());
		
	}
	
	

}
