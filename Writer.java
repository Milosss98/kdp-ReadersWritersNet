package kdp.rw;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Writer {
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public Writer(String host, int port) {
		try {
			socket = new Socket(host, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
			Writer w = new Writer(args[0], Integer.parseInt(args[1]));
         try {
        	 System.out.println("Writer started");
			for (int i = 0; i < 10; i++) {
				String s = w.startWrite();
				Thread.sleep((long)((double)Math.random()*5000+5000));
				System.out.println(s);
				w.endWrite(i);
				//Thread.sleep((long)15000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try{
				w.out.close();
			
				w.in.close();
			
				w.socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void endWrite(int i) {
		try {
			out.writeObject("endWrite");
			out.writeObject("Upis broj "+i);
			out.flush();
			in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String startWrite() {
		try {
			out.writeObject("startWrite");
			out.flush();
			String s = (String) in.readObject();
			return s;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
