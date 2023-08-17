import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class AppView extends JFrame implements ItemListener, ActionListener{

	DBController db = new DBController();
	Room currRoom;
	
	
	JPanel ControlerPanel = new JPanel();
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
	JComboBox<String> roomsComboBox = new JComboBox<>(model);
	
	JPanel RoomInfoPanel = new  JPanel();
	JLabel RoomIDLabel = new JLabel("Room:");
	JLabel RoomPriceLabel = new JLabel("Price:");
	JLabel RoomBedsLabel = new JLabel("Beds:");
	JLabel RoomStarsLabel = new JLabel("Stars:");
	
	AppView() {
		super("Hotel Application");
		TXTController.readFile("Rooms.txt", db);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setLayout(new BorderLayout());
		CreateComboBox();
		CreateRoomInfoPanel();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppView app = new AppView();
	}
	
	public  void CreateComboBox() {
		ResultSet result = db.getAllRooms();
		
		try {
			while(result.next()) {
				int RoomID = result.getInt(1);
				double Price = result.getDouble(2);
				int Beds = result.getInt(3);
				int Stars = result.getInt(4);
				
				model.addElement("Room :" + RoomID);
			}
		
		ControlerPanel.add(roomsComboBox);
		roomsComboBox.addItemListener(this);
		add(ControlerPanel,BorderLayout.LINE_START);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void CreateRoomInfoPanel() {
		RoomInfoPanel.setLayout(new BoxLayout(RoomInfoPanel,BoxLayout.Y_AXIS));
		RoomInfoPanel.add(RoomIDLabel);
		RoomInfoPanel.add(RoomPriceLabel);
		RoomInfoPanel.add(RoomBedsLabel);
		RoomInfoPanel.add(RoomStarsLabel);
		add(RoomInfoPanel,BorderLayout.CENTER);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj == roomsComboBox) {
			String room = (String) roomsComboBox.getSelectedItem();
			int RoomID = Integer.parseInt(room.substring(room.indexOf(":")+1)); 
			ResultSet result = db.selectRoom(RoomID);
			
			try {
				while(result.next()) {
					
					double Price = result.getDouble(2);
					int Beds = result.getInt(3);
					int Stars = result.getInt(4);
					
					currRoom = new Room(RoomID,Price,Beds,Stars);
					RoomIDLabel.setText("Room:"+ RoomID);
					RoomPriceLabel.setText("Price:"+ Price);
					RoomBedsLabel.setText("Beds:"+ Beds);
					RoomStarsLabel.setText("Stars:"+ Stars);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		}
	}


}
 