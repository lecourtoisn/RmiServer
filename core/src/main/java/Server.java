import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/*
* Options
* -Djava.rmi.server.codebase=http://DESKTOP-OHAS6O1:2001/ -Djava.security.policy="core/secPolicy.policy"
* */
public class Server extends UnicastRemoteObject implements IServer {
    private Map<String, Serializable> binding;

    protected Server() throws RemoteException {
        this.binding = new HashMap<>();
    }

    protected Server(int port) throws RemoteException {
        super(port);
        this.binding = new HashMap<>();
    }

    @Override
    public void bind(String key, Serializable distantObject) throws RemoteException {
        /*
        * Associate the key to the object in the map
        * Download the classes and store them
        * */
        binding.put(key, distantObject);
    }

    @Override
    public Serializable lookup(String key) throws RemoteException {
        /*
         * Check in the map if the object has already been stored
         * Return it
         * */
        return binding.get(key);
    }

    public Map<String, Serializable> getBinding() throws RemoteException {
        return binding;
    }

    public void setBinding(Map<String, Serializable> binding) throws RemoteException {
        this.binding = binding;
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
            System.out.println("Security manager installed.");
        }
        else {
            System.out.println("Security manager already exists.");
        }

        IServer server = new Server();
        Naming.rebind("rmi://localhost:4000/Registry", server);

//        server.bind("Hello", new ObjetDistant());
    }
}
