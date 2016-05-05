import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CsServer extends UnicastRemoteObject implements Service {
    protected CsServer() throws RemoteException {
    }

    protected CsServer(int port) throws RemoteException {
        super(port);
    }

    @Override
    public String getInfo() throws RemoteException {
        return "Mega serveur de CSGO";
    }

    @Override
    public void accessService() throws RemoteException {
        System.out.println("Connection ... Connected!");
    }
}
