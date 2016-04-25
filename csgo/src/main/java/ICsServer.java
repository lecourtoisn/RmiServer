import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICsServer extends Remote {
    boolean connect(String pseudo) throws RemoteException;
}
