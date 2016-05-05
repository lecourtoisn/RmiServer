import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/*
* Options
* -Djava.security.policy="C:\Users\Nicolas\Documents\Java\RmiServer\client\secPolicy"
* */
public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        IServer server = (IServer) Naming.lookup("rmi://localhost:4000/Registry");
        /*Distante hello = (Distante) server.lookup("Hello");

        hello.sayHello();*/

        ICsServer csgo = (ICsServer) server.lookup("Csgo");
        csgo.connect("Drym");
    }
}
