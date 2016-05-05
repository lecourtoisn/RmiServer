import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IServer extends Remote {
    Serializable lookup(String key) throws RemoteException;
    void bind(String key, Serializable distantObject) throws RemoteException;

    String[] getLastInfos(int x) throws RemoteException;

    String[] getLastService(int x) throws RemoteException;
}
