import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSimplu {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = null;
        Socket socket = null;
        try {
            String line = "";
            ss = new ServerSocket(1900);    //crează obiectul serversocket
            System.out.println("astept conexiuni…");
            socket = ss.accept(); //incepe aşteptarea pe portul 1900
//în momentul în care un client s-a conectat ss.accept() returnează
//un socket care identifică conexiunea
//crează fluxurile de intrare/ieşire
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream())), true);
            Scanner scanner = new Scanner(System.in);
            String input = "";
            do  {
                String str = in.readLine();
                System.out.print(str);         //aşteaptă răspuns
                System.out.println(">");
                input = scanner.next();
                out.println(input);
            }
            while (!input.equals("END"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
            if (socket != null) socket.close();
        }

    }

}