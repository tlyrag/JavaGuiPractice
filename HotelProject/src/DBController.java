import java.sql.*;

public class DBController {
	String DBURL = "jdbc:derby:hotel;create=true";
	Connection conn;
	
	DBController() {
		createConnection();
		dropTables();
		createTables();
	}
	public void createConnection() {
		try {
			this.conn = DriverManager.getConnection(DBURL);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in Connection :" + e.getMessage());
		}
	}
	public void dropTables() {
		try {
			Statement stmt = conn.createStatement();
			String dropTableRoom = "DROP TABLE Rooms";
			String dropTableReservation = "DROP TABLE Reservation";
			stmt.execute(dropTableReservation);
			stmt.execute(dropTableRoom);
			System.out.println("Tables Droped");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in DropTables :" + e.getMessage());
			
		}
		
	}
	public void createTables() {
		try {
			
			Statement stmt = conn.createStatement();
			
			String sqlRooms ="CREATE TABLE Rooms (" +
					"RoomID INT, " +
                    "Price Double, " +
                    "Beds INT, " +
                    "Stars INT" +
                     ")";
			
			String sqlGuest ="CREATE TABLE Reservation (" +
					"GuestID INT, " +
                    "Name VARCHAR(100), " +
                    "RoomID INT, " +
                    "PricePaid Double" +
                     ")";
			stmt.execute(sqlRooms);
			stmt.execute(sqlGuest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Creating Tables :" + e.getMessage());
		}
	}
	public void insertRooms(int RoomID,Double price,int beds,int stars) {
		
		try {
			Statement stmt = conn.createStatement();
			String insertRoomSql = "Insert Into Rooms Values (" +
					RoomID+","+
					price+","+
					beds+","+
					stars+
	                 ")";
			stmt.execute(insertRoomSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Inserting into Rooms Tables :" + e.getMessage());
		}
		
	}
	public void insertReservation(int GuestID,String name,int RoomID,int PricePaid) {
		try {
			Statement stmt = conn.createStatement();
			String insertReservSql = "Insert Into Reservation Values (" +
					+GuestID+","+
					"'" +name+"',"+
					+RoomID+","+
					+PricePaid+
	                 ")";
			stmt.execute(insertReservSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Inserting into Reservation Tables :" + e.getMessage());
		}
	}
	
	public ResultSet selectReservation(int GuestID) {
		try {
			Statement stmt = conn.createStatement();
			String selectReservSql = "Select * from Reservation "
					+ "Where GuestId = " + GuestID;
			return stmt.executeQuery(selectReservSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Selecting  Reservation  :" + e.getMessage());
			return null;
		}
	}
	public ResultSet selectRoom(int RoomID) {
		try {
			Statement stmt = conn.createStatement();
			String selectReservSql = "Select * from Rooms "
					+ "Where RoomID = " + RoomID;
			return stmt.executeQuery(selectReservSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Selecting  Room  :" + e.getMessage());
			return null;
		}
	}
	public ResultSet getAllRooms() {
		try {
			Statement stmt = conn.createStatement();
			String selectReservSql = "Select * from Rooms";				
			return stmt.executeQuery(selectReservSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Selecting  Room  :" + e.getMessage());
			return null;
		}
	}
}

