import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {

            DatagramSocket socket = new DatagramSocket();
            //byte[] buf = new byte[256];
            String message = "";
            do {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter your message: ");
                message = reader.readLine();
                byte[] buf = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length,InetAddress.getByName("localhost"), 1977);
                socket.send(packet);
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                System.out.println(new String(packet.getData()));
            }
            while (!message.equals("exit"));


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

} 