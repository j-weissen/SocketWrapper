package Network;

import java.io.*;

public abstract class Socket<T> extends Thread {
    public static final String CLOSE = "close";
    protected java.net.Socket socket;
    protected ObjectOutputStream send;
    protected ObjectInputStream recv;
    protected T object;

    private boolean closed = false;


    Socket() {
        object = null;
    }
    
    protected void setStreams() throws IOException {
        send = new ObjectOutputStream(socket.getOutputStream());
        recv = new ObjectInputStream(socket.getInputStream());
    }

    public void sendData(Serializable data) throws IOException {
        send.writeObject(data);
    }

    private Object recvData() throws IOException, ClassNotFoundException {
        return recv.readObject();
    }

    @Override
    public void run() {
        Object rawData = null;
        while (!socket.isClosed()) {
            try {
                rawData = recvData();
            } catch (IOException | ClassNotFoundException e) {
            }
            if (rawData != null) {
                if (rawData.equals(CLOSE)) {
                    closed = true;
                    System.out.println("Closed");
                    return;
                }
                setObject((T) rawData);
            }
        }
    }

    public void close() throws IOException, InterruptedException {
        send.close();
        recv.close();
        socket.close();
        join();
    }

    synchronized public T getObject() {
        T rv =  object;
        object = null;
        return rv;
    }

    protected void setObject(T object) {
        this.object = object;
    }

    public boolean isClosed() {
        return closed;
    }
}
