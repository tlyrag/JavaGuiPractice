import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class FormComboBox extends FormElement implements ItemListener{
	JComboBox comboBox = new JComboBox();
	
	public FormComboBox(String[] data) {
		// TODO Auto-generated constructor stub
		JComboBox tempComboBox = new JComboBox(data);
		this.comboBox= tempComboBox;
	}
	@Override
	
	public Object getElement() {
		// TODO Auto-generated method stub
		return this.comboBox;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return this.comboBox.getToolTipText();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}
