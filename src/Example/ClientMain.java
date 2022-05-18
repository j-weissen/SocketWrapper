package Example;

import Network.Client;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        Main.connection(new Client<Position>("127.0.0.1", 6969));
    }
}