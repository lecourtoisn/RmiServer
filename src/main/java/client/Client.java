package client;

import csgo.ICsServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        core.IServer server = (core.IServer) Naming.lookup("rmi://localhost:4000/Registry");
        core.Distante hello = (core.Distante) server.lookup("Hello");

        hello.sayHello();
        ICsServer csgo = (ICsServer) server.lookup("Csgo");
        csgo.connect("Drym");
    }
}
