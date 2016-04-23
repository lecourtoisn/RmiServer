package csgo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CsServer extends UnicastRemoteObject implements ICsServer {
    protected CsServer() throws RemoteException {
    }

    protected CsServer(int port) throws RemoteException {
        super(port);
    }

    @Override
    public boolean connect(String pseudo) throws RemoteException {
        System.out.println("Connection ... Connected!");
        return true;
    }
}
