package Network;

import java.io.IOException;
import java.net.ServerSocket;

public class Server<T> extends Socket<T> {
    ServerSocket serverSocket;

    public Server(int port) throws IOException {
        super();
        serverSocket = new ServerSocket(port);
        System.out.println("Waiting");
        socket = serverSocket.accept();
        System.out.println("Accepted");
        setStreams();
    }

    @Override
    public void close() throws IOException {
        super.close();
        serverSocket.close();
    }
}
