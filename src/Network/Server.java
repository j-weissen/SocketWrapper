package Network;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Socket {
    ServerSocket serverSocket;

    Server(int port) throws IOException {
        super();
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        setStreams();
    }

    @Override
    public void close() throws IOException {
        super.close();
        serverSocket.close();
    }
}
