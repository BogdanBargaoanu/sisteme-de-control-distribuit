import java.io.*;

import java.net.*;


public class SerialTest extends Thread {

    public void run() {

        try {
            ServerSocket ss = new ServerSocket(1977);
            Socket s = ss.accept();
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            //Pers p = (Pers) ois.readObject();
            //System.out.println(p);
            FirExecutie f = (FirExecutie) ois.readObject();
            f.start();
            FirExecutie2 f2 = (FirExecutie2) ois.readObject();
            f2.start();
            s.close();
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//trimite obiect prin socket
        (new SerialTest()).start();
        Socket s = new Socket(InetAddress.getByName("localhost"), 1977);
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        //Pers p = new Pers("Alin", 14);
        FirExecutie f = new FirExecutie();
        //oos.writeObject(p);
        oos.writeObject(f);
        FirExecutie2 f2 = new FirExecutie2();
        oos.writeObject(f2);
        s.close();
    }
}

class Pers implements Serializable {
    String nume;
    int varsta;

    Pers(String n, int v) {
        nume = n;
        varsta = v;
    }

    public String toString() {
        return "Persoana: " + nume + " varsta: " + varsta;
    }
}

