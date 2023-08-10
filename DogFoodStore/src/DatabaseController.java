import java.sql.*;

public class DatabaseController {
	String dogFoodUrl = "jdbc:derby:DogFood;create=true";
	Connection conn;
	DogFood currFood;
	
	public DatabaseController() {
		createConnection();
		dropTables();
		createTables();
	}
	public void createConnection() {
		try {

			this.conn = DriverManager.getConnection(dogFoodUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void dropTables() {
		try {
			Statement stmt = conn.createStatement();
			
			stmt.execute("DROP TABLE DogInventory");
			stmt.execute("DROP TABLE Customer");
		} catch (Exception err) {
			// TODO Auto-generated catch block
			System.out.println("Drop Table Error: "+ err.getMessage());
		}
	}
	
	public void createTables() {
		try {
			Statement stmt = conn.createStatement();
			
			stmt.execute("CREATE TABLE DogInventory "
					+ "(  "
					+ "Name VARCHAR(50),"
					+ "Size VARCHAR(50),"
					+ "UnitPrice DOUBLE"
					+ ") ");
			
			stmt.execute("CREATE TABLE Customer "
					+ "(  "
					+ "Name VARCHAR(50),"
					+ "OrderInfo VARCHAR(50),"
					+ "FinalPrice DOUBLE"
					+ ") ");
		} catch (Exception err) {
			// TODO Auto-generated catch block
			System.out.println("Create Table Error: "+ err.getMessage());
		}
		
	}
	
	public void InsertFood(String name,String size, Double unitPrice) {
		try {
			Statement stmt = conn.createStatement();

			
			stmt.execute("INSERT INTO DogInventory Values"
					+ "(  "
					+ "'" +name+"',"
					+ "'" +size+"',"
					+ unitPrice
					+  ")"
					);
			

		} catch (Exception err) {
			// TODO Auto-generated catch block
			System.out.println("Insert Food Table Error: "+ err.getMessage());
		}
	}
	public void InsertCust(String name,String Order, Double FinalPrice) {
		try {
			Statement stmt = conn.createStatement();
			
			stmt.execute("INSERT INTO Customer VALUES"
					+ "(  "
					+ "'" +name+"',"
					+ "'" +Order+"',"
					+ FinalPrice
					+  ")"
					);
			

		} catch (Exception err) {
			// TODO Auto-generated catch block
			System.out.println("Insert Customer Table Error: "+ err.getMessage());
		}
	}
	public ResultSet SelectFood() {
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet result = stmt.executeQuery("Select * From DogInventory");
			return result;
			

		} catch (Exception err) {
			// TODO Auto-generated catch block
			System.out.println("Insert Customer Table Error: "+ err.getMessage());
			return null;
		}
	}
	public ResultSet getFood(String name) {
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet result = stmt.executeQuery("Select * From DogInventory WHere name = '"+ name+"'");
			return result;
			

		} catch (Exception err) {
			// TODO Auto-generated catch block
			System.out.println("Insert Customer Table Error: "+ err.getMessage());
			return null;
		}
	}
}


