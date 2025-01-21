package three_station_railway;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SchedulerAgent implements Runnable {
    private DatagramSocket socket;
    private DatagramPacket packet;

    public SchedulerAgent(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;
    }

    @Override
    public void run() {
        try {
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            String receivedMessage = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received: " + receivedMessage);

            String message = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter your request: ");
            message = reader.readLine();
            byte[] buffer = message.getBytes();
            packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
