import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        final int port = 1250;

        ServerSocket server = new ServerSocket(port);
        System.out.println("Serverside log. Waiting for connection... ");
        Socket connection = server.accept();

    }
}
