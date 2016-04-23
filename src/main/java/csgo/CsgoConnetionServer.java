package csgo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CsgoConnetionServer extends UnicastRemoteObject implements ICsgoServer {
    protected CsgoConnetionServer() throws RemoteException {
    }

    protected CsgoConnetionServer(int port) throws RemoteException {
        super(port);
    }

    @Override
    public boolean connect(String pseudo) throws RemoteException {
        System.out.println("Connection ... Connected!");
        return true;
    }
}
