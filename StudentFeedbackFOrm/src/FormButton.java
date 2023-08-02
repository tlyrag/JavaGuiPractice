import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FormButton extends FormElement implements ActionListener{
	JButton btn = new JButton();
	
	public FormButton() {
	// TODO Auto-generated constructor stub
		this.btn.addActionListener(this);
	}
	@Override
	public Object getElement() {
		// TODO Auto-generated method stub
		
		return this.btn;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return this.btn.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Form Submitted");
	}

}
