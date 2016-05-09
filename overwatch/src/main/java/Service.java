import javax.jms.Queue;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    String getInfo() throws RemoteException;
    Serializable accessService() throws RemoteException;
    String subscribe() throws RemoteException;
}
