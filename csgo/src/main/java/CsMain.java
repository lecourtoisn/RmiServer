import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Timer;

/*
* Options :
* -Djava.rmi.server.codebase=http://DESKTOP-OHAS6O1:2001/
* */
public class CsMain {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        IServer server = (IServer) Naming.lookup("rmi://localhost:4000/Registry");
        CsServer cs = new CsServer();

        server.bind("Csgo", cs);
        server.bind("Ladder", new Ladder());

        long time = System.currentTimeMillis();
        while(true) {
            if (System.currentTimeMillis() - time > 3 * 1000) {
                cs.publish(new Date().toString());
                time = System.currentTimeMillis();
            }
        }
    }
}
