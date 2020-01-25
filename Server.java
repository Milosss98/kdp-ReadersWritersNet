package kdp.rw;

import java.io.IOException;
import java.net.*;

public class Server {

	public static void main(String[] args) {
		ReadersWriters rw=new ReadersWriters();
		try (ServerSocket socket = new ServerSocket(Integer.parseInt(args[0]))) {
			
			System.out.println(String.format("Server started on port %s", args[0]));
			
			while (true) {
				
				Socket client = socket.accept();
				
				new WorkingThread(client, rw).start();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
