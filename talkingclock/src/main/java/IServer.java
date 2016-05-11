import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
    Serializable lookup(String key) throws RemoteException;
    //void bind(String key, Serializable distantObject) throws RemoteException;
    void bind(String key, Service distantObject) throws RemoteException;
    void bind(String key, Data distantObject) throws RemoteException;
    void bind(String key, AccessRemote distantObject) throws RemoteException;

    String[] getLastInfos(int x) throws RemoteException;

    String[] getLastService(int x) throws RemoteException;
}
