import java.rmi.RemoteException;

public interface ICallBackService extends Service {
    public boolean callMeBack(AccessRemote remote) throws RemoteException;
}
