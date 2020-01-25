package kdp.rw;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Book implements Serializable{
	String autor;
	String content;
	public String getAutor() {
		return autor;
	}
	public void setAutor(String a) {
		autor=a;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String c) {
		content=c;
	}
}
