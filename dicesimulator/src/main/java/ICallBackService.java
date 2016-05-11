import java.rmi.RemoteException;

public interface ICallBackService extends Service {
    boolean callMeBack(AccessRemote remote) throws RemoteException;
    void callBack() throws RemoteException;
}
