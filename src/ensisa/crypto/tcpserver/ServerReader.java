package ensisa.crypto.tcpserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import ensisa.crypto.tcpcommon.PROTOCOL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerReader {

    private DataInputStream inputStream;
    private String fileName;
    private int type;

    public ServerReader(InputStream inputStream) {
        this.inputStream = new DataInputStream(inputStream);
    }

    /**
     * @return int read
     */
    public int readInt() {
        try {
            return inputStream.readInt();
        } catch (IOException ex) {
            Logger.getLogger(ServerReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * receive the data from the client
     */
    public void receive() {
        type = readInt();
        switch (type) {
            case PROTOCOL.GET_FILE_LIST:
                break;

            case PROTOCOL.GET_FILE:
                fileName = readUTF();
                break;
        }
    }

    /**
     * @return type of data
     */
    public int getType() {
        return type;
    }

    /**
     * @return return the filename
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Read a stirng
     *
     * @return a string read
     */
    private String readUTF() {
        try {
            return inputStream.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(ServerReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }
}
