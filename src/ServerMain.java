
/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *           Fachrichtung Elektronik und Technische Informatik
 *----------------------------------------------------------------------------*/

import Network.Position;
import Network.Server;
import Network.Socket;

import java.io.IOException;

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
        while (true){
            srv.sendData("AAAAAA");
            if (srv.getObject() != null) {
                System.out.println(srv.getObject());
                srv.sendData(Socket.CLOSE);
                break;
            }
        }

        srv.close();
        srv.join();
    }
}
