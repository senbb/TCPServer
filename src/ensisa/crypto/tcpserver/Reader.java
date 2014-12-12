package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Reader {

	private DataInputStream inputStream;
	private String fileName;
	private int type;
	
	public Reader(InputStream inputStream) {
		this.inputStream = new DataInputStream (inputStream);
	}

	public int readInt () throws IOException {
		return inputStream.readInt();
	}

	public void receive() throws IOException {
		type = readInt ();
		switch(type){
		case Protocol.GET_FILE_LIST:
			break;
			
		case Protocol.GET_FILE:
			fileName= inputStream.readUTF();
			break;
		}
		
	}

	
	public int getType () {
		return type;
	}

	public String getFileName(){
		return fileName;
	}
}

