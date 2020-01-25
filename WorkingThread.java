package kdp.rw;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class WorkingThread extends Thread {
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
    private ReadersWriters rw;
	public WorkingThread(Socket client, ReadersWriters rw) {
		socket = client;
		this.rw=rw;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			 in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}

	public void run() {
		try  {
            
			while(!socket.isClosed()) {
				String op=(String)in.readObject();
				switch (op) {
				case "startRead":
					rw.startRead();
					Book b=new Book();
					b.setContent(rw.book.getContent());
					out.writeObject(b);
					out.flush();
				    break;
				case "startWrite":
					rw.startWrite();
					out.writeObject(rw.book.getContent());
					out.flush();
					break;
				case "endRead":
					rw.endRead();
					out.writeObject("OK");
					out.flush();
					break;
				case "endWrite":
					String bb=(String)in.readObject();
					rw.book.setContent(bb);
					rw.endWrite();
					out.writeObject("OK");
					out.flush();
					break;
				default:break;
				}		
			}
			out.close();in.close();socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
