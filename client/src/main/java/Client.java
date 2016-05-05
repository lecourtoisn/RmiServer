import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

/*
* Options
* -Djava.security.policy="client/secPolicy.policy" -Djava.rmi.server.codebase=http://DESKTOP-OHAS6O1:2001/
* */
public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
            System.out.println("Security manager installed.");
        }
        else {
            System.out.println("Security manager already exists.");
        }


        IServer server = (IServer) Naming.lookup("rmi://localhost:4000/Registry");

        Service csgo = (Service) server.lookup("Csgo");
        Data ladder = (Data) server.lookup("Ladder");
        System.out.println(csgo.getInfo());
        csgo.accessService();
        System.out.println(ladder.getData());

        for (String s : (server.getLastInfos(1))) {
            System.out.println(s);
        }
        for (String s : (server.getLastService(1))) {
            System.out.println(s);
        }

        for (String s : (server.getLastService(2000000))) {
            System.out.println(s);
        }
    }
}

