package core;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Server extends UnicastRemoteObject implements IServer {
    private Map<String, Remote> binding;

    protected Server() throws RemoteException {
        this.binding = new HashMap<>();
    }

    protected Server(int port) throws RemoteException {
        super(port);
        this.binding = new HashMap<>();
    }

    @Override
    public void bind(String key, Remote distantObject) throws RemoteException {
        /*
        * Associate the key to the object in the map
        * Download the classes and store them
        * */
        binding.put(key, distantObject);
    }

    @Override
    public Remote lookup(String key) throws RemoteException {
        /*
         * Check in the map if the object has already been stored
         * Return it
         * */
        return binding.get(key);
    }

    public Map<String, Remote> getBinding() throws RemoteException {
        return binding;
    }

    public void setBinding(Map<String, Remote> binding) throws RemoteException {
        this.binding = binding;
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        IServer server = new Server();
        Naming.rebind("rmi://localhost:4000/Registry", server);

        server.bind("Hello", new ObjetDistant());
    }
}
