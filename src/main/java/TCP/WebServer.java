package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) throws IOException {
        final int port = 80;

        ServerSocket server = new ServerSocket(port);
        Socket connection = server.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);

        StringBuilder header = new StringBuilder("<br>");
        for (int i = 0; i < 14; i++) {
            header.append("<br>").append(reader.readLine());
        }

        System.out.println(header);

        final String htmlPrint =
                "HTTP/1.0 200 OK \n" +
                "Content-Type: text/html; charset=utf-8 \n" +
                "\n" +
                "<html> <body>" +
                "<h1> Web T. Jenner </h1>" +
                "Header: " +
                header +
                "</body></html>";

        writer.println(htmlPrint);
        writer.close();
        connection.close();
    }
}