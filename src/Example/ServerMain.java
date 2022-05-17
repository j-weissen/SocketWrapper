package Example;
/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *           Fachrichtung Elektronik und Technische Informatik
 *----------------------------------------------------------------------------*/

import Network.Server;
import Network.Socket;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class ServerMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server<Position> srv = new Server<>(6969);
        srv.start();
        Scanner scanner = new Scanner(System.in);
        AtomicReference<String> inp = new AtomicReference<>("");

        AtomicBoolean stop = new AtomicBoolean(false);
        Thread networkInput = new Thread(() -> {
            while (true) {
                Object temp;
                if ((temp = srv.getObject()) != null) {
                    if (temp.equals("\\q")) {
                        stop.set(true);
                        try {
                            srv.sendData(Socket.CLOSE);
                        } catch (IOException ignored) {
                        }
                        break;
                    }
                    System.out.println(temp);
                }
            }
        });

        Thread localInput = new Thread(() -> {
            while (!stop.get()) {
                try {
                    inp.set(scanner.nextLine());
                } catch (Exception ignored) {
                }
            }
        });

        networkInput.start();
        localInput.start();

        while (!stop.get()) {
            if (!inp.get().equals("")) {
                srv.sendData(new Position(inp.get()));
                inp.set("");
            }
        }

        srv.close();
        localInput.interrupt();
        localInput.join();
        networkInput.join();
    }
}
