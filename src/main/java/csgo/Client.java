package csgo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        core.IServer server = (core.IServer) Naming.lookup("rmi://localhost:4000/Registry");
        server.bind("Csgo", new CsgoServer());
    }
}
