package Example;

import Network.Server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server<Position> srv = new Server<>(6969);
        srv.waitForConnection();
        Main.connection(srv);
    }
}
