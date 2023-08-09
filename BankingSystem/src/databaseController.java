import java.sql.*;

public class databaseController {


   final String DB_URL = "jdbc:derby:BankDB;create=true";
   
	public Connection createConnection () {
		
		try {
			Connection conn = DriverManager.getConnection(this.DB_URL );
			return conn;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
		
	
	public  void dropTable(Connection conn, String tableName) {
		System.out.println("Checking for existing tables.");
		
		try {
			Statement stmt  = conn.createStatement();
			stmt.execute("DROP TABLE "+tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error:"+ e.getMessage());
			//e.printStackTrace();
		};
	}
	public  void createTable(Connection conn) {
		System.out.println("Creating tables.");
		
		try {
			Statement stmt  = conn.createStatement();
			stmt.execute(
						"CREATE TABLE BankInfo (" +
						"Name VARCHAR(35), " +
	                    "AccountNum INT NOT NULL PRIMARY KEY, " +
	                    "Password INT, " +
	                    "Funds DOUBLE" +
	                    
	                     ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	public void insertRows(Connection conn,client cust) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.execute("INSERT INTO BankInfo VALUES ( " +
				"'"+cust.Name+"',"+
				+cust.accountNumbber+","+
				+cust.password+","+
				+cust.funds+
	           ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ResultSet selectRows(Connection conn,int AccountNumber,int Password) {
		try {
			Statement stmt = conn.createStatement();
			System.out.println("DB Connected");
			 ResultSet queryResult = stmt.executeQuery(
					 "SELECT * FROM BankInfo "
					+ "Where AccountNum = "+AccountNumber
					+ " AND Password = " + Password
					);
		
			 
			 
			 return queryResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error no SelectRows:"+ e.getMessage());
			return null;
		}
	}
	public void updateInfo(Connection conn,client user) {

		try {
			Statement stmt = conn.createStatement();
			stmt.execute(
					"UPDATE BankInfo " 
					+"SET Funds = " +user.funds
					+" WHERE AccountNum = " +user.accountNumbber);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
