import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientSimplu {
    public static void main(String[] args) throws Exception {
        Socket socket = null;
        try {

//creare obiect address care identifică adresa serverului
            InetAddress address = InetAddress.getByName("localhost");
            //se putea utiliza varianta alternativă: InetAddress.getByName("127.0.0.1")
            socket = new Socket(address, 1900);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            // Output is automatically flushed  by PrintWriter:
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);

            /*
            for (int i = 0; i < 10; i++) {
                out.println("mesaj " + i);
                String str = in.readLine();       //trimite mesaj
                System.out.println(str);         //aşteaptă răspuns
            }
            */
            Scanner scanner = new Scanner(System.in);
            String input = "";
            do {
                System.out.print(">");
                input = scanner.next();
                out.println(input);
                String str = in.readLine();       //trimite mesaj
                System.out.println(str);
            }while (!input.equals("END"));

            out.println("END");        //trimite mesaj care determină serverul să închidă conexiunea
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            socket.close();
        }
    }
}