package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        final int port = 1251;

        try {
            // Set up server socket and wait for connection.
            ServerSocket server = new ServerSocket(port);
            System.out.println("TCP.Server initialized.");

            while (true) {
                // Accept client connection request.
                Socket connection = server.accept();

                // Set up streams, reader and writer.
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);

                // Send request to separate thread
                Thread thread = new ClientHandler(connection, reader, writer);
                thread.start();
                System.out.println("Connection established to a client using thread: " + thread.getId());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
