import java.io.*;
import java.net.*;

public class ServerMultifir {
    public static final int PORT = 1900;
    void startServer() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(PORT);
            while (true) {
                Socket socket = ss.accept();
                new TratareClient(socket).start();
            }
        } catch (IOException ex) {
            System.err.println("Eroare :" + ex.getMessage());
        } finally {
            try {
                ss.close();
            } catch (IOException ex2) {
            }
        }
    }
    public static void main(String args[]) {
        ServerMultifir smf = new ServerMultifir();
        smf.startServer();
    }
}

class TratareClient extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    TratareClient(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())));
    }

    public void run() {
        try {
            while (true) {
                String str = in.readLine();
                if (str.equals("END")) break;
                System.out.println("Echoing: " + str);
                out.println(str);
            }//.while
            System.out.println("closing...");
        } catch (IOException e) {
            System.err.println("IO Exception");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }//.run
}

