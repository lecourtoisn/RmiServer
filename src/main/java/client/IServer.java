package client;

import java.rmi.Remote;


/* Client side, like a stub */
public interface IServer extends Remote {
    Remote lookup(String key);
    void bind(String key, Remote distantObject);
}
