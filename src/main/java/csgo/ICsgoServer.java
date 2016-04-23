package csgo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICsgoServer extends Remote {
    boolean connect(String pseudo) throws RemoteException;
}
