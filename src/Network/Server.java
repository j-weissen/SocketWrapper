package Network;

import java.io.IOException;
import java.net.ServerSocket;

public class Server<T> extends Socket<T> {
    ServerSocket serverSocket;

    public Server(int port) throws IOException {
        super();
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        setStreams();
    }

    @Override
    public void close() throws IOException, InterruptedException {
        super.close();
        serverSocket.close();
    }
}
