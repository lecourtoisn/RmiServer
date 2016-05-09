import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

public class ClockMain {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        IServer server = (IServer) Naming.lookup("rmi://localhost:4000/Registry");
        ClockServer cs = new ClockServer();

        server.bind("Clock", cs);
        server.bind("News", new News());

        long time = System.currentTimeMillis();
        while(true) {
            if (System.currentTimeMillis() - time > 3 * 1000) {
                cs.publish(new Date().toString());
                time = System.currentTimeMillis();
            }
        }
    }
}
