import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by lucas on 05/05/16.
 */
public class Test extends UnicastRemoteObject implements ITest {

    int i;

    protected Test() throws RemoteException {
    }

    protected Test(int i) throws RemoteException {
        super(i);
    }

    protected Test(int i, RMIClientSocketFactory rmiClientSocketFactory, RMIServerSocketFactory rmiServerSocketFactory) throws RemoteException {
        super(i, rmiClientSocketFactory, rmiServerSocketFactory);
    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
