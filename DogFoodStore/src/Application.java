import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.*;

public class Application extends JFrame implements ActionListener, ItemListener{
	
	DatabaseController db = new DatabaseController();
	
	JPanel FoodPanel = new JPanel();
	ArrayList<JRadioButton> foodBtns = new ArrayList<JRadioButton>();
	ButtonGroup foodBtnGroup = new ButtonGroup();
	DogFood currFood = new DogFood();
	
	JPanel foodInfo = new JPanel();
	JLabel foodNameLabel = new JLabel();
	JLabel foodSizeLabel = new JLabel();
	JLabel foodPriceLabel = new JLabel();
	JLabel SpaceLabel = new JLabel("--------------------");
	JLabel OrderInfoLabel = new JLabel("Order Information");
	JLabel TotalAmountLabel = new JLabel("Total Amount: ");
	
	
	JPanel controlInfo = new JPanel();
	JLabel userNameLabel = new JLabel("Enter your Name");
	JTextField  userNameTextField = new JTextField(15);
	JLabel quantityLabel = new JLabel("Enter Desired Quantity");
	JTextField  quantityTextField = new JTextField(15);

	JCheckBox fastDelivery = new JCheckBox("Fast Delivery");
	JCheckBox recurrentPurchase = new JCheckBox("Recurrent Purchase");
	
	
	
	
	public static void main(String args[]) {
		
		Application app = new Application();
	}
	
	public Application() {
		super("Food Market");
		txtController txt = new txtController("dogFood.txt");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(800,800);
		foodPannel();
		foodInfo();
		controPanel();
		setVisible(true);
	}
	public void foodInfo() {
		foodInfo.setLayout(new BoxLayout(foodInfo, BoxLayout.Y_AXIS));
		foodNameLabel.setText("Name: ");
		foodSizeLabel.setText("Size: ");
		foodPriceLabel.setText("Price: ");
		foodInfo.add(foodNameLabel);
		foodInfo.add(foodSizeLabel);
		foodInfo.add(foodPriceLabel);
		foodInfo.add(SpaceLabel);
		foodInfo.add(OrderInfoLabel);
		foodInfo.add(TotalAmountLabel);
		foodInfo.setBorder(BorderFactory.createEmptyBorder(20,100,20,20));
		add(foodInfo,BorderLayout.CENTER);
	}
	
	public void foodPannel() {
		
		FoodPanel.setLayout(new BoxLayout(FoodPanel, BoxLayout.Y_AXIS));
		ResultSet result = db.SelectFood();
		try {
			while(result.next()) {
				String name = result.getString(1);
				String size = result.getString(2);
				Double unitPrice = result.getDouble(3);
				currFood = new DogFood(name,size,unitPrice);
				JRadioButton rbtn = new JRadioButton(name);
				rbtn.addActionListener(this);
				foodBtns.add(rbtn);
				foodBtnGroup.add(rbtn);
				FoodPanel.add(rbtn);
			}
			add(FoodPanel,BorderLayout.LINE_START);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(FoodPanel,BorderLayout.LINE_START);
		
	}
	
	public void controPanel() {
		controlInfo.setLayout(new BoxLayout(controlInfo, BoxLayout.Y_AXIS));
		controlInfo.add(userNameLabel);
		controlInfo.add(userNameTextField);
		controlInfo.add(quantityLabel);
		controlInfo.add(quantityTextField);

		
		controlInfo.add(fastDelivery);
		controlInfo.add(recurrentPurchase);
		quantityTextField.addActionListener(this);
		
		fastDelivery.addItemListener(this);
		recurrentPurchase.addItemListener(this);

		add(controlInfo,BorderLayout.LINE_END);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (fastDelivery.isSelected()) {

			String price = TotalAmountLabel.getText().substring(TotalAmountLabel.getText().indexOf(":")+1,TotalAmountLabel.getText().indexOf(".")+2);
			double FinalPrice = Double.parseDouble(price)+10;
			TotalAmountLabel.setText("Total Amount: " +  FinalPrice	);
		} else if (!fastDelivery.isSelected()) {
			String price = TotalAmountLabel.getText().substring(TotalAmountLabel.getText().indexOf(":")+1,TotalAmountLabel.getText().indexOf(".")+2);
			double FinalPrice = Double.parseDouble(price)-10 ;
			TotalAmountLabel.setText("Total Amount: " +  FinalPrice);
		}
		
		else if(recurrentPurchase.isSelected()) {
			String price = TotalAmountLabel.getText().substring(TotalAmountLabel.getText().indexOf(":")+1,TotalAmountLabel.getText().indexOf(".")+2);
			double FinalPrice = Double.parseDouble(price) *0.9;
			TotalAmountLabel.setText("Total Amount: " +  FinalPrice	);
		} else if(!recurrentPurchase.isSelected()) {
			String price = TotalAmountLabel.getText().substring(TotalAmountLabel.getText().indexOf(":")+1,TotalAmountLabel.getText().indexOf(".")+2);
			double FinalPrice = Double.parseDouble(price) /0.9;; 
			TotalAmountLabel.setText("Total Amount: " +  FinalPrice	);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if (foodBtns.contains(obj)) {
			
			Enumeration<AbstractButton> allRadioButton = foodBtnGroup.getElements();
			while(allRadioButton.hasMoreElements()) {
				JRadioButton tmp = (JRadioButton) allRadioButton.nextElement();
				if(tmp.isSelected())
				{	
					System.out.println(tmp.getText());
					getFoodInDb(tmp.getText());
					
					foodNameLabel.setText("Food Name: "+currFood.name);
					foodSizeLabel.setText("Food Size: "+ currFood.size);
					foodPriceLabel.setText("Food Price: "+currFood.unitPrice);
				}
			}
		}
		if(obj == quantityTextField) {
			TotalAmountLabel.setText("Total Amount: " +  (Integer.parseInt(quantityTextField.getText())*currFood.unitPrice));
		}
	}
	public void getFoodInDb(String Name) {
		ResultSet result = db.getFood(Name);
		try {
			while(result.next()) {
				String name = result.getString(1);
				String size = result.getString(2);
				Double unitPrice = result.getDouble(3);
				currFood = new DogFood(name,size,unitPrice);
			} 
		} catch(Exception err) {
			System.out.println("Exception " + err.getMessage());
		}
	}
		
	

}

