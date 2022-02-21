package UDP;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {

    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private final int port = 1251;
    private byte[] buffer;

    public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("localhost");
        Client client = new Client(datagramSocket, inetAddress);
        System.out.println("Send a calculation like this: [1 + 1]");
        client.sendAndReceive();
    }

    public void sendAndReceive() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {

                String messageToSend = scanner.nextLine();

                if (messageToSend.equals("exit"))
                    break;

                buffer = messageToSend.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(datagramPacket);
                String messageFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

                System.out.println(messageFromServer);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
