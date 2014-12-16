package ensisa.crypto.tcpserver;

import ensisa.crypto.tcpcommon.PROTOCOL;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {

    ServerSocket connection = null;

    public void connect() throws IOException {
        connection = new ServerSocket(PROTOCOL.PORT);
    }

    @Deprecated
    //should be in Session
    public void ftp() {
        try {
            Socket client = connection.accept();
            ServerReader reader = new ServerReader(client.getInputStream());
            reader.receive();
            ServerWriter writer = new ServerWriter(client.getOutputStream());
            switch(reader.getType())
            {
                case PROTOCOL.GET_FILE_LIST:
                    writer.writeType(PROTOCOL.SEND_FILE_LIST);
                    writer.writeList();
                    break;
                case PROTOCOL.GET_FILE:
                    writer.writeType(PROTOCOL.SEND_FILE);
                    writer.writeFile(new File(reader.getFileName()));
                    break;
            }
            writer.send();
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param args les (éventuels) arguments passés en ligne de commande
     */
    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        
        try {
            server.connect();
            server.ftp();
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
