import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class CallBackService extends UnicastRemoteObject implements ICallBackService {

    private AccessRemote remote;

    protected CallBackService() throws RemoteException {
    }

    @Override
    public boolean callMeBack(AccessRemote remote) throws RemoteException {
        this.remote = remote;
        return true;
    }

    @Override
    public void callBack() throws RemoteException {
        remote.access();
    }

    public AccessRemote getRemote() {
        return remote;
    }
}
