import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.*;


public class Application extends JFrame implements ActionListener, ItemListener{

	JLabel NameLabel = new JLabel("Name");
	JLabel AccountNumberLabel = new JLabel("Account Number");
	JLabel PasswordLabel = new JLabel("Password");
	JLabel FundsLabel = new JLabel("Funds");
	JLabel StatusLabel = new JLabel("Enter Account Number");
	
	
	JLabel NameText = new JLabel("");
	JLabel AccountNumberText = new JLabel("");
	JLabel PasswordText = new JLabel("");
	JTextField FundsText = new JTextField(12);

	
	JButton WithdrawBtn = new JButton("Whitdraw");
	JButton DepositBtn = new JButton("Deposit");
	JButton CheckBalanceBtn = new JButton("Check Balance");
	JButton ClearFields = new JButton("Clear");
	JButton ConfirmBtn = new JButton("ConfirmBtn");

	static String AccountNumber ="";
	static String PasswordNumber="";
	static boolean isAcct = true;
	static boolean isPW = false;
	
	ArrayList<JButton> KeyPad = new ArrayList<JButton>();
	
	static client Customer = new client();
	
	
	static ArrayList<client> customers = new ArrayList<client>();
	static databaseController db = new databaseController();
	static Connection conn = db.createConnection();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		db.dropTable(conn, "BankInfo");
		db.createTable(conn);
		Path filepath = Paths.get("clients.txt");

		txtController.readFile(filepath);
		customers = txtController.customers;
		Application bank = new Application();
		
	}
	Application() {
		super("Banking System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700,300);
		setLayout(new BorderLayout());
		
		keyPad();
		UserPannel();
		Control();
		statusLabel();
		setVisible(true);
		
	}
	public  void UserPannel() {
		JPanel UserPannel = new JPanel();
		UserPannel.setLayout(new BoxLayout(UserPannel, BoxLayout.Y_AXIS));
		UserPannel.add(NameLabel);
		UserPannel.add(NameText);
		UserPannel.add(AccountNumberLabel);
		UserPannel.add(AccountNumberText);
		UserPannel.add(PasswordLabel);
		UserPannel.add(PasswordText);
		UserPannel.add(FundsLabel);
		UserPannel.add(FundsText);
		add(UserPannel,BorderLayout.LINE_START);
	}
	public void Control() {
		JPanel UserPannel = new JPanel();
		UserPannel.setLayout(new BoxLayout(UserPannel, BoxLayout.Y_AXIS));
		UserPannel.add(WithdrawBtn);
		WithdrawBtn.addActionListener(this);
		
		UserPannel.add(DepositBtn);
		DepositBtn.addActionListener(this);
		
		UserPannel.add(CheckBalanceBtn);
		CheckBalanceBtn.addActionListener(this);
		

		
		
		add(UserPannel,BorderLayout.LINE_END);
	}
	public void statusLabel() {
		JPanel UserPannel = new JPanel();
		UserPannel.setLayout(new FlowLayout());
		UserPannel.add(StatusLabel);
		
	
		add(UserPannel,BorderLayout.SOUTH);
	}
	public void keyPad() {
		JPanel MainPannel = new JPanel();
		MainPannel.setLayout(new BoxLayout(MainPannel, BoxLayout.Y_AXIS));

		
		JPanel PadPannel = new JPanel();
		for(int i=0;i<10;i++) {
			JButton pad = new JButton(i+"");
			pad.addActionListener(this);
			PadPannel.add(pad);
			KeyPad.add(pad);
		}
		MainPannel.add(PadPannel);
		MainPannel.add(ConfirmBtn,LEFT_ALIGNMENT);
		MainPannel.add(ClearFields,LEFT_ALIGNMENT);
		ClearFields.addActionListener(this);
		ConfirmBtn.addActionListener(this);
		add(MainPannel,BorderLayout.CENTER);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void randomOrder() {
		ArrayList<JButton> TempKeyPad = (ArrayList<JButton>) KeyPad.clone();
		ArrayList<String> RandomKeyPad = new ArrayList<String>();
		
		
		while(TempKeyPad.size()>0) {
		
			int randomIndex = (int)(Math.random()*TempKeyPad.size());
			
			RandomKeyPad.add(TempKeyPad.get(randomIndex).getText());

			TempKeyPad.remove(randomIndex);
		
			
		}
	
		for(int i=0;i<KeyPad.size();i++) {
			
			String key = RandomKeyPad.get(i);
			
			//System.out.println(KeyPad.get(i).getText()+ "is now "+key);
			KeyPad.get(i).setText(key);
			
		}
		System.out.println(" ");
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj == ClearFields) {
			StatusLabel.setText("Fields Cleared");
			NameText.setText(""); 
			AccountNumberText.setText("");
			PasswordText.setText(""); 
			FundsText.setText(""); 
			PasswordNumber="";
			AccountNumber="";
			Customer = new client();
			isAcct=true;
			StatusLabel.setText("Enter Account Number");
		} else if(KeyPad.contains(obj)) {
			JButton btn = (JButton) obj;
			if(isAcct) {
				AccountNumber+=btn.getText();
				AccountNumberText.setText(AccountNumber);
				
			} else {
				PasswordNumber+=btn.getText();
				randomOrder();
				PasswordText.setText(PasswordNumber);
				
			}
			
		} else if(obj == ConfirmBtn) {
			if (!isAcct) {
				
				isAcct=true;
				int userAcct = Integer.parseInt(AccountNumber);
				int userPw = Integer.parseInt(PasswordNumber);
				
				ResultSet result = (ResultSet) db.selectRows(conn, userAcct,userPw);
				StatusLabel.setText("Login Successfull");
				try {
					while(result.next()) {
						String userName = result.getString(1);
						Double userFunds = result.getDouble(4);
						NameText.setText(userName);
						FundsText.setText(userFunds.toString());
						
						client currUser = new client(userName,userAcct,userPw,userFunds);
						Customer= currUser;
						
					}
				} catch (Exception err) {
					// TODO Auto-generated catch block
					System.out.println("Errror in Select: "+err.getMessage());
				}
				
				System.out.println(result);
				
				
			} else if(isAcct) {
				
				isAcct=false;
				StatusLabel.setText("Enter Password");
			}
		} else if(obj==WithdrawBtn) {
			Customer.withdraw(Double.parseDouble(FundsText.getText()) , Customer.password);
			db.updateInfo(conn, Customer);
			StatusLabel.setText("Withdraw Successfull!");
			
			
		} else if(obj==DepositBtn) {
			Customer.deposit(Double.parseDouble(FundsText.getText()) , Customer.password);
			db.updateInfo(conn, Customer);
			StatusLabel.setText("Deposit Successfull!");
		}else if(obj==CheckBalanceBtn) {
			ResultSet result = (ResultSet) db.selectRows(conn, Customer.accountNumbber,Customer.password);
			StatusLabel.setText("Please Check you Balance");
			
			try {
				while(result.next()) {
					String userName = result.getString(1);
					Double userFunds = result.getDouble(4);
					NameText.setText(userName);
					FundsText.setText(userFunds.toString());

					
				}
			} catch (Exception err) {
				// TODO Auto-generated catch block
				System.out.println("Errror in Select: "+err.getMessage());
			}
		}
		
		
	
		
		
	}

}
