package ensisa.crypto.tcpserver;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import ensisa.crypto.tcpcommon.FileHelper;

public class ServerWriter {

    private OutputStream outputStream;
    private ByteArrayOutputStream baos;
    private DataOutputStream output;
    
    private FileHelper fileHelper;

    public ServerWriter(OutputStream outputStream) {
        baos = new ByteArrayOutputStream();
        output =  new DataOutputStream(baos);
        this.outputStream = outputStream;
    }

    @Deprecated
    //useless
    private void writeBoolean(boolean v) {
        try {
            if (v) {
                output.writeInt(1);
            } else {
                output.writeInt(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeInt(int v) {
        try {
            output.writeInt(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeLong(long v) {
        try {
            output.writeLong(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeUTF(String v) {
        try {
            output.writeUTF(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeByteArray(byte[] content) {
        try {
            writeInt(content.length);
            output.write(content, 0, content.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send() {
        byte[] message = baos.toByteArray();
        try {
            outputStream.write(message);
            baos.reset();
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeFile(File file) {
        writeLong(file.length());
        //writeByteArray();
    }

    void writeList() {
        writeInt(3);
    }

    void writeType(int type) {
        writeInt(type);
    }
}
