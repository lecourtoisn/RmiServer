import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObjetDistant extends UnicastRemoteObject implements Distante {
    public ObjetDistant(int numPort) throws RemoteException {
        super(numPort);
    }

    public ObjetDistant() throws RemoteException {
        //
    }

    @Override
    public void echo() throws RemoteException {
        System.out.println("Hello");
    }

    @Override
    public String sayHello() throws RemoteException {
        System.out.println("sayHello called");
        return "Hello";
    }
}
