package client;

import csgo.ICsgoServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        core.IServer server = (core.IServer) Naming.lookup("rmi://localhost:4000/Registry");
        core.Distante hello = (core.Distante) server.lookup("Hello");

        hello.sayHello();
        csgo.ICsgoServer csgo = (csgo.ICsgoServer) server.lookup("Csgo");
        csgo.connect("Drym");
    }
}
