import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

/*
* Options
* -Djava.security.policy="client/secPolicy.policy" -Djava.rmi.server.codebase=http://DESKTOP-OHAS6O1:2001/
* */
public class ClientBis {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("user","user","tcp://localhost:61616/");
        javax.jms.Connection connection = factory.createConnection("user", "user");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        connection.start();

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
            System.out.println("Security manager installed.");

        }
        else {
            System.out.println("Security manager already exists.");
        }


        IServer server = (IServer) Naming.lookup("rmi://localhost:4000/Registry");

        Service csgo = (Service) server.lookup("Dice");
        Data ladder = (Data) server.lookup("SomeData");
        System.out.println(csgo.getInfo());
        csgo.accessService();
        System.out.println(ladder.getData());

        String queueName = csgo.subscribe();

        Queue queue = session.createQueue(queueName);
        MessageConsumer consumer = session.createConsumer(queue);
        while(true) {
            TextMessage messageRecu = (TextMessage) consumer.receive();
            System.out.println(messageRecu.getText());
        }
    }
}

