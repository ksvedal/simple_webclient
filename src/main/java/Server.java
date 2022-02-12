import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        final int port = 1250;

        // Set up server socket and wait for connection.
        ServerSocket server = new ServerSocket(port);
        System.out.println("Serverside log. Waiting for connection... ");
        Socket connection = server.accept();

        // Opens a connection for communication with client using streams.
        InputStreamReader read_connection = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(read_connection);
        PrintWriter writer = new PrintWriter(connection.getOutputStream());

        // Sends startup message to client.
        writer.println("Contact with server!");
        writer.println("Write what you want and i will repeat it. Use enter key quit.");

        // Receive data from client.
        String line3 = reader.readLine(); // Receive line of text.
        while (line3 != null) { // Connection on client side is closed.
            System.out.println("Client wrote: " + line3);
            writer.println("You wrote: " + line3); // Repeats line to client.
            line3 = reader.readLine();
        }

        // Close connection.
        reader.close();
        writer.close();
        connection.close();
    }
}
