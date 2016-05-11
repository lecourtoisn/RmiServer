import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class DiceMain {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException {
        IServer server = (IServer) Naming.lookup("rmi://localhost:4000/Registry");
        DiceServer diceServer = new DiceServer();

        server.bind("Dice", diceServer);
        server.bind("SomeData", new SomeData());

        System.out.println("On verifie si callMeBack a ete appele (il sera appele dans le main de ClientBis)");

        // On attend qu'un client appelle callMeBack
        long start = System.currentTimeMillis();
        while(diceServer.getRemote() == null) {
            long now = System.currentTimeMillis();
            if (now - start > 1000) {
                System.out.print(".");
                start = now;
            }
        }

        System.out.println("\nUn client a appele callMeBack");
        diceServer.callBack();
        System.out.println("Le client a ete rappele");

    }
}
