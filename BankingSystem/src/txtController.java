import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;

public class txtController {
	static Path _file;
	static databaseController db = new databaseController();
	static ArrayList<client> customers = new ArrayList<>();
    
	public static void readFile(Path file) {
    	try {
    		_file = file;
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String Header = reader.readLine();
			String rawData = reader.readLine();
			while(rawData!= null) {
				String[] custData = rawData.split(",");
				Connection conn = db.createConnection();
				
				String name =custData[0];
				int accountNumbber = Integer.parseInt(custData[1]);
				int password = Integer.parseInt(custData[2]);
				Double funds= Double.parseDouble(custData[3]);
				client cust = new client(name,accountNumbber,password,funds);
				customers.add(cust);
				db.insertRows(conn, cust);
				rawData = reader.readLine();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
