package Network;

import java.io.IOException;

public class Client<T> extends Socket<T> {
    /**
     *
     * @param ip
     * IP to connect to
     * @param port
     * Port to connect to
     * @throws IOException
     */
    public Client(String ip, int port) throws IOException {
        super();
        socket = new java.net.Socket(ip, port);
        setStreams();
    }
}
