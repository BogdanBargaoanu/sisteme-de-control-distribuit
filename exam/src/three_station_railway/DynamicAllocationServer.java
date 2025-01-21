package three_station_railway;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DynamicAllocationServer {
    private static final int PORT = 1977;
    private static final int THREAD_POOL_SIZE = 10;
    private boolean running = true;
    private ExecutorService threadPool;

    public DynamicAllocationServer() {
        threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public void startServer() {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            while (running) {
                byte[] buf = new byte[1028];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                threadPool.execute(new SchedulerAgent(socket, packet));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stopServer() {
        running = false;
        threadPool.shutdown();
    }

    public static void main(String[] args) {
        DynamicAllocationServer timeServer = new DynamicAllocationServer();
        timeServer.startServer();
    }
}
