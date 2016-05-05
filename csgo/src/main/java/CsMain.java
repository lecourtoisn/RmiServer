import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

/*
* Options :
* -Djava.rmi.server.codebase=http://DESKTOP-OHAS6O1:2001/ -Djava.security.policy="csgo/secPolicy.policy"
* */
public class CsMain {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
//        System.setProperty("java.security.policy", "file:./csgo/secPolicy.policy"
        // Create and install a security manager

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
            System.out.println("Security manager installed.");
        }
        else {
            System.out.println("Security manager already exists.");
        }
        System.setProperty("java.security.policy", "file:src/main/java/secPolicy.policy");

        IServer server = (IServer) Naming.lookup("rmi://localhost:4000/Registry");
        CsServer cs = new CsServer();

        server.bind("Csgo", cs);

    }
}
