import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    String getInfo() throws RemoteException;
    void accessService() throws RemoteException;
}
