import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class DiceMain {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        IServer server = (IServer) Naming.lookup("rmi://localhost:4000/Registry");
        DiceServer cs = new DiceServer();

        server.bind("Dice", cs);
        server.bind("SomeData", new SomeData());
    }
}
