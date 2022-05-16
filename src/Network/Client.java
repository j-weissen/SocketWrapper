package Network;

import java.io.IOException;

public class Client<T> extends Socket<T> {

    public Client(String ip, int port) throws IOException {
        super();
        socket = new java.net.Socket(ip, port);
        setStreams();
    }
}
