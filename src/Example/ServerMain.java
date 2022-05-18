package Example;

import Network.Server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        Main.connection(new Server<Position>(6969));
    }
}
