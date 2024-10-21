import java.io.Serializable;

public class FirExecutie2 extends Thread implements Serializable {
    public void run() {
        System.out.print(this.getName());
    }
}
