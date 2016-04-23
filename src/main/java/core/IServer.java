package core;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
    Remote lookup(String key) throws RemoteException;
    void bind(String key, Remote distantObject) throws RemoteException;

    // + toutes les autres méthodes cheloues qu'elle demande
}
