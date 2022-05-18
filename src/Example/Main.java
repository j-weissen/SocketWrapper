package Example;

import Network.Server;
import Network.Socket;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void connection(Socket<?> socket) throws IOException, InterruptedException {
        socket.start();
        System.out.println("Connected");

        Scanner scanner = new Scanner(System.in);
        AtomicReference<String> inp = new AtomicReference<>("");

        AtomicBoolean stop = new AtomicBoolean(false);
        Thread networkInput = new Thread(() -> {
            while (!stop.get()) {
                if (socket.isClosed()) {
                    stop.set(true);
                }
                Object temp;
                if ((temp = socket.getObject()) != null) {
                    System.out.println(temp);
                }
            }
        });

        Thread localInput = new Thread(() -> {
            while (!stop.get()) {
                    inp.set(scanner.nextLine());
                    if (inp.get().equals("\\q")) {
                        try {
                            socket.sendData(Socket.CLOSE);
                            stop.set(true);
                        } catch (IOException ignored) {
                        }
                    }
            }
        });

        networkInput.start();
        localInput.start();

        while (!stop.get()) {
            if (!inp.get().equals("") && !inp.get().equals("\\q")) {
                socket.sendData(new Position(inp.get()));
                inp.set("");
            }
        }

        socket.close();
        System.exit(0);
    }
}
