import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

public class TXTController {
	
	public static void readFile(String path,DBController db) {
		Path file = Paths.get(path);
		try {
			
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String Header = reader.readLine();
			String RawData = reader.readLine();
			
			while(RawData!=null) {
				String[] arr = RawData.split(",");
				int RoomID = Integer.parseInt(arr[0]);
				Double Price = Double.parseDouble(arr[1]);
				int beds = Integer.parseInt(arr[2]);
				int Stars = Integer.parseInt(arr[3]);
				db.insertRooms(RoomID, Price, beds, Stars);
				RawData = reader.readLine();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void writeFile(String path,String writeInfo) {
		Path file = Paths.get(path);
		try {
			FileChannel fc = FileChannel.open(file, WRITE,READ,CREATE,APPEND);
			byte[] data = writeInfo.getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(data);
			fc.write(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
