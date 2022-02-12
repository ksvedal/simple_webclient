import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    Socket connection;
    final BufferedReader reader;
    final PrintWriter writer;

    public ClientHandler(Socket connection,
                         BufferedReader reader,
                         PrintWriter writer) {
        this.connection = connection;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() {
        super.run();

        int x = 0;
        int y = 0;
        String line = "";
        String operation = "";

        writer.println("Connection established.");
        writer.println("Pressing enter with an empty terminal exits the application.");

        while (line != null) {
            try {
                writer.println("Do you want to add or subtract? "
                + "1: Add"
                + "2: Subtract");
                operation = reader.readLine();

                writer.println("Type your first number: ");
                x = Integer.parseInt(reader.readLine());

                writer.println("Type your second number: ");
                y = Integer.parseInt(reader.readLine());

                if (operation.equals("1")) {
                    writer.println("Result: " + add(x, y));
                } else if (operation.equals("2")) {
                    writer.println("Result: " + subtract(x, y));
                } else {
                    writer.println("something went wrong. Try again.");
                }

                line = null;

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                // Close connection.
                this.writer.close();
                this.reader.close();
                this.connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds two numbers.
     * @param x First number.
     * @param y Second number.
     * @return result of addition.
     */
    private static int add(int x, int y) {
        return x + y;
    }

    /**
     * Subtract number y from number x.
     * @param x First number.
     * @param y Second number.
     * @return Result of subtraction.
     */
    private static int subtract(int x, int y) {
        return x - y;
    }
}
