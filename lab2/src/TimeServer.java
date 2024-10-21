import java.io.*;

import java.net.*;

import java.util.*;


public class TimeServer extends Thread {
    boolean running = true;

    public TimeServer() {
        start();
    }

    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(1977);
            while (running) {
                //asteapta client
                byte[] buf = new byte[1028];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                //citeste adresa si portul clientului
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received message: " + receivedMessage);
                // trimite un reply catre client
                String message = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter your message: ");
                message = reader.readLine();
                byte[] buffer = message.getBytes();
                packet = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(packet);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TimeServer timeServer1 = new TimeServer();
    }
}