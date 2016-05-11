import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class utilisee pour la fonction de callback de notre service
 */
public class RemoteClass extends UnicastRemoteObject implements AccessRemote {
    protected RemoteClass() throws RemoteException {
    }

    @Override
    public void access() throws RemoteException {
        System.out.println("I've been called back \\o/ ");
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        IServer server = (IServer) Naming.lookup("rmi://localhost:4000/Registry");

        server.bind("ClientBisAccessRemote", new RemoteClass());
        System.out.println("RemoteClass started");
    }
}
