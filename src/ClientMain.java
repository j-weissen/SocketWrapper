import Network.Client;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Client<String> c = new Client<String>("10.52.7.63", 6969);
        c.start();
        while (true) {
            c.sendData("AAAB");
        }

    }
}