import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccessRemote extends Remote {
    void access() throws RemoteException;
}
