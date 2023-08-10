import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class txtController {
	
	Path file;
	DatabaseController db = new DatabaseController();
	
	txtController(String path) {
		this.file =  Paths.get(path);
		readFile();
	}
	
	public void readFile() {
		try {
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String header = reader.readLine();
			String rawData = reader.readLine();
			
			while(rawData!=null) {
				String[] arr = rawData.split(",");
				
				String name =arr[0];
				String size =arr[1];
				Double unitPrice = Double.parseDouble(arr[2].substring(1));
				db.InsertFood(name, size, unitPrice);
				rawData=reader.readLine();
			
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
