import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccessRemote extends Remote, Serializable {
    void access() throws RemoteException;
}
