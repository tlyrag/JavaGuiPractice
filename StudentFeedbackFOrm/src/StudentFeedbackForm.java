import java.awt.FlowLayout;

import javax.swing.*;

public class StudentFeedbackForm extends JFrame
{
	FormButton submitBtn = new FormButton();
	
	
	public static void main(String[] args) {
	
		StudentFeedbackForm form = new StudentFeedbackForm();
	}
	
	StudentFeedbackForm () {
		super("Student Feedback Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(300,500);
		
		add(formGenerator("2175")); 
		add(formGenerator("2230"));
		add(formGenerator("2270"));
		
		FormButton submit = new FormButton();
		submit.btn.setText("Submit");
		add(submit.btn);
		setVisible(true);
		
	}
	public static JPanel formGenerator(String className) {
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.X_AXIS));
		
		FormLabel mainLabel = new FormLabel();
		mainLabel.label.setText(className);
		newPanel.add(mainLabel.label);
		FormCheckBox isEnrolled = new FormCheckBox();
		isEnrolled.CheckBox.setText("Enrolled");
		newPanel.add(isEnrolled.CheckBox);
		String[] comboOpotions = { "Very Dissatisfied", "Dissatisfied", "Neutral", "Satisfied", "Very Satisfied"};
		FormComboBox classFeedBackGrade = new FormComboBox(comboOpotions);
		newPanel.add(classFeedBackGrade.comboBox);
		return newPanel;
		
	}
	
	
}
