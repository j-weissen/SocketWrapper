
/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *           Fachrichtung Elektronik und Technische Informatik
 *----------------------------------------------------------------------------*/

import Network.Position;
import Network.Server;
import Network.Socket;

import java.io.IOException;
import java.util.Scanner;

/**
 * Kurzbeschreibung
 *
 * @author : sprechtl
 * @date : 16.05.2021
 * @details Detailbeschreibung
 */
public class ServerMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server<String> srv = new Server<>(6971);
        srv.start();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String inp = scanner.next();
            srv.sendData(inp);
            Object temp;
            if ((temp = srv.getObject()) != null) {
                System.out.println(temp);
                if (temp.equals("\\q")){
                    srv.sendData(Socket.CLOSE);
                    break;
                }
            }
        }

        srv.close();
        srv.join();
    }
}
