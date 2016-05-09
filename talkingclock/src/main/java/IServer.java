import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
    Serializable lookup(String key) throws RemoteException;
    void bind(String key, Serializable distantObject) throws RemoteException;
}
