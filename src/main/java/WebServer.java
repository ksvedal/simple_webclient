import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) throws IOException {
        final int port = 80;
        final String htmlPrint =
                        "HTTP/1.0 200 OK \n" +
                        "Content-Type: text/html; charset=utf-8 \n" +
                        "\n" +
                        "<html> <body>" +
                        "<h1> Web T. Jenner </h1>" +
                        "</body></html>";

        ServerSocket server = new ServerSocket(port);
        Socket connection = server.accept();
        PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);

        writer.println(htmlPrint);
        writer.close();
        connection.close();
    }
}