import javax.swing.*;

public class FormCheckBox extends FormElement{
	JCheckBox CheckBox = new JCheckBox();
	@Override
	public Object getElement() {
		// TODO Auto-generated method stub
		return this.CheckBox;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return this.CheckBox.getText();
	}

}
