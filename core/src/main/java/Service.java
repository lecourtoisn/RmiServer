import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote, Serializable {
    String getInfo() throws RemoteException;
    Serializable accessService() throws RemoteException;
    String subscribe() throws RemoteException;
}
