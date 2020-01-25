package kdp.rw;

import java.io.Serializable;
import java.util.concurrent.locks.*;

public class ReadersWriters implements Serializable{

	int nr, nw;
    Book book=new Book();
	synchronized public void startRead() {
		try {
			while (nw > 0)
				wait();
			nr++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	synchronized public void startWrite() {
		try {
			while (nr > 0 || nw > 0)
				wait();
			nw++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	synchronized public void endRead() {
             nr--;
             if(nr==0) notifyAll();
	}

	synchronized public void endWrite() {
           nw--;
           notifyAll();
	}

}
