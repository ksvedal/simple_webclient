package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.StringTokenizer;

public class Server {
    static int port = 1251;
    DatagramSocket datagramSocket;
    byte[] buffer = new byte[256];

    public Server(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public static void main(String[] args) throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket(port);
        Server server = new Server(datagramSocket);
        server.receiveAndRespond();
    }

    public void receiveAndRespond() {
        while (true) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);

                // Info of client
                InetAddress clientAddress = datagramPacket.getAddress();
                int clientPort = datagramPacket.getPort();
                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

                System.out.println("Client: " + messageFromClient);

                StringTokenizer stringTokenizer = new StringTokenizer(messageFromClient);

                int result;
                int nr1 = Integer.parseInt(stringTokenizer.nextToken());
                String operand = stringTokenizer.nextToken();
                int nr2 = Integer.parseInt(stringTokenizer.nextToken());

                if (operand.equals("+")) {
                    result = nr1 + nr2;
                } else if (operand.equals("-")) {
                    result = nr1 - nr2;
                } else {
                    break;
                }

                String results = Integer.toString(result);
                buffer = results.getBytes();

                datagramPacket = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                datagramSocket.send(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

}
