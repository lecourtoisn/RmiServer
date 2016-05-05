import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/*
* Options
* -Djava.rmi.server.codebase=http://DESKTOP-OHAS6O1:2001/ -Djava.security.policy="core/secPolicy.policy"
* */
public class Server extends UnicastRemoteObject implements IServer {
    private Map<String, Serializable> binding;

    private List<String> dataTags;
    private List<String> serviceTags;

    protected Server() throws RemoteException {
        this.binding = new HashMap<>();
        this.dataTags = new ArrayList<>();
        this.serviceTags = new ArrayList<>();
    }

    protected Server(int port) throws RemoteException {
        super(port);
        this.binding = new HashMap<>();
        this.dataTags = new ArrayList<>();
        this.serviceTags = new ArrayList<>();
    }

    @Override
    public void bind(String key, Serializable distantObject) throws RemoteException {
        /*
        * Associate the key to the object in the map
        * Download the classes and store them
        * */
        binding.put(key, distantObject);
        if (distantObject instanceof Data) {
            System.out.println("Added to dataTags");
            dataTags.add(0, key);
        } else if (distantObject instanceof Service) {
            System.out.println("Added to serviceTag");
            serviceTags.add(0, key);
        }
    }

    @Override
    public Serializable lookup(String key) throws RemoteException {
        /*
         * Check in the map if the object has already been stored
         * Return it
         * */
        return binding.get(key);
    }

    @Override
    public String[] getLastInfos(int x) throws RemoteException {
        System.out.println("In getLastInfos");
        x = x > dataTags.size() ? dataTags.size() : x;
        System.out.println(Arrays.toString(dataTags.subList(0, x).toArray()));
        return dataTags.subList(0, x).toArray(new String[0]);
    }

    @Override
    public String[] getLastService(int x) throws RemoteException {
        System.out.println("In getLastServices");
        x = x > serviceTags.size() ? serviceTags.size() : x;
        return serviceTags.subList(0, x).toArray(new String[0]);
    }



    public Map<String, Serializable> getBinding() throws RemoteException {
        return binding;
    }

    public void setBinding(Map<String, Serializable> binding) throws RemoteException {
        this.binding = binding;
    }

    public List<String> getDataTags() {
        return dataTags;
    }

    public void setDataTags(List<String> dataTags) {
        this.dataTags = dataTags;
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
    }

    public List<String> getServiceTags() {
        return serviceTags;
    }

    public void setServiceTags(List<String> serviceTags) {
        this.serviceTags = serviceTags;
    }
}
