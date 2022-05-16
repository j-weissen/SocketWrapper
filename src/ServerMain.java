
/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *           Fachrichtung Elektronik und Technische Informatik
 *----------------------------------------------------------------------------*/

import Network.Position;
import Network.Server;
import Network.Socket;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Kurzbeschreibung
 *
 * @author : sprechtl
 * @date : 16.05.2021
 * @details Detailbeschreibung
 */
public class ServerMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server<Position> srv = new Server<>(6971);
        srv.start();
        Scanner scanner = new Scanner(System.in);
        AtomicBoolean stop = new AtomicBoolean(false);
        Thread fred = new Thread(() ->  {
            while (true){
                Object temp;
                if ((temp = srv.getObject()) != null) {
                    System.out.println(temp);
                    if (temp.equals("\\q")){
                        stop.set(true);
                        try {
                            srv.sendData(Socket.CLOSE);

                        } catch (IOException e) {}
                        break;
                    }
                }
            }
        });

        fred.start();
        while (!stop.get()){
            String inp = scanner.nextLine();
            srv.sendData(new Position(inp));
        }

        srv.close();
        srv.join();
        fred.join();
    }
}
