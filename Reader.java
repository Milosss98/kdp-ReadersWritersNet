package kdp.rw;

import java.io.*;
import java.net.Socket;

public class Reader {

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public Reader(String host, int port) {
		try {
			socket = new Socket(host, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
			Reader r = new Reader(args[0], Integer.parseInt(args[1]));
         try {
        	 System.out.println("Reader started");
			for (int i = 0; i < 10; i++) {
				String b=r.startRead();
				Thread.sleep((long)((double)Math.random()*5000+5000));
				System.out.println("Book number"+i);
				System.out.println(b);
				r.endRead();
				//Thread.sleep((long)15000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try{
				r.out.close();
			
				r.in.close();
			
				r.socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void endRead() {
		try {
			out.writeObject("endRead");
			out.flush();
			in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String startRead() {
		try {
			out.writeObject("startRead");
			out.flush();
		   Book b = (Book) in.readObject();
		   return b.getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}
}
