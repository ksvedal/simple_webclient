import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        final int port = 1250;

        // Lets user select server by scanning input.
        Scanner sc = new Scanner(System.in);
        System.out.println("Name of server: ");
        String server_machine = sc.nextLine();

        // Sets up connection to server.
        Socket connection = new Socket(server_machine, port);
        System.out.println("Connection established to " + server_machine);

        // Opens a connection for communication with server using streams.
        InputStreamReader read_connection = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(read_connection);
        PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);

        // Reads startup message from server and writes to terminal.
        String line1 = reader.readLine();
        String line2 = reader.readLine();
        System.out.println(line1 + "\n" + line2);

        // Reads from client terminal.
        String line3 = sc.nextLine();
        while (!line3.equals("")) {
            writer.println(line3);
            String response = reader.readLine();
            System.out.println("From server: " + response);
            line3 = sc.nextLine();
        }

        // Close connection.
        reader.close();
        writer.close();
        connection.close();
    }
}
