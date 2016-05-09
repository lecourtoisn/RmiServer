import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/*
* Options :
* -Djava.rmi.server.codebase=http://DESKTOP-OHAS6O1:2001/
* */
public class CsMain {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        IServer server = (IServer) Naming.lookup("rmi://localhost:4000/Registry");
        CsServer cs = new CsServer();

        server.bind("Dice", cs);
        server.bind("SomeData", new SomeData());
    }
}