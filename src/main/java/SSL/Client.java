package SSL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocketFactory;

/**
 * @web http://java-buddy.blogspot.com/
 * @web http://java-buddy.blogspot.com/2016/07/java-example-of-ssl-server-and-client.html
 *
 * Without keystore, both server and client will fail.
 * Type the following command in your command window to create a keystore named examplestore and to generate keys:
 * $ keytool -genkey -alias signFiles -keystore examplestore
 *
 * Run SSL server and client by entering the commands:
 * $ java -jar -Djavax.net.ssl.keyStore=keystore -Djavax.net.ssl.keyStorePassword=password "...Server.jar"
 * $ java -jar -Djavax.net.ssl.trustStore=keystore -Djavax.net.ssl.trustStorePassword=password "...Client.jar"
 */
public class Client {

    static final int port = 8000;

    public static void main(String[] args) {

        SSLSocketFactory sslSocketFactory =
                (SSLSocketFactory)SSLSocketFactory.getDefault();
        try {
            Socket socket = sslSocketFactory.createSocket("localhost", port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            try (BufferedReader bufferedReader =
                         new BufferedReader(
                                 new InputStreamReader(socket.getInputStream()))) {
                Scanner scanner = new Scanner(System.in);
                while(true){
                    System.out.println("Enter something:");
                    String inputLine = scanner.nextLine();
                    if(inputLine.equals("q")){
                        break;
                    }

                    out.println(inputLine);
                    System.out.println(bufferedReader.readLine());
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }

}