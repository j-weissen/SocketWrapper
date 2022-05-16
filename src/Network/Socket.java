package Network;

import java.io.*;
import java.util.Objects;

public abstract class Socket<T> extends Thread {
    public static final String CLOSE = "close";
    protected java.net.Socket socket;
    protected ObjectOutputStream send;
    protected ObjectInputStream recv;
    protected T object;


    Socket() {
        object = null;
    }
    
    protected void setStreams() throws IOException {
        send = new ObjectOutputStream(socket.getOutputStream());
        recv = new ObjectInputStream(socket.getInputStream());
    }

    public void sendData(T data) throws IOException {
        send.writeObject(data);
    }

    private Object recvData() throws IOException, ClassNotFoundException {
        return recv.readObject();
    }

    @Override
    public void run() {
        Object rawData;
        while (true) {
            try {
                rawData = recvData();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (rawData != null) {
                if (rawData.equals(CLOSE)) {
                    return;
                }
                setObject((T) rawData);
            }
        }
    }

    public void close() throws IOException {
        send.close();
        recv.close();
        socket.close();
    }

    synchronized public T getObject() {
        T rv =  object;
        object = null;
        return rv;
    }

    protected void setObject(T object) {
        this.object = object;
    }
}
