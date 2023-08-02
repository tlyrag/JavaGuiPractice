import javax.swing.*;

public class FormLabel extends FormElement{
	JLabel label = new JLabel();
	
	@Override
	public Object getElement() {
		// TODO Auto-generated method stub
		return this.label;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return this.label.getText();
	}

}
