package SSL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * @web http://java-buddy.blogspot.com/
 * @web http://java-buddy.blogspot.com/2016/07/java-example-of-ssl-server-and-client.html
 *
 * Without keystore, both server and client will fail.
 * Type the following command in your command window to create a keystore named examplestore and to generate keys:
 * $ keytool -genkey -alias signFiles -keystore examplestore
 *
 * Run SSL server and client by entering the commands:
 * $ java -jar -Djavax.net.ssl.keyStore=keystore -Djavax.net.ssl.keyStorePassword=password "...JavaSSLServer.jar"
 * $ java -jar -Djavax.net.ssl.trustStore=keystore -Djavax.net.ssl.trustStorePassword=password "...JavaSSLClient.jar"
 */
public class Server {

    static final int port = 8000;

    public static void main(String[] args) {


        SSLServerSocketFactory sslServerSocketFactory =
                (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();

        try {
            ServerSocket sslServerSocket =
                    sslServerSocketFactory.createServerSocket(port);
            System.out.println("SSL ServerSocket started");
            System.out.println(sslServerSocket.toString());

            Socket socket = sslServerSocket.accept();
            System.out.println("ServerSocket accepted");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            try (BufferedReader bufferedReader =
                         new BufferedReader(
                                 new InputStreamReader(socket.getInputStream()))) {
                String line;
                while((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                    out.println(line);
                }
            }
            System.out.println("Closed");

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}